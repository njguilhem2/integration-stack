package com.bff.integration.service;

import com.bff.integration.config.RestTemplanteConfig;
import com.bff.integration.model.AddonsExecute;
import com.bff.integration.model.KeyResponse;
import com.bff.integration.model.KindResponse;
import com.bff.integration.model.StackInfraInput;
import com.bff.integration.model.Status;
import com.bff.integration.model.StatusInfra;
import com.bff.integration.model.StatusInput;
import com.bff.integration.model.Verify;
import com.bff.integration.schema.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class VerifyService {

    @Value("${http.request.verify}")
    private String urlVerify;
    @Value("${http.request.stack}")
    private String urlStack;
    @Value("${http.request.status-infra}")
    private String urlStatusInfra;
    @Value("${http.request.addons}")
    private String urlAddons;

    @Autowired
    private RestTemplanteConfig restTemplanteConfig;

    public Verify verifyKey(Verify verify) {
        try {
            restTemplanteConfig.restTemplate()
                    .postForEntity(urlVerify, verify, KeyResponse.class);
            return verify;
        } catch (Exception ex) {
            throw new DomainException("Invalid keys");
        }
    }

    public StatusInfra stackInfra(StackInfraInput stackInfraInput) {
        restTemplanteConfig.restTemplate()
                .postForEntity(urlStack, stackInfraInput, KeyResponse.class);
        return new StatusInfra("started");
    }

    public Status verifyStatus(StatusInput statusInput) {
        var verifyResponse =
                restTemplanteConfig.restTemplate().getForObject(urlStatusInfra, KindResponse.class);
        if (Boolean.getBoolean(verifyResponse.getCompleted()) == true) {
            AddonsExecute addonsExecute = new AddonsExecute(statusInput.getEnvironment(),statusInput.getAccessKey(),
                    statusInput.getSecretKey(),statusInput.getRoute53Domain());
            restTemplanteConfig.restTemplate().postForEntity(urlAddons,addonsExecute,KindResponse.class);
        }
        return new Status(Boolean.getBoolean(verifyResponse.getCompleted()), verifyResponse.getKindStatus());
    }
}
