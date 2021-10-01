package com.bff.integration.service;

import com.bff.integration.config.RestTemplanteConfig;
import com.bff.integration.model.AddonsExecute;
import com.bff.integration.model.JenkinsStatusRequest;
import com.bff.integration.model.KeyResponse;
import com.bff.integration.model.KindResponse;
import com.bff.integration.model.StackInfraInput;
import com.bff.integration.model.StartedResponse;
import com.bff.integration.model.Status;
import com.bff.integration.model.StatusInfra;
import com.bff.integration.model.AddonsInput;
import com.bff.integration.model.Verify;
import com.bff.integration.schema.exceptions.DomainException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class VerifyService {
    Logger logger = LoggerFactory.getLogger(VerifyService.class);
    @Value("${http.request.verify}")
    private String urlVerify;
    @Value("${http.request.stack}")
    private String urlStack;
    @Value("${http.request.status-infra}")
    private String urlStatusInfra;
    @Value("${http.request.addons}")
    private String urlAddons;
    @Value("${http.request.status-jenkins}")
    private String urlJenkins;

    @Autowired
    private RestTemplanteConfig restTemplanteConfig;

    public Verify verifyKey(Verify verify) {
        logger.info("send request verify Keys");
        try {
            restTemplanteConfig.restTemplate()
                    .postForEntity(urlVerify, verify, KeyResponse.class);
            return verify;
        } catch (Exception ex) {
            throw new DomainException("Invalid keys");
        }
    }

    public StatusInfra stackInfra(StackInfraInput stackInfraInput) {
        logger.info("send request stack infra");
        restTemplanteConfig.restTemplate()
                .postForEntity(urlStack, stackInfraInput, KeyResponse.class);
        return new StatusInfra("started");
    }

    public Status verifyStatus() {
        Boolean completed = false;
        logger.info("send request status");
        var verifyResponse =
                restTemplanteConfig.restTemplate().getForObject(urlStatusInfra, KindResponse.class);
        if (verifyResponse.getCompleted().equals("true")) {
            completed = true;
        }
        var status = new Status(completed, verifyResponse.getKindStatus());
        return status;
    }

    public String addonsPost(AddonsInput addonsInput){
        logger.info("send request addons");
        AddonsExecute addonsExecute = new AddonsExecute(addonsInput.getEnvironment(), addonsInput.getAccessKey(),
                addonsInput.getSecretKey(), addonsInput.getRoute53Domain());
        restTemplanteConfig.restTemplate().postForEntity(urlAddons,addonsExecute,Boolean.class);
        return "started";
    }

    public Boolean statusJenkins(String route53Domain,String environment){
        logger.info("send request status jenkins");
        var result = restTemplanteConfig.restTemplate()
                .postForEntity(urlJenkins,new JenkinsStatusRequest(route53Domain,environment)
                , StartedResponse.class);
        return result.getBody().getStarted();
    }
}
