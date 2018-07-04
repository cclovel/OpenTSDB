package com.ygsoft.opentsdb.client.builder;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.ygsoft.opentsdb.client.util.Preconditions.checkNotNullOrEmpty;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.annotations.SerializedName;

/**
 * 数据结构实例
 * @author yinlilan
 *
 */
public class Metric {

	@SerializedName("metric")
	private String name;
	
	private long timestamp;
	
	private Object value;
	
	private Map<String, String> tags = new HashMap<String, String>();
	
	protected Metric(String name){
		this.name = checkNotNullOrEmpty(name);
	}
	
	/**
	 * 为数据点增加标签（单个）
	 * @param name
	 * @param value
	 * @return
	 */
	public Metric addTag(String name, String value){
		checkNotNullOrEmpty(name);
		checkNotNullOrEmpty(value);
		tags.put(name, value);
		
		return this;
	}
	
	/**
	 * 为数据点增加标签（多个）
	 * @param tags
	 * @return
	 */
	public Metric addTags(Map<String, String> tags){
		checkNotNull(tags);
		this.tags.putAll(tags);
		
		return this;
	}
	
	/**
	 * 为数据点增加值、时间（传入时间）
	 * @param timestamp
	 * @param value
	 * @return
	 */
	protected Metric innerAddDataPoint(long timestamp, Object value){
		checkArgument(timestamp > 0);
		this.timestamp = timestamp;
		this.value = checkNotNull(value);
		
		return this;
	}
	
	/**
	 * 为数据点增加值、时间（当前时间）
	 * @param value
	 * @return
	 */
	public Metric setDataPoint(long value){
		return innerAddDataPoint(System.currentTimeMillis(), value);
	}
	
	/**
	 * 为数据点增加值、时间（传入时间）
	 * @param timestamp
	 * @param value
	 * @return
	 */
	public Metric setDataPoint(long timestamp, long value) {
		return innerAddDataPoint(timestamp, value);
	}
	
	/**
	 * Adds the data point to the metric.
	 *
	 * @param timestamp
	 *            when the measurement occurred
	 * @param value
	 *            the measurement value
	 * @return the metric
	 */
	public Metric setDataPoint(long timestamp, double value) {
		return innerAddDataPoint(timestamp, value);
	}

	/**
	 * Adds the data point to the metric with a timestamp of now.
	 *
	 * @param value
	 *            the measurement value
	 * @return the metric
	 */
	public Metric setDataPoint(double value) {
		return innerAddDataPoint(System.currentTimeMillis(), value);
	}
	
	public long getTimestamp() {
		return timestamp;
	}

	public Object getValue() {
		return value;
	}

	public String stringValue() throws DataFormatException {
		return value.toString();
	}

	public long longValue() throws DataFormatException {
		try {
			return ((Number) value).longValue();
		} catch (Exception e) {
			throw new DataFormatException("Value is not a long");
		}
	}

	public double doubleValue() throws DataFormatException {
		try {
			return ((Number) value).doubleValue();
		} catch (Exception e) {
			throw new DataFormatException("Value is not a double");
		}
	}

	public boolean isDoubleValue() {
		return !(((Number) value).doubleValue() == Math.floor(((Number) value)
				.doubleValue()));
	}

	public boolean isIntegerValue() {
		return ((Number) value).doubleValue() == Math.floor(((Number) value)
				.doubleValue());
	}
	
	public String getName() {
		return name;
	}
	
	public Map<String, String> getTags() {
		return Collections.unmodifiableMap(tags);
	}

	
}
