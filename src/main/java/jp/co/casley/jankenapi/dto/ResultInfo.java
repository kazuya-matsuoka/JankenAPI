package jp.co.casley.jankenapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultInfo {
    /** 結果コード */
    private String resultCode;
    /** 結果メッセージ */
    private String resultMsg;
}