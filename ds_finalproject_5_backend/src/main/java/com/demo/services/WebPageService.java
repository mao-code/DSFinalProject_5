package com.demo.services;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.demo.models.Keyword;
import com.demo.models.WebPage;
import com.demo.services.contracts.IHTMLService;
import com.demo.services.contracts.IKeywordService;
import com.demo.services.contracts.IWebPageService;

@Service
@Qualifier("WebPageService")
public class WebPageService implements IWebPageService{
	
	@Autowired
	@Qualifier("KeywordService")
	private IKeywordService keywordService;
	
	@Autowired
	@Qualifier("HTMLService")
	private IHTMLService htmlService;
	
	@Override
	public void setScore(WebPage webPage, ArrayList<Keyword> keywords) throws IOException, URISyntaxException {		
		String content = this.htmlService.fetchContentBySite(webPage.url); 
		double score = 0;
		for(Keyword k : keywords)
		{
		    int count = this.keywordService.countKeywords(content, k.name);
			score += count * k.weight;
		}
		webPage.score = score;
	}

	@Override
	public void addScore(WebPage page, double score) {
		page.score += score;
	}	
}
