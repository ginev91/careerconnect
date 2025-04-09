package com.careerconnect.service;

import com.careerconnect.model.Role;
import com.careerconnect.model.User;
import com.careerconnect.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsers_ShouldReturnListOfUsers() {
        User user1 = new User("alice", "pass1", "a@example.com", Role.USER);
        User user2 = new User("bob", "pass2", "b@example.com", Role.ADMIN);
        when(userRepository.findAll()).thenReturn(List.of(user1, user2));

        List<User> result = userService.getAllUsers();

        assertThat(result).hasSize(2);
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void registerUser_ShouldEncodePasswordAndSaveUser() {
        String rawPass = "myPassword";
        String encodedPass = "encodedPass";
        when(passwordEncoder.encode(rawPass)).thenReturn(encodedPass);

        userService.registerUser("john", "john@example.com", rawPass);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userCaptor.capture());

        User saved = userCaptor.getValue();
        assertThat(saved.getUsername()).isEqualTo("john");
        assertThat(saved.getEmail()).isEqualTo("john@example.com");
        assertThat(saved.getPassword()).isEqualTo(encodedPass);
        assertThat(saved.getRole()).isEqualTo(Role.USER);
    }

    @Test
    void usernameExists_ShouldReturnTrueIfExists() {
        when(userRepository.findByUsername("jane")).thenReturn(Optional.of(new User()));
        assertThat(userService.usernameExists("jane")).isTrue();
    }

    @Test
    void usernameExists_ShouldReturnFalseIfNotExists() {
        when(userRepository.findByUsername("ghost")).thenReturn(Optional.empty());
        assertThat(userService.usernameExists("ghost")).isFalse();
    }

    @Test
    void findByUsername_ShouldReturnUser() {
        User user = new User("admin", "pass", "admin@example.com", Role.ADMIN);
        when(userRepository.findByUsername("admin")).thenReturn(Optional.of(user));

        User result = userService.findByUsername("admin");

        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("admin");
    }

    @Test
    void updateQualifications_ShouldUpdateFieldAndSaveUser() {
        User user = new User("dev", "pwd", "dev@site.com", Role.USER);
        when(userRepository.findByUsername("dev")).thenReturn(Optional.of(user));

        userService.updateQualifications("dev", "Spring, Docker");

        assertThat(user.getQualifications()).isEqualTo("Spring, Docker");
        verify(userRepository).save(user);
    }
}