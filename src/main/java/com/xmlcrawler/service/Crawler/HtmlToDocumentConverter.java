package com.xmlcrawler.service.Crawler;

import org.w3c.dom.Document;
import org.w3c.tidy.Tidy;

import java.io.StringReader;
import java.io.StringWriter;

public class HtmlToDocumentConverter {

    public Document convert(String html) {

        Tidy tidy = new Tidy();
        StringReader reader = new StringReader(html);
        StringWriter sw = new StringWriter();

        tidy.setQuiet(true);
        tidy.setShowWarnings(false);
        tidy.setShowErrors(0);
        tidy.setWraplen(Integer.MAX_VALUE);

        tidy.setDropEmptyParas(true);
        tidy.setDropFontTags(true);
        tidy.setXmlOut(true);

        Document doc = tidy.parseDOM(reader, sw);

        return doc;
    }
}
