package com.stackroute.contentanalysingservice.service;

import com.stackroute.contentanalysingservice.model.ExternalApiResponse;
import com.stackroute.contentanalysingservice.model.Response;

import java.util.List;

public interface PageService {


    public List<Response> getTheData(ExternalApiResponse externalApiResponse);
}


