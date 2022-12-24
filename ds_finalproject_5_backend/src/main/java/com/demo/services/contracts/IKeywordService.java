package com.demo.services.contracts;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.demo.models.Keyword;

public interface IKeywordService {
	int countKeywords(String content, String keyword);
	ArrayList<String> deriveRelativeKeywords(String content, ArrayList<Keyword> keywords);
}
