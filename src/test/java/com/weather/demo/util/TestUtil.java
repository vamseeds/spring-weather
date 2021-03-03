package com.weather.demo.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    private static final String TEST_RESOURCE_BASE_PATH = "src/test/resources/";

    public static String readFile(String fileName) throws IOException {

        return Files.readString(Path.of(TEST_RESOURCE_BASE_PATH+fileName));
    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
