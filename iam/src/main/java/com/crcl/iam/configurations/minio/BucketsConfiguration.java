package com.crcl.iam.configurations.minio;

import com.crcl.iam.configurations.props.BucketProperties;
import com.crcl.iam.configurations.props.MinioProperties;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


/**
 * Configuration class for handling bucket properties related operations with Minio.
 */
@Configuration
@AllArgsConstructor
@Slf4j
public class BucketsConfiguration {

    private final MinioClient minioClient;
    private final MinioProperties minioProperties;

    /**
     * Returns a CommandLineRunner that checks for bucket properties
     * on application ready event and creates them if they do not exist.
     *
     * @return CommandLineRunner
     */
    @Bean
    public CommandLineRunner handleApplicationReadyEvent() {
        return args -> {
            List<BucketProperties> buckets = minioProperties.getBuckets();

            if (buckets.isEmpty()) {
                log.info("The buckets list is empty.");
                return;
            }

            buckets.forEach(this::createBucketIfNotExists);
        };
    }

    /**
     * Attempts to create a bucket specified by the bucketProperties,
     * if it doesn't already exist.
     *
     * @param bucketProperties The properties of the bucket to possibly create
     */
    private void createBucketIfNotExists(BucketProperties bucketProperties) {
        try {
            if (!bucketExists(bucketProperties.getName())) {
                makeBucket(bucketProperties);
                log.info("{} bucket was created successfully.", bucketProperties.getName());
            }
        } catch (Exception e) {
            log.error("Error creating Minio buckets: {}", e.getMessage());
        }
    }

    /**
     * Checks if a bucket with specified name exists.
     *
     * @param bucketName The name of the bucket
     * @return True if bucket exists, false otherwise
     */
    private boolean bucketExists(String bucketName) {
        try {
            return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        } catch (Exception e) {
            log.error("Error checking if bucket exists: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Creates a new bucket using the bucketProperties specifications.
     *
     * @param bucketProperties The properties of the bucket to create
     */
    private void makeBucket(BucketProperties bucketProperties) {
        try {
            minioClient.makeBucket(MakeBucketArgs.builder()
                    .objectLock(bucketProperties.isObjectLock())
                    .bucket(bucketProperties.getName())
                    .build());
        } catch (Exception e) {
            log.error("Error creating bucket: {}", e.getMessage());
        }
    }
}