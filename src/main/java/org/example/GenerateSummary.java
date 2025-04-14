package org.example;
import org.jsoup.Jsoup;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class GenerateSummary {
    public  String generateSummary(String Blogcontent) {
        // Define the prompt you want to send to Ollama
        String prompt = (String) Blogcontent;
        String plainText = "Generate summary of this blog in 150 words" + Jsoup.parse(prompt).text() ;

        StringBuilder response = new StringBuilder();

        // Ollama API endpoint (adjust if your Ollama server uses a different path)
        String apiUrl = "http://localhost:11434/v1/chat/completions";

        try {
            // Create a URL object
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set up the connection properties
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

           // Create JSON request body
            String requestBody = "{\n" +
                    "  \"model\": \"llama3.2:1b\",\n" +
                    "  \"messages\": [\n" +
                    "    {\n" +
                    "      \"role\": \"user\",\n" +
                    "      \"content\": \"" + plainText.replace("\"", "\\\"").replace("\n", "\\n") + "\"\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}";
            // Send the request body
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = requestBody.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Read the response
            int responseCode = connection.getResponseCode();
            if (responseCode >= 200 && responseCode < 300)
            {
                // Success: Read from the input stream
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String line;
                while ((line = in.readLine()) != null)
                {
                    response.append(line);
                }
                in.close();

                // Print the response
                System.out.println("Response from Ollama:");
//                System.out.println(response.toString());
                return response.toString();
            }
            else
            {
                // Error: Read the error stream
                BufferedReader errorReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                StringBuilder errorResponse = new StringBuilder();
                String line;
                while ((line = errorReader.readLine()) != null) {
                    errorResponse.append(line);
                }
                errorReader.close();
                System.err.println("Error Response: " + errorResponse.toString());
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return response.toString();
    }
}
