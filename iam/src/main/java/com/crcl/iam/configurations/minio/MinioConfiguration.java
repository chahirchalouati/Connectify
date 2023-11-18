package com.crcl.iam.configurations.minio;

import com.crcl.iam.configurations.props.MinioProperties;
import io.minio.MinioClient;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfiguration {
    @Bean
    @SneakyThrows
    public MinioClient minioClient(MinioProperties minioProperties) {
        return MinioClient.builder()
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .endpoint(minioProperties.getUrl())
                .build();
    }
}