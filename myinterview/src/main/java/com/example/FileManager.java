package com.example;

import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import java.io.*;
import java.util.*;

public class FileManager {

    public static void writeCsvFile(Map<String, Integer> data, String fileName) {
        FileWriter fileWriter = null;
        PrintWriter printWriter = null;
        boolean failed = false;

        fileName += ".csv";

        try {
            fileWriter = new FileWriter(fileName);
            printWriter = new PrintWriter(fileWriter);

            // Escrever cabeçalho
            for (Map.Entry<String, Integer> entry : data.entrySet()) {
                printWriter.print(entry.getKey() + ";" + entry.getValue() + ";");
            }
            printWriter.println();

        } catch (IOException error) {
            System.out.println("Error writing to the file");
            failed = true;
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    System.out.println("Error closing the file");
                    failed = true;
                }
            }
            if (failed) {
                System.exit(1);
            }
        }
    }

    public static void uploadToS3(String filePath) {
        String bucketName = "interview-digiage";
        String key = filePath;

        S3Client s3 = S3Client.builder()
                .region(Region.US_WEST_2)
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();

        try {
            PutObjectRequest objectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .build();

            s3.putObject(objectRequest, RequestBody.fromFile(new File(filePath)));

            System.out.println("File uploaded successfully to S3.");

        } catch (S3Exception e) {
            e.printStackTrace();
        }
    }
}
