package com.bff.integration.service;

import com.bff.integration.model.KeyResponse;
import com.bff.integration.config.RestTemplanteConfig;
import com.bff.integration.model.Verify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class VerifyService {
    @Value("${http.request}")
    private String url;

    @Autowired
    private RestTemplanteConfig restTemplanteConfig;
    public Verify verifyKey(Verify verify){
        restTemplanteConfig.restTemplate()
                .postForEntity(url,verify, KeyResponse.class);
        return verify;
    }
}
