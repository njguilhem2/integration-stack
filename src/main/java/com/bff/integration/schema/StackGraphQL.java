package com.bff.integration.schema;


import com.bff.integration.model.Verify;
import com.bff.integration.service.VerifyService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StackGraphQL implements GraphQLQueryResolver {
    @Autowired
    private VerifyService verifyService;

    public Verify verify(String accessKey, String secretKey){
        verifyService.verifyKey(new Verify(accessKey,secretKey));
        return new Verify(accessKey,secretKey);
    }
}
