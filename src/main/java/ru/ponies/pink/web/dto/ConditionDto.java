package ru.ponies.pink.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ponies.pink.domain.entity.enums.CompareMethod;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConditionDto {
    String metricName;
    CompareMethod method;
    String value;
}
