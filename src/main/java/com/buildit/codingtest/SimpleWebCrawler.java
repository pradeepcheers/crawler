package com.buildit.codingtest;

import org.apache.commons.validator.routines.UrlValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Simple implementation of {@code WebCrawler}
 */
public class SimpleWebCrawler implements WebCrawler {

    private URLParser urlParser;

    public SimpleWebCrawler(URLParser urlParser) {
        this.urlParser = urlParser;
    }

    @Override
    public CrawlResponse crawl(String url) {
        if(!new UrlValidator().isValid(url))
            throw new IllegalArgumentException("Invalid URL");

        Map<String, Set<String>> urlMap = new HashMap<>();

        extractLinks(url, urlMap);

        return new CrawlResponse(urlMap);
    }

    @Override
    public void siteMapPrettyPrint(String url) {
        CrawlResponse crawlResponse = crawl(url);
        crawlResponse.getURLMap().keySet().forEach(p -> {
            System.out.println(p);
            crawlResponse.getURLMap().get(p).stream().forEach(c ->System.out.println(" **"+c));
            System.out.println();
        });
    }

    private void extractLinks(String url, Map<String, Set<String>> urlMap) {
        Set<String> pageUrls = urlParser.extractLinks(url);

        urlMap.put(url, pageUrls);

        pageUrls.stream()
                .filter(pageUrl -> !urlMap.containsKey(pageUrl))
                .forEach(pageUrl -> extractLinks(pageUrl, urlMap));
    }
}
