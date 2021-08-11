package com.codedifferently.groupone.SpyGlass.controllers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.codedifferently.groupone.SpyGlass.entities.Contribution;
import com.codedifferently.groupone.SpyGlass.entities.Goal;
import com.codedifferently.groupone.SpyGlass.enums.Frequency;
import com.codedifferently.groupone.SpyGlass.enums.Priority;
import com.codedifferently.groupone.SpyGlass.services.GoalService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {GoalController.class})
@ExtendWith(SpringExtension.class) // enables the mockito extension
public class GoalControllerTest {
    @Autowired
    private GoalController goalController;

    @MockBean
    private GoalService goalService;

    private Goal goal;

    @BeforeEach
    void setUp(){
        goal = new Goal();
        goal.setContributions(new ArrayList<>());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        goal.setDeadLine(Date.from(atStartOfDayResult.atZone(ZoneId.systemDefault()).toInstant()));
        goal.setCurrentlySaved(10.0);
        goal.setFrequency(Frequency.DAILY);
        goal.setId(123L);
        goal.setTimeStamp(mock(Timestamp.class));
        goal.setPictureURL("https://example.org/example");
        goal.setDescription("The characteristics of someone or something");
        goal.setGoalAmount(10.0);
        goal.setContributionAmount(10.0);
        goal.setPriority(Priority.LOW);
    }

    @Test
    public void testGetGoals() throws Exception {
        when(this.goalService.getGoals()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/goals");
        MockMvcBuilders.standaloneSetup(this.goalController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testGetGoals2() throws Exception {
        when(this.goalService.getGoals()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/goals");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.goalController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testGetGoalById() throws Exception {
        Optional<Goal> ofResult = Optional.of(goal);
        when(this.goalService.getGoalById(any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/goals/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.goalController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(500))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"description\":\"The characteristics of someone or something\",\"deadLine\":18000000}"));
    }

    @Test
    public void testGetGoalByInvalidId() throws Exception {
        Optional<Goal> ofResult = Optional.of(goal);
        when(this.goalService.getGoalById(any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/goals/{id}", 111L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.goalController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(500))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

    @Test
    public void testAddContribution() throws Exception {
        when(this.goalService.addContribution(any(), any()))
                .thenReturn(new ResponseEntity(HttpStatus.CONTINUE));
        Contribution contribution = new Contribution();
        contribution.setAmount(10.0);
        contribution.setGoal(goal);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        contribution.setContributionDate(Date.from(atStartOfDayResult1.atZone(ZoneId.systemDefault()).toInstant()));
        String content = (new ObjectMapper()).writeValueAsString(contribution);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/goals/contribute/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.goalController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    @Test
    public void testDeleteToDo() throws Exception {
        when(this.goalService.deleteGoal(any())).thenReturn(new ResponseEntity(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/goals/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.goalController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

}

