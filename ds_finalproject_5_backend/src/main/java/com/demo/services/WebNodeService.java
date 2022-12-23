package com.demo.services;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.demo.models.Keyword;
import com.demo.models.WebNode;
import com.demo.services.contracts.IWebNodeService;
import com.demo.services.contracts.IWebPageService;

@Service
@Qualifier("WebNodeService")
public class WebNodeService implements IWebNodeService{

	@Autowired
	@Qualifier("WebPageService")
	private IWebPageService webPageService;
	
	@Override
	public void setNodeScore(WebNode startNode, ArrayList<Keyword> keywords) throws IOException, URISyntaxException {
		// webpage's score (based on keyword)
		this.webPageService.setScore(startNode.getPage(), keywords);
	
		//webnode's score (this page add sub page)
		startNode.setNodeScore(startNode.getPage().score);
		for(WebNode child : startNode.getChildren())
		{
			startNode.addNodeScore(child.getNodeScore());
		}
	}

}
