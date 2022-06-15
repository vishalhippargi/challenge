package com.sample.homebrewadapter.model;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Vishal
 *
 */
public class Formula {
	
	private String description;
	private String version;
	private List<String> dependencies;
	private LocalDate generated_date;
	
	
	public Formula() {
		super();
	}
	
	public Formula(String description, String version, List<String> dependencies, LocalDate generated_date) {
		super();
		this.description = description;
		this.version = version;
		this.dependencies = dependencies;
		this.generated_date = generated_date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public List<String> getDependencies() {
		return dependencies;
	}
	public void setDependencies(List<String> dependencies) {
		this.dependencies = dependencies;
	}
	public LocalDate getGenerated_date() {
		return generated_date;
	}
	public void setGenerated_date(LocalDate generated_date) {
		this.generated_date = generated_date;
	}
	

}
