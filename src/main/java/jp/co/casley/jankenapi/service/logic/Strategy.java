package jp.co.casley.jankenapi.service.logic;

/**
 * ストラテジーインターフェース
 */
public interface Strategy {

    /**
     * <p>出し手算出処理</p>
     * @return 出し手
     */
    public String calcHand();
}
