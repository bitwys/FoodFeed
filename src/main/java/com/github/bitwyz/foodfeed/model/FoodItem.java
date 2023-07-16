package com.github.bitwyz.foodfeed.model;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Builder
@Value
public class FoodItem {
    @NonNull private final String id;
    @NonNull private final String name;
    @NonNull private final float cost;
    @NonNull private final String pageUrl;
}
