package com.codedifferently.groupone.SpyGlass.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

import org.junit.jupiter.api.BeforeEach;
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

    private User user;
    private User user1;
    private User user2;
    private User user3;
    private Goal goal;
    private Goal goal1;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setUsername("janedoe");
        user.setEnabled(true);
        user.setLocked(true);
        user.setUserRole(UserRole.USER);
        user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setUsername("janedoe");
        user1.setEnabled(true);
        user1.setLocked(true);
        user1.setUserRole(UserRole.USER);
        user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setPassword("iloveyou");
        user2.setUsername("janedoe");
        user2.setEnabled(true);
        user2.setLocked(true);
        user2.setUserRole(UserRole.USER);
        user3 = new User();
        user3.setEmail("jane.doe@example.org");
        user3.setPassword("iloveyou");
        user3.setUsername("janedoe");
        user3.setEnabled(true);
        user3.setLocked(true);
        user3.setUserRole(UserRole.USER);
        goal = new Goal();
        goal.setContributions(new ArrayList<>());
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
        goal1 = new Goal();
        goal1.setContributions(new ArrayList<>());
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

    }

    @Test
    public void testAddGoalToUser() {
        Optional<User> ofResult = Optional.of(user);

        when(this.userRepo.save(any())).thenReturn(user1);
        when(this.userRepo.findById(any())).thenReturn(ofResult);

        Optional<Goal> ofResult1 = Optional.of(goal);

        when(this.goalRepo.save(any())).thenReturn(goal1);
        when(this.goalRepo.findById(any())).thenReturn(ofResult1);
        this.userGoalService.addGoalToUser(123L, 123L);
        verify(this.userRepo).findById(any());
        verify(this.userRepo).save(any());
        verify(this.goalRepo).findById(any());
        verify(this.goalRepo).save(any());
    }

    @Test
    public void testAddGoalToUser2() {
        when(this.userRepo.save(any())).thenReturn(user);
        when(this.userRepo.findById(any())).thenReturn(Optional.empty());

        Optional<Goal> ofResult = Optional.of(goal);

        when(this.goalRepo.save(any())).thenReturn(goal1);
        when(this.goalRepo.findById(any())).thenReturn(ofResult);
        assertThrows(UserNotFoundException.class, () -> this.userGoalService.addGoalToUser(123L, 123L));
        verify(this.userRepo).findById(any());
        verify(this.goalRepo).findById(any());
    }

    @Test
    public void testDeleteGoalFromUser() {
        Optional<User> ofResult = Optional.of(user);

        when(this.userRepo.save(any())).thenReturn(user1);
        when(this.userRepo.findById(any())).thenReturn(ofResult);

        Optional<Goal> ofResult1 = Optional.of(goal);
        doNothing().when(this.goalRepo).delete(any());
        when(this.goalRepo.findById(any())).thenReturn(ofResult1);
        this.userGoalService.deleteGoalFromUser(123L, 123L);
        verify(this.userRepo).findById(any());
        verify(this.userRepo).save(any());
        verify(this.goalRepo).delete(any());
        verify(this.goalRepo).findById(any());
    }

    @Test
    public void testDeleteGoalFromUser2() {
        when(this.userRepo.save(any())).thenReturn(user);
        when(this.userRepo.findById(any())).thenReturn(Optional.empty());

        Timestamp timestamp = mock(Timestamp.class);
        when(timestamp.toInstant()).thenReturn(null);

        Optional<Goal> ofResult = Optional.of(goal);
        doNothing().when(this.goalRepo).delete(any());
        when(this.goalRepo.findById(any())).thenReturn(ofResult);
        assertThrows(UserNotFoundException.class, () -> this.userGoalService.deleteGoalFromUser(123L, 123L));
        verify(this.userRepo).findById(any());
        verify(this.goalRepo).findById(any());
    }

    @Test
    public void testDeleteGoalFromUser3() {
        ArrayList<Goal> goalList = new ArrayList<>();
        goalList.add(goal);

        Optional<User> ofResult = Optional.of(user1);

        when(this.userRepo.save(any())).thenReturn(user2);
        when(this.userRepo.findById(any())).thenReturn(ofResult);
        doNothing().when(this.goalRepo).delete(any());
        when(this.goalRepo.findById(any())).thenReturn(Optional.empty());

        Timestamp timestamp = mock(Timestamp.class);
        when(timestamp.toInstant()).thenReturn(null);

        assertThrows(GoalNotFoundException.class, () -> this.userGoalService.deleteGoalFromUser(123L, 123L));
        verify(this.goalRepo).findById(any());
    }
}

