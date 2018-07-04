package com.ygsoft.opentsdb.server.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ygsoft.opentsdb.server.client.DateTimeUtil;
import com.ygsoft.opentsdb.server.client.OpentsdbClient;
import com.ygsoft.opentsdb.server.dto.ImportDateDTO;
import com.ygsoft.opentsdb.server.dto.QueryDateDTO;
import com.ygsoft.opentsdb.server.dto.ResponseData;
import com.ygsoft.opentsdb.server.service.OpentsdbService;

@Service
public class OpentsdbServiceImpl implements OpentsdbService{

	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(OpentsdbServiceImpl.class);
	
	@Value("${opentsdb.url}")
	String url;
	
	
	@Override
	public ResponseData<Map<String, Object>> importData(
			final ImportDateDTO importDateDTO) throws Exception {
		if (logger.isInfoEnabled()) {
			logger.info("##### 将数据存入OpenTSDB开始 ##### importDateDTO: {}", importDateDTO);
		}
		ResponseData<Map<String, Object>> responseData = new ResponseData<>();
		
		OpentsdbClient client = new OpentsdbClient(url);
		String data = JSON.toJSONString(importDateDTO.getData());
		JSONArray jsonArr = JSONArray.parseArray(data);
		boolean flag = client.putData(jsonArr);
		
		if (logger.isInfoEnabled()) {
			logger.info("##### 将数据存入OpenTSDB结束 ##### flag: {}", flag);
		}
		
		if(flag){
			responseData.setMsg("数据写入成功");
			responseData.setSuccess(true);
			responseData.setData(null);
			return responseData;
		}else{
			responseData.setMsg("数据写入失败");
			responseData.setSuccess(true);
			responseData.setData(null);
			return responseData;
		}
	}


	@Override
	public ResponseData<Map<String, Object>> queryData(
			QueryDateDTO queryDateDTO) throws Exception {
		if (logger.isInfoEnabled()) {
			logger.info("##### 查询OpenTSDB数据开始 ##### queryDateDTO: {}", queryDateDTO);
		}
		ResponseData<Map<String, Object>> responseData = new ResponseData<>();
		Map<String, Object> map = new HashMap<>();
		
		OpentsdbClient client = new OpentsdbClient(url);
		//{\"index\":\"wcgpiTp13u+d420GaDlCncOfNREwZ18/1c9XqR+gfK/CycjhjXW1e4hMGGrdxGh9\",\"term\":[{\"term\":\"deviceid\",\"sign\":\"=\",\"data\":\"A0\"}],\"order\":{\"term\":\"timestamp\",\"order\":\"desc\"},\"page\":{\"start\":200,\"end\":200}}
		String data = JSON.toJSONString(queryDateDTO.getData());
		JSONObject json = JSONObject.parseObject(data);
		JSONArray jsonArr = json.getJSONArray("term");
		String metric = "";
		String startTime = "";
		String endTime = "";
		Map<String, String> tagMap = new HashMap<>();
		for(int i=0; i<jsonArr.size(); i++){
			String keyWord = jsonArr.getJSONObject(i).getString("term");
			if("timestamp".equals(keyWord)){
				startTime = jsonArr.getJSONObject(i).getString("from");
				endTime = jsonArr.getJSONObject(i).getString("to");
			}else if("metric".equals(keyWord)){
				metric = jsonArr.getJSONObject(i).getString("data");
			}else{
				tagMap.put(jsonArr.getJSONObject(i).getString("term"), jsonArr.getJSONObject(i).getString("data"));
			}
		}
        
		if("".equals(startTime) || "".equals(endTime) || "".equals(metric)){
			responseData.setMsg("请求参数缺失，数据查询失败");
			responseData.setSuccess(true);
			responseData.setData(null);
			return responseData;
		}
		
        String resContent = client.getData(metric, tagMap, OpentsdbClient.AGGREGATOR_SUM, "1s", startTime, endTime);
		if (logger.isInfoEnabled()) {
			logger.info("##### 查询OpenTSDB数据结束 #####");
		}
		map.put("data", JSONArray.parseArray(resContent));
		
		responseData.setMsg("数据写入失败");
		responseData.setSuccess(true);
		responseData.setData(map);
		return responseData;
		
	}

}
