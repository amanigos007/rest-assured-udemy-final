package org.restassuredudemy.com.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class Employee {
    private String name;
    private long idNumber;
}
