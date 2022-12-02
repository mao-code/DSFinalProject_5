package com.demo.services.contracts;

import java.util.ArrayList;

import com.demo.models.Keyword;
import com.demo.models.WebNode;
import com.demo.models.WebTree;

public interface IWebTreeService {
	void buildTree(WebNode startNode);
	void setPostOrderScore(WebNode startNode, ArrayList<Keyword> keywords);
}
