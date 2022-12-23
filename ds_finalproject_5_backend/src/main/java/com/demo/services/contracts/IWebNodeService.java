package com.demo.services.contracts;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import com.demo.models.Keyword;
import com.demo.models.WebNode;

public interface IWebNodeService {
	public void setNodeScore(WebNode startNode, ArrayList<Keyword> keywords) throws IOException, URISyntaxException;
}
