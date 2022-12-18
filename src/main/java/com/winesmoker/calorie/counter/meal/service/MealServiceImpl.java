package com.winesmoker.calorie.counter.meal.service;

import com.winesmoker.calorie.counter.enums.MealIntake;
import com.winesmoker.calorie.counter.exceptions.UserNotFoundException;
import com.winesmoker.calorie.counter.meal.CalorieCalculator;
import com.winesmoker.calorie.counter.meal.Meal;
import com.winesmoker.calorie.counter.meal.MealRepository;
import com.winesmoker.calorie.counter.user.User;
import com.winesmoker.calorie.counter.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.time.LocalDate;
import java.util.Arrays;

@Service
@Transactional
@RequiredArgsConstructor
public class MealServiceImpl implements MealService {

    private final MealRepository mealRepository;

    private final UserRepository userRepository;

    @Override
    public String create(Message message, MealIntake mealIntake) {
        String[] params = message.getText().split(" ");
        User user = userRepository.findByTgId(message.getFrom().getId())
                .orElseThrow(() -> new UserNotFoundException("Пользователя с таким tgId не существует"));

        Meal result = Meal.builder()
                .type(mealIntake)
                .name(params[0])
                .protein(Double.parseDouble(params[1]))
                .carbohydrate(Double.parseDouble(params[2]))
                .fat(Double.parseDouble(params[3]))
                .fiber(Double.parseDouble(params[4]))
                .alcohol(Double.parseDouble(params[5]))
                .calories(CalorieCalculator.calculateCalories(params))
                .userId(user.getId())
                .added(LocalDate.now())
                .build();
        mealRepository.save(result);
        return "Данные успешно сохранены!";
    }

    @Override
    public void deleteAllByUserId(Message message) {
        System.out.println(message.getFrom().getId());
        User user = userRepository.findByTgId(message.getFrom().getId())
                .orElseThrow(() -> new UserNotFoundException("Пользователя с таким tgId не существует"));
        mealRepository.deleteAllByUserId(user.getId());
    }
}
