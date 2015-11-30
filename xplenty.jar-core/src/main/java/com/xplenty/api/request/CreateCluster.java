package com.xplenty.api.request;

import com.xplenty.api.Xplenty;
import com.xplenty.api.model.Cluster;
import com.xplenty.api.util.Http;
import com.xplenty.api.util.Http.Method;

public class CreateCluster extends ClusterRequest {

	public CreateCluster(Cluster cluster) {
		super(cluster);
	}

	@Override
	public Method getHttpMethod()  {
		return Http.Method.POST;
	}

	@Override
	public String getName()  {
		return Xplenty.Resource.CreateCluster.name;
	}

	@Override
	public String getEndpoint() {
		return Xplenty.Resource.CreateCluster.value;
	}

}