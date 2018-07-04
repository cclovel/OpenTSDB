package com.ygsoft.opentsdb.server.service;

import java.util.Map;

import com.ygsoft.opentsdb.server.dto.ImportDateDTO;
import com.ygsoft.opentsdb.server.dto.QueryDateDTO;
import com.ygsoft.opentsdb.server.dto.ResponseData;

public interface OpentsdbService {

	ResponseData<Map<String, Object>> importData(final ImportDateDTO importDateDTO) throws Exception;
	
	ResponseData<Map<String, Object>> queryData(final QueryDateDTO queryDateDTO) throws Exception;
}
