package jp.co.casley.jankenapi.service;

import javax.ws.rs.BadRequestException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.casley.jankenapi.dto.CommonRequestDto;
import jp.co.casley.jankenapi.dto.CommonResponseDto;
import jp.co.casley.jankenapi.dto.ResultInfo;
import lombok.Data;

/**
 * Service基底クラス
 */
@org.springframework.stereotype.Service
@Data
public abstract class BasicService<I, O> implements Service {
    /** JSON⇔オブジェクト変換 */
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /** HTTPヘッダー情報 */
    private HttpHeaders headers = new HttpHeaders();
    /** HTTPステータス情報 */
    private HttpStatus status = HttpStatus.OK;
    /** リクエストDTOタイプ */
    private Class<I> requestDtoType;

    protected BasicService(final Class<I> requestDtoType) {
        this.requestDtoType = requestDtoType;
    }

    @Override
    public ResponseEntity<String> execute(String request) {
        CommonRequestDto<I> requestDto = null;
        try {
            /* サブクラスのリクエストDTOの型オブジェクトを生成 */
            JavaType requestType = MAPPER.getTypeFactory().constructParametricType(CommonRequestDto.class, this.requestDtoType);
            /* JSONリクエスト文字列からリクエストDTOへの変換 */
            requestDto = MAPPER.readValue(request, requestType);
            /* バリデーション処理実行 */
            validate(requestDto);
            /* サブクラスのサービス処理実行 */
            CommonResponseDto<O> responseDto =  execute(requestDto);
            /* レスポンスDTOからレスポンスエンティティに変換※特に設定がない場合HTTPヘッダーとステータスはデフォルトを設定 */
            return new ResponseEntity<>(MAPPER.writeValueAsString(responseDto), headers, status);
        } catch (Exception e) {
            return this.handleException(e);
        }
    }

    /**
     * <p>サービス実行処理</p>
     * <pre>
     * サブクラスのサービス実行処理
     * 当クラスで変換されたリクエストDTOを引数に渡され実行される
     * <pre>
     * @param requestDto リクエストDTO
     * @return レスポンスDTO
     */
    protected abstract CommonResponseDto<O> execute(CommonRequestDto<I> requestDto);

    /**
     * <p>バリデーション処理</p>
     * <pre>
     * バリデーションのチェックに引っかかった場合、例外をスローする
     * ※サービス固有のチェックはオーバーライドで拡張を想定
     * </pre>
     * @param requestDto リクエストDTO
     */
    protected void validate(CommonRequestDto<I> requestDto) {
        if (requestDto == null) throw new BadRequestException("ルートのリクエスト情報がnullです");

        if (requestDto.getRequestInfo() == null) throw new BadRequestException("リクエスト情報がnullです");
    }

    /**
     * <p>例外ハンドリング処理</p>
     * @param e 例外
     * @return 例外を元に作成されたエラーレスポンス
     */
    private ResponseEntity<String> handleException(Exception e) {
        try {
            if (e instanceof BadRequestException) {
                CommonResponseDto<O> responseDto = new CommonResponseDto<>();
                responseDto.setResultInfo(ResultInfo.builder().resultCode("1").resultMsg(e.getMessage()).build());
                return new ResponseEntity<>(MAPPER.writeValueAsString(responseDto), headers, HttpStatus.BAD_REQUEST);
            }
            if (e instanceof JsonMappingException) {
                CommonResponseDto<O> responseDto = new CommonResponseDto<>();
                responseDto.setResultInfo(ResultInfo.builder().resultCode("1").resultMsg("JSONのマッピングで致命的な問題が発生しました。").build());
                return new ResponseEntity<>(MAPPER.writeValueAsString(responseDto), headers, HttpStatus.BAD_REQUEST);
            }
            if (e instanceof JsonProcessingException) {
                CommonResponseDto<O> responseDto = new CommonResponseDto<>();
                responseDto.setResultInfo(ResultInfo.builder().resultCode("1").resultMsg("JSONコンテンツを処理（解析、生成）時に問題が発生しました。").build());
                return new ResponseEntity<>(MAPPER.writeValueAsString(responseDto), headers, HttpStatus.BAD_REQUEST);
            }
        } catch(JsonProcessingException e2) {
            throw new RuntimeException(e);
        }

        throw new RuntimeException(e);
    }
}
