package com.xmlcrawler.service.Crawler;

import java.util.List;

public class CrawlRequest {

    private String url;
    private String target;
    private boolean regex;
    private boolean deepSearch;
    private List<String> foundTargets;
    private String message;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public boolean isRegex() {
        return regex;
    }

    public void setRegex(boolean regex) {
        this.regex = regex;
    }

    public boolean isDeepSearch() {
        return deepSearch;
    }

    public void setDeepSearch(boolean deepSearch) {
        this.deepSearch = deepSearch;
    }

    public List<String> getFoundTargets() {
        return foundTargets;
    }

    public void setFoundTargets(List<String> foundTargets) {
        this.foundTargets = foundTargets;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
