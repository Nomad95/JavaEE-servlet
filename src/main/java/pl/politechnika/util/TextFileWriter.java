package pl.politechnika.util;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class TextFileWriter {

    public void saveToFile(Map<String, String[]> valuesMap) {

        URL resource = getClass().getClassLoader().getResource("pollValues.txt");
        Map<String, String> mapFromFile = readFromFile();

        try (FileWriter fw = new FileWriter(resource.getFile())) {
            valuesMap.forEach((key, value) -> {
                int numberOfChosenTechnologies = Integer.parseInt(mapFromFile.get(key));
                numberOfChosenTechnologies++;
                mapFromFile.put(key, String.valueOf(numberOfChosenTechnologies));
            });

            mapFromFile.forEach((key, value) -> {
                try {
                    fw.write(key + ":" + value + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            System.out.println("Blad pliku");
        }
    }


    public Map readFromFile() {
        String filePath = getClass().getClassLoader().getResource("pollValues.txt").getFile();
        File file = new File(filePath);
        String line;
        HashMap<String, String> resultMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            while ((line = br.readLine()) != null) {
                String[] splittedLine = line.split(":");
                resultMap.put(splittedLine[0], splittedLine[1]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultMap;
    }

    public static void initFile() {
        URL file = TextFileWriter.class.getClassLoader().getResource("pollValues.txt");

        HashMap<String, String> valuesMap = new HashMap<>();
        valuesMap.put("ASP.NET", "0");
        valuesMap.put("JavaScript", "0");
        valuesMap.put("PHP", "0");
        valuesMap.put("Python", "0");
        valuesMap.put("RubyOnRails", "0");
        valuesMap.put("Servlets & JSP", "0");

        try (FileWriter fw = new FileWriter(file.getFile())) {
            valuesMap.forEach((key, value) -> {
                try {
                    fw.write(key + ":" + value + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            System.out.println("Blad pliku");
        }
    }
}
