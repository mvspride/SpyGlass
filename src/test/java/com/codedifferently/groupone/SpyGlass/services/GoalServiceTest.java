package com.codedifferently.groupone.SpyGlass.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @Test
    public void testGetGoals() {
        ArrayList<Goal> goalList = new ArrayList<Goal>();
        when(this.goalRepo.findAll()).thenReturn(goalList);
        List<Goal> actualGoals = this.goalService.getGoals();
        assertSame(goalList, actualGoals);
        assertTrue(actualGoals.isEmpty());
        verify(this.goalRepo).findAll();
    }

    @Test
    public void testGetGoalById() throws GoalNotFoundException {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setUsername("janedoe");
        user.setId(123L);
        user.setEnabled(true);
        user.setLocked(true);
        user.setGoals(new ArrayList<Goal>());
        user.setUserRole(UserRole.USER);

        Goal goal = new Goal();
        goal.setContributions(new ArrayList<Contribution>());
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
        Optional<Goal> ofResult = Optional.<Goal>of(goal);
        when(this.goalRepo.findById((Long) any())).thenReturn(ofResult);
        when(this.goalRepo.existsById((Long) any())).thenReturn(true);
        Optional<Goal> actualGoalById = this.goalService.getGoalById(123L);
        assertSame(ofResult, actualGoalById);
        assertTrue(actualGoalById.isPresent());
        verify(this.goalRepo).existsById((Long) any());
        verify(this.goalRepo).findById((Long) any());
        assertTrue(this.goalService.getGoals().isEmpty());
    }

    @Test
    public void testGetGoalById2() throws GoalNotFoundException {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setUsername("janedoe");
        user.setId(123L);
        user.setEnabled(true);
        user.setLocked(true);
        user.setGoals(new ArrayList<Goal>());
        user.setUserRole(UserRole.USER);

        Goal goal = new Goal();
        goal.setContributions(new ArrayList<Contribution>());
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
        Optional<Goal> ofResult = Optional.<Goal>of(goal);
        when(this.goalRepo.findById((Long) any())).thenReturn(ofResult);
        when(this.goalRepo.existsById((Long) any())).thenReturn(false);
        assertThrows(GoalNotFoundException.class, () -> this.goalService.getGoalById(123L));
        verify(this.goalRepo).existsById((Long) any());
    }

    @Test
    public void testEditGoal() throws GoalNotFoundException {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setUsername("janedoe");
        user.setId(123L);
        user.setEnabled(true);
        user.setLocked(true);
        user.setGoals(new ArrayList<Goal>());
        user.setUserRole(UserRole.USER);

        Goal goal = new Goal();
        goal.setContributions(new ArrayList<Contribution>());
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
        Optional<Goal> ofResult = Optional.<Goal>of(goal);
        when(this.goalRepo.updateGoal(anyString(), (Date) any(), (Frequency) any(), (Priority) any(), anyDouble(),
                anyDouble(), anyDouble(), anyString(), (Long) any())).thenReturn(1);
        when(this.goalRepo.findById((Long) any())).thenReturn(ofResult);

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setUsername("janedoe");
        user1.setId(123L);
        user1.setEnabled(true);
        user1.setLocked(true);
        user1.setGoals(new ArrayList<Goal>());
        user1.setUserRole(UserRole.USER);

        Goal goal1 = new Goal();
        goal1.setContributions(new ArrayList<Contribution>());
        goal1.setUser(user1);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        goal1.setDeadLine(Date.from(atStartOfDayResult1.atZone(ZoneId.systemDefault()).toInstant()));
        goal1.setCurrentlySaved(10.0);
        goal1.setFrequency(Frequency.DAILY);
        goal1.setId(123L);
        goal1.setTimeStamp(mock(Timestamp.class));
        goal1.setPictureURL("https://example.org/example");
        goal1.setDescription("The characteristics of someone or something");
        goal1.setGoalAmount(10.0);
        goal1.setContributionAmount(10.0);
        goal1.setPriority(Priority.LOW);
        ResponseEntity actualEditGoalResult = this.goalService.editGoal(123L, goal1);
        assertTrue(actualEditGoalResult.hasBody());
        assertTrue(actualEditGoalResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualEditGoalResult.getStatusCode());
        verify(this.goalRepo).findById((Long) any());
        verify(this.goalRepo).updateGoal(anyString(), (Date) any(), (Frequency) any(), (Priority) any(), anyDouble(),
                anyDouble(), anyDouble(), anyString(), (Long) any());
    }

    @Test
    public void testEditGoal2() throws GoalNotFoundException {
        when(this.goalRepo.updateGoal(anyString(), (Date) any(), (Frequency) any(), (Priority) any(), anyDouble(),
                anyDouble(), anyDouble(), anyString(), (Long) any())).thenReturn(1);
        when(this.goalRepo.findById((Long) any())).thenReturn(Optional.<Goal>empty());

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setUsername("janedoe");
        user.setId(123L);
        user.setEnabled(true);
        user.setLocked(true);
        user.setGoals(new ArrayList<Goal>());
        user.setUserRole(UserRole.USER);

        Goal goal = new Goal();
        goal.setContributions(new ArrayList<Contribution>());
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
        assertThrows(GoalNotFoundException.class, () -> this.goalService.editGoal(123L, goal));
        verify(this.goalRepo).findById((Long) any());
    }

    @Test
    public void testDeleteGoal() throws GoalNotFoundException {
        doNothing().when(this.goalRepo).deleteById((Long) any());
        when(this.goalRepo.existsById((Long) any())).thenReturn(true);
        ResponseEntity actualDeleteGoalResult = this.goalService.deleteGoal(123L);
        assertNull(actualDeleteGoalResult.getBody());
        assertEquals("<200 OK OK,[]>", actualDeleteGoalResult.toString());
        assertEquals(HttpStatus.OK, actualDeleteGoalResult.getStatusCode());
        assertTrue(actualDeleteGoalResult.getHeaders().isEmpty());
        verify(this.goalRepo).deleteById((Long) any());
        verify(this.goalRepo).existsById((Long) any());
        assertTrue(this.goalService.getGoals().isEmpty());
    }

    @Test
    public void testDeleteGoal2() throws GoalNotFoundException {
        doNothing().when(this.goalRepo).deleteById((Long) any());
        when(this.goalRepo.existsById((Long) any())).thenReturn(false);
        assertThrows(GoalNotFoundException.class, () -> this.goalService.deleteGoal(123L));
        verify(this.goalRepo).existsById((Long) any());
    }

    @Test
    public void testAddContribution() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setUsername("janedoe");
        user.setId(123L);
        user.setEnabled(true);
        user.setLocked(true);
        user.setGoals(new ArrayList<Goal>());
        user.setUserRole(UserRole.USER);

        Goal goal = new Goal();
        goal.setContributions(new ArrayList<Contribution>());
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
        when(this.goalRepo.updateGoal(anyString(), (Date) any(), (Frequency) any(), (Priority) any(), anyDouble(),
                anyDouble(), anyDouble(), anyString(), (Long) any())).thenReturn(1);
        when(this.goalRepo.getById((Long) any())).thenReturn(goal);

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setUsername("janedoe");
        user1.setId(123L);
        user1.setEnabled(true);
        user1.setLocked(true);
        user1.setGoals(new ArrayList<Goal>());
        user1.setUserRole(UserRole.USER);

        Goal goal1 = new Goal();
        goal1.setContributions(new ArrayList<Contribution>());
        goal1.setUser(user1);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        goal1.setDeadLine(Date.from(atStartOfDayResult1.atZone(ZoneId.systemDefault()).toInstant()));
        goal1.setCurrentlySaved(10.0);
        goal1.setFrequency(Frequency.DAILY);
        goal1.setId(123L);
        goal1.setTimeStamp(mock(Timestamp.class));
        goal1.setPictureURL("https://example.org/example");
        goal1.setDescription("The characteristics of someone or something");
        goal1.setGoalAmount(10.0);
        goal1.setContributionAmount(10.0);
        goal1.setPriority(Priority.LOW);

        Contribution contribution = new Contribution();
        contribution.setAmount(10.0);
        contribution.setGoal(goal1);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        contribution.setContributionDate(Date.from(atStartOfDayResult2.atZone(ZoneId.systemDefault()).toInstant()));
        when(this.contributionRepository.save((Contribution) any())).thenReturn(contribution);

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setPassword("iloveyou");
        user2.setUsername("janedoe");
        user2.setId(123L);
        user2.setEnabled(true);
        user2.setLocked(true);
        user2.setGoals(new ArrayList<Goal>());
        user2.setUserRole(UserRole.USER);

        Goal goal2 = new Goal();
        goal2.setContributions(new ArrayList<Contribution>());
        goal2.setUser(user2);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        goal2.setDeadLine(Date.from(atStartOfDayResult3.atZone(ZoneId.systemDefault()).toInstant()));
        goal2.setCurrentlySaved(10.0);
        goal2.setFrequency(Frequency.DAILY);
        goal2.setId(123L);
        goal2.setTimeStamp(mock(Timestamp.class));
        goal2.setPictureURL("https://example.org/example");
        goal2.setDescription("The characteristics of someone or something");
        goal2.setGoalAmount(10.0);
        goal2.setContributionAmount(10.0);
        goal2.setPriority(Priority.LOW);

        Contribution contribution1 = new Contribution();
        contribution1.setAmount(10.0);
        contribution1.setGoal(goal2);
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        contribution1.setContributionDate(Date.from(atStartOfDayResult4.atZone(ZoneId.systemDefault()).toInstant()));
        ResponseEntity actualAddContributionResult = this.goalService.addContribution(123L, contribution1);
        assertTrue(actualAddContributionResult.hasBody());
        assertTrue(actualAddContributionResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualAddContributionResult.getStatusCode());
        Goal goal3 = ((Contribution) actualAddContributionResult.getBody()).getGoal();
        assertSame(goal, goal3);
        assertEquals(1, goal3.getContributions().size());
        verify(this.goalRepo).getById((Long) any());
        verify(this.goalRepo).updateGoal(anyString(), (Date) any(), (Frequency) any(), (Priority) any(), anyDouble(),
                anyDouble(), anyDouble(), anyString(), (Long) any());
        verify(this.contributionRepository).save((Contribution) any());
    }
}

