# WebCrawler
The crawler should be limited to one domain. Given a starting URL - it should visit all pages within the domain, but not follow the links to external sites such as Google or Twitter.
The output should be a simple site map, showing links to other pages under the same domain, links to static content such as images, and to external URLs

## Assumptions:
* 'www.' prefix is ignored in the urls
* Urls with 404 errors are ignored
* " **" indicates the Urls from child page