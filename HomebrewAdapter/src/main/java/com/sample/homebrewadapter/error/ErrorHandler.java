package com.sample.homebrewadapter.error;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.server.ResponseStatusException;

public class ErrorHandler  extends DefaultResponseErrorHandler{
	
	  @Override
	  public void handleError(ClientHttpResponse response) throws IOException {
		  new ResponseStatusException(HttpStatus.NOT_FOUND, "Formula not Found");
	  }
	

}
