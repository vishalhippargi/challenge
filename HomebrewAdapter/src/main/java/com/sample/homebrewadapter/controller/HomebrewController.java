package com.sample.homebrewadapter.controller;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.sample.homebrewadapter.model.Formula;
import com.sample.homebrewadapter.service.HomeBrewService;


@RestController
public class HomebrewController {
	
	private Logger log = LogManager.getLogger();
	
	@Autowired
	HomeBrewService homebrewService;
	
	@GetMapping("/formula")
	public Formula getFormulaDetails(@RequestParam(value = "name") String name) {
		log.debug("Entered getFormulaDetails");
		Optional<Formula> formula = Optional.ofNullable(homebrewService.getFormulaDataByName(name));
		 if (formula.isPresent()) {
	           return formula.get();
	        }
		  throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Formula not Found");
	}

}
