package jp.co.casley.jankenapi.dto.battle;

import lombok.Data;

@Data
/** じゃんけん結果情報 */
public class JankenResultInfo {
    /** 送信者情報 */
    private SenderInfo senderInfo;
    /** 受信者情報 */
    private ReceiverInfo receiverInfo;
    /** 結果 */
    private String result;
    /** 結果名称 */
    private String resultName;

}
