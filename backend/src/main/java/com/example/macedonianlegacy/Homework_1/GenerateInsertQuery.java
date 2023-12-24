package com.example.macedonianlegacy.Homework_1;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GenerateInsertQuery {

    public static void jsonToSql(String inputFile, String outputFile) {
        ObjectMapper objectMapper = new ObjectMapper();

        try (FileWriter writer = new FileWriter(outputFile)) {
            JsonNode jsonNode = objectMapper.readTree(new File(inputFile));

            if (jsonNode.isArray()) {
                for (JsonNode item : jsonNode) {
                    String name = item.path("name").asText();
                    String category = item.path("category").asText();
                    double longitude = item.path("coordinates").get(0).asDouble();
                    double latitude = item.path("coordinates").get(1).asDouble();

                    String sql = "INSERT INTO location (name, category, longitude, latitude) VALUES ('" +
                            name + "', '" + category + "', " + longitude + ", " + latitude + ");";

                    writer.write(sql + "\n");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String inputFile = "C:\\Users\\Bojan\\Desktop\\lab1example\\MacedonianLegacy\\backend\\src\\main\\resources\\data\\SimplifiedData.json";
        String outputFile = "C:\\Users\\Bojan\\Desktop\\lab1example\\MacedonianLegacy\\backend\\src\\main\\resources\\db\\migration\\V2__Init_tables.sql";

        jsonToSql(inputFile, outputFile);
    }
}
