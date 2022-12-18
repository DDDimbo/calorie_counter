package com.winesmoker.calorie.counter.meal;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CalorieCalculator {

    public static Long calculateCalories(String[] calories) {
        return Math.round(Double.parseDouble(calories[1]) * 4
                + Double.parseDouble(calories[2]) * 4
                + Double.parseDouble(calories[3]) * 9
                + Double.parseDouble(calories[4]) * 1.5
                + Double.parseDouble(calories[5]) * 7);
    }
}
