package com.bff.integration.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JenkinsStatusRequest {
    private String route53Domain;
    private String environment;
}
