package com.victormartins.firstexample.model.errors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class ApiErrors {
    private String code;
    private List<String> messages;
    private Integer errorCount;
    private Instant timeStamp;
}
