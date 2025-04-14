package org.example;

import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class saveInFile {
    // Step 4: Save unique links to a file
    public  void saveUniqueToFile(List<String> links, List<String> blogTitles, String fileName) throws IOException {
        // Load existing links from the file
        Set<String> existingLinks = new HashSet<>();
        File file = new File(fileName);

        if (file.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                existingLinks.add(line);
            }
            reader.close();
        }

        // Write only unique links to the file
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        int i=0;
        System.out.println(links.size());
        while(links.size()-1>i){
            if (!existingLinks.contains(links.get(i))) {
                writer.write(blogTitles.get(i));
                writer.newLine();
                writer.write(links.get(i));
                writer.newLine();
                i++;
            }

        }

//        for (String link : links) {
//            if (!existingLinks.contains(link)) {
//                writer.write(link);
//                writer.newLine();
//            }
//        }
        writer.close();
    }
}
