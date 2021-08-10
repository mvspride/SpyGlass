package com.codedifferently.groupone.SpyGlass.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.codedifferently.groupone.SpyGlass.entities.Contribution;
import com.codedifferently.groupone.SpyGlass.entities.Goal;
import com.codedifferently.groupone.SpyGlass.entities.User;
import com.codedifferently.groupone.SpyGlass.enums.Frequency;
import com.codedifferently.groupone.SpyGlass.enums.Priority;
import com.codedifferently.groupone.SpyGlass.enums.UserRole;
import com.codedifferently.groupone.SpyGlass.exceptions.goal.GoalNotFoundException;
import com.codedifferently.groupone.SpyGlass.repos.ContributionRepository;
import com.codedifferently.groupone.SpyGlass.repos.GoalRepo;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {GoalService.class})
@ExtendWith(SpringExtension.class)
public class GoalServiceTest {
    @MockBean
    private ContributionRepository contributionRepository;

    @MockBean
    private GoalRepo goalRepo;

    @Autowired
    private GoalService goalService;

    private Goal goal;
    private User user;


    @BeforeEach
    void setUp(){
        user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setUsername("janedoe");
        user.setEnabled(true);
        user.setLocked(true);
        user.setUserRole(UserRole.USER);
        goal = new Goal();
        goal.setContributions(new ArrayList<>());
        goal.setUser(user);
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
    public void testGetGoals() {
        ArrayList<Goal> goalList = new ArrayList<>();
        when(this.goalRepo.findAll()).thenReturn(goalList);
        List<Goal> actualGoals = this.goalService.getGoals();
        assertSame(goalList, actualGoals);
        assertTrue(actualGoals.isEmpty());
        verify(this.goalRepo).findAll();
    }

    @Test
    public void testGetGoalById() throws GoalNotFoundException {
        Optional<Goal> ofResult = Optional.of(goal);
        when(this.goalRepo.findById(any())).thenReturn(ofResult);
        when(this.goalRepo.existsById(any())).thenReturn(true);
        Optional<Goal> actualGoalById = this.goalService.getGoalById(123L);
        assertSame(ofResult, actualGoalById);
        assertTrue(actualGoalById.isPresent());
        verify(this.goalRepo).existsById(any());
        verify(this.goalRepo).findById(any());
        assertTrue(this.goalService.getGoals().isEmpty());
    }

    @Test
    public void testEditGoal() throws GoalNotFoundException {
        Optional<Goal> ofResult = Optional.of(goal);
        when(this.goalRepo.updateGoal(anyString(), any(), any(), any(), anyDouble(),
                anyDouble(), anyDouble(), anyString(), any())).thenReturn(1);
        when(this.goalRepo.findById(any())).thenReturn(ofResult);

        ResponseEntity actualEditGoalResult = this.goalService.editGoal(123L, goal);
        assertTrue(actualEditGoalResult.hasBody());
        assertTrue(actualEditGoalResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualEditGoalResult.getStatusCode());
        verify(this.goalRepo).findById(any());
        verify(this.goalRepo).updateGoal(anyString(), any(), any(), any(), anyDouble(),
                anyDouble(), anyDouble(), anyString(), any());
    }

    @Test
    public void testDeleteGoal() throws GoalNotFoundException {
        doNothing().when(this.goalRepo).deleteById(any());
        when(this.goalRepo.existsById(any())).thenReturn(true);
        ResponseEntity actualDeleteGoalResult = this.goalService.deleteGoal(123L);
        assertNull(actualDeleteGoalResult.getBody());
        assertEquals("<200 OK OK,[]>", actualDeleteGoalResult.toString());
        assertEquals(HttpStatus.OK, actualDeleteGoalResult.getStatusCode());
        assertTrue(actualDeleteGoalResult.getHeaders().isEmpty());
        verify(this.goalRepo).deleteById(any());
        verify(this.goalRepo).existsById(any());
        assertTrue(this.goalService.getGoals().isEmpty());
    }

    @Test
    public void testAddContribution() {
        when(this.goalRepo.updateGoal(anyString(), any(), any(), any(), anyDouble(),
                anyDouble(), anyDouble(), anyString(), any())).thenReturn(1);
        when(this.goalRepo.getById(any())).thenReturn(goal);

        Contribution contribution = new Contribution();
        contribution.setAmount(10.0);
        contribution.setGoal(goal);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        contribution.setContributionDate(Date.from(atStartOfDayResult2.atZone(ZoneId.systemDefault()).toInstant()));
        when(this.contributionRepository.save(any())).thenReturn(contribution);

        Contribution contribution1 = new Contribution();
        contribution1.setAmount(10.0);
        contribution1.setGoal(goal);
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        contribution1.setContributionDate(Date.from(atStartOfDayResult4.atZone(ZoneId.systemDefault()).toInstant()));
        ResponseEntity actualAddContributionResult = this.goalService.addContribution(123L, contribution1);
        assertTrue(actualAddContributionResult.hasBody());
        assertTrue(actualAddContributionResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualAddContributionResult.getStatusCode());
        Goal goal3 = ((Contribution) Objects.requireNonNull(actualAddContributionResult.getBody())).getGoal();
        assertSame(goal, goal3);
        assertEquals(1, goal3.getContributions().size());
        verify(this.goalRepo).getById(any());
        verify(this.goalRepo).updateGoal(anyString(), any(), any(), any(), anyDouble(),
                anyDouble(), anyDouble(), anyString(), any());
        verify(this.contributionRepository).save(any());
    }
}

