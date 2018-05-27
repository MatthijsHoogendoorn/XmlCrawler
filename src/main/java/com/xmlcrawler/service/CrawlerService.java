package com.xmlcrawler.service;

import com.xmlcrawler.service.Crawler.CrawlRequest;
import com.xmlcrawler.service.Crawler.Crawler;

import java.util.ArrayList;
import java.util.List;

public class CrawlerService {

    public CrawlRequest FindFirst(CrawlRequest crawlRequest) throws Exception {

        Crawler crawler = new Crawler(crawlRequest.getUrl());
        List<String> foundTargets = new ArrayList<>();

        String foundTarget = crawler.findFirst(crawlRequest.getTarget(), crawlRequest.isRegex(), crawlRequest.isDeepSearch());
        if(foundTarget != null && foundTarget.length() > 0){
            foundTargets.add(foundTarget);
            crawlRequest.setMessage("Success");
        }
        else{
            crawlRequest.setMessage("Target not found.");
        }

        crawlRequest.setFoundTargets(foundTargets);

        return crawlRequest;
    }

    public CrawlRequest FindLast(CrawlRequest crawlRequest) throws Exception {

        Crawler crawler = new Crawler(crawlRequest.getUrl());
        List<String> foundTargets = new ArrayList<>();

        String foundTarget = crawler.findLast(crawlRequest.getTarget(), crawlRequest.isRegex(), crawlRequest.isDeepSearch());
        if(foundTarget != null && foundTarget.length() > 0){
            foundTargets.add(foundTarget);
            crawlRequest.setMessage("Success");
        }
        else{
            crawlRequest.setMessage("Target not found.");
        }

        crawlRequest.setFoundTargets(foundTargets);

        return crawlRequest;
    }

    public CrawlRequest FindAll(CrawlRequest crawlRequest) throws Exception {

        Crawler crawler = new Crawler(crawlRequest.getUrl());
        List<String> foundTargets = crawler.findAll(crawlRequest.getTarget(), crawlRequest.isRegex(), crawlRequest.isDeepSearch());
        if(foundTargets != null && foundTargets.size() > 0){
            crawlRequest.setFoundTargets(foundTargets);
            crawlRequest.setMessage("Success");
        }
        else{
            crawlRequest.setFoundTargets(new ArrayList<>());
            crawlRequest.setMessage("Target not found.");
        }

        return crawlRequest;
    }
}