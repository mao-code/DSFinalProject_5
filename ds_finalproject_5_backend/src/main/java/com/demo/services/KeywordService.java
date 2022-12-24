package com.demo.services;

import java.lang.annotation.Annotation;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.demo.models.Keyword;
import com.demo.services.contracts.IKeywordService;

@Service
@Qualifier("KeywordService")
public class KeywordService implements IKeywordService{

	@Override
	public int countKeywords(String content, String keyword) {
		if (content == null){
		    return 0;
		}
		
		//To do a case-insensitive search, we turn the whole content and keyword into upper-case:
		content = content.toUpperCase();
		keyword = keyword.toUpperCase();
	
		int res = 0;
		int fromIdx = 0;
		int found = -1;
	
		while ((found = content.indexOf(keyword, fromIdx)) != -1){
		    res++;
		    fromIdx = found + keyword.length();
		}
		
		return res;
	}

	@Override
	public ArrayList<String> deriveRelativeKeywords(String content, ArrayList<Keyword> keywords) {
		
		
		return null;
	}
	
}
