package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final Meal MEAL_1 = new Meal(START_SEQ + 2, LocalDateTime.of(2020, 1, 30, 10, 0, 0), "Завтрак", 500);
    public static final Meal MEAL_2 = new Meal(START_SEQ + 3, LocalDateTime.of(2020, 1, 30, 13, 0, 0), "Обед", 1500);
    public static final Meal MEAL_3 = new Meal(START_SEQ + 4, LocalDateTime.of(2020, 1, 30, 20, 0, 0), "Ужин", 500);
    public static final Meal MEAL_4 = new Meal(START_SEQ + 5, LocalDateTime.of(2020, 1, 31, 0, 0, 0), "Еда на граничное значение", 100);
    public static final Meal MEAL_5 = new Meal(START_SEQ + 6, LocalDateTime.of(2020, 1, 31, 10, 0, 0), "Завтрак", 500);
    public static final Meal MEAL_6 = new Meal(START_SEQ + 7, LocalDateTime.of(2020, 1, 31, 13, 0, 0), "Обед", 1000);
    public static final Meal MEAL_7 = new Meal(START_SEQ + 8, LocalDateTime.of(2020, 1, 31, 20, 0, 0), "Ужин", 510);
    public static final Meal MEAL_8 = new Meal(START_SEQ + 9, LocalDateTime.of(2020, 1, 30, 10, 0, 0), "Админ ланч", 510);
    public static final Meal MEAL_9 = new Meal(START_SEQ + 10, LocalDateTime.of(2020, 1, 30, 10, 0, 0), "Админ ужин", 1500);

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static Meal getNew() {
        return new Meal(LocalDateTime.of(2021, 2, 24, 15, 0, 0), "mealDescription", 500);
    }

    public static Meal getUpdated() {
        Meal newMeal = new Meal(MEAL_4);
        newMeal.setDateTime(LocalDateTime.of(2021, 2, 25, 10, 0, 0));
        newMeal.setDescription("newDescription");
        newMeal.setCalories(999);
        return newMeal;
    }
}