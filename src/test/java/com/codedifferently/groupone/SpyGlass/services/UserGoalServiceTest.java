package com.codedifferently.groupone.SpyGlass.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
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
import com.codedifferently.groupone.SpyGlass.exceptions.user.UserNotFoundException;
import com.codedifferently.groupone.SpyGlass.repos.GoalRepo;
import com.codedifferently.groupone.SpyGlass.repos.UserRepo;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserGoalService.class})
@ExtendWith(SpringExtension.class)
public class UserGoalServiceTest {
    @MockBean
    private GoalRepo goalRepo;

    @Autowired
    private UserGoalService userGoalService;

    @MockBean
    private UserRepo userRepo;

    @Test
    public void testAddGoalToUser() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setUsername("janedoe");
        user.setId(123L);
        user.setEnabled(true);
        user.setLocked(true);
        user.setGoals(new ArrayList<Goal>());
        user.setUserRole(UserRole.USER);
        Optional<User> ofResult = Optional.<User>of(user);

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setUsername("janedoe");
        user1.setId(123L);
        user1.setEnabled(true);
        user1.setLocked(true);
        user1.setGoals(new ArrayList<Goal>());
        user1.setUserRole(UserRole.USER);
        when(this.userRepo.save((User) any())).thenReturn(user1);
        when(this.userRepo.findById((Long) any())).thenReturn(ofResult);

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setPassword("iloveyou");
        user2.setUsername("janedoe");
        user2.setId(123L);
        user2.setEnabled(true);
        user2.setLocked(true);
        user2.setGoals(new ArrayList<Goal>());
        user2.setUserRole(UserRole.USER);

        Goal goal = new Goal();
        goal.setContributions(new ArrayList<Contribution>());
        goal.setUser(user2);
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
        Optional<Goal> ofResult1 = Optional.<Goal>of(goal);

        User user3 = new User();
        user3.setEmail("jane.doe@example.org");
        user3.setPassword("iloveyou");
        user3.setUsername("janedoe");
        user3.setId(123L);
        user3.setEnabled(true);
        user3.setLocked(true);
        user3.setGoals(new ArrayList<Goal>());
        user3.setUserRole(UserRole.USER);

        Goal goal1 = new Goal();
        goal1.setContributions(new ArrayList<Contribution>());
        goal1.setUser(user3);
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
        when(this.goalRepo.save((Goal) any())).thenReturn(goal1);
        when(this.goalRepo.findById((Long) any())).thenReturn(ofResult1);
        this.userGoalService.addGoalToUser(123L, 123L);
        verify(this.userRepo).findById((Long) any());
        verify(this.userRepo).save((User) any());
        verify(this.goalRepo).findById((Long) any());
        verify(this.goalRepo).save((Goal) any());
    }

    @Test
    public void testAddGoalToUser2() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setUsername("janedoe");
        user.setId(123L);
        user.setEnabled(true);
        user.setLocked(true);
        user.setGoals(new ArrayList<Goal>());
        user.setUserRole(UserRole.USER);
        when(this.userRepo.save((User) any())).thenReturn(user);
        when(this.userRepo.findById((Long) any())).thenReturn(Optional.<User>empty());

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setUsername("janedoe");
        user1.setId(123L);
        user1.setEnabled(true);
        user1.setLocked(true);
        user1.setGoals(new ArrayList<Goal>());
        user1.setUserRole(UserRole.USER);

        Goal goal = new Goal();
        goal.setContributions(new ArrayList<Contribution>());
        goal.setUser(user1);
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

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setPassword("iloveyou");
        user2.setUsername("janedoe");
        user2.setId(123L);
        user2.setEnabled(true);
        user2.setLocked(true);
        user2.setGoals(new ArrayList<Goal>());
        user2.setUserRole(UserRole.USER);

        Goal goal1 = new Goal();
        goal1.setContributions(new ArrayList<Contribution>());
        goal1.setUser(user2);
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
        when(this.goalRepo.save((Goal) any())).thenReturn(goal1);
        when(this.goalRepo.findById((Long) any())).thenReturn(ofResult);
        assertThrows(UserNotFoundException.class, () -> this.userGoalService.addGoalToUser(123L, 123L));
        verify(this.userRepo).findById((Long) any());
        verify(this.goalRepo).findById((Long) any());
    }

    @Test
    public void testDeleteGoalFromUser() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setUsername("janedoe");
        user.setId(123L);
        user.setEnabled(true);
        user.setLocked(true);
        user.setGoals(new ArrayList<Goal>());
        user.setUserRole(UserRole.USER);
        Optional<User> ofResult = Optional.<User>of(user);

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setUsername("janedoe");
        user1.setId(123L);
        user1.setEnabled(true);
        user1.setLocked(true);
        user1.setGoals(new ArrayList<Goal>());
        user1.setUserRole(UserRole.USER);
        when(this.userRepo.save((User) any())).thenReturn(user1);
        when(this.userRepo.findById((Long) any())).thenReturn(ofResult);

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setPassword("iloveyou");
        user2.setUsername("janedoe");
        user2.setId(123L);
        user2.setEnabled(true);
        user2.setLocked(true);
        user2.setGoals(new ArrayList<Goal>());
        user2.setUserRole(UserRole.USER);

        Goal goal = new Goal();
        goal.setContributions(new ArrayList<Contribution>());
        goal.setUser(user2);
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
        Optional<Goal> ofResult1 = Optional.<Goal>of(goal);
        doNothing().when(this.goalRepo).delete((Goal) any());
        when(this.goalRepo.findById((Long) any())).thenReturn(ofResult1);
        this.userGoalService.deleteGoalFromUser(123L, 123L);
        verify(this.userRepo).findById((Long) any());
        verify(this.userRepo).save((User) any());
        verify(this.goalRepo).delete((Goal) any());
        verify(this.goalRepo).findById((Long) any());
    }

    @Test
    public void testDeleteGoalFromUser2() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setUsername("janedoe");
        user.setId(123L);
        user.setEnabled(true);
        user.setLocked(true);
        user.setGoals(new ArrayList<Goal>());
        user.setUserRole(UserRole.USER);
        when(this.userRepo.save((User) any())).thenReturn(user);
        when(this.userRepo.findById((Long) any())).thenReturn(Optional.<User>empty());

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setUsername("janedoe");
        user1.setId(123L);
        user1.setEnabled(true);
        user1.setLocked(true);
        user1.setGoals(new ArrayList<Goal>());
        user1.setUserRole(UserRole.USER);
        Timestamp timestamp = mock(Timestamp.class);
        when(timestamp.toInstant()).thenReturn(null);

        Goal goal = new Goal();
        goal.setContributions(new ArrayList<Contribution>());
        goal.setUser(user1);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        goal.setDeadLine(Date.from(atStartOfDayResult.atZone(ZoneId.systemDefault()).toInstant()));
        goal.setCurrentlySaved(10.0);
        goal.setFrequency(Frequency.DAILY);
        goal.setId(123L);
        goal.setTimeStamp(timestamp);
        goal.setPictureURL("https://example.org/example");
        goal.setDescription("The characteristics of someone or something");
        goal.setGoalAmount(10.0);
        goal.setContributionAmount(10.0);
        goal.setPriority(Priority.LOW);
        Optional<Goal> ofResult = Optional.<Goal>of(goal);
        doNothing().when(this.goalRepo).delete((Goal) any());
        when(this.goalRepo.findById((Long) any())).thenReturn(ofResult);
        assertThrows(UserNotFoundException.class, () -> this.userGoalService.deleteGoalFromUser(123L, 123L));
        verify(this.userRepo).findById((Long) any());
        verify(this.goalRepo).findById((Long) any());
    }

    @Test
    public void testDeleteGoalFromUser3() {
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

        ArrayList<Goal> goalList = new ArrayList<Goal>();
        goalList.add(goal);

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setUsername("janedoe");
        user1.setId(123L);
        user1.setEnabled(true);
        user1.setLocked(true);
        user1.setGoals(goalList);
        user1.setUserRole(UserRole.USER);
        Optional<User> ofResult = Optional.<User>of(user1);

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setPassword("iloveyou");
        user2.setUsername("janedoe");
        user2.setId(123L);
        user2.setEnabled(true);
        user2.setLocked(true);
        user2.setGoals(new ArrayList<Goal>());
        user2.setUserRole(UserRole.USER);
        when(this.userRepo.save((User) any())).thenReturn(user2);
        when(this.userRepo.findById((Long) any())).thenReturn(ofResult);
        doNothing().when(this.goalRepo).delete((Goal) any());
        when(this.goalRepo.findById((Long) any())).thenReturn(Optional.<Goal>empty());

        User user3 = new User();
        user3.setEmail("jane.doe@example.org");
        user3.setPassword("iloveyou");
        user3.setUsername("janedoe");
        user3.setId(123L);
        user3.setEnabled(true);
        user3.setLocked(true);
        user3.setGoals(new ArrayList<Goal>());
        user3.setUserRole(UserRole.USER);
        Timestamp timestamp = mock(Timestamp.class);
        when(timestamp.toInstant()).thenReturn(null);

        Goal goal1 = new Goal();
        goal1.setContributions(new ArrayList<Contribution>());
        goal1.setUser(user3);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        goal1.setDeadLine(Date.from(atStartOfDayResult1.atZone(ZoneId.systemDefault()).toInstant()));
        goal1.setCurrentlySaved(10.0);
        goal1.setFrequency(Frequency.DAILY);
        goal1.setId(123L);
        goal1.setTimeStamp(timestamp);
        goal1.setPictureURL("https://example.org/example");
        goal1.setDescription("The characteristics of someone or something");
        goal1.setGoalAmount(10.0);
        goal1.setContributionAmount(10.0);
        goal1.setPriority(Priority.LOW);
        assertThrows(GoalNotFoundException.class, () -> this.userGoalService.deleteGoalFromUser(123L, 123L));
        verify(this.goalRepo).findById((Long) any());
    }
}

