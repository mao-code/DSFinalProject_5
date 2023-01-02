package com.demo.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.models.Keyword;
import com.demo.models.ResponseData;
import com.demo.models.SearchResult;
import com.demo.models.SearchResultList;
import com.demo.models.WebNode;
import com.demo.models.WebPage;
import com.demo.models.WebTree;
import com.demo.services.contracts.IHTMLService;
import com.demo.services.contracts.IKeywordService;
import com.demo.services.contracts.IWebNodeService;
import com.demo.services.contracts.IWebPageService;
import com.demo.services.contracts.IWebTreeService;

@RestController
@RequestMapping("search")
public class SearchController {
	
	@Autowired
	@Qualifier("KeywordService")
	private IKeywordService keywordService;

	@Autowired
	@Qualifier("HTMLService")
	private IHTMLService htmlService;

	@Autowired
	@Qualifier("WebTreeService")
	private IWebTreeService webTreeService;

	@Autowired
	@Qualifier("WebNodeService")
	private IWebNodeService webNodeService;
	
	@Autowired
	@Qualifier("WebPageService")
	private IWebPageService webPageService;

	private ArrayList<Keyword> keywords;

	public SearchController()
	{
		this.keywords = new ArrayList<Keyword>();
		keywords.add(new Keyword("電影", 10));
		keywords.add(new Keyword("影集", 10));
		keywords.add(new Keyword("影評", 8));
		keywords.add(new Keyword("Netflix", 7));
		keywords.add(new Keyword("Disney+", 7));
		keywords.add(new Keyword("Disney Plus", 7));
		keywords.add(new Keyword("電影院", 5.5));
		keywords.add(new Keyword("場次", 5.5));
		keywords.add(new Keyword("票價", 5));
		
		//new added
		keywords.add(new Keyword("电影", 10));
		keywords.add(new Keyword("movie", 10));
		keywords.add(new Keyword("改編", 4.5));
		keywords.add(new Keyword("觀賞", 4.5));
		keywords.add(new Keyword("评分", 4));
		keywords.add(new Keyword("評分", 4));
		keywords.add(new Keyword("小說", 4));	
	}
	
	@GetMapping("/movie/search/default")
	public ResponseEntity<ResponseData<Object>> search()
	{
		return ResponseEntity.ok().body(new ResponseData<Object>(200, true, "search successfully!"));
	}
	/*
	 * Recommend search keyword
	 * 1. 計程車司機
	 * 2. 億萬富翁
	 * 3. 二戰
	 * */
	@GetMapping("/movie/search/{keyword}/{count}/{skip}")
	public ResponseEntity<ResponseData<Object>> search(@PathVariable("keyword") String keyword, @PathVariable("count") int count, @PathVariable("skip") int skip)
	{
		SearchResultList resList = new SearchResultList();
		HashMap<String, String> googleResults = new HashMap<String, String>();
		String parseKeyword = keyword.indexOf("電影")==-1 ? keyword+" 電影" : keyword;
		
		try {
			HashMap<String, String> originalGoogleResults = this.htmlService.searchGoogle(keyword, count, skip);
			googleResults = this.htmlService.searchGoogle(parseKeyword, count, skip);
			resList.setOriginalGoogleResults(originalGoogleResults);
			for(Map.Entry<String, String> googleResult : googleResults.entrySet())
			{				
				String[] value = googleResult.getValue().split(":::");
				
				String url = value[0];
				String description = value.length > 1 ? value[1] : "";	
				
				WebPage page = new WebPage(googleResult.getKey(), url, parseKeyword, description);
				try {
					//bad request (may be the site side error)
					//just skip it
					
					// Build WebTree
					WebTree tree = this.webTreeService.buildTree(new WebNode(page));
					
					// Set Score
					this.webTreeService.setPostOrderScore(tree, keywords);
					
					ArrayList<String> relativeKeywordsFromDescription = this.keywordService.deriveRelativeKeywordsFromDescription(description, keywords);
					
					resList.addResult(new SearchResult(tree.getRoot(), relativeKeywordsFromDescription));
					
				} catch (IOException e) {
					System.out.println(e.getLocalizedMessage()+page.name+", url: "+page.url);
					continue;
				} 
			}
			
			// Ranking
			resList.sort(); 
			
			//給前後對比
			return ResponseEntity.ok().body(
				new ResponseData<>(
					200, 
					true, 
					"search successfully! filtered results count: "+resList.getResultCount(), 
					resList.getResult()
				)
			);
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body(new ResponseData<>(500, false, "Internal Server Error!", e.getMessage()));
		}
	}
	
	/*
	 * Flow: (Google search) -> no use web tree because it is too hard to handle
	 * User: send keyword search
	 * Backend: use keyword to search results from google
	 * Backend: each result is a WebPage
	 * Backend: And then rank them by score
	 * 
	 * Flow: (Default)
	 * User: None
	 * Backend: each input is a WebTree
	 * Backend: For one of the WebTrees, set postorder score
	 * Backend: And then rank the sites
	 * 
	 * */
	
}
