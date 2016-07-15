package com.buildit.codingtest;

/**
 * Represents a web crawler
 */
public interface WebCrawler {

    /**
     * Crawls through the passed url and return {@code CrawlResponse}
     * @param url to crawl
     * @return {@code CrawlResponse}
     */
    CrawlResponse crawl(String url);

    /**
     * Prints the site map for the Crawled page
     * @param url to crawl and print site map
     */
    void siteMapPrettyPrint(String url);
}
