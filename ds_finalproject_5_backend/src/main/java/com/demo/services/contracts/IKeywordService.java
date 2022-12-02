package com.demo.services.contracts;

import org.springframework.stereotype.Service;

public interface IKeywordService {
	int countKeywords(String content, String keyword);
}
