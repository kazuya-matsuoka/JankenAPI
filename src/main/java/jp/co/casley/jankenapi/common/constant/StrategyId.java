package jp.co.casley.jankenapi.common.constant;

public enum StrategyId {
    /** ランダムに手を出す */
    RANDOM_STRATEGY("0"),
    /** 絶対勝つ手を出す(じゃんけんAPIが勝つ) */
    ABS_WINNING_STRATEGY("1"),
    /** 絶対負ける手を出す(じゃんけんAPIが負ける) */
    ABS_LOSING_STRATEGY("2"),
    /** 絶対引き分けになる手を出す */
    ABS_DRAWING_STRATEGY("3"),
    /** 過去に最も出していた手に勝つ手を出す */
    PROBABILITY_STRATEGY("4"),
    ;

    /** 戦略ID */
    private final String id;

    private StrategyId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
