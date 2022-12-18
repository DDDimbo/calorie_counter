package com.winesmoker.calorie.counter.mode.impl;

import com.winesmoker.calorie.counter.enums.MealIntake;
import com.winesmoker.calorie.counter.enums.Request;
import com.winesmoker.calorie.counter.mode.ModeService;

import java.util.HashMap;
import java.util.Map;

public class HashMapModeService implements ModeService {

    private final Map<Long, MealIntake> targetMeal = new HashMap<>();

    private final Map<Long, Request> targetRequest = new HashMap<>();

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


    @Override
    public Request getTargetRequest(long chatId) {
        if (targetRequest.containsKey(chatId))
            return targetRequest.get(chatId);
        return null;
    }

    @Override
    public void setTargetRequest(long chatId, Request request) {
        targetRequest.put(chatId, request);
    }
}
