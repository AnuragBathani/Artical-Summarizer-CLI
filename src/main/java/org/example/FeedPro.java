package org.example;


import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FeedPro {

    static FetchBlogContent fetchblogbontent;


    public static void main(String[] args) {

        fetchData fetchData=new fetchData();
        parseData parseData=new parseData();
        saveInFile saveInFile=new saveInFile();
        GenerateSummary gs=new GenerateSummary();
        FetchContentFromResponse fetchContentFromResponse=new FetchContentFromResponse();
        try {
            // Step 1: Take user input
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the feed URL (XML format):");
            String feedUrl = scanner.nextLine();

            // Step 2: Fetch XML feed data
            System.out.println("Fetching feed data...");
            String xmlData = fetchData.fetchFeedData(feedUrl);

            // Step 3: Parse XML to extract blog links
            System.out.println("Parsing feed data...");
            List<String> blogLinks =parseData.parseXmlFeed_link(xmlData);
            List<String> blogTitles = parseData.parseXmlFeed_title(xmlData);

            String blogcontent=fetchblogbontent.fetchBlogContent(blogLinks.get(0));
            String summary=gs.generateSummary(blogcontent);
            String content=fetchContentFromResponse.fetchContentfromResesponce(summary);
//          System.out.println(content);
            System.out.println(summary);


//          Step 4: Save blog links to file (with duplicate check)
            System.out.println("Saving blog links to file...");
            saveInFile.saveUniqueToFile(blogLinks,blogTitles, "blogs.txt");

            // Step 5: Notify user
            System.out.println("Blog links saved successfully to blogs.txt!");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

}
