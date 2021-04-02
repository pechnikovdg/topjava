package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.web.formatters.LocalDateFormatter;
import ru.javawebinar.topjava.web.formatters.LocalTimeFormatter;

import java.net.URI;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(value = MealRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MealRestController extends AbstractMealController {

    @Autowired
    private LocalDateFormatter localDateFormatter;

    @Autowired
    private LocalTimeFormatter localTimeFormatter;

    public static final String REST_URL = "/rest/meals";

    @Override
    @GetMapping("/{id}")
    public Meal get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @GetMapping
    public List<MealTo> getAll() {
        return super.getAll();
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> createWithLocation(@RequestBody Meal meal) {
        Meal created = super.create(meal);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Meal meal, @PathVariable int id) {
        super.update(meal, id);
    }

    @GetMapping("/filter")
    public List<MealTo> getBetweenRest(@RequestParam String startDate, @RequestParam String endDate,
                                       @RequestParam String startTime, @RequestParam String endTime) throws ParseException {
        LocalDate startLocalDate = localDateFormatter.parse(startDate, Locale.getDefault());
        LocalDate endLocalDate = localDateFormatter.parse(endDate, Locale.getDefault());
        LocalTime startLocalTime = localTimeFormatter.parse(startTime, Locale.getDefault());
        LocalTime endLocalTime = localTimeFormatter.parse(endTime, Locale.getDefault());

        return super.getBetween(startLocalDate, startLocalTime, endLocalDate, endLocalTime);
    }

}




























