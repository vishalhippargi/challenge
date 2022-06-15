package com.sample.homebrewadapter.service;


import java.time.LocalDate;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.sample.homebrewadapter.error.ErrorHandler;
import com.sample.homebrewadapter.model.Formula;

/**
 * @author Vishal
 *
 */
@Service
@ComponentScan
public class HomeBrewService {
	
	private Logger log = LogManager.getLogger();
	
	private final String FORMULA_URL ="https://formulae.brew.sh/api/formula";
	
	@Bean
	public RestTemplate restTemplate() {
	       RestTemplate restTemplate = new  RestTemplate();
	       restTemplate.setErrorHandler(new ErrorHandler());
	       return restTemplate;
	}
	
	

	/**
	 * @param name
	 * @return Formula
	 */
	public Formula getFormulaDataByName(String name){
		
		HttpHeaders  headers = new HttpHeaders();
		MultiValueMap<String, String> map = new  LinkedMultiValueMap<>();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<?> request = new HttpEntity<>(map,headers);
		log.info(String.format("%s/%s%s", FORMULA_URL,name,".json"));
		Formula formulaData = getFormulaData(restTemplate().exchange(String.format("%s/%s%s", FORMULA_URL,name,".json"), HttpMethod.GET, request, String.class, map));
		return formulaData;
		
	}
	
	/**
	 * @param responseEntity
	 * @return Formula
	 */
	public Formula getFormulaData(ResponseEntity<String> responseEntity){
		Formula formula = null;
		if(responseEntity.getStatusCode().equals(HttpStatus.OK)) {
			log.debug(()->"Response Entity :"+responseEntity.getBody());
		formula =new Formula();
		DocumentContext documentContext = JsonPath.parse(responseEntity.getBody());
		formula.setDescription(documentContext.read("$.desc"));
		String read = documentContext.read("$.generated_date");
		formula.setGenerated_date(LocalDate.parse(read));
		formula.setVersion(documentContext.read("$.versions.stable"));
		formula.setDependencies(documentContext.read("$.dependencies"));
		}
		return formula;
		
	}
		
}

