package jp.co.casley.jankenapi.service.logic;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import jp.co.casley.jankenapi.common.constant.ResultId;
import jp.co.casley.jankenapi.repository.db.entity.generate.MtJankenComb;
import jp.co.casley.jankenapi.repository.db.entity.generate.TtJankenScore;

/**
 * 統計ストラテジークラス
 */
public class ProbabilityStrategy implements Strategy {
    /** ランダム値 */
    private Random random;
    /** じゃんけん組み合わせマスタ情報リスト */
    private List<MtJankenComb> mtJankenCombList;

    public ProbabilityStrategy(List<MtJankenComb> mtJankenCombList) {
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

    /**
     * <p>最多出し手算出</p>
     * <pre>
     * じゃんけん成績テーブル情報リストから今までで
     * 最もそのユーザーが出している手を算出する。
     * </pre>
     * @param ttJankenScoreList じゃんけん成績テーブル情報リスト
     * @return 最多出し手
     */
    public static String calcMostUsedHand(List<TtJankenScore> ttJankenScoreList) {
        /* じゃんけん成績テーブル情報リストから各出し手毎のカウントをグルーピング */
        Map<String, Long> grpByTypeCount = ttJankenScoreList.stream().collect(
                Collectors.groupingBy(TtJankenScore::getClientHand,
                    Collectors.counting()));

        /* 各出し手カウントから最も多い数字の出し手を取得 */
        Optional<Entry<String, Long>> mostUsedEntry = grpByTypeCount.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue));

        return mostUsedEntry.get().getKey();
    }

}
