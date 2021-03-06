/**
 * 
 */
package com.xplenty.api.request;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.ClientResponse;
import com.xplenty.api.Xplenty;
import com.xplenty.api.exceptions.XplentyAPIException;
import com.xplenty.api.model.Cluster;
import com.xplenty.api.model.Schedule;
import com.xplenty.api.util.Http.MediaType;
import com.xplenty.api.util.Http.Method;

import java.util.List;
import java.util.Properties;


/**
 * Request for retrieval of all available schedules
 * Author: Xardas
 * Date: 16.12.15
 * Time: 18:08
 */
public class ListSchedules extends AbstractParametrizedRequest<List<Schedule>> {


	public ListSchedules(Properties params) {
		super(params, true);
        validateParameters(params);
	}


	private void validateParameters(Properties params) {
        if (params.containsKey(PARAMETER_STATUS)
                && !(params.get(PARAMETER_STATUS) instanceof Xplenty.ScheduleStatus)) {
            throw new XplentyAPIException("Invalid 'status' parameter");
        }
	}

	@Override
	public Method getHttpMethod() {
		return Method.GET;
	}

	@Override
	public MediaType getResponseType() {
		return MediaType.JSON;
	}



	@Override
	public List<Schedule> getResponse(ClientResponse response) {
		String json = response.getEntity(String.class);
		try {
			return new ObjectMapper().readValue(json, new TypeReference<List<Schedule>>() {});
		} catch (Exception e) {
			throw new XplentyAPIException(getName() + ": error parsing response object", e);
		}
	}

	@Override
	public String getName() {
		return Xplenty.Resource.Schedules.name;
	}

	@Override
	public boolean hasBody() {
		return false;
	}

	@Override
	public List<Cluster> getBody() {
		return null;
	}

    @Override
    protected String getEndpointRoot() {
        return Xplenty.Resource.Schedules.value;
    }
}
