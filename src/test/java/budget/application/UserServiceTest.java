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
        userTest1 = new User("test1", "passTest1", "test1@test.com");
        userTest2 = new User("test2", "passTest2", "test2@test.com");
        userTest3 = new User("test3", "passTest3", "test3@test.com");
    }

    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
        userTest1 = userTest2 = userTest3 = null;
    }

    @Test
    void createAndSaveUser() {
        //stubbing
        when(userRepository.save(any())).thenReturn(userTest1);
        userService.create(userTest1.getUsername(), userTest1.getPassword(), userTest1.getEmail());
        verify(userRepository, times(1)).save(any());
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
}
