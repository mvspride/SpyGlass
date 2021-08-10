package com.codedifferently.groupone.SpyGlass.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.codedifferently.groupone.SpyGlass.entities.User;
import com.codedifferently.groupone.SpyGlass.enums.UserRole;
import com.codedifferently.groupone.SpyGlass.exceptions.user.UserNotFoundException;
import com.codedifferently.groupone.SpyGlass.registration.token.ConfirmationTokenService;
import com.codedifferently.groupone.SpyGlass.repos.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {ConfirmationTokenService.class, UserService.class, BCryptPasswordEncoder.class})
@ExtendWith(SpringExtension.class)
public class UserServiceTest {
    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @MockBean
    private ConfirmationTokenService confirmationTokenService;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @Test
    public void testLoadUserByUsername() throws UsernameNotFoundException {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setUsername("janedoe");
        user.setEnabled(true);
        user.setLocked(true);
        user.setUserRole(UserRole.USER);
        Optional<User> ofResult = Optional.<User>of(user);
        when(this.userRepository.findByEmail(anyString())).thenReturn(ofResult);
        assertSame(user, this.userService.loadUserByUsername("jane.doe@example.org"));
        verify(this.userRepository).findByEmail(anyString());
        assertTrue(this.userService.getAllUsers().isEmpty());
    }

    @Test
    public void testLoadUserByUsername2() throws UsernameNotFoundException {
        when(this.userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class, () -> this.userService.loadUserByUsername("jane.doe@example.org"));
        verify(this.userRepository).findByEmail(anyString());
    }

    @Test
    public void testSignUpUser() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setUsername("janedoe");
        user.setEnabled(true);
        user.setLocked(true);
        user.setUserRole(UserRole.USER);
        Optional<User> ofResult = Optional.of(user);
        when(this.userRepository.findByEmail(anyString())).thenReturn(ofResult);

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setUsername("janedoe");
        user1.setEnabled(true);
        user1.setLocked(true);
        user1.setUserRole(UserRole.USER);
        assertThrows(IllegalStateException.class, () -> this.userService.signUpUser(user1));
        verify(this.userRepository).findByEmail(anyString());
    }

    @Test
    public void testSignUpUser2() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setUsername("janedoe");
        user.setEnabled(true);
        user.setLocked(true);
        user.setUserRole(UserRole.USER);
        when(this.userRepository.save(any())).thenReturn(user);
        when(this.userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        doNothing().when(this.confirmationTokenService)
                .saveConfirmationToken(any());
        when(this.bCryptPasswordEncoder.encode(any())).thenReturn("foo");

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setUsername("janedoe");
        user1.setEnabled(true);
        user1.setLocked(true);
        user1.setUserRole(UserRole.USER);
        this.userService.signUpUser(user1);
        verify(this.userRepository).findByEmail(anyString());
        verify(this.userRepository).save(any());
        verify(this.confirmationTokenService)
                .saveConfirmationToken(any());
        verify(this.bCryptPasswordEncoder).encode(any());
        assertEquals("foo", user1.getPassword());
        assertTrue(this.userService.getAllUsers().isEmpty());
    }

    @Test
    public void testGenerateToken() {
        doNothing().when(this.confirmationTokenService)
                .saveConfirmationToken(any());

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setUsername("janedoe");
        user.setEnabled(true);
        user.setLocked(true);
        user.setUserRole(UserRole.USER);
        this.userService.generateToken(user);
        verify(this.confirmationTokenService)
                .saveConfirmationToken(any());
        assertTrue(this.userService.getAllUsers().isEmpty());
    }

    @Test
    public void testGetAllUsers() {
        ArrayList<User> userList = new ArrayList<User>();
        when(this.userRepository.findAll()).thenReturn(userList);
        List<User> actualAllUsers = this.userService.getAllUsers();
        assertSame(userList, actualAllUsers);
        assertTrue(actualAllUsers.isEmpty());
        verify(this.userRepository).findAll();
    }

    @Test
    public void testGetUserById() throws UserNotFoundException {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setUsername("janedoe");
        user.setEnabled(true);
        user.setLocked(true);
        user.setUserRole(UserRole.USER);
        Optional<User> ofResult = Optional.of(user);
        when(this.userRepository.findById(any())).thenReturn(ofResult);
        when(this.userRepository.existsById(any())).thenReturn(true);
        assertSame(user, this.userService.getUserById(123L));
        verify(this.userRepository).existsById(any());
        verify(this.userRepository).findById(any());
        assertTrue(this.userService.getAllUsers().isEmpty());
    }

    @Test
    public void testGetUserById2() throws UserNotFoundException {
        when(this.userRepository.findById(any())).thenReturn(Optional.<User>empty());
        when(this.userRepository.existsById(any())).thenReturn(false);
        assertThrows(UserNotFoundException.class, () -> this.userService.getUserById(123L));
        verify(this.userRepository).existsById(any());
    }

    @Test
    public void testEnableUser() {
        when(this.userRepository.enableUser(anyString())).thenReturn(1);
        assertEquals(1, this.userService.enableUser("jane.doe@example.org"));
        verify(this.userRepository).enableUser(anyString());
        assertTrue(this.userService.getAllUsers().isEmpty());
    }
}

