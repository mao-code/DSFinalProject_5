package com.demo.services.contracts;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

public interface IHTMLService {
	String fetchContentBySite(String url) throws IOException, URISyntaxException;
	String fetchGoogleResultContent(String keyword, int count) throws IOException;
	HashMap<String, String> searchGoogle(String keyword, int count) throws IOException;
}
