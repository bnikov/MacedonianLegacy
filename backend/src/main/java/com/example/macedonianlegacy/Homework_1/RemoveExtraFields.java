package com.example.macedonianlegacy.Homework_1;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class RemoveExtraFields implements Filter<JsonNode> {

    @Override
    public JsonNode execute(JsonNode input) {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode simplifiedFeatures = objectMapper.createArrayNode();

        for (JsonNode feature : input) {
            ObjectNode simplifiedFeature = objectMapper.createObjectNode();

            JsonNode properties = feature.path("properties");
            simplifiedFeature.put("name", properties.path("name").asText());
            simplifiedFeature.put("historic", properties.path("historic").asText());

            JsonNode geometry = feature.path("geometry");
            simplifiedFeature.set("coordinates", geometry.path("coordinates"));

            simplifiedFeatures.add(simplifiedFeature);
        }

        return simplifiedFeatures;
    }
}
