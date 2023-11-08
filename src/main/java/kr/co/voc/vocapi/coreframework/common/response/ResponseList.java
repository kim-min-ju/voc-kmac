package kr.co.cgv.kioskapi.coreframework.common.response;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.cgv.kioskapi.common.constants.CommonConstants;
import kr.co.cgv.kioskapi.common.util.MessageUtil;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author kyudam
 * @class ResponseList
 * @description
 * @since 2022/03/03
 */
@Data
@Builder(toBuilder = true)
@EqualsAndHashCode(callSuper = false)
public class ResponseList<List> {
    @Schema(description = "API 서비스 구분")
    @Builder.Default
    private final String serviceArea = CommonConstants.SERVICE_AREA;
    @Schema(description = "API URL 주소")
    private String api;
    @Schema(description = "오류 그룹 코드")
    @Builder.Default
    private String errorCode = CommonConstants.DEFAULT_SUCCESS;
    @Schema(description = "오류 그룹 메시지")
    @Builder.Default
    private String errorMessage = MessageUtil.getMessage(CommonConstants.DEFAULT_KEY + CommonConstants.DEFAULT_SUCCESS);
    @Schema(description = "메시지 코드")
    @Builder.Default
    private String messageCode = CommonConstants.DEFAULT_SUCCESS;
    @Schema(description = "메시지")
    @Builder.Default
    private String message = MessageUtil.getMessage(CommonConstants.DEFAULT_KEY + CommonConstants.DEFAULT_SUCCESS);
    @Schema(description = "조회된 데이터 수")
    @Builder.Default
    private int totalCount = 0;
    @Schema(description = "응답 데이터 목록")
    private List data;
}
