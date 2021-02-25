package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final Meal Meal1User = new Meal(START_SEQ + 2, LocalDateTime.of(2020, 1, 30, 10, 0, 0), "Завтрак", 500);
    public static final Meal Meal2User = new Meal(START_SEQ + 3, LocalDateTime.of(2020, 1, 30, 13, 0, 0), "Обед", 1500);
    public static final Meal Meal3User = new Meal(START_SEQ + 4, LocalDateTime.of(2020, 1, 30, 20, 0, 0), "Ужин", 500);
    public static final Meal Meal4User = new Meal(START_SEQ + 5, LocalDateTime.of(2020, 1, 31, 0, 0, 0), "Еда на граничное значение", 100);
    public static final Meal Meal5User = new Meal(START_SEQ + 6, LocalDateTime.of(2020, 1, 31, 10, 0, 0), "Завтрак", 500);
    public static final Meal Meal6User = new Meal(START_SEQ + 7, LocalDateTime.of(2020, 1, 31, 13, 0, 0), "Обед", 1000);
    public static final Meal Meal7User = new Meal(START_SEQ + 8, LocalDateTime.of(2020, 1, 31, 20, 0, 0), "Ужин", 510);
    public static final Meal Meal8Admin = new Meal(START_SEQ + 9, LocalDateTime.of(2015, 6, 1, 14, 0, 0), "Админ ланч", 510);
    public static final Meal Meal9Admin = new Meal(START_SEQ + 10, LocalDateTime.of(2015, 6, 1, 21, 0, 0), "Админ ужин", 1500);

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static Meal getNew() {
        return new Meal(LocalDateTime.of(2021, 2, 24, 15, 0, 0), "mealDescription", 500);
    }

    public static Meal getUpdated() {
        Meal newMeal = new Meal(Meal4User);
        newMeal.setDateTime(LocalDateTime.of(2021, 2, 25, 10, 0, 0));
        newMeal.setDescription("newDescription");
        newMeal.setCalories(999);
        return newMeal;
    }
}