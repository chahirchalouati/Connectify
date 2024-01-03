package com.crcl.iam.configurations.minio;

import com.crcl.iam.configurations.props.MinioProperties;
import io.minio.MinioClient;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class represents the configuration for Minio, an open-source object storage solution.
 * The MinioConfiguration class is used to configure and provide the MinioClient bean.
 */
@Configuration
public class MinioConfiguration {
    /**
     * Initializes and returns a MinioClient object using the provided MinioProperties.
     *
     * @param minioProperties The MinioProperties object containing the access key, secret key, and URL for the Minio client.
     * @return The initialized MinioClient object.
     * @throws Exception if there are any errors while creating the MinioClient.
     */
    @Bean
    @SneakyThrows
    public MinioClient minioClient(MinioProperties minioProperties) {
        return MinioClient.builder()
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .endpoint(minioProperties.getUrl())
                .build();
    }
}