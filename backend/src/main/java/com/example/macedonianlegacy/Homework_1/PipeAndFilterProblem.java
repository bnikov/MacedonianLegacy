package com.example.macedonianlegacy.Homework_1;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class PipeAndFilterProblem {

    public static void main(String[] args) throws IOException {

        File inputFile = new File("C:\\Users\\Bojan\\Desktop\\lab1example\\MacedonianLegacy\\backend\\src\\main\\resources\\data\\StartingData.geojson");
        File outputFile = new File("C:\\Users\\Bojan\\Desktop\\lab1example\\MacedonianLegacy\\backend\\src\\main\\resources\\data\\SimplifiedData.json");

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(inputFile);

        Pipe<JsonNode> pipe = new Pipe<>();
        RemoveExtraFields removeExtraFields = new RemoveExtraFields();
        MapCategoryFilter mapCategoryFilter = new MapCategoryFilter();

        pipe.addFilter(removeExtraFields);
        pipe.addFilter(mapCategoryFilter);

        JsonNode simplified = pipe.runFilters(jsonNode);

        objectMapper.writeValue(outputFile, simplified);

        System.out.println("Simplified JSON saved to: " + outputFile.getAbsolutePath());
    }
}
