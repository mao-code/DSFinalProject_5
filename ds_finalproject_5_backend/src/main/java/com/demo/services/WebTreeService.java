package com.demo.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.demo.models.Keyword;
import com.demo.models.WebNode;
import com.demo.models.WebPage;
import com.demo.models.WebTree;
import com.demo.services.contracts.IWebPageService;
import com.demo.services.contracts.IWebTreeService;

@Service
@Qualifier("WebTreeService")
public class WebTreeService implements IWebTreeService{

	@Autowired
	@Qualifier("WebPageService")
	private IWebPageService webPageService;
	
	
	//TODO: only in default search, if based on google search will be too difficult
	@Override
	public void buildTree(WebNode startNode) {
		
	}

	@Override
	public void setPostOrderScore(WebNode startNode, ArrayList<Keyword> keywords) {
		// TODO Auto-generated method stub
		
	}

}
