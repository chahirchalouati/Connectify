package com.crcl.iam.configuration.clients.interceptors;

import com.crcl.iam.domain.Client;
import com.crcl.iam.repository.MongoClientRepository;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

import static com.crcl.core.common.constant.CommonConstants.AUTHORIZATION_HEADER;
import static com.crcl.core.common.constant.CommonConstants.BEARER_TOKEN_TYPE;

@Slf4j
@AllArgsConstructor
public class ServiceOauth2TokenInterceptorHelper implements RequestInterceptor {
    private final MongoClientRepository mongoClientRepository;
    private final Oauth2TokenInterceptorHelper oauth2TokenInterceptorHelper;

    @Override
    public void apply(RequestTemplate template) {
        Optional<Client> service = this.mongoClientRepository.findByClientId("SYSTEM");
        service.ifPresent(
                clientAuthToken -> {
                    var token = oauth2TokenInterceptorHelper.getClientAccessToken(clientAuthToken.getClientId(), "SYSTEM");
                    if (!StringUtils.isBlank(token)) {
                        template.header(AUTHORIZATION_HEADER, String.format("%s %s", BEARER_TOKEN_TYPE, token));
                    }
                }
        );
    }

}
