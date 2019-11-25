package jp.co.casley.jankenapi.common.constant;

public enum ApiId {
    /** じゃんけんAPI */
    BATTLE("Battle"),
    /** じゃんけん成績照会API */
    SCORE("Score"),
    /** じゃんけん戦略変更API */
    STRATEGY("Strategy"),
    ;

    /** API_ID */
    private final String id;

    private ApiId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
