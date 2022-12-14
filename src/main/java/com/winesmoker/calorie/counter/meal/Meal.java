package com.winesmoker.calorie.counter.meal;

import com.winesmoker.calorie.counter.enums.MealIntake;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Builder
@Getter @Setter
@ToString
@Entity
@Table(name = "meals")
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private MealIntake type;

    @NotBlank
    private String name;

    private Double protein;

    private Double carbohydrate;

    private Double fat;

    private Double fiber;

    private Double alcohol;

    private Double calories;

    @NotNull
    @Column(name = "user_id")
    private Long userId;

    private LocalDate added;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Meal meal = (Meal) o;
        return Objects.equals(id, meal.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
