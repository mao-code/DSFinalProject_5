package com.demo.models;

public class WebPage {
	public String url;
	public String searchKeyword;
	public String name;
	public double score;
	
	public WebPage(String name, String url, String searchKeyword)
	{
		this.url = url;
		this.searchKeyword = searchKeyword;
		this.name = name;
	}
}
