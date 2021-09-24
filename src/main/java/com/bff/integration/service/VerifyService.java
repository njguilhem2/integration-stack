package com.bff.integration.service;

import com.bff.integration.model.KeyResponse;
import com.bff.integration.config.RestTemplanteConfig;
import com.bff.integration.model.StackInfraInput;
import com.bff.integration.model.StatusResponse;
import com.bff.integration.model.Verify;
import com.bff.integration.model.VerifyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VerifyService {

    @Value("${http.request.verify}")
    private String urlVerify;
    @Value("${http.request.stack}")
    private String urlStack;
    @Value("${http.request.status-infra}")
    private String urlStatusInfra;

    @Autowired
    private RestTemplanteConfig restTemplanteConfig;

    public Verify verifyKey(Verify verify) {
        restTemplanteConfig.restTemplate()
                .postForEntity(urlVerify, verify, KeyResponse.class);
        return verify;
    }

    public Boolean stackInfra(StackInfraInput stackInfraInput) {
        restTemplanteConfig.restTemplate()
                .postForEntity(urlStack, stackInfraInput, KeyResponse.class);
        return Boolean.TRUE;
    }

    public List<StatusResponse> verifyStatus() {
        ParameterizedTypeReference<List<StatusResponse>> parameterizedTypeReference =
                new ParameterizedTypeReference<>() {
                };
        var verifyResponse =
                restTemplanteConfig.restTemplate().exchange(urlStatusInfra, HttpMethod.GET, null, parameterizedTypeReference);
        return verifyResponse.getBody();
    }
}
