package com.ygsoft.opentsdb.server.dto;

/**
 * Created by yinlilan on 2018/1/19.
 */
public class QueryDateDTO {
	private Object data;
    private String apiKey;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
}
