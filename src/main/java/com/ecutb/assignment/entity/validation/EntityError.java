package com.ecutb.assignment.entity.validation;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EntityError {
   private String field;
   private String message;
   private String rejectedValue;
}
