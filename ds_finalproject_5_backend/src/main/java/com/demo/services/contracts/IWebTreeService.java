package com.demo.services.contracts;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import com.demo.models.Keyword;
import com.demo.models.WebNode;
import com.demo.models.WebTree;

public interface IWebTreeService {
	WebTree buildTree(WebNode startNode) throws IOException;
	void setPostOrderScore(WebTree tree, ArrayList<Keyword> keywords)  throws IOException, URISyntaxException;
}
