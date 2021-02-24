package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        //надо ли здесь это, учитывая, что:
        // add SLF4JBridgeHandler to j.u.l's root logger, should be done once during
        // the initialization phase of your application
        //??
        SLF4JBridgeHandler.install();
    }

    @Autowired
    MealService service;

    @Test
    public void get() {
        Meal meal = service.get(START_SEQ + 6, USER_ID);
        assertMatch(meal, MEAL_5);
    }

    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(START_SEQ + 6, ADMIN_ID));
    }

    @Test
    public void delete() {
        service.delete(START_SEQ + 6, USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(START_SEQ + 6, USER_ID));
    }

    @Test
    public void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(START_SEQ + 6, ADMIN_ID));
    }

    @Test
    public void update() {
        Meal updated = getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(MEAL_4.getId(), USER_ID), getUpdated());
    }

    @Test
    public void updateNotFound() {
        Meal updated = getUpdated();
        assertThrows(NotFoundException.class, () -> service.update(updated, ADMIN_ID));
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> meals = service.getBetweenInclusive(null, LocalDate.of(2020, 1, 30), USER_ID);
        assertThat(meals).isEqualTo(Arrays.asList(MEAL_3, MEAL_2, MEAL_1));
    }

    @Test
    public void getAll() {
        assertThat(service.getAll(ADMIN_ID)).isEqualTo(Arrays.asList(MEAL_9, MEAL_8));
    }

    @Test
    public void create() {
        Meal created = service.create(getNew(), ADMIN_ID);
        Integer newId = created.getId();
        Meal newMeal = getNew();
        newMeal.setId(newId);
        assertMatch(created, newMeal);
        assertMatch(service.get(newId, ADMIN_ID), newMeal);
    }

    @Test
    public void duplicateDateTimeCreate() {
        Meal duplicate = new Meal(LocalDateTime.of(2020, 1, 30, 10, 0, 0),
                "description", 100);
        assertThrows(DataAccessException.class, () -> service.create(duplicate, USER_ID));
    }
}