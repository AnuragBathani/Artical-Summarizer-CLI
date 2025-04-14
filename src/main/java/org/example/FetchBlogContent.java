package org.example;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class FetchBlogContent {


    public static String fetchBlogContent(String blogUrl) throws IOException {
        StringBuilder content = new StringBuilder();
        URL url = new URL(blogUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        return content.toString();
    }




}
