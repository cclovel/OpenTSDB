package com.ygsoft.opentsdb.client.response;

/**
 * http响应返回
 * @author yinlilan
 *
 */
public class SimpleHttpResponse {
	private int statusCode;
	private String content;

	public boolean isSuccess() {
		return statusCode == 200 || statusCode == 204;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
