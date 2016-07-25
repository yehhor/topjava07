package ru.javawebinar.topjava.web.meal;

import org.junit.Test;
import org.springframework.http.MediaType;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;


import java.time.Month;

import static java.time.LocalDateTime.of;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER_ID;


/**
 * Created by yehhor.k on 24.07.2016.
 */
public class UserMealRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = UserMealRestController.REST_URL + "/";


    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + MEAL1.getId()))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(MEAL1));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + MEAL1.getId()))
                .andExpect(status().isOk());
        MATCHER.assertCollectionEquals(USER_MEALS_TEST_DELETED, userMealService.getAll(USER_ID));
    }

    @Test
    public void testUpdate() throws Exception {
        UserMeal updated = getUpdated();

        mockMvc.perform(put(REST_URL + updated.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());
        MATCHER.assertEquals(updated, userMealService.get(updated.getId(), AuthorizedUser.id));
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        MATCHER.assertCollectionEquals(USER_MEALS, userMealService.getAll(USER_ID));
    }

    @Test
    public void testGetBetween() throws Exception {
        mockMvc.perform(post(REST_URL + "/between")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(of(2015, Month.MAY, 30, 20, 0)))
                .content(JsonUtil.writeValue(of(2015, Month.MAY, 31, 13, 0))))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }
}