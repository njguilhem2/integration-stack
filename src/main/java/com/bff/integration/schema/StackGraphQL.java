package com.bff.integration.schema;


import com.bff.integration.model.StackInfraInput;
import com.bff.integration.model.Status;
import com.bff.integration.model.StatusInfra;
import com.bff.integration.model.AddonsInput;
import com.bff.integration.model.Verify;
import com.bff.integration.service.VerifyService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StackGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver {

    Logger logger = LoggerFactory.getLogger(StackGraphQL.class);

    @Autowired
    private VerifyService verifyService;

    public String verify(String accessKey, String secretKey) {
        logger.info("received request verify");
        verifyService.verifyKey(new Verify(accessKey, secretKey));
        logger.info("return response verify");
        return "success";
    }

    public String addons(AddonsInput addonsInput) {
        logger.info("received request addons");
        verifyService.addonsPost(addonsInput);
        logger.info("return response addons");
        return "started";
    }

    public Status status() {
        logger.info("received request status");
        var status = verifyService.verifyStatus();
        logger.info("return response status");
        return status;
    }

    public StatusInfra stackInfra(StackInfraInput stackInfraInput) {
        logger.info("received request stackInfra");
        var statusInfra = verifyService.stackInfra(stackInfraInput);
        logger.info("return response stackInfra");
        return statusInfra;
    }
    public Boolean statusJenkins(String route53Domain,String environment){
        logger.info("received request status Jenkins");
        var result = verifyService.statusJenkins(route53Domain,environment);
        return result;
    }
}
