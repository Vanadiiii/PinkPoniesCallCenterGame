package ru.ponies.pink.web.dto;

import lombok.Builder;
import lombok.Data;
import ru.ponies.pink.domain.entity.enums.CompareMethod;

@Data
@Builder
public class ConditionDto {
   private String metricName;
   private CompareMethod method;
   private String value;
}
