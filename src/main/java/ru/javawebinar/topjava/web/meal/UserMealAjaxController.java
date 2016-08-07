package ru.javawebinar.topjava.web.meal;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.to.UserMealWithExceed;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by T on 07.08.2016.
 */

@RestController
@RequestMapping("/ajax/user/meals")
public class UserMealAjaxController extends AbstractUserMealController {

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserMealWithExceed> getAll() {
        return super.getAll();
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createWithLocation(
            @RequestParam("localDateTime") @DateTimeFormat LocalDateTime ltd,
            @RequestParam("description") String description,
            @RequestParam("calories") Integer calories) {
        super.create(new UserMeal(ltd, description, calories));
    }

}
