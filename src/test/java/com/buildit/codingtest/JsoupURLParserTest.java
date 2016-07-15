package com.buildit.codingtest;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.StringStartsWith.startsWith;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@code JsoupURLParserTest}
 */
public class JsoupURLParserTest extends JsoupURLParser {

    private URLParser urlParser;

    @Before
    public void init() {
        urlParser = new JsoupURLParserTest();
    }

    @Test public void
    should_return_a_set_of_URLs_by_parsing_page() {
        Set<String> urls = urlParser.extractLinks("http://www.oneplus.net");

        assertThat(urls, is(notNullValue()));
        urls.stream().forEach(url -> assertThat(url, startsWith("http://www.oneplus")));
    }

    protected Set<String>getAllLinksOnPage(String url) {
        String urlOne = "http://www.oneplus.net";
        String urlTwo = "http://www.oneplus.net/2";
        String urlThree = "http://www.oneplus.net/3";

        Set<String> pageUrls = new HashSet<String>() {{
            add("");
            add(null);
            add(urlOne);
            add(urlTwo);
            add(urlThree);
            add("http://google.com");
            add("https://www.twitter.com");
            add("http://www.facebook.com");
        }};

        return pageUrls;
    }
}
