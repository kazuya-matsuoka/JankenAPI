package jp.co.casley.jankenapi.service.logic;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import jp.co.casley.jankenapi.common.constant.ResultId;
import jp.co.casley.jankenapi.repository.db.entity.generate.MtJankenComb;

/**
 * 絶対勝利ストラテジークラス
 */
public class AbsWinningStrategy implements Strategy {
    /** ランダム値 */
    private Random random;
    /** じゃんけん組み合わせマスタ情報リスト */
    private List<MtJankenComb> mtJankenCombList;

    public AbsWinningStrategy(List<MtJankenComb> mtJankenCombList) {
        this.random = new Random();
        this.mtJankenCombList = mtJankenCombList;
    }

    @Override
    public String calcHand() {
        /* じゃんけん組み合わせマスタ情報リストが未設定の場合：ランダムに出し手(グー、チョキ、パーのいづれか)を算出 */
        if (this.mtJankenCombList == null || this.mtJankenCombList.isEmpty()) {
            return Integer.toString(this.random.nextInt(3));
        }

        /* じゃんけん組み合わせマスタ情報リストからクライアントの出し手に勝つ出し手を取得 */
        Optional<MtJankenComb> mtJankenComb = this.mtJankenCombList.stream()
                                                                               .filter(s->s.getResult().equals(ResultId.LOSE.getId())).findFirst();

        /* 勝ちになる出し手を返却(該当なしの場合：ランダムに出し手を返却) */
        return mtJankenComb.isPresent() ? mtJankenComb.get().getHand2() :
                                                                                          Integer.toString(this.random.nextInt(3));
    }

}
