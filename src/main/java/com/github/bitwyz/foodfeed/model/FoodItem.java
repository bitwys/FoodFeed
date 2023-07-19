package com.github.bitwyz.foodfeed.model;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import javax.annotation.Nonnull;

@Builder
@Value
public class FoodItem {
    @Nonnull private final String id;
    @Nonnull private final String name;
    @Nonnull private final Float cost;
    @Nonnull private final String location;
    @Nonnull private final String pageUrl;
}
