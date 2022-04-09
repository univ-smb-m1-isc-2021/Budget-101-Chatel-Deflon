package budget.application;

import budget.application.BudgetService;
import budget.application.UserService;
import budget.persistence.budget.Budget;
import budget.persistence.budget.BudgetRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class BudgetServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private BudgetRepository budgetRepository;

    @InjectMocks
    private BudgetService budgetService;
    private UserService userService;
    private Budget budgetTest1;
    private Budget budgetTest2;
    private Budget budgetTest3;
    private User user;


    @BeforeEach
    public void setUp() {
        user = new User("userTest", "passUserTest", "userTest@test.com");
        budgetTest1 = new Budget("test1", user.getId());
        budgetTest2 = new Budget("test2", user.getId());
        budgetTest3 = new Budget("test3", user.getId());
    }

    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
        budgetTest1 = budgetTest2 = budgetTest3 = null;
        user = null;
    }

    @Test
    void createAndSaveBudget() {
        when(budgetRepository.save(any())).thenReturn(budgetTest1);
        budgetService.create(budgetTest1.getName(), budgetTest1.getUserId());
        budgetService.create(budgetTest2.getName(), budgetTest2.getUserId());
        verify(budgetRepository, times(2)).save(any());
    }

    @Test
    void deleteBuget() {
        when(budgetRepository.findById(budgetTest1.getId())).thenReturn(Optional.ofNullable(budgetTest1));
        budgetService.delete(budgetTest1.getId());
        verify(budgetRepository, times(1)).delete(any());
    }

    @Test
    void editBudget() {
        when(budgetRepository.save(any())).thenReturn(budgetTest1);
        budgetService.edit(budgetTest1.getId(), budgetTest1.getName(), budgetTest1.getUserId());
        verify(budgetRepository, times(1)).save(any());
    }
}
