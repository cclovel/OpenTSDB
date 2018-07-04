package com.ygsoft.opentsdb.client;

import java.io.IOException;

import com.ygsoft.opentsdb.client.builder.MetricBuilder;
import com.ygsoft.opentsdb.client.request.QueryBuilder;
import com.ygsoft.opentsdb.client.response.Response;
import com.ygsoft.opentsdb.client.response.SimpleHttpResponse;

public interface HttpClient extends Client {

	public Response pushMetrics(MetricBuilder builder,
			ExpectResponse exceptResponse) throws IOException;

	public SimpleHttpResponse pushQueries(QueryBuilder builder,
                                          ExpectResponse exceptResponse) throws IOException;
}
