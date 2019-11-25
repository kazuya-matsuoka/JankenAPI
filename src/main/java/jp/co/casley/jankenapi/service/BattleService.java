package jp.co.casley.jankenapi.service;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.BadRequestException;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import jp.co.casley.jankenapi.common.constant.StrategyId;
import jp.co.casley.jankenapi.dto.CommonRequestDto;
import jp.co.casley.jankenapi.dto.CommonResponseDto;
import jp.co.casley.jankenapi.dto.ResultInfo;
import jp.co.casley.jankenapi.dto.battle.BattleRequestDto;
import jp.co.casley.jankenapi.dto.battle.BattleResponseDto;
import jp.co.casley.jankenapi.dto.battle.JankenResultInfo;
import jp.co.casley.jankenapi.dto.battle.ReceiverInfo;
import jp.co.casley.jankenapi.dto.battle.SenderInfo;
import jp.co.casley.jankenapi.repository.db.dao.MultiJankenDao;
import jp.co.casley.jankenapi.repository.db.entity.generate.MtJankenComb;
import jp.co.casley.jankenapi.repository.db.entity.generate.MtJankenHand;
import jp.co.casley.jankenapi.repository.db.entity.generate.MtJankenStrategyUser;
import jp.co.casley.jankenapi.repository.db.entity.generate.TtJankenScore;
import jp.co.casley.jankenapi.service.logic.AbsDrawingStrategy;
import jp.co.casley.jankenapi.service.logic.AbsLosingStrategy;
import jp.co.casley.jankenapi.service.logic.AbsWinningStrategy;
import jp.co.casley.jankenapi.service.logic.ProbabilityStrategy;
import jp.co.casley.jankenapi.service.logic.RandomStrategy;
import jp.co.casley.jankenapi.service.logic.Strategy;

/**
 * じゃんけんAPIServiceクラス
 */
@org.springframework.stereotype.Service
public class BattleService extends BasicService<BattleRequestDto, BattleResponseDto> {
    /** マルチじゃんけんDAO */
    private final MultiJankenDao multiJankenDao;

    public BattleService(final MultiJankenDao multiJankenDao) {
        super(BattleRequestDto.class);
        this.multiJankenDao = multiJankenDao;
    }

    @Override
    @Transactional
    protected CommonResponseDto<BattleResponseDto> execute(CommonRequestDto<BattleRequestDto> requestDto) {
        BattleRequestDto requestInfo = requestDto.getRequestInfo();

        /* ********************
         *  マスタ情報取得・ストラテジー算出処理
         * ********************/
        /* じゃんけん戦略ユーザーマスタから登録ユーザーに対応したストラテジー情報を取得する */
        MtJankenStrategyUser mtJankenStrategyUser = this.multiJankenDao.findMtJankenStrategyUser(requestInfo.getUserId());
        final String strategyId = mtJankenStrategyUser != null ? mtJankenStrategyUser.getStrategy() : null;

        /* ストラテジーオブジェクト生成 */
        Strategy strategy = this.createStrategy(strategyId, requestInfo);
        /* ストラテジーオブジェクトからサーバー側の出し手を算出 */
        final String serverHand = strategy.calcHand();

        /* クライアント側とサーバー側のじゃんけん出し手マスタ情報を取得する */
        List<MtJankenHand> mtJankenHandList = this.multiJankenDao.queryMtJankenHand(requestInfo.getHand(), serverHand);
        Optional<MtJankenHand> mtJankenHandForClient = mtJankenHandList.stream().filter(s -> requestInfo.getHand().equals(s.getHand())).findFirst();
        Optional<MtJankenHand> mtJankenHandForServer = mtJankenHandList.stream().filter(s -> serverHand.equals(s.getHand())).findFirst();

        /* クライアント側とサーバー側の出し手を元にじゃんけん組み合わせマスタ情報を取得する */
        MtJankenComb mtJankenComb = this.multiJankenDao.findMtJankenComb(requestInfo.getHand(), serverHand);

        /* ********************
         *  登録処理
         * ********************/
        /* じゃんけん成績テーブル登録 */
        this.multiJankenDao.insertTtJankenScore(requestInfo.getHand(), serverHand, mtJankenComb.getResult(), requestInfo.getUserId());

        /* ********************
         *  返却値設定処理
         * ********************/
        JankenResultInfo jankenResultInfo = new JankenResultInfo();
        /* 送信者情報を設定 */
        jankenResultInfo.setSenderInfo(this.createSenderInfo(requestInfo.getHand(),
                                                                                     mtJankenHandForClient.orElse(new MtJankenHand()).getHandName()));
        /* 受信者情報を設定 */
        jankenResultInfo.setReceiverInfo(this.createReceiverInfo(serverHand,
                                                                                         mtJankenHandForServer.orElse(new MtJankenHand()).getHandName()));
        /* じゃんけんの結果を設定 */
        jankenResultInfo.setResult(mtJankenComb.getResult());
        jankenResultInfo.setResultName(mtJankenComb.getResultName());
        /* じゃんけん結果情報を設定 */
        BattleResponseDto responseInfo = new BattleResponseDto();
        responseInfo.setJankenResultInfo(jankenResultInfo);
        /* レスポンス情報と結果情報を設定 */
        CommonResponseDto<BattleResponseDto> responseDto = new CommonResponseDto<>();
        responseDto.setResponseInfo(responseInfo);
        responseDto.setResultInfo(ResultInfo.builder().resultCode("0").resultMsg("正常終了").build());

        return responseDto;
    }

    /**
     * <p>ストラテジーオブジェクト生成処理</p>
     * <pre>
     * ストラテジーIDに対応したストラテジーオブジェクトを生成する
     * ※デフォルトはランダムストラテジー
     * </pre>
     * @param strategyId ストラテジーID
     * @param requestInfo リクエスト情報
     * @return 生成したストラテジーオブジェクト
     */
    private Strategy createStrategy(final String strategyId, final BattleRequestDto requestInfo) {

        /* ▼ ストラテジーIDが「"1"」の場合：絶対勝利ストラテジーオブジェクトを作成 */
        if (StrategyId.ABS_WINNING_STRATEGY.getId().equals(strategyId)) {
            return new AbsWinningStrategy(this.multiJankenDao.queryMtJankenCombByHand1(requestInfo.getHand()));
        }
        /* ▼ ストラテジーIDが「"2"」の場合：絶対敗北ストラテジーオブジェクトを作成 */
        if (StrategyId.ABS_LOSING_STRATEGY.getId().equals(strategyId)) {
            return new AbsLosingStrategy(this.multiJankenDao.queryMtJankenCombByHand1(requestInfo.getHand()));
        }
        /* ▼ ストラテジーIDが「"3"」の場合：絶対引き分けストラテジーオブジェクトを作成 */
        if (StrategyId.ABS_DRAWING_STRATEGY.getId().equals(strategyId)) {
            return new AbsDrawingStrategy(this.multiJankenDao.queryMtJankenCombByHand1(requestInfo.getHand()));
        }
        /* ▼ ストラテジーIDが「"4"」の場合：統計ストラテジーオブジェクトを作成 */
        if (StrategyId.PROBABILITY_STRATEGY.getId().equals(strategyId)) {
            List<TtJankenScore> ttJankenScoreList = this.multiJankenDao.queryTtJankenScoreByUserId(requestInfo.getUserId());
            String mostUsedHand = ProbabilityStrategy.calcMostUsedHand(ttJankenScoreList);
            return new ProbabilityStrategy(this.multiJankenDao.queryMtJankenCombByHand1(mostUsedHand));
        }

        /* ストラテジーオブジェクトのデフォルトはランダムストラテジー */
        return new RandomStrategy();
    }

    private SenderInfo createSenderInfo(final String hand, final String handName) {
        SenderInfo senderInfo = new SenderInfo();
        senderInfo.setHand(hand);
        senderInfo.setHandName(handName);
        return senderInfo;
    }

    private ReceiverInfo createReceiverInfo(final String hand, final String handName) {
        ReceiverInfo receiverInfo = new ReceiverInfo();
        receiverInfo.setHand(hand);
        receiverInfo.setHandName(handName);
        return receiverInfo;
    }

    @Override
    protected void validate(CommonRequestDto<BattleRequestDto> requestDto) {
        super.validate(requestDto);

        BattleRequestDto requestInfo = requestDto.getRequestInfo();

        /* ・リクエスト項目「出し手(hand)」がnull又は空文字の場合：不正リクエスト例外をスローする */
        if (StringUtils.isEmpty(requestInfo.getHand())) throw new BadRequestException("出し手(hand)を設定してください");
        /* ・リクエスト項目「ユーザーID(userId)」がnull又は空文字の場合：不正リクエスト例外をスローする */
        if (StringUtils.isEmpty(requestInfo.getUserId())) throw new BadRequestException("ユーザーID(userId)を設定してください");
    }
}
