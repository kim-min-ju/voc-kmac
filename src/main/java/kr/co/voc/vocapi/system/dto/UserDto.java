package kr.co.voc.vocapi.system.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import kr.co.voc.vocapi.coreframework.model.BaseModel;
import kr.co.voc.vocapi.util.validation.PostMethod;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

/**
 * 사용자정보 모델 클래스
 * 
 * @ClassName SysUser.java
 * @Description 사용자정보 모델 클래스
 * @author mjkim
 * @since 2023. 4. 4.
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
@Alias("system.sysuser")
public class SysUser extends BaseModel
{
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
    @Schema(description = "등록자")
    @Size(max=20, message="등록자: 20자를 넘을 수 없습니다.", groups=PostMethod.class)
    private String regUserNo;
    @Schema(description = "등록일시")
    @NotBlank(message="등록일시: 필수 항목입니다.", groups=PostMethod.class)
    private timestamp regDt;
    @Schema(description = "수정자")
    @Size(max=20, message="수정자: 20자를 넘을 수 없습니다.", groups=PostMethod.class)
    private String modUserNo;
    @Schema(description = "수정일시")
    @NotBlank(message="수정일시: 필수 항목입니다.", groups=PostMethod.class)
    private timestamp modDt;
}
