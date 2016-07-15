package com.buildit.codingtest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@code WebCrawler}
 */
@RunWith(MockitoJUnitRunner.class)
public class SimpleWebCrawlerTest {

    private static final String urlOne = "http://www.oneplus.net";
    private static final String urlTwo = "http://www.oneplus.net/2";
    private static final String urlThree = "http://www.oneplus.net/3";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @InjectMocks
    private SimpleWebCrawler webCrawler;

    @Mock
    private  URLParser urlParser;

    @Before
    public void init() {
        webCrawler = new SimpleWebCrawler(urlParser);
    }

    @Test public void
    should_throw_exception_for_null_url() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Invalid URL");

        webCrawler.crawl(null);
    }

    @Test public void
    should_throw_exception_for_empty_url() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Invalid URL");

        webCrawler.crawl("");
    }

    @Test public void
    should_get_crawl_response_on_crawl() {
        Set<String> pageUrls = getPageUrls();
        Map<String, Set<String>> urlMap = getUrlMap(pageUrls);

        when(urlParser.extractLinks(anyString())).thenReturn(pageUrls);

        CrawlResponse crawlResponse = webCrawler.crawl(urlOne);

        assertThat(crawlResponse, is(notNullValue()));
        assertThat(crawlResponse.getURLMap(), is(notNullValue()));
        assertThat(crawlResponse.getURLMap(), is(urlMap));
    }

    @Test public void
    should_pretty_print_site_map() {
        Set<String> pageUrls = getPageUrls();
        when(urlParser.extractLinks(anyString())).thenReturn(pageUrls);

        webCrawler.siteMapPrettyPrint(urlOne);

        verify(urlParser).extractLinks(urlOne);
    }

    private Map<String, Set<String>> getUrlMap(Set<String> pageUrls) {
        Map<String, Set<String>> urlMap = new HashMap<>();
        urlMap.put(urlOne, pageUrls);
        urlMap.put(urlTwo, pageUrls);
        urlMap.put(urlThree, pageUrls);
        return urlMap;
    }

    private Set<String> getPageUrls() {
        return new HashSet<String>() {{
                    add(urlTwo);
                    add(urlThree);
            }};
    }
}
