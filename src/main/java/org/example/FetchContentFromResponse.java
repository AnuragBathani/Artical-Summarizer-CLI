package org.example;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
public class FetchContentFromResponse {

    public  String fetchContentfromResesponce(String Responce){
        String content = null;
        String jsonResponse = Responce;

        try {
            // Parse the JSON string
            JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();

            // Navigate to the "choices" array and fetch the first object
            JsonObject firstChoice = jsonObject
                    .getAsJsonArray("choices")
                    .get(0)
                    .getAsJsonObject();

            // Navigate to the "message" object and get the "content" field
             content = firstChoice
                    .getAsJsonObject("message")
                    .get("content")
                    .getAsString();

            // Print the content
            System.out.println("Extracted Content: " + content);


        } catch (Exception e) {
            e.printStackTrace();
        }

       return content;

    }
}
