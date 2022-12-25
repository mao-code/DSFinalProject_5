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
	public ArrayList<String> deriveRelativeKeywordsFromDescription(String description, ArrayList<Keyword> keywords) {
		// use DP to find LCS of keywords and website's description
		ArrayList<String> res = new ArrayList<String>();
				
		for(Keyword keyword : keywords) 
		{
			String[][] dpTable = new String[description.length()+1][keyword.name.length()+1];
			for(int i=0; i<description.length()+1; i++)
			{
				dpTable[i][0]="";
			}
			for(int i=0; i<keyword.name.length()+1; i++)
			{
				dpTable[0][i]="";
			}
			
			for(int i=0; i<description.length(); i++)
			{
				for(int j=0; j<keyword.name.length(); j++)
				{
					if(description.charAt(i) == keyword.name.charAt(j)) {
						dpTable[i+1][j+1] = dpTable[i][j]+keyword.name.charAt(j);
					}else {
						dpTable[i+1][j+1] = dpTable[i][j+1].length()>dpTable[i+1][j].length() ? dpTable[i][j+1] : dpTable[i+1][j];
					}
				}
			}
			if(
				!dpTable[description.length()][keyword.name.length()].strip().isEmpty() 
				&& !res.contains(dpTable[description.length()][keyword.name.length()])
			) {
				res.add(dpTable[description.length()][keyword.name.length()]+"=>"+keyword.name);
			}
		}
		
		return res;
	}


}
