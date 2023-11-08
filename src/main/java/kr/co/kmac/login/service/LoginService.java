package kr.co.cgv.kms.user.service;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.cgv.kms.coreframework.service.BaseService;
import kr.co.cgv.kms.user.mapper.LoginMapper;
import kr.co.cgv.kms.user.model.Login;
import kr.co.cgv.kms.user.model.UserSessionInfo;

/**
 * 로그인 서비스 클래스
 * 
 * @ClassName LoginService.java
 * @Description 로그인 서비스 클래스
 * @author dongkum
 * @since 2022. 3. 7.
 */
@Service
public class LoginService extends BaseService {
	
	@Autowired
	private LoginMapper loginMapper;
	
	/**
	 * @Description 로그인 체크
	 * @author dongkum
	 * @since 2022. 3. 7.
	 */
	public Login loginCheck(Login login) {
		Login result = new Login();
		int passwordChangeDate = 180;
		int lastLoginDate = 90;
		result.setResultCd("F");
		int idChk = this.idChk(login);
		if (idChk <= 0) {
			// 아이디가 존재하지 않을 경우
			result.setResultMsg("로그인에 실패하였습니다.");
		} else {
			int lockChk = this.lockChk(login);
			if (lockChk > 5) {
				// LOCK 처리 / 비밀번호 불일치 5회 이상
				this.setLock(login);
				result.setResultCd("S");
				result.setResultMsg("로그인에 실패하였습니다.");
			} else {
				int idPwdChk = this.idPwdChk(login);
				if (idPwdChk <= 0) {
					// 비밀번호 불일치 (비밀번호 실패 카운트 +1)
					this.setPwdFail(login);
					result.setResultCd("F");
					result.setResultMsg("로그인에 실패하였습니다.");
				} else {
					result = this.getUserInfo(login);
//					int lastLoginChk = this.lastLoginChk(result);
//					if (lastLoginChk >= lastLoginDate) {
//						// LOCK 처리 / 마지막로그인 날짜 90일 경과
//						this.setLock(login);
//						result.setResultCd("S");
//						result.setResultMsg("로그인에 실패하였습니다.");
//					} else 
					if (result.getPwddatechk() >= passwordChangeDate) {
						// 비밀번호 변경 기간 초과, 비밀번호 변경 요청
						logger.error("비밀번호 변경 기간 초과, 비밀번호 변경 요청");
						result.setResultCd( String.valueOf(result.getUridx()) );
						result.setResultMsg("로그인에 실패하였습니다.");
					} else {
						if (login.getMuserid().equals(login.getPwd())) {
							// 비밀번호 초기 설정 요청
							logger.error("비밀번호 초기 설정 요청");
							result.setResultCd( String.valueOf(result.getUridx()) );
							result.setResultMsg("로그인에 실패하였습니다.");
						} else {
							login.setUridx(result.getUridx());
							if (this.setLoginSuccess(login) > 0) {
								// 로그인 이력 저장
								result.setResultCd("T");
								result.setResultMsg("로그인 성공");
							} else {
								result.setResultCd("F");
								result.setResultMsg("로그인에 실패하였습니다.");
							}
						}
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * @Description 계정잠김 해제 처리
	 * @author dongkum
	 * @since 2022. 3. 8.
	 */
	public Login lockIdCheck(Login login) {
		Login result = this.getLockIdChk(login);
		if (result == null) {
			result = new Login();
			result.setResultCd("F");
			result.setResultMsg("존재하지 않는 사용자 입니다.");
		} else {
			if (result.getOEmail() == null || "".equals(result.getOEmail())) {
				result.setResultCd("F");
				result.setResultMsg("계정에 등록된 이메일이 없습니다.");
			} else {
				// 임시비밀번호 저장 / Login_TempPwdSave
				int tempPwdSave = this.tempPwdSave(login);
				if (tempPwdSave <= 0) {
					result.setResultCd("F");
					result.setResultMsg("처리 되지 않았습니다.");
				} else {
					// 임시 비밀번호 메일 발송
					result.setUserid(login.getUserid());
					result.setTempPwd(login.getTempPwd());
					this.sendMail(result);
				}
			}
		}
		return result;
	}
	
	/**
	 * TODO : 메일 발송 프로세스 확인 후 완성
	 * @Description 임시 비밀번호 메일 발송
	 * @author dongkum
	 * @since 2022. 3. 8.
	 */
	private boolean sendMail(Login login) {
		SimpleDateFormat nDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String mailTitle = login.getOUsername()+"님의 KMS 비밀번호 초기화 메일입니다.";
		StringBuilder mailBody = new StringBuilder();
		mailBody.append("<br>고객님의 KMS 비밀번호가 초기화 처리되었습니다.<br>");
		mailBody.append("임시 비밀번호로 로그인 하신 후 비밀번호를 반드시 변경해주세요.<br><br>");
		mailBody.append("<div style = 'background-color: #eee; padding: 20px;'>");
		mailBody.append("<b> *임시 비밀번호 변경 내역 </b><br>");
		mailBody.append("&nbsp;&nbsp; 일시: "+nDate+"<br>");
		mailBody.append("&nbsp;&nbsp; 아이디: "+login.getUserid()+"<br>");
		mailBody.append("&nbsp;&nbsp; 임시 비밀번호: "+login.getTempPwd()+"<br>");
		mailBody.append("</div><br/>");
		
		logger.error(mailTitle);
		logger.error(mailBody.toString());
		return true;
	}
	
//	/**
//	 * @Description 로그인이력 팝업 폼
//	 * @author moonkyu
//	 * @since 2022. 10. 14.
//	 */
//	public Login loginHistory(Login login) {
//		Login result = this.getLockIdChk(login);
//		
//		return result;
//	}
	
	@Transactional
	public int idChk(Login login) {
		return loginMapper.getIdCheck(login);
	}
	@Transactional
	public int idPwdChk(Login login) {
		return loginMapper.getIdPwdCheck(login);
	}
	@Transactional
	public int lockChk(Login login) {
		return loginMapper.getLockCheck(login);
	}
	@Transactional
	public int lastLoginChk(Login login) {
		return loginMapper.getLastLoginCheck(login);
	}
	@Transactional
	public int setLock(Login login) {
		return loginMapper.setLock(login);
	}
	@Transactional
	public int setPwdFail(Login login) {
		return loginMapper.setPwdFail(login);
	}
	@Transactional
	public int setLoginSuccess(Login login) {
		return loginMapper.setLoginSuccess(login);
	}
	@Transactional
	public int tempPwdSave(Login login) {
		return loginMapper.tempPwdSave(login);
	}
	@Transactional
	public Login getUserInfo(Login login) {
		return loginMapper.getUserInfo(login);
	}
	@Transactional
	public Login getLockIdChk(Login login) {
		return loginMapper.getLockIdChk(login);
	}
	@Transactional
	public UserSessionInfo getSessionInfo(Login login) {
		return loginMapper.getSessionInfo(login);
	}

}
