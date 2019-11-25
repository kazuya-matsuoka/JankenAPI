package jp.co.casley.jankenapi.service.dispatcher;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import jp.co.casley.jankenapi.common.constant.ApiId;
import jp.co.casley.jankenapi.service.Service;


@Component
public class BasicServiceDispatcher implements ServiceDispatcher {
    /** Service実装クラスのマップ */
    @Autowired
    private Map<String,Service> services;

    @Override
    public ResponseEntity<String> dispatch(final String request, final String apiId) {
        Service service = null;

        /* ▼じゃんけんAPIのServiceクラスを呼び出す */
        if (apiId.equals(ApiId.BATTLE.getId())) {
            service = this.services.get("battleService");
        }
        /* ▼じゃんけん成績照会APIのServiceクラスを呼び出す */
        if (apiId.equals(ApiId.SCORE.getId())) {
            service = this.services.get("scoreService");
        }
        /* ▼じゃんけん戦略変更APIのServiceクラスを呼び出す */
        if (apiId.equals(ApiId.STRATEGY.getId())) {
            service = this.services.get("strategyService");
        }
        return service.execute(request);
    }
}
