package com.buildit.codingtest;

import org.junit.Test;

/**
 * Integration Test
 */
public class SimpleWebCrawlerIT {

    private SimpleWebCrawler simpleWebCrawler = new SimpleWebCrawler(new JsoupURLParser());

    @Test public void
    should_pretty_print_site_map() {
        simpleWebCrawler.siteMapPrettyPrint("http://www.procensus.com");
    }
}
