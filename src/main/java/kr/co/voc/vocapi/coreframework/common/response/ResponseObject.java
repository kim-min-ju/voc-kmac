package kr.co.rmsoft.tms.coreframework.common.response;

public class ResponseObject {
	
	/*
	 * 응답 데이터
	 */
	private Object data;
	/*
	 * 응답 상태 메시지
	 */
	private ResponseStatusObject rspStatus;
	
	public ResponseObject() {
		
	}
	
	public ResponseObject(ResponseStatusObject rspStatus) {
		this.rspStatus = rspStatus;
	}
	
	public ResponseObject(Object data) {
		this.data = data;
	}
	
	public ResponseObject(ResponseStatusObject rspStatus, Object data) {
		this.rspStatus = rspStatus;
		this.data = data;
	}
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public ResponseStatusObject getRspStatus() {
		return rspStatus;
	}
	public void setRspStatus(ResponseStatusObject rspStatus) {
		this.rspStatus = rspStatus;
	}
}
