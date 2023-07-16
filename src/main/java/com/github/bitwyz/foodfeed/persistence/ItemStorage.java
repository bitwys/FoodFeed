package com.github.bitwyz.foodfeed.persistence;

import com.github.bitwyz.foodfeed.model.FoodItem;

import java.util.Optional;

public interface ItemStorage {

    void initialize();
    void addFoodItem(FoodItem item);
    Optional<FoodItem> getFoodItem(String itemId);

}
