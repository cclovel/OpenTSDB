package com.ygsoft.opentsdb.client.response;

/**
 * OpenTSDB服务器返回的响应
 * @author yinlilan
 *
 */
public class Response {
	private int statusCode;
	private ErrorDetail errorDetail;

	public Response() {
	}

	public boolean isSuccess() {
		return statusCode == 200 || statusCode == 204;
	}

	public Response(int statusCode) {
		this.statusCode = statusCode;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public ErrorDetail getErrorDetail() {
		return errorDetail;
	}

	public void setErrorDetail(ErrorDetail errorDetail) {
		this.errorDetail = errorDetail;
	}

	@Override
	public String toString() {
		return "Response [statusCode=" + statusCode + ", errorDetail="
				+ errorDetail + "]";
	}

}
