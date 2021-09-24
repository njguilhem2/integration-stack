package com.bff.integration.schema;


import com.bff.integration.model.StackInfraInput;
import com.bff.integration.model.StatusResponse;
import com.bff.integration.model.Verify;
import com.bff.integration.service.VerifyService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class StackGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver {

    Logger logger = LoggerFactory.getLogger(StackGraphQL.class);

    @Autowired
    private VerifyService verifyService;

    public String verify(String accessKey, String secretKey) {
        logger.info("received request verify");
        //verifyService.verifyKey(new Verify(accessKey, secretKey));
        logger.info("return response verify");
        return "success";
    }

    public List<StatusResponse> status() {
        logger.info("received request status");
        //return verifyService.verifyStatus();
        var list = new ArrayList<StatusResponse>(
                Arrays.asList(new StatusResponse("resource","false","subnet")));
        list.add(new StatusResponse("ec2.aws.crossplane.io/v1beta1","true","Subnet"));
        list.add(new StatusResponse("ec2.aws.crossplane.io/v1beta1","false","Subnet"));
        list.add(new StatusResponse("ec2.aws.crossplane.io/v1beta1","false","VPC"));
        list.add(new StatusResponse("ec2.aws.crossplane.io/v1beta1","false","SecurityGroup"));
        list.add(new StatusResponse("eks.aws.crossplane.io/v1alpha1","true","NodeGroup"));
        list.add(new StatusResponse("ec2.aws.crossplane.io/v1beta1","true","InternetGateway"));
        logger.info("return response status");
        return list;
    }

    public String stackInfra(StackInfraInput stackInfraInput) {
        logger.info("received request stackInfra");
        System.out.println(stackInfraInput.getAccessKey() +stackInfraInput.getDesiredSize()+
                stackInfraInput.getEnvironment()+stackInfraInput.getMaxSize()+ stackInfraInput.getRoute53Domain()+
                stackInfraInput.getVpc()+stackInfraInput.getSubnet());
        //verifyService.stackInfra(stackInfraInput);
        logger.info("return response stackInfra");
        return "started";
    }
}
