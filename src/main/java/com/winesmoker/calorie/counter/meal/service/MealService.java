package com.winesmoker.calorie.counter.meal.service;

import com.winesmoker.calorie.counter.enums.MealIntake;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface MealService {

    String create(Message message, MealIntake meal);

    void deleteAllByUserId(Message message);
}
