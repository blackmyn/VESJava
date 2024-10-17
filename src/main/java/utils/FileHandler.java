package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    public static void saveToFile(String filename, List<String> data) throws IOException {
        FileWriter fileWriter = new FileWriter(filename);
        for (String line : data) {
            fileWriter.write(line + "\n");
        }
        fileWriter.close();
    }

    public static List<String> loadFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        List<String> data = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            data.add(line);
        }
        reader.close();
        return data;
    }
}
