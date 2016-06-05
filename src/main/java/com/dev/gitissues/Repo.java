package com.dev.gitissues;

import javax.validation.constraints.Size;
//Basic getters and setters
public class Repo {

	@Size(min=4, max=35)
	private String url;
	
	
 
	public String geturl() {
		return url;
	}
 
	public void seturl(String url) {
		this.url = url;
	}
 
	

}
