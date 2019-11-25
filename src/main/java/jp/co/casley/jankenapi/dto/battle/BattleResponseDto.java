package jp.co.casley.jankenapi.dto.battle;

import lombok.Data;

@Data
/** 勝負用レスポンス情報 */
public class BattleResponseDto {
    /** じゃんけん結果情報 */
    private JankenResultInfo jankenResultInfo;
}
