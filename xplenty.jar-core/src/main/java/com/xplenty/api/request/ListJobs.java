/**
 * 
 */
package com.xplenty.api.request;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.ClientResponse;
import com.xplenty.api.Xplenty;
import com.xplenty.api.Xplenty.ClusterStatus;
import com.xplenty.api.exceptions.XplentyAPIException;
import com.xplenty.api.model.Job;
import com.xplenty.api.util.Http;
import com.xplenty.api.util.Http.MediaType;
import com.xplenty.api.util.Http.Method;

import java.util.List;
import java.util.Properties;

/**
 * @author Yuriy Kovalek
 *
 */
public class ListJobs extends AbstractParametrizedRequest<List<Job>> {
	
	public ListJobs(Properties params) {
        super(params, true);
        validateParameters(params);
	}

	private void validateParameters(Properties params) {
		if (	params.containsKey(PARAMETER_STATUS)
				&& !(params.get(PARAMETER_STATUS) instanceof ClusterStatus) 
				&& !(params.get(PARAMETER_STATUS) instanceof String && "all".equals(params.get(PARAMETER_STATUS)))
			)
			throw new XplentyAPIException("Invalid 'status' parameter");
	}

	@Override
	public String getName() {
		return Xplenty.Resource.Jobs.name;
	}

	@Override
	public Method getHttpMethod() {
		return Http.Method.GET;
	}

	@Override
	public MediaType getResponseType() {
		return Http.MediaType.JSON;
	}

    @Override
    protected String getEndpointRoot() {
        return Xplenty.Resource.Jobs.value;
    }

    @Override
	public List<Job> getResponse(ClientResponse response) {
		String json = response.getEntity(String.class);
		try {
			return new ObjectMapper().readValue(json, new TypeReference<List<Job>>() {});
		} catch (Exception e) {
			throw new XplentyAPIException(getName() + ": error parsing response object", e);
		}
	}

	@Override
	public boolean hasBody() {
		return false;
	}

	@Override
	public List<Job> getBody() {
		return null;
	}

}
