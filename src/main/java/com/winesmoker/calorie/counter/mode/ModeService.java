package com.winesmoker.calorie.counter.mode;

import com.winesmoker.calorie.counter.enums.MealIntake;
import com.winesmoker.calorie.counter.enums.Request;
import com.winesmoker.calorie.counter.mode.impl.HashMapModeService;

public interface ModeService {

    static ModeService getInstance() {
        return new HashMapModeService();
    }

    MealIntake getTargetMeal(long chatId);

    void setTargetMeal(long chatId, MealIntake mealIntake);

    Request getTargetRequest(long chatId);

    void setTargetRequest(long chatId, Request request);
}
