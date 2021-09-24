package com.bff.integration.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StackInfraInput {
    private String environment;
    private String accessKey;
    private String secretKey;
    private String route53Domain;
    private String instanceType;
    private String desiredSize;
    private String maxSize;
    private String vpc;
    private List<SubNet> subnet;
}
