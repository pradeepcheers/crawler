package com.buildit.codingtest;

import java.util.Map;
import java.util.Set;

/**
 * Response class for {@code WebCrawler}'s crawl
 */
public class CrawlResponse {

    /**
     * Represents URL and the mapped URLs for each page as an entry
     */
    private Map<String, Set<String>> urlMap;

    public CrawlResponse() {
    }

    public CrawlResponse(Map<String, Set<String>> urlMap) {
        this.urlMap = urlMap;
    }

    public Map<String, Set<String>> getURLMap() {
        return urlMap;
    }

    public void setUrlMap(Map<String, Set<String>> urlMap) {
        this.urlMap = urlMap;
    }
}
