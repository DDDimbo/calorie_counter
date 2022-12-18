package com.winesmoker.calorie.counter.meal;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal, Long> {

    @Override
    <S extends Meal> S save(S entity);

    void deleteAllByUserId(Long id);
}
