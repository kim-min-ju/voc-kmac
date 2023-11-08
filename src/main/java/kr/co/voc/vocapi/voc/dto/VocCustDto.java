package kr.co.voc.vocapi.voc.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.voc.vocapi.common.util.validation.PostMethod;
import kr.co.voc.vocapi.coreframework.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

public class VocMstDto {
    @Data
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper= false)
    @Schema(description = "VOC 조회 request", name="VocSearchRequest")
    public static class Request extends BaseDto.Request {
        @Schema(description = "VOC 아이디")
        public String vocId;
        @Schema(description = "고객번호")
        public String custNo;
        @Schema(description = "VOC구분코드")
        public String vocCaseCd;
        @Schema(description = "VOC유형코드_대")
        public String vocTypeCd1;
        @Schema(description = "VOC유형코드_중")
        public String vocTypeCd2;
        @Schema(description = "VOC유형코드_소")
        public String vocTypeCd3;
        @Schema(description = "접수채널코드")
        public String rcptChnnCd;
        @Schema(description = "발생장소코드")
        public String sourceCd;
        @Schema(description = "발생일시")
        public String sourceDt;
        @Schema(description = "VOC제목")
        public String vocTitle;
        @Schema(description = "처리유형코드")
        public String vocActTypeCd;
        @Schema(description = "처리일시")
        public String vocActDt;
        @Schema(description = "처리자")
        public String vocActUserNo;
        @Schema(description = "VOC상태코드")
        public String vocStatusCd;

    }

    @Data
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper= false)
    @Schema(description = "VOC 정보객제", name="VocInfo")
    public static class Info extends BaseDto.Base {

        @Schema(description = "VOC일련번호")
        private int vocSeq;

        @Schema(description = "VOC 아이디")
        private String vocId;

        @Schema(description = "고객번호")
        @Size(max=20, message="고객번호: 20자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String custNo;

        @Schema(description = "VOC구분코드")
        @Size(max=20, message="VOC구분코드: 20자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String vocCaseCd;

        @Schema(description = "VOC유형코드_대")
        @Size(max=20, message="VOC유형코드_대: 20자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String vocTypeCd1;

        @Schema(description = "VOC유형코드_중")
        @Size(max=20, message="VOC유형코드_중: 20자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String vocTypeCd2;

        @Schema(description = "VOC유형코드_소")
        @Size(max=20, message="VOC유형코드_소: 20자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String vocTypeCd3;

        @Schema(description = "접수채널코드")
        @Size(max=20, message="접수채널코드: 20자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String rcptChnnCd;

        @Schema(description = "발생장소코드")
        @Size(max=20, message="발생장소코드: 20자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String sourceCd;

        @Schema(description = "발생일시")
        private String sourceDt;

        @Schema(description = "고객회신요청여부")
        @Size(max=1, message="고객회신요청여부: 1자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String custReplyYn;

        @Schema(description = "고객회신요청방법코드")
        @Size(max=20, message="고객회신요청방법코드: 20자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String custReplyCd;

        @Schema(description = "VOC제목")
        @Size(max=1000, message="VOC제목: 1000자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String vocTitle;

        @Schema(description = "VOC내용")
        private String vocCont;

        @Schema(description = "즉시처리여부")
        @Size(max=1, message="즉시처리여부: 1자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String immeActYn;

        @Schema(description = "처리유형코드")
        @Size(max=20, message="처리유형코드: 20자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String vocActTypeCd;

        @Schema(description = "처리일시")
        private String vocActDt;

        @Schema(description = "처리자")
        @Size(max=20, message="처리자: 20자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String vocActUserNo;

        @Schema(description = "처리내용")
        private String vocActCont;

        @Schema(description = "VOC상태코드")
        @Size(max=20, message="VOC상태코드: 20자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String vocStatusCd;

        @Schema(description = "익명고객여부")
        @Size(max=1, message="익명고객여부: 1자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String anonymCustYn;

        @Schema(description = "삭제여부")
        @Size(max=1, message="삭제여부: 1자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String delYn;

    }

    @Data
    @SuperBuilder
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper= false)
    @Schema(description = "VOC 처리 결과", name="VocResponse")
    public static class Response extends BaseDto.Result {

    }

    @Data
    @SuperBuilder
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper= false)
    @Schema(description = "VOC 조회목록 결과", name="VocListResponse")
    public static class ListResponse extends BaseDto.ListResult {
        List<Info> list;
    }
}
