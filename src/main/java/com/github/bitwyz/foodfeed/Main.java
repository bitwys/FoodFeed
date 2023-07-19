package com.github.bitwyz.foodfeed;


import com.github.bitwyz.foodfeed.model.FoodItem;
import com.github.bitwyz.foodfeed.persistence.ItemStorage;
import com.github.bitwyz.foodfeed.persistence.hsqldb.HsqldbItemStorage;

import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        final ItemStorage storage = new HsqldbItemStorage();
        storage.initialize();

        String mutableInput;
        final Scanner inputScanner = new Scanner(System.in);

        do {
            System.out.println("[q]: quit");
            System.out.println("[0]: list all food items");
            System.out.println("[1]: add a food item");
            mutableInput = inputScanner.nextLine();

            switch (mutableInput) {
                case "0":
                    storage.listFoodItems().forEach(System.out::println);
                    break;
                case "1":
                    addItemCLI(storage);
                    break;
                default:
                    break;
            }


        } while (!mutableInput.equals("q"));

    }

    private static void addItemCLI(final ItemStorage storage) {
        final Scanner inputScanner = new Scanner(System.in);
        System.out.print("Item Name: ");
        final String itemName = inputScanner.nextLine();

        System.out.print("Item Cost: ");
        final float cost = Float.parseFloat(inputScanner.nextLine());

        System.out.print("Location: ");
        final String location = inputScanner.nextLine();

        storage.addFoodItem(FoodItem.builder()
                .id(UUID.randomUUID().toString())
                .name(itemName)
                .cost(cost)
                .location(location)
                .pageUrl("unknown")
                .build());
    }
}