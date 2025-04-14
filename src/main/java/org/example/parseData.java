package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class parseData {
    // Step 3: Parse XML feed data to extract blog links
    public  List<String> parseXmlFeed_link(String xmlData) throws Exception {
        List<String> blogLinks = new ArrayList<>();


        // Parse the XML using Jsoup
        Document doc = Jsoup.parse(xmlData, "", org.jsoup.parser.Parser.xmlParser());

        // Extract all <item> elements, which usually contain the blog details
        Elements items = doc.select("item");

        // Iterate over each <item> element to get the link
        for (Element item : items) {
            String link = item.select("link").text(); // Extract the link from <link> tag


            blogLinks.add(link); // Add the link to the list
        }

        return blogLinks; // Return the list of blog links
    }

    public  List<String> parseXmlFeed_title(String xmlData) throws Exception {
        List<String> blogtitle = new ArrayList<>();


        // Parse the XML using Jsoup
        Document doc = Jsoup.parse(xmlData, "", org.jsoup.parser.Parser.xmlParser());

        // Extract all <item> elements, which usually contain the blog details
        Elements items = doc.select("item");

        // Iterate over each <item> element to get the link
        for (Element item : items) {
            String link = item.select("title").text(); // Extract the link from <link> tag


            blogtitle.add(link); // Add the link to the list
        }

        return blogtitle; // Return the list of blog links
    }
}
