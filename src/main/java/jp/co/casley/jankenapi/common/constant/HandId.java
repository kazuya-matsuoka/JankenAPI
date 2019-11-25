package jp.co.casley.jankenapi.common.constant;

public enum HandId {
    /** ぐー */
    GU("0"),
    /** ちょき */
    TYOKI("1"),
    /** ぱー */
    PA("2"),
    ;

    /** 出し手ID */
    private final String id;

    private HandId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
