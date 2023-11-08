package kr.co.cgv.kioskapi.coreframework.common.response;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author kyudam
 * @class Response
 * @description 메시지 필터
 * @since 2022/02/23
 */
@Data
@Builder(toBuilder = true)
@EqualsAndHashCode(callSuper = false)
public class ResponseMessage {
    private String errorCode;
    private String errorMessage;
    private String messageCode;
    private String message;
}
