package com.buildit.codingtest;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Jsoup implementation of {@code URLParser}
 */
public class JsoupURLParser implements URLParser {

    private String domain = "";

    @Override
    public Set<String> extractLinks(String url) {
        domain = domain.isEmpty() ? extractDomain(url) : domain;

        Set<String> links = getAllLinksOnPage(url);

        return links.stream()
                .filter(filterNonNullOrEmptyUrls().and(filterWithInTheDomainUrls()))
                .collect(Collectors.toSet());
    }

    protected Set<String>getAllLinksOnPage(String url) {
        Set<String> links = null;

        try {
            Connection connection = Jsoup.connect(url).timeout(5000);
            Document htmlDocument =  connection.ignoreHttpErrors(true).get();
            Elements linksOnPage = htmlDocument.select("a[href]");

            links = linksOnPage.stream()
                        .map(link -> link.absUrl("href"))
                        .collect(Collectors.toSet());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return links;
    }

    private Predicate<String> filterNonNullOrEmptyUrls() {
        return link -> link != null && !link.isEmpty();
    }

    private Predicate<String> filterWithInTheDomainUrls() {
        return link -> domain.equalsIgnoreCase(extractDomain(link));
    }

    private String extractDomain(String url) {
        String domain = null;
        try {
            domain =  new URL(url).getHost().toLowerCase().replaceFirst("www.","");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return domain;
    }
}
