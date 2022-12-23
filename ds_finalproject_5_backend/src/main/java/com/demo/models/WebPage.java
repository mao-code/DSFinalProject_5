package com.demo.models;

public class WebPage {
	public String url;
	public String searchKeyword;
	public String name;
	public double score;
	public String description;
	
	public WebPage(String name, String url, String searchKeyword)
	{
		this.url = url;
		this.searchKeyword = searchKeyword;
		this.name = name;
	}
	
	public WebPage(String name, String url, String searchKeyword, String description)
	{
		this.url = url;
		this.searchKeyword = searchKeyword;
		this.name = name;
		this.description = description;
	}
	
	public WebPage(String url)
	{
		this.url = url;
	}
}
