package kr.co.kmac.system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.kmac.coreframework.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

public class VoctypeDto {
    @Data
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper= false)
    @Schema(description = "VOC유형 조회 request", name="VoctypeSearchRequest")
    public static class Request extends BaseDto.Request {
        @Schema(description = "업종코드")
        private String voctypeCd;
        @Schema(description = "VOC유형그룹명")
        private String voctypeNm;
        @Schema(description = "사용여부")
        private String useYn;
    }

    @Data
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper= false)
    @Schema(description = "VOC유형마스터 정보객제", name="VoctypeMstInfo")
    public static class Info extends BaseDto.Base {
        @Schema(description = "VOC유형마스터일련번호")
        private int voctypeMstSeq;

        @Schema(description = "VOC유형그룹코드")
        private int voctypeGrpCd;

        @Schema(description = "VOC유형그룹명")
        private int voctypeGrpNm;

        @Schema(description = "업종코드")
        private int industryCd;

        @Schema(description = "VOC유형그룹설명")
        private int voctypeDesc;

        @Schema(description = "사용여부")
        private String useYn;
    }

    @Data
    @SuperBuilder
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper= false)
    @Schema(description = "VOC유형 처리 결과", name="VoctypeResponse")
    public static class Response extends BaseDto.Result {

    }

    @Data
    @SuperBuilder
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper= false)
    @Schema(description = "VOC유형 조회목록 결과", name="VoctypeListResponse")
    public static class ListResponse extends BaseDto.ListResult {
        List<Info> list;
    }
}
