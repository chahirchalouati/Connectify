package com.crcl.iam.clients;

import com.crcl.iam.configuration.clients.FeignFormConfig;
import com.crcl.iam.configuration.clients.OAuthFeignConfig;
import com.crcl.iam.dto.ProfileDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


@ReactiveFeignClient(
        name = "${client.profile.name}",
        url = "${client.profile.url}",
        configuration = {
                OAuthFeignConfig.class,
                FeignFormConfig.class
        })
public interface ProfileClient {
    @GetMapping("/searches/username/{username}")
    Mono<ProfileDto> findByUsername(@PathVariable("username") String username);

    @GetMapping("/searches/profile/usernames")
    Flux<ProfileDto> findByUsernames(@RequestParam("usernames") List<String> userNames);
}
