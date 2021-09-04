package ru.ponies.pink.client.dto;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
@Builder(toBuilder = true)
public class GoodDto {
    UUID id;
    String name;
    BigDecimal price;
    String imageUrl;
}
