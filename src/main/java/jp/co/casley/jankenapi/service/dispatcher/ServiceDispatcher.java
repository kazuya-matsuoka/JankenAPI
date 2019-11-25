package jp.co.casley.jankenapi.service.dispatcher;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Service振り分けインターフェース
 */
@Component
public interface ServiceDispatcher {

    /**
     * <p>Service振り分け処理</p>
     * <pre>
     * apiIdをキーに対応したServiceクラスを呼び出す。
     * </pre>
     * @param request リクエストJSON文字列
     * @param apiId API_ID
     * @return Service処理結果
     */
    public ResponseEntity<String> dispatch(final String request, final String apiId);
}
