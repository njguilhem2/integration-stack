package com.bff.integration.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KindResponse {
    private String completed;
    private List<StatusResponse> kindStatus;
}
