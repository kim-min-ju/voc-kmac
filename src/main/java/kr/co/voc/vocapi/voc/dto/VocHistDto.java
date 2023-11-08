package kr.co.voc.vocapi.voc.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.voc.vocapi.common.util.validation.PostMethod;
import kr.co.voc.vocapi.coreframework.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Size;
import java.util.List;

public class VocCustDto {
    @Data
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper= false)
    @Schema(description = "VOC고객 조회 request", name="VocCustSearchRequest")
    public static class Request extends BaseDto.Request {
        @Schema(description = "고객번호")
        private String custNo;

        @Schema(description = "고객명")
        private String custNm;

    }

    @Data
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper= false)
    @Schema(description = "VOC고객 정보객제", name="VocCustInfo")
    public static class Info extends BaseDto.Base {

        @Schema(description = "고객일련번호")
        private int custSeq;

        @Schema(description = "고객번호")
        @Size(max=20, message="고객번호: 20자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String custNo;

        @Schema(description = "고객명")
        @Size(max=100, message="고객명: 100자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String custNm;

        @Schema(description = "고객명_영문")
        @Size(max=100, message="고객명_영문: 100자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String custNmEn;

        @Schema(description = "전화번호")
        @Size(max=100, message="전화번호: 100자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String telNo;

        @Schema(description = "이메일")
        @Size(max=500, message="이메일: 500자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String emailAddr;

        @Schema(description = "삭제여부")
        @Size(max=1, message="삭제여부: 1자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String delYn;

    }

    @Data
    @SuperBuilder
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper= false)
    @Schema(description = "VOC고객 처리 결과", name="VocCustResponse")
    public static class Response extends BaseDto.Result {

    }

    @Data
    @SuperBuilder
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper= false)
    @Schema(description = "VOC고객 조회목록 결과", name="VocCustListResponse")
    public static class ListResponse extends BaseDto.ListResult {
        List<Info> list;
    }
}
