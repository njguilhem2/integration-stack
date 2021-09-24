package com.bff.integration.schema;


import com.bff.integration.model.StackInfraInput;
import com.bff.integration.model.StatusResponse;
import com.bff.integration.model.Verify;
import com.bff.integration.service.VerifyService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class StackGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private VerifyService verifyService;

    public String verify(String accessKey, String secretKey) {
        //verifyService.verifyKey(new Verify(accessKey, secretKey));
        return "success";
    }

    public List<StatusResponse> status() {
        //return verifyService.verifyStatus();

        return new ArrayList<StatusResponse>(
                Arrays.asList(new StatusResponse("resource","false","subnet")));
    }

    public String stackInfra(StackInfraInput stackInfraInput) {
        System.out.println(stackInfraInput.getAccessKey() +stackInfraInput.getDesiredSize()+
                stackInfraInput.getEnvironment()+stackInfraInput.getMaxSize()+ stackInfraInput.getRoute53Domain()+
                stackInfraInput.getVpc()+stackInfraInput.getSubnet());
        //verifyService.stackInfra(stackInfraInput);
        return "started";
    }
}
