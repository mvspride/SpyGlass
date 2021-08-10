package com.codedifferently.groupone.SpyGlass.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.codedifferently.groupone.SpyGlass.email.EmailSender;
import com.codedifferently.groupone.SpyGlass.entities.Goal;
import com.codedifferently.groupone.SpyGlass.entities.User;
import com.codedifferently.groupone.SpyGlass.enums.UserRole;
import com.codedifferently.groupone.SpyGlass.registration.RegistrationRequest;
import com.codedifferently.groupone.SpyGlass.registration.token.ConfirmationToken;
import com.codedifferently.groupone.SpyGlass.registration.token.ConfirmationTokenService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {EmailValidator.class, RegistrationService.class, ConfirmationTokenService.class,
        UserService.class})
@ExtendWith(SpringExtension.class)
public class RegistrationServiceTest {
    @MockBean
    private ConfirmationTokenService confirmationTokenService;
    @MockBean
    private EmailSender emailSender;

    @MockBean
    private EmailValidator emailValidator;

    @Autowired
    private RegistrationService registrationService;

    @MockBean
    private UserService userService;

    @Test
    public void testRegister() {
        when(this.userService.signUpUser((com.codedifferently.groupone.SpyGlass.entities.User) any())).thenReturn("foo");
        when(this.emailValidator.test(anyString())).thenReturn(true);
        doNothing().when(this.emailSender).send(anyString(), anyString());

        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setPassword("iloveyou");
        registrationRequest.setEmail("jane.doe@example.org");
        registrationRequest.setUsername("janedoe");
        assertEquals("foo", this.registrationService.register(registrationRequest));
        verify(this.userService).signUpUser((com.codedifferently.groupone.SpyGlass.entities.User) any());
        verify(this.emailValidator).test(anyString());
        verify(this.emailSender).send(anyString(), anyString());
    }

    @Test
    public void testRegister2() {
        when(this.userService.signUpUser((com.codedifferently.groupone.SpyGlass.entities.User) any())).thenReturn("foo");
        when(this.emailValidator.test(anyString())).thenReturn(false);
        doNothing().when(this.emailSender).send(anyString(), anyString());

        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setPassword("iloveyou");
        registrationRequest.setEmail("jane.doe@example.org");
        registrationRequest.setUsername("janedoe");
        assertThrows(IllegalStateException.class, () -> this.registrationService.register(registrationRequest));
        verify(this.emailValidator).test(anyString());
    }

    @Test
    public void testConfirmToken() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setUsername("janedoe");
        user.setId(123L);
        user.setEnabled(true);
        user.setLocked(true);
        user.setGoals(new ArrayList<Goal>());
        user.setUserRole(UserRole.USER);

        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setUser(user);
        confirmationToken.setToken("ABC123");
        confirmationToken.setConfirmationTime(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setExpirationTime(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setCreationTime(LocalDateTime.of(1, 1, 1, 1, 1));
        Optional<ConfirmationToken> ofResult = Optional.<ConfirmationToken>of(confirmationToken);
        when(this.confirmationTokenService.getToken(anyString())).thenReturn(ofResult);
        assertThrows(IllegalStateException.class, () -> this.registrationService.confirmToken("ABC123"));
        verify(this.confirmationTokenService).getToken(anyString());
    }

    @Test
    public void testConfirmToken2() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setUsername("janedoe");
        user.setId(123L);
        user.setEnabled(true);
        user.setLocked(true);
        user.setGoals(new ArrayList<Goal>());
        user.setUserRole(UserRole.USER);

        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setUser(user);
        confirmationToken.setToken("ABC123");
        confirmationToken.setConfirmationTime(null);
        confirmationToken.setExpirationTime(LocalDateTime.of(1, 1, 1, 1, 1));
        confirmationToken.setCreationTime(LocalDateTime.of(1, 1, 1, 1, 1));
        Optional<ConfirmationToken> ofResult = Optional.<ConfirmationToken>of(confirmationToken);
        when(this.confirmationTokenService.getToken(anyString())).thenReturn(ofResult);
        assertThrows(IllegalStateException.class, () -> this.registrationService.confirmToken("ABC123"));
        verify(this.confirmationTokenService).getToken(anyString());
    }

    @Test
    public void testConfirmToken3() {
        when(this.confirmationTokenService.getToken(anyString())).thenReturn(Optional.<ConfirmationToken>empty());
        assertThrows(IllegalStateException.class, () -> this.registrationService.confirmToken("ABC123"));
        verify(this.confirmationTokenService).getToken(anyString());
    }
}


