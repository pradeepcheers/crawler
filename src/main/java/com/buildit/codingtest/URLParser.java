package com.buildit.codingtest;

import java.util.Set;

/**
 * Interface to parse the URL and extract data
 */
public interface URLParser {

    /**
     * Parse the URL and extracts links
     * @return a set of links
     */
    Set<String> extractLinks(String url);
}
