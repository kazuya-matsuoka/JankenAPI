package jp.co.casley.jankenapi.dto;

import lombok.Data;

@Data
public class CommonResponseDto<T> {
    /** レスポンス情報 */
    private T responseInfo;
    /** 結果情報 */
    private ResultInfo resultInfo;
}
