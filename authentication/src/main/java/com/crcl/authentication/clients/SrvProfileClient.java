package com.crcl.authentication.clients;

import com.crcl.authentication.configuration.clients.ServerOauth2ClientConfig;
import com.crcl.authentication.dto.ProfileDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "${client.srvProfile.name}", url = "${client.srvProfile.url}",
        configuration = {ServerOauth2ClientConfig.class})
public interface SrvProfileClient {
    @GetMapping("/searches/username/{username}")
    ProfileDto findByUsername(@PathVariable String username);

    @PostMapping("/profiles")
    ProfileDto save(@RequestBody ProfileDto profileDto);
}
