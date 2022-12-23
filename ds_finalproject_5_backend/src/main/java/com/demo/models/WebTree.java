package com.demo.models;

public class WebTree {
	private WebNode root;
	private double score;
	
	public WebTree(WebNode root)
	{
		this.root = root;
		this.score = 0;
	}
	
	public WebNode getRoot()
	{
		return this.root;
	}
	public void setScore(double score)
	{
		this.score = score;
	}
}
