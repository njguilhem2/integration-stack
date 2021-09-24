package com.bff.integration.service;

import com.bff.integration.model.KeyResponse;
import com.bff.integration.config.RestTemplanteConfig;
import com.bff.integration.model.KindResponse;
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
import java.util.stream.Collectors;

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
    public String stackInfra(StackInfraInput stackInfraInput) {
        restTemplanteConfig.restTemplate()
                .postForEntity(urlStack, stackInfraInput, KeyResponse.class);
        return "Started";
    }

    public List<StatusResponse> verifyStatus() {
        var verifyResponse =
                restTemplanteConfig.restTemplate().getForObject(urlStatusInfra, KindResponse.class);
        return verifyResponse.getKindStatus().stream().map(statusResponse ->
                new StatusResponse
                        (statusResponse.getResource(),statusResponse.getFinish(),statusResponse.getKind()))
                .collect(Collectors.toList());
    }
}
