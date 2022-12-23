package com.demo.services;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.demo.models.Keyword;
import com.demo.models.WebNode;
import com.demo.models.WebPage;
import com.demo.models.WebTree;
import com.demo.services.contracts.IWebNodeService;
import com.demo.services.contracts.IWebPageService;
import com.demo.services.contracts.IWebTreeService;

@Service
@Qualifier("WebTreeService")
public class WebTreeService implements IWebTreeService{

	@Autowired
	@Qualifier("WebPageService")
	private IWebPageService webPageService;
	
	@Autowired
	@Qualifier("WebNodeService")
	private IWebNodeService webNodeService;
	
	//TODO: only in default search, if based on google search will be too difficult
	//TODO: So we just catch some url(3 of them) in "a" tag 
	@Override
	public WebTree buildTree(WebNode startNode) throws IOException {
		// 1. get 3 sub url that contains in "a" tag, and satisfied the http format
		String rootUrl = startNode.getPage().url;
		ArrayList<String> validUrls = new ArrayList<String>();
		
		Connection conn = Jsoup.connect(rootUrl);
		conn.timeout(10000);
		Document doc = conn.get();
		Elements aTags = doc.select("a");
		for(Element aTag : aTags) {
			String tryUrl = aTag.attr("href");
			if(tryUrl.startsWith("http")) {
				validUrls.add(tryUrl);
			}
			if(validUrls.size()==3) {
				break;
			}
		}
		
		// 2. add WebNode under the tree
		for(String url : validUrls)
		{
			WebPage page = new WebPage(url);
			WebNode node = new WebNode(page);
			node.setParent(startNode);
			startNode.addChild(node);
		}
		
		WebTree tree = new WebTree(startNode);
		
		return tree;
	}

	@Override
	public void setPostOrderScore(WebTree tree, ArrayList<Keyword> keywords) throws IOException, URISyntaxException {
		// TODO Auto-generated method stub
		WebNode root = tree.getRoot();
		this.setPostOrderScore(root, keywords);
	}
	
	public void setPostOrderScore(WebNode startNode, ArrayList<Keyword> keywords) throws IOException, URISyntaxException
	{
		for(WebNode node : startNode.getChildren())
		{
			this.setPostOrderScore(node, keywords);
		}
		this.webNodeService.setNodeScore(startNode, keywords);;
	}

}
