package com.crcl.core.helper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * The FileHelper class provides methods for loading files as strings or byte arrays.
 * It uses a ResourceLoader to access the files, which are located in the classpath.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class FileHelper {

    private final ResourceLoader resourceLoader;

    /**
     * Loads the content of a file as a string.
     *
     * @param filePath The path of the file to load as a string.
     * @return The content of the file as a string, or null if there was an error loading the file.
     */
    public String loadFileAsString(String filePath) {
        try {
            Resource resource = getResource(filePath);
            return readResourceToString(resource);
        } catch (IOException e) {
            log.error("Error when loading file as string: {}", filePath, e);
            return null;
        }
    }

    /**
     * Loads a file as an array of bytes.
     *
     * @param filePath the path of the file to be loaded
     * @return the byte array representing the contents of the file, or null if an error occurs
     */
    public byte[] loadFileAsBytes(String filePath) {
        try {
            Resource resource = getResource(filePath);
            return readResourceToBytes(resource);
        } catch (IOException e) {
            log.error("Error when loading file as bytes: {}", filePath, e);
            return null;
        }
    }

    /**
     * Load a file as a String using a buffered approach.
     *
     * @param filePath the path of the file to be loaded
     * @return the contents of the file as a String, or null if an error occurs
     */
    public String loadBufferedFileAsString(String filePath) {
        try {
            Resource resource = getResource(filePath);
            return readBufferedResourceToString(resource);
        } catch (IOException e) {
            log.error("Error when loading buffered file as string: {}", filePath, e);
            return null;
        }
    }

    /**
     * Reads the content of a file as bytes and returns the result as a byte array.
     *
     * @param filePath the path to the file
     * @return a byte array containing the file content, or null if an error occurred
     */
    public byte[] loadBufferedFileAsBytes(String filePath) {
        try {
            Resource resource = getResource(filePath);
            return readBufferedResourceToBytes(resource);
        } catch (IOException e) {
            log.error("Error when loading buffered file as bytes: {}", filePath, e);
            return null;
        }
    }

    /**
     * Retrieves a resource from the file system given a file path.
     *
     * @param filePath the path to the resource file
     * @return the retrieved resource
     */
    private Resource getResource(String filePath) {
        return resourceLoader.getResource("classpath:" + filePath);
    }

    /**
     * Reads the content of a resource file and returns it as a string.
     *
     * @param resource The resource to read.
     * @return The content of the resource file as a string.
     * @throws IOException If an I/O error occurs while reading the resource.
     */
    private String readResourceToString(Resource resource) throws IOException {
        try (InputStream inputStream = resource.getInputStream()) {
            byte[] fileBytes = FileCopyUtils.copyToByteArray(inputStream);
            return new String(fileBytes, StandardCharsets.UTF_8);
        }
    }

    /**
     * Reads the contents of a resource file into a byte array.
     *
     * @param resource the resource file to read
     * @return a byte array containing the contents of the resource file
     * @throws IOException if an I/O error occurs while reading the resource file
     */
    private byte[] readResourceToBytes(Resource resource) throws IOException {
        try (InputStream inputStream = resource.getInputStream()) {
            return FileCopyUtils.copyToByteArray(inputStream);
        }
    }

    /**
     * Reads the contents of a buffered resource to a string.
     *
     * @param resource the resource to read
     * @return the contents of the resource as a string
     * @throws IOException if an I/O error occurs while reading the resource
     */
    private String readBufferedResourceToString(Resource resource) throws IOException {
        try (InputStream inputStream = resource.getInputStream()) {
            byte[] fileBytes = FileCopyUtils.copyToByteArray(inputStream);
            return new String(fileBytes, StandardCharsets.UTF_8);
        }
    }

    /**
     * Reads the contents of a buffered resource into a byte array.
     *
     * @param resource the resource to be read
     * @return the byte array containing the contents of the resource
     * @throws IOException if there is an error reading the resource
     */
    private byte[] readBufferedResourceToBytes(Resource resource) throws IOException {
        try (InputStream inputStream = resource.getInputStream()) {
            return FileCopyUtils.copyToByteArray(inputStream);
        }
    }

}
