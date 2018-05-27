package com.xmlcrawler.service.Crawler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlRequestHandler {

    public String getHtml(String strUrl) throws Exception {

        if(isValidUrl(strUrl)){

            URL url = new URL(strUrl);

            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

            String html = "";
            String line;
            while ((line = reader.readLine()) != null)
            {
                html += line;
            }

            reader.close();

            return html;
        }

        throw new Exception("Invalid url.");
    }

    private boolean isValidUrl(String url){

        String patternStr = "^(http(s)?:\\/\\/.)?(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)$";
        Pattern pattern = Pattern.compile(patternStr);

        Matcher matcher = pattern.matcher(url);

        boolean hasMatch = matcher.matches();

        return hasMatch;
    }
}