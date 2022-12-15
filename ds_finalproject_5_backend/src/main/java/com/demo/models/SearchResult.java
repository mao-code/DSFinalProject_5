package com.demo.models;

public class SearchResult implements Comparable<SearchResult>{
	public String url;
	public String displayName;
	public double score;
	public String description;
	
	public SearchResult(String url, String displayName, double score, String description)
	{
		this.url = url;
		this.displayName = displayName;
		this.score = score;
		this.description = description;
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
