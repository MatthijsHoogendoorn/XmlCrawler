package com.xmlcrawler.service;

import com.xmlcrawler.service.Crawler.CrawlRequest;
import io.javalin.Handler;

public class CrawlerController {

    public static Handler FindFirst = ctx -> {

        CrawlRequest cr;
        try {

            cr = ctx.bodyAsClass(CrawlRequest.class);

            try {

                CrawlerService crawlerService = new CrawlerService();
                CrawlRequest result = crawlerService.FindFirst(cr);

                ctx.json(result);
            } catch (Exception ex) {

                cr.setMessage(ex.getMessage());
                ctx.json(cr);
            }
        } catch (Exception ex) {
            cr = new CrawlRequest();
            cr.setMessage("Invalid arguments.");
            ctx.json(cr);
        }
    };

    public static Handler FindLast = ctx -> {

        CrawlRequest cr;
        try {

            cr = ctx.bodyAsClass(CrawlRequest.class);

            try {

                CrawlerService crawlerService = new CrawlerService();
                CrawlRequest result = crawlerService.FindLast(cr);

                ctx.json(result);
            } catch (Exception ex) {

                cr.setMessage(ex.getMessage());
                ctx.json(cr);
            }
        } catch (Exception ex) {
            cr = new CrawlRequest();
            cr.setMessage("Invalid arguments.");
            ctx.json(cr);
        }
    };

    public static Handler FindAll = ctx -> {

        CrawlRequest cr;
        try {

            cr = ctx.bodyAsClass(CrawlRequest.class);

            try {

                CrawlerService crawlerService = new CrawlerService();
                CrawlRequest result = crawlerService.FindAll(cr);

                ctx.json(result);
            } catch (Exception ex) {

                cr.setMessage(ex.getMessage());
                ctx.json(cr);
            }
        } catch (Exception ex) {
            cr = new CrawlRequest();
            cr.setMessage("Invalid arguments.");
            ctx.json(cr);
        }
    };
}
