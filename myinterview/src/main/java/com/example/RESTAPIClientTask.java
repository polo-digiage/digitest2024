package com.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

import static com.example.FileManager.uploadToS3;

/**
 * This task expects you to create an implementation of a Rest API client.
 * Your code should call the API endpoint related below.
 * After receiving the JSON Response, print out how many records exists for each gender
 * API endpoint => https://3ospphrepc.execute-api.us-west-2.amazonaws.com/prod/RDSLambda 
 * 
 * >>> Bonus <<<
 * Generate a CSV file containing many records exists for each gender and save this file to AWS S3 Bucket
 * The filename need to contains your entire name, separated by uderscore. Example: john_lennon.csv
 * AWS S3 bucket name => interview-digiage
 * The credentials you can find in Coodesh platform or ask via e-mail for recrutamento@digiage.com.br
 */

public class RESTAPIClientTask {

    // API endpoint => https://3ospphrepc.execute-api.us-west-2.amazonaws.com/prod/RDSLambda
    private static final String API_URL = "https://3ospphrepc.execute-api.us-west-2.amazonaws.com/prod/RDSLambda";

    public static void main(String[] args) {
        try {
            String jsonResponse = callApi(API_URL);

            Map<String, Integer> genderCounts = processJsonResponse(jsonResponse);

            printGenderCounts(genderCounts);

            FileManager.writeCsvFile(genderCounts, "matheus_gabriel");

            uploadToS3("matheus_gabriel.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String callApi(String apiUrl) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    private static Map<String, Integer> processJsonResponse(String jsonResponse) throws IOException {
        Map<String, Integer> genderCounts = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonResponse);
        Iterator<JsonNode> elements = rootNode.elements();

        while (elements.hasNext()) {
            JsonNode record = elements.next();
            String gender = record.path("gender").asText();

            genderCounts.put(gender, genderCounts.getOrDefault(gender, 0) + 1);
        }

        return genderCounts;
    }

    private static void printGenderCounts(Map<String, Integer> genderCounts) {
        for (Map.Entry<String, Integer> entry : genderCounts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}