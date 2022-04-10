package budget.application;

import budget.persistence.user.User;
import budget.persistence.user.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;
    private User userTest1;
    private User userTest2;
    private User userTest3;

    @BeforeEach
    public void setUp() {
        userTest1 = new User(1L, "test1", "passTest1", "test1@test.com");
        userTest2 = new User(2L, "test2", "passTest2", "test2@test.com");
        userTest3 = new User(3L, "test3", "passTest3", "test3@test.com");
    }

    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
        userTest1 = userTest2 = userTest3 = null;
    }

    @Test
    void createAndSaveUser() {
        //stubbing
        when(userRepository.saveAndFlush(any())).thenReturn(userTest1);
        userService.create(userTest1);
        verify(userRepository, times(1)).saveAndFlush(any());
    }

    @Test
    void delete() {
        when(userRepository.findById(userTest1.getId())).thenReturn(Optional.ofNullable(userTest1));
        userService.delete(userTest1.getId());
        verify(userRepository, times(1)).delete(any());
    }

    @Test
    void edit() {
        when(userRepository.saveAndFlush(any())).thenReturn(userTest1);
        userService.edit(userTest2);
        verify(userRepository, times(1)).saveAndFlush(any());
    }

    @Test
    void findUserByUsername() {
        when(userRepository.findUserByUsername(any())).thenReturn(userTest1);
        userService.userByName(userTest1.getUsername());
        verify(userRepository, times(1)).findUserByUsername(any());
    }

    @Test
    void findUserById() {
        when(userRepository.findUserById(userTest1.getId())).thenReturn(userTest1);
        userService.userById(userTest1.getId());
        verify(userRepository, times(1)).findUserById(userTest1.getId());
    }
}
