package com.demo.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.HashMap;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.demo.services.contracts.IHTMLService;

@Service
@Qualifier("HTMLService")
public class HTMLService implements IHTMLService{
	@Override
	public String fetchContentBySite(String url) throws IOException, URISyntaxException {
		Connection conn = Jsoup.connect(url); //decode base64 encoding character
		conn.timeout(10000);
		Document doc = conn.get();
		
		return doc.toString();
	}
	
	@Override
	public String fetchGoogleResultContent(String keyword, int count) throws IOException {
		String url = "https://www.google.com/search?q="+keyword+"&ie=UTF-8&num="+count;
	
		Connection conn = Jsoup.connect(url); //decode base64 encoding character
		conn.timeout(10000);
		conn.header("User-agent", "Mozilla/5.0");	
		
		Document doc = conn.get();
		
		return doc.toString();

	}

	@Override
	public HashMap<String, String> searchGoogle(String keyword, int count) throws IOException {
		String content = this.fetchGoogleResultContent(keyword, count);
				
		HashMap<String, String> res = new HashMap<String, String>();

		//using Jsoup analyze html string
		Document doc = Jsoup.parse(content);
		
		//select particular element(tag) which you want 
		Elements lis = doc.select("div");
		lis = lis.select(".kCrYT"); // google 搜尋後結果中的class
		
		for(Element li : lis)
		{
			try 
			{
				String citeUrl = li.select("a").get(0).attr("href");
				
				//修正奇怪的回傳URL, 因為爬回來的結果不完全和搜尋結果一樣
				// parse guide: https://system.camp/tutorial/searching-google-results-and-parsing-in-java/
				// 把抓回來的content print出來看看 分析
				citeUrl = "http"+citeUrl.split("http")[1]; 
				citeUrl = citeUrl.split("&sa").length > 0 ? citeUrl.split("&sa")[0] : citeUrl ;
				citeUrl = citeUrl.replace("%25", "%");
				
				String title = li.select("a").get(0).select(".vvjwJb").text();
				
				if(title.equals("")) 
				{
					continue;
				}
				
				
				//put title and pair into HashMap
				res.put(title, citeUrl);

			} catch (IndexOutOfBoundsException e) 
			{
				//e.printStackTrace();
			}
		}
		return res;
	}
}
