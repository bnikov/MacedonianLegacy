package com.example.macedonianlegacy.Homework_1;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.HashMap;
import java.util.Map;

public class MapCategoryFilter implements Filter<JsonNode> {

    private static final Map<String, String> CATEGORY_MAPPING = new HashMap<>();

    static {
        CATEGORY_MAPPING.put("archaeological_site", "Археолошки Локалитет");
        CATEGORY_MAPPING.put("wayside_shrine", "Светилиште");
        CATEGORY_MAPPING.put("castle", "Замок");
        CATEGORY_MAPPING.put("tomb", "Гробница");
        CATEGORY_MAPPING.put("aircraft", "Авион");
        CATEGORY_MAPPING.put("monument", "Споменик");
        CATEGORY_MAPPING.put("city_gate", "Градска порта");
        CATEGORY_MAPPING.put("ruins", "Урнатини");
        CATEGORY_MAPPING.put("battlefield", "Бојно поле");
        CATEGORY_MAPPING.put("locomotive", "Локомотива");
        CATEGORY_MAPPING.put("boundary_stone", "Граничен Камен");
        CATEGORY_MAPPING.put("memorial", "Спомен");
    }

    @Override
    public JsonNode execute(JsonNode input) {
        if (input.isArray()) {
            ArrayNode arrayNode = (ArrayNode) input;

            for (JsonNode feature : arrayNode) {
                if (feature.isObject()) {
                    ObjectNode featureNode = (ObjectNode) feature;

                    if (featureNode.has("historic") && !featureNode.path("historic").isMissingNode()) {
                        String historicTag = featureNode.path("historic").asText();

                        if (CATEGORY_MAPPING.containsKey(historicTag)) {
                            String category = CATEGORY_MAPPING.get(historicTag);
                            featureNode.put("category", category);
                            featureNode.remove("historic");
                        }
                    }
                }
            }
        }

        return input;
    }
}
