package jp.co.casley.jankenapi.repository.db.dao;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import jp.co.casley.jankenapi.repository.db.entity.generate.MtJankenComb;
import jp.co.casley.jankenapi.repository.db.entity.generate.MtJankenCombExample;
import jp.co.casley.jankenapi.repository.db.entity.generate.MtJankenCombKey;
import jp.co.casley.jankenapi.repository.db.entity.generate.MtJankenHand;
import jp.co.casley.jankenapi.repository.db.entity.generate.MtJankenHandExample;
import jp.co.casley.jankenapi.repository.db.entity.generate.MtJankenStrategyUser;
import jp.co.casley.jankenapi.repository.db.entity.generate.TtJankenScore;
import jp.co.casley.jankenapi.repository.db.entity.generate.TtJankenScoreExample;
import lombok.RequiredArgsConstructor;

/** マルチじゃんけんDAO  */
@Repository
@RequiredArgsConstructor
public class MultiJankenDao {
    /** じゃんけん出し手マスタ */
    private final MtJankenHandDao mtJankenHandDao;
    /** じゃんけん戦略ユーザーマスタ */
    private final MtJankenStrategyUserDao mtJankenStrategyUserDao;
    /** じゃんけん組み合わせマスタ */
    private final MtJankenCombDao mtJankenCombDao;
    /** じゃんけん成績テーブル */
    private final TtJankenScoreDao ttJankenScoreDao;

    /**
     * <p>じゃんけん戦略ユーザーマスタPK検索処理</p>
     * <pre>
     * ユーザーIDをキーにじゃんけん戦略ユーザーマスタを検索する
     * </pre>
     * @param userId ユーザーID
     * @return 検索結果(じゃんけん戦略ユーザーマスタのエンティティ)
     */
    public MtJankenStrategyUser findMtJankenStrategyUser(final String userId) {
        return this.mtJankenStrategyUserDao.selectByPrimaryKey(userId);
    }

    /**
     * <p>じゃんけん組み合わせマスタPK検索処理</p>
     * <pre>
     * 出し手１と出し手２をキーにじゃんけん組み合わせマスタを検索する
     * </pre>
     * @param hand1 出し手１
     * @param hand2 出し手２
     * @return 検索結果(じゃんけん組み合わせマスタのエンティティ)
     */
    public MtJankenComb findMtJankenComb(final String hand1, final String hand2) {
        MtJankenCombKey key = new MtJankenCombKey();
        key.setHand1(hand1);
        key.setHand2(hand2);
        return this.mtJankenCombDao.selectByPrimaryKey(key);
    }

    /**
     * <p>じゃんけん組み合わせマスタ条件検索処理(出し手１)</p>
     * <pre>
     * 出し手１をキーにじゃんけん組み合わせマスタを検索する(複数該当あり)
     * </pre>
     * @param hand1 出し手１
     * @return 検索結果(じゃんけん組み合わせマスタのエンティティリスト)
     */
    public List<MtJankenComb> queryMtJankenCombByHand1(final String hand1) {
        MtJankenCombExample example = new MtJankenCombExample();
        example.createCriteria().andHand1EqualTo(hand1);
        return this.mtJankenCombDao.selectByExample(example);
    }

    /**
     * <p>じゃんけん出し手マスタ条件検索処理(出し手(複数可))</p>
     * <pre>
     * 出し手をキーにじゃんけん出し手マスタを検索する(複数該当あり)
     * </pre>
     * @param hands 出し手(複数可)
     * @return 検索結果(じゃんけん出し手マスタのエンティティリスト)
     */
    public List<MtJankenHand> queryMtJankenHand(final String ...hands) {
        MtJankenHandExample example = new MtJankenHandExample();
        example.createCriteria().andHandIn(Arrays.asList(hands));
        return this.mtJankenHandDao.selectByExample(example);
    }

    /**
     * <p>じゃんけん成績テーブル条件検索処理(ユーザーID)</p>
     * <pre>
     * ユーザーIDをキーにじゃんけん成績テーブルを検索する(複数該当あり)
     * </pre>
     * @param userId ユーザーID
     * @return 検索結果(じゃんけん成績テーブルのエンティティリスト)
     */
    public List<TtJankenScore> queryTtJankenScoreByUserId(final String userId) {
        TtJankenScoreExample example = new TtJankenScoreExample();
        example.createCriteria().andUserIdEqualTo(userId);
        return this.ttJankenScoreDao.selectByExample(example);
    }

    /**
     * <p>じゃんけん成績テーブル登録処理</p>
     * <pre>
     * 引数に渡された値でエンティティを作成し、じゃんけん成績テーブルへ登録する
     * </pre>
     * @param clientHand クライアント側出し手
     * @param serverHand サーバー側出し手
     * @param result じゃんけんの結果
     * @param userId ユーザーID
     * @return 登録結果(登録されたレコード件数)
     */
    public int insertTtJankenScore(final String clientHand, final String serverHand, final String result, final String userId) {
        TtJankenScore record = new TtJankenScore();
        record.setClientHand(clientHand);
        record.setServerHand(serverHand);
        record.setResult(result);
        record.setUserId(userId);
        record.setCreateUser(userId);
        record.setUpdateUser(userId);
        return this.ttJankenScoreDao.insertSelective(record);
    }
}
