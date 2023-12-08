package com.crcl.core.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.*;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * FileUtils class provides utility methods for file operations.
 */
@Slf4j
public class FileUtils {
    /**
     * Cleans up the specified files by permanently deleting them from the file system.
     *
     * @param paths The paths of the files to be cleaned up.
     *              Each path should be a valid String representing the file's location.
     *              Multiple file paths can be provided.
     *              Path can be relative or absolute.
     *              It is recommended to use {@link Paths#get(String, String...)} to create the path.
     * @throws RuntimeException If any error occurs while deleting a file.
     *                          The exception message will contain the path of the file and the error message.
     */
    public static void cleanUpFiles(String... paths) {
        Arrays.stream(paths).forEach(
                path -> {
                    try {
                        Files.delete(Paths.get(path).normalize().toAbsolutePath());
                        log.info("Deleted file: {}", path);
                    } catch (IOException e) {
                        log.error("Error deleting file: {} {} ", path, e.getMessage());
                        throw new RuntimeException(e);
                    }
                }
        );
    }

    /**
     * Creates a file with the given fileName in the specified directoryPath.
     * If the directory does not exist, it will be created.
     *
     * @param directoryPath the path of the directory in which to create the file
     * @param fileName      the name of the file to be created
     * @return the created file as a Path object
     */
    public static Path createFileInDirectory(String directoryPath, String fileName) {
        createDirectoryIfNotExists(directoryPath);
        Path filePath = Paths.get(directoryPath, fileName);
        try {
            Path file = Files.createFile(filePath);
            log.info("File created successfully: " + filePath);
            return file;
        } catch (FileAlreadyExistsException e) {
            log.error("File already exists: " + filePath);
        } catch (IOException e) {
            log.error("Failed to create file: " + filePath, e);
        }
        return null;
    }

    /**
     * Creates a directory at the specified path if it does not already exist.
     *
     * @param directoryPath the path of the directory to be created
     */
    public static void createDirectoryIfNotExists(String directoryPath) {
        Path directory = Paths.get(directoryPath);
        if (!Files.exists(directory)) {
            try {
                Files.createDirectories(directory);
                log.info("Directory created successfully: " + directoryPath);
            } catch (IOException e) {
                log.error("Failed to create directory: " + directoryPath, e);
            }
        }
    }

    /**
     * Compresses the input stream into a zip archive and writes it to the output stream.
     *
     * @param sourceInputStream The input stream to be compressed.
     * @param zipOutputStream The output stream where the zip archive will be written.
     */
    public static void zip(InputStream sourceInputStream, OutputStream zipOutputStream) {
        try (ZipOutputStream zos = new ZipOutputStream(zipOutputStream)) {
            ZipEntry zipEntry = new ZipEntry("file.txt");
            zos.putNextEntry(zipEntry);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = sourceInputStream.read(buffer)) != -1) {
                zos.write(buffer, 0, bytesRead);
            }
            zos.closeEntry();
            log.debug("File zipped successfully.");
        } catch (IOException e) {
            log.error("Error when zipping file: {}", e.getMessage());
        }
    }

    /**
     * Unzips the contents of a given zip file and writes them to the specified output stream.
     *
     * @param zipInputStream   the input stream of the zip file to be unzipped
     * @param unzipOutputStream   the output stream to write the unzipped contents to
     */
    public static void unzip(InputStream zipInputStream, OutputStream unzipOutputStream) {
        try (ZipInputStream zis = new ZipInputStream(zipInputStream)) {
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {
                readAndWriteEntryContent(zis, unzipOutputStream);
                zipEntry = zis.getNextEntry();
            }
            log.debug("File unzipped successfully.");
        } catch (IOException e) {
            log.error("Error when unzipping file: {}", e.getMessage());
        }
    }

    /**
     * Reads the content of an entry in a ZipInputStream and writes it to an OutputStream.
     *
     * @param zis The ZipInputStream to read the entry content from.
     * @param outputStream The OutputStream to write the entry content to.
     * @throws IOException If an I/O error occurs while reading or writing the entry content.
     */
    private static void readAndWriteEntryContent(ZipInputStream zis, OutputStream outputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = zis.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
    }
}
