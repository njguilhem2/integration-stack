package com.bff.integration.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddonsInput {
    private String environment;
    private String accessKey;
    private String secretKey;
    private String route53Domain;
}
