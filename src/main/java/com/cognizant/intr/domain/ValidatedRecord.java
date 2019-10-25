package com.cognizant.intr.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class ValidatedRecord {
    private String transactionReference;
    private String description;
}
