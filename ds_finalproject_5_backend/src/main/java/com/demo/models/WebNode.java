package com.demo.models;

import java.util.ArrayList;

public class WebNode {
	private WebPage webPage;
	private WebNode parent;
	private ArrayList<WebNode> children;
	private double nodeScore;
	
	public WebNode(WebPage webPage)
	{
		this.webPage = webPage;
		this.parent = null;
		this.children = new ArrayList<WebNode>();
	}
	
	public ArrayList<WebNode> getChildren()
	{
		return this.children;
	}
	public boolean hasChildren()
	{
		return this.children.size() > 0;
	}
	public WebPage getPage()
	{
		return this.webPage;
	}
	public double getNodeScore()
	{
		return this.nodeScore;
	}
	
	public void setParent(WebNode parent)
	{
		this.parent = parent;
	}
	public void addChild(WebNode child)
	{
		this.children.add(child);
	}
	public void setNodeScore(double score)
	{
		this.nodeScore = score;
	}
	
	public void addNodeScore(double score)
	{
		this.nodeScore += score;
	}
}
