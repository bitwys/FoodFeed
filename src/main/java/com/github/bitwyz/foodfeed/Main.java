package com.github.bitwyz.foodfeed;


import com.github.bitwyz.foodfeed.model.FoodItem;
import com.github.bitwyz.foodfeed.persistence.ItemStorage;
import com.github.bitwyz.foodfeed.persistence.hsqldb.HsqldbItemStorage;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        ItemStorage storage = new HsqldbItemStorage();
        storage.initialize();



    }
}