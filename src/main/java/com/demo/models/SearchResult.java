package com.demo.models;

public class SearchResult implements Comparable<SearchResult>{
	public String url;
	public String displayName;
	public double score;
	
	public SearchResult(String url, String displayName, double score)
	{
		this.url = url;
		this.displayName = displayName;
		this.score = score;
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
