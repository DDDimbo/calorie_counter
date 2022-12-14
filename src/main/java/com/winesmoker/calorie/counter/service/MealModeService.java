package com.winesmoker.calorie.counter.service;

import com.winesmoker.calorie.counter.enums.MealIntake;
import com.winesmoker.calorie.counter.service.impl.HashMapModeService;

public interface MealModeService {

  static MealModeService getInstance() {
    return new HashMapModeService();
  }

  MealIntake getTargetMeal(long chatId);

  void setTargetMeal(long chatId, MealIntake meal);
}
