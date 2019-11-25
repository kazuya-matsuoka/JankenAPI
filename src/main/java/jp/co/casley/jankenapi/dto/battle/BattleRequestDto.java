package jp.co.casley.jankenapi.dto.battle;

import lombok.Data;

@Data
/** 勝負用リクエスト情報 */
public class BattleRequestDto {
    /** ユーザーID */
    private String userId;
    /** 出し手 */
    private String hand;

}
