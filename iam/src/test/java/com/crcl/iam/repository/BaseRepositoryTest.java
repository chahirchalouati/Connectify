package com.crcl.iam.repository;

import com.crcl.iam.configuration.MappersConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@Import(MappersConfiguration.class)
@DataMongoTest
@ActiveProfiles("test")
public class BaseRepositoryTest {

}
