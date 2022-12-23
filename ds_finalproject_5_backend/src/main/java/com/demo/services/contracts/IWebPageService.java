package com.demo.services.contracts;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

import com.demo.models.Keyword;
import com.demo.models.WebPage;

public interface IWebPageService {
	void setScore(WebPage webPage, ArrayList<Keyword> keywords) throws IOException, URISyntaxException;
	void addScore(WebPage page, double score);
}
