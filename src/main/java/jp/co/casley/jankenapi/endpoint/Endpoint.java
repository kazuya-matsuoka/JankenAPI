package jp.co.casley.jankenapi.endpoint;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jp.co.casley.jankenapi.service.dispatcher.ServiceDispatcher;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class Endpoint{
    /** サービス振り分けクラス */
    private final ServiceDispatcher serviceDispatcher;

    /** HTTP-GETメソッド(テスト用) */
    @GetMapping
    public ResponseEntity<String> get() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("header1", "heaer1-value");
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>("text content", headers, status);
    }

    /** HTTP-POSTメソッド */
    @PostMapping(value = "/{apiName}"
            , consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> post(@RequestBody String request, @PathVariable("apiName") String apiId) {
        return this.serviceDispatcher.dispatch(request, apiId);
    }
}
