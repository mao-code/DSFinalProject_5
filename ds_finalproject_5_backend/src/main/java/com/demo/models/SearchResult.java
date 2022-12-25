package com.demo.models;

import java.util.ArrayList;

public class SearchResult implements Comparable<SearchResult>{
	public String url;
	public String displayName;
	public double score;
	public String description;
	public ArrayList<WebNode> subSites;
	public ArrayList<String> relativeKeywords;
	
	public SearchResult(String url, String displayName, double score, String description)
	{
		this.url = url;
		this.displayName = displayName;
		this.score = score;
		this.description = description;
	}
	
	public SearchResult(WebNode node)
	{
		this.url = node.getPage().url;
		this.displayName = node.getPage().name;
		this.score = node.getNodeScore();
		this.description = node.getPage().description;
		this.subSites = node.getChildren();
	}
	
	public SearchResult(WebNode node, ArrayList<String> relativeKeywords)
	{
		this.url = node.getPage().url;
		this.displayName = node.getPage().name;
		this.score = node.getNodeScore();
		this.description = node.getPage().description;
		this.subSites = node.getChildren();
		this.relativeKeywords = relativeKeywords;
	}

	@Override
	public int compareTo(SearchResult o) {
		if(this.score > o.score) {
			return -1;
		}else if(this.score < o.score) {
			return 1;
		}else {
			return 0;
		}
	}
}
