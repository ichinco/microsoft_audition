package com.ichinco.foodtrucks;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FoodTruckControllerTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testGetById() throws Exception {
        this.mvc.perform(get("/v1/foodtruck/364218")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> content().string(containsString("The Chai Cart")));
    }

    @Test
    public void testGetByIdMissing() throws Exception {
        this.mvc.perform(get("/v1/foodtruck/364216")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetByBlock() throws Exception {
        this.mvc.perform(get("/v1/foodtrucks?block=5311")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> content().string(containsString("Reecees Soulicious")));
    }

    @Test
    public void testAddTruckHappyPath() throws Exception {
        this.mvc.perform(post("/v1/foodtruck")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"locationId\":1, \"block\":\"abc\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testMissingData() throws Exception {
        this.mvc.perform(post("/v1/foodtruck")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"block\":\"abc\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testDuplicateId() throws Exception {
        this.mvc.perform(post("/v1/foodtruck")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"locationId\":364218, \"block\":\"abc\"}"))
                .andExpect(status().isBadRequest());
    }
}
