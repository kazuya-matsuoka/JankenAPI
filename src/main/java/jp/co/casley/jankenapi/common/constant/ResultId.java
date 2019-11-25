package jp.co.casley.jankenapi.common.constant;

public enum ResultId {
    /** 勝ち */
    WIN("0"),
    /** 負け */
    LOSE("1"),
    /** あいこ */
    DRAW("2"),
    ;

    /** 結果ID */
    private final String id;

    private ResultId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
