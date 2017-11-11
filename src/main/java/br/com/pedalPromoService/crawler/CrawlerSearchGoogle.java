package br.com.pedalPromoService.crawler;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pedalPromoService.service.LinkService;

@RestController
@RequestMapping(CrawlerSearchGoogle.QUERYSEARCH)
public class CrawlerSearchGoogle implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public static final String QUERYSEARCH = "api/querySearch/";
	
	@Autowired
	private Crawler crawler;
	
	@Autowired
	private LinkService linkService;
	
	private static Matcher matcher;
    private static final String DOMAIN_NAME_PATTERN
            = "([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,15}";
    private static Pattern patrn = Pattern.compile(DOMAIN_NAME_PATTERN);

    
    
    public static String getDomainName(String url) {

        String domainName = "";
        matcher = patrn.matcher(url);

        if (matcher.find()) {
            domainName = matcher.group(0).toLowerCase().trim();
        }

        return domainName;
    }

    @RequestMapping(value = "{query}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<String> getListLink(@PathVariable("query") final String query) throws IOException {
        
    	String queryS = query.replace(" ", "+");

        String url = "https://www.google.com/search?q=" + queryS + "&num=10";

        Document doc = Jsoup
                .connect(url)
                .userAgent("Jsoup client")
                .timeout(5000).get();

        Elements links = doc.select("a[href]");

        Set<String> result = new HashSet<>();

        for (Element link : links) {

            String attr1 = link.attr("href");
            String attr2 = link.attr("class");

            if (!attr2.startsWith("_Zkb") && attr1.startsWith("/url?q=")) {

                result.add(attr1);
            }
        }
        List<String> list = new ArrayList<String>();
        for (String el : result) {
        	String[] l = el.split("q=");
        	list.add(l[1]);
            linkService.salvar(l[1]);
        }
        //crawler.getContent(list);
        
		return list;
    }
    
}
