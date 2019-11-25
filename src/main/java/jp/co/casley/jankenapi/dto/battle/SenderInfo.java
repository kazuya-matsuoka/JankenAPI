package jp.co.casley.jankenapi.dto.battle;

import lombok.Data;

@Data
/** 受信者情報 */
public class SenderInfo {
    /** 出し手 */
    private String hand;
    /** 出し手名称 */
    private String handName;
}