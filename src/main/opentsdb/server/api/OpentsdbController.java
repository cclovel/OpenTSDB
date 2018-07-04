package com.ygsoft.opentsdb.server.api;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ygsoft.opentsdb.server.dto.ImportDateDTO;
import com.ygsoft.opentsdb.server.dto.QueryDateDTO;
import com.ygsoft.opentsdb.server.dto.ResponseData;
import com.ygsoft.opentsdb.server.service.OpentsdbService;



/**
 * opentsdb Restful Api
 * @author yinlilan
 *
 */
@RestController
@RequestMapping("/api/opentsdb")
public class OpentsdbController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OpentsdbController.class);
	
	@Autowired
	OpentsdbService opentsdbService;
	
	/**
	 * 数据写入
	 * @return
	 */
    @PostMapping("/importData")
	public ResponseData<Map<String, Object>> importData(@RequestBody final ImportDateDTO importDateDTO){
    	ResponseData<Map<String, Object>> responseData = new ResponseData<>("写入数据失败");
        try {
        	responseData = opentsdbService.importData(importDateDTO);
        }catch(Exception e){
        	LOGGER.error("##### 调数据写入接口异常 #####", e);
        	responseData.setMsg("数据写入异常");
			responseData.setSuccess(true);
			responseData.setData(null);
			return responseData;
        }
    	return responseData;
	}
    
    
    @PostMapping("/queryData")
    public ResponseData<Map<String, Object>> queryData(@RequestBody final QueryDateDTO queryDateDTO){
    	ResponseData<Map<String, Object>> responseData = new ResponseData<>("查询数据失败");
        try {
        	responseData = opentsdbService.queryData(queryDateDTO);
        }catch(Exception e){
        	LOGGER.error("##### 调数据查询接口异常 #####", e);
        	responseData.setMsg("数据查询异常");
			responseData.setSuccess(true);
			responseData.setData(null);
			return responseData;
        }
    	return responseData;
    }

}
