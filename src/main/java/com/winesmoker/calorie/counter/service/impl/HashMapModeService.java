package com.winesmoker.calorie.counter.service.impl;

import com.winesmoker.calorie.counter.enums.MealIntake;
import com.winesmoker.calorie.counter.service.MealModeService;

import java.util.HashMap;
import java.util.Map;

public class HashMapModeService implements MealModeService {

    private final Map<Long, MealIntake> targetMeal = new HashMap<>();

    public HashMapModeService() {
        System.out.println("HASHMAP MODE is created");
    }

    @Override
    public MealIntake getTargetMeal(long chatId) {
        if (targetMeal.containsKey(chatId))
            return targetMeal.get(chatId);
        return null;
    }

    @Override
    public void setTargetMeal(long chatId, MealIntake meal) {
        targetMeal.put(chatId, meal);
    }
}
