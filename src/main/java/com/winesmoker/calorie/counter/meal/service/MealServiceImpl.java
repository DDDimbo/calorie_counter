package com.winesmoker.calorie.counter.meal.service;

import com.winesmoker.calorie.counter.enums.MealIntake;
import com.winesmoker.calorie.counter.exceptions.UserNotFoundException;
import com.winesmoker.calorie.counter.meal.Meal;
import com.winesmoker.calorie.counter.meal.MealRepository;
import com.winesmoker.calorie.counter.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jvnet.hk2.annotations.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.net.UnknownServiceException;
import java.time.LocalDate;

@Service
@Transactional
@RequiredArgsConstructor
public class MealServiceImpl extends MealService {

    private final MealRepository mealRepository;

    private final UserRepository userRepository;

    @Override
    public String create(Message message, MealIntake meal) {
        String[] params = message.getText().split(" ");
        Meal.builder()
                .type(meal)
                .name(params[0])
                .protein(Double.parseDouble(params[1]))
                .carbohydrate(Double.parseDouble(params[2]))
                .fat(Double.parseDouble(params[3]))
                .fiber(Double.parseDouble(params[4]))
                .alcohol(Double.parseDouble(params[5]))
                .userId(userRepository.findIdByTgId(message.getFrom().getId())
                        .orElseThrow(() -> new UserNotFoundException("Пользователь с таким id не найден")))
                .added(LocalDate.now());
        return null;
    }
}
