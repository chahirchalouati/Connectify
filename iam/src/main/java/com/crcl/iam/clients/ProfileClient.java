package com.crcl.iam.clients;

import com.crcl.iam.configuration.clients.FeignFormConfig;
import com.crcl.iam.configuration.clients.OAuthFeignConfig;
import com.crcl.iam.dto.ProfileDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(
        name = "${client.profile.name}",
        url = "${client.profile.url}",
        configuration = {
                OAuthFeignConfig.class,
                FeignFormConfig.class
        })
public interface ProfileClient {
    @GetMapping("/searches/username/{username}")
    ProfileDto findByUsername(@PathVariable("username") String username);

    @GetMapping("/searches/profile/usernames")
    List<ProfileDto> findByUsernames(@RequestParam("usernames") List<String> userNames);
}
