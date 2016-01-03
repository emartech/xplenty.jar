/**
 * 
 */
package com.xplenty.api.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.ClientResponse.Status;
import com.sun.jersey.core.header.InBoundHeaders;
import com.xplenty.api.Xplenty;
import com.xplenty.api.exceptions.XplentyAPIException;
import com.xplenty.api.model.Cluster;
import com.xplenty.api.model.ClusterTest;
import com.xplenty.api.util.Http;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @author Yuriy Kovalek
 *
 */
public class TerminateClusterTest extends TestCase {
	@Test
	public void testIntegrity() {
		TerminateCluster tc = new TerminateCluster(1L);
		
		assertEquals(Xplenty.Resource.TerminateCluster.format(Long.toString(1)), tc.getEndpoint());
		assertEquals(Xplenty.Resource.TerminateCluster.name, tc.getName());
		assertEquals(Http.Method.DELETE, tc.getHttpMethod());
		assertEquals(Http.MediaType.JSON, tc.getResponseType());
		assertFalse(tc.hasBody());
		assertNull(tc.getBody());
	}

	@Test
	public void testValidResponseHandling() throws JsonProcessingException, UnsupportedEncodingException {
		TerminateCluster tc = new TerminateCluster(1L);
		Cluster c = ClusterTest.createMockCluster(new Date());
		
		String json = new ObjectMapper().writeValueAsString(c);
		c = tc.getResponse(new ClientResponse(Status.OK.getStatusCode(),
								new InBoundHeaders(), 
								new ByteArrayInputStream(json.getBytes("UTF-8")),
								Client.create().getMessageBodyWorkers()));
		assertNotNull(c);
	}
	
	@Test
	public void testInvalidResponseHandling() throws JsonProcessingException, UnsupportedEncodingException {
		TerminateCluster tc = new TerminateCluster(1L);
		Cluster c = ClusterTest.createMockCluster(new Date());
		
		String json = new ObjectMapper().writeValueAsString(c).replace("2", "two");
		try {
			c = tc.getResponse(new ClientResponse(Status.OK.getStatusCode(),
									new InBoundHeaders(), 
									new ByteArrayInputStream(json.getBytes("UTF-8")),
									Client.create().getMessageBodyWorkers()));
			assertTrue(false);
		} catch (XplentyAPIException e) {
			assertEquals(Xplenty.Resource.TerminateCluster.name + ": error parsing response object", e.getMessage());
		} 
	}
}
