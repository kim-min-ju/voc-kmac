package kr.co.voc.vocapi.system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.voc.vocapi.common.util.validation.PostMethod;
import kr.co.voc.vocapi.coreframework.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

public class UserDto {
    @Data
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper= false)
    @Schema(description = "사용자 조회 request", name="UserSearchRequest")
    public static class Request extends BaseDto.Request {
        @Schema(description = "호텔코드")
        private String hotelCd;

        @Schema(description = "사용자아이디")
        private String userId;
        @Schema(description = "사번")
        private String empNo;

        @Schema(description = "사용자명")
        private String userNm;

        @Schema(description = "사용자명_영문")
        private String userNmEn;

    }

    @Data
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper= false)
    @Schema(description = "사용자 정보객제", name="UserInfo")
    public static class Info extends BaseDto.Base {

        @Schema(description = "사용자일련번호")
        @NotBlank(message="사용자일련번호: 필수 항목입니다.")
        private int userSeq;

        @Schema(description = "호텔코드")
        @NotBlank(message="호텔코드: 필수 항목입니다.", groups=PostMethod.class)
        @Size(max=10, message="호텔코드: 10자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String hotelCd;
        @Schema(description = "사용자아이디")
        @NotBlank(message="사용자아이디: 필수 항목입니다.", groups=PostMethod.class)
        @Size(max=50, message="사용자아이디: 50자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String userId;
        @Schema(description = "직원구분코드")
        @Size(max=20, message="직원구분코드: 20자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String empTypeCd;
        @Schema(description = "사번")
        @Size(max=20, message="사번: 20자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String empNo;
        @Schema(description = "사용자명")
        @Size(max=100, message="사용자명: 100자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String userNm;
        @Schema(description = "사용자명_영문")
        @Size(max=100, message="사용자명_영문: 100자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String userNmEn;
        @Schema(description = "회사코드")
        @Size(max=20, message="회사코드: 20자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String companyCd;
        @Schema(description = "회사명")
        @Size(max=200, message="회사명: 200자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String companyNm;
        @Schema(description = "부서코드")
        @Size(max=20, message="부서코드: 20자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String deptCd;
        @Schema(description = "부서명")
        @Size(max=200, message="부서명: 200자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String deptNm;
        @Schema(description = "실부서코드")
        @Size(max=20, message="실부서코드: 20자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String realDeptCd;
        @Schema(description = "실부서명")
        @Size(max=200, message="실부서명: 200자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String realDeptNm;
        @Schema(description = "직무코드")
        @Size(max=20, message="직무코드: 20자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String dutyCd;
        @Schema(description = "직무명")
        @Size(max=200, message="직무명: 200자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String dutyNm;
        @Schema(description = "직위코드")
        @Size(max=20, message="직위코드: 20자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String positionCd;
        @Schema(description = "직위명")
        @Size(max=200, message="직위명: 200자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String positionNm;
        @Schema(description = "직급코드")
        @Size(max=20, message="직급코드: 20자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String titleCd;
        @Schema(description = "직급명")
        @Size(max=200, message="직급명: 200자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String titleNm;
        @Schema(description = "퇴사여부")
        @Size(max=1, message="퇴사여부: 1자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String retireYn;
        @Schema(description = "입사일자")
        @Size(max=8, message="입사일자: 8자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String joinDt;
        @Schema(description = "퇴사일자")
        @Size(max=8, message="퇴사일자: 8자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String retireDt;
        @Schema(description = "전화번호")
        @Size(max=100, message="전화번호: 100자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String telNo;
        @Schema(description = "휴대전화번호")
        @Size(max=100, message="휴대전화번호: 100자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String cellNo;
        @Schema(description = "이메일주소")
        @Size(max=500, message="이메일주소: 500자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String emailAddr;
        @Schema(description = "비밀번호")
        @Size(max=100, message="비밀번호: 100자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String pw;
        @Schema(description = "비밀번호변경일")
        @Size(max=8, message="비밀번호변경일: 8자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String pwChgDt;
        @Schema(description = "사용자계정잠금여부")
        @Size(max=1, message="사용자계정잠금여부: 1자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String accountLockYn;
        @Schema(description = "기타1")
        @Size(max=500, message="기타1: 500자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String etc1;
        @Schema(description = "기타2")
        @Size(max=500, message="기타2: 500자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String etc2;
        @Schema(description = "기타3")
        @Size(max=500, message="기타3: 500자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String etc3;
        @Schema(description = "사용여부")
        @Size(max=1, message="사용여부: 1자를 넘을 수 없습니다.", groups=PostMethod.class)
        private String useYn;
    }

    @Data
    @SuperBuilder
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper= false)
    @Schema(description = "사용자 처리 결과", name="UserResponse")
    public static class Response extends BaseDto.Result {

    }

    @Data
    @SuperBuilder
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper= false)
    @Schema(description = "사용자 조회목록 결과", name="UserListResponse")
    public static class ListResponse extends BaseDto.ListResult {
        List<Info> list;
    }
}
