package br.com.luizalabs.api.to;

public class GenericResponseEntity {

	private String reason;
	private Integer code;

	public GenericResponseEntity() {}
	
	public GenericResponseEntity(String reason, Integer code) {
		super();
		this.reason = reason;
		this.code = code;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("GenericExceptionEntity [reason=");
		stringBuilder.append(reason);
		stringBuilder.append(", code=");
		stringBuilder.append(code);
		stringBuilder.append("]");
		return stringBuilder.toString();
	}

}
