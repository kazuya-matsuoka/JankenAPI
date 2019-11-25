package jp.co.casley.jankenapi.service;

import org.springframework.http.ResponseEntity;

/**
 * Serviceインターフェース
 */
@org.springframework.stereotype.Service
public interface Service {

    /**
     * <p>サービス実行処理</p>
     * @param request リクエストJSON文字列
     * @return レスポンス情報
     */
    public ResponseEntity<String> execute(final String request);

}
