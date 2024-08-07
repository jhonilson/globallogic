package com.global.logic.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.global.logic.entities.User;
import com.global.logic.repositories.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplDiffblueTest {
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;


    @Test
    void testSave() {
        User user = new User();
        user.setCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setEmail("jane.doe@example.org");
        user.setId("42");
        user.setIsActive(true);
        user.setLastLogin(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPhones(new ArrayList<>());
        user.setToken("ABC123");
        when(userRepository.save(Mockito.<User>any())).thenReturn(user);

        User user2 = new User();
        user2.setCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        user2.setEmail("jane.doe@example.org");
        user2.setId("42");
        user2.setIsActive(true);
        user2.setLastLogin(LocalDate.of(1970, 1, 1).atStartOfDay());
        user2.setName("Name");
        user2.setPassword("iloveyou");
        user2.setPhones(new ArrayList<>());
        user2.setToken("ABC123");

        User actualSaveResult = userServiceImpl.save(user2);

        verify(userRepository).save(isA(User.class));
        assertEquals(actualSaveResult, user2);
        assertSame(user, actualSaveResult);
    }


    @Test
    void testFindAll() {
        ArrayList<User> userList = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(userList);

        List<User> actualFindAllResult = userServiceImpl.findAll();

        verify(userRepository).findAll();
        assertTrue(actualFindAllResult.isEmpty());
        assertSame(userList, actualFindAllResult);
    }


    @Test
    void testFindByEmailAndPassword() {
        User user = new User();
        user.setCreated(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setEmail("jane.doe@example.org");
        user.setId("42");
        user.setIsActive(true);
        user.setLastLogin(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPhones(new ArrayList<>());
        user.setToken("ABC123");
        when(userRepository.findByEmailAndPassword(Mockito.<String>any(), Mockito.<String>any())).thenReturn(user);

        User actualFindByEmailAndPasswordResult = userServiceImpl.findByEmailAndPassword("jane.doe@example.org",
                "iloveyou");

        verify(userRepository).findByEmailAndPassword(eq("jane.doe@example.org"), eq("iloveyou"));
        assertSame(user, actualFindByEmailAndPasswordResult);
    }
}
