package br.com.pedalPromoService.crawler;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class Crawler implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public void getContent(List<String> links) {
		
//		try {
//			ProxyConfig config = new ProxyConfig();
//			config.defineJVMVariables("proxy-1dn.mb", "6060", "10132153700", "d2882a", "");
//			config.changeAuthenticator();
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
		
		for (String x : links) {
			Document document;
			try {
				document = Jsoup.connect(x).get();
				Elements masthead = document.select("ul.vitrineProdutos > li");
				//masthead.get(1);
				//Elements discount = document.select("div.discount");
				//discount.get(0);
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		}
	}

	
}
