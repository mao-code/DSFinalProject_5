package com.demo.models;

import java.util.ArrayList;

public class WebNode {
	public WebPage webPage;
	public WebNode parent;
	public ArrayList<WebNode> children;
	public double nodeScore;
	
	public WebNode(WebPage webPage)
	{
		this.webPage = webPage;
	}
	
	public void setParent(WebNode parent)
	{
		this.parent = parent;
	}
	public void addChild(WebNode child)
	{
		this.children.add(child);
	}
}
