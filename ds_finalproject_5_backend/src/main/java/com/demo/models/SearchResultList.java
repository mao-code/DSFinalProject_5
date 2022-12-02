package com.demo.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class SearchResultList {
	private ArrayList<SearchResult> results;
	private HashMap<String, String> originalGoogleResults;
	
	public SearchResultList()
	{
		this.results = new ArrayList<SearchResult>();
		this.originalGoogleResults = new HashMap<String, String>();
	}
	
	public Object getResult()
	{
		return new Object() {
			public final Object orginalGoogleResults = originalGoogleResults;
			public final  Object myResult = results;
		};
	}
	
	public void addResult(SearchResult res)
	{
		this.results.add(res);
	}
	
	public void sort()
	{
		Collections.sort(this.results);
	}
	
	public int getResultCount()
	{
		return this.results.size();
	}
	
	public void setOriginalGoogleResults(HashMap<String, String> org)
	{
		this.originalGoogleResults = org;
	}
}
