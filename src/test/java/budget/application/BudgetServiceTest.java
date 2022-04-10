package budget.application;

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

import java.util.ArrayList;
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
    private ArrayList<Budget> budgetArrayList;
    private Budget budgetTest1;
    private Budget budgetTest2;
    private Budget budgetTest3;
    private User user;


    @BeforeEach
    public void setUp() {
        user = new User(1L, "userTest", "passUserTest", "userTest@test.com");
        budgetArrayList = new ArrayList<>();
        budgetTest1 = new Budget("test1", user.getId());
        budgetTest2 = new Budget("test2", user.getId());
        budgetTest3 = new Budget("test3", user.getId());
        budgetArrayList.add(budgetTest1);
        budgetArrayList.add(budgetTest2);
        budgetArrayList.add(budgetTest3);
    }

    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
        budgetTest1 = budgetTest2 = budgetTest3 = null;
        user = null;
    }

    @Test
    void createAndSave() {
        when(budgetRepository.saveAndFlush(any())).thenReturn(budgetTest1);
        budgetService.create(budgetTest1);
        budgetService.create(budgetTest2);
        verify(budgetRepository, times(2)).saveAndFlush(any());
    }

    @Test
    void delete() {
        when(budgetRepository.findById(budgetTest1.getId())).thenReturn(Optional.ofNullable(budgetTest1));
        budgetService.delete(budgetTest1.getId());
        verify(budgetRepository, times(1)).delete(any());
    }

    @Test
    void edit() {
        when(budgetRepository.saveAndFlush(any())).thenReturn(budgetTest1);
        budgetService.edit(budgetTest1);
        verify(budgetRepository, times(1)).saveAndFlush(any());
    }

    @Test
    void findByUserId() {
        when(budgetRepository.findBudgetByUserId(user.getId())).thenReturn(budgetArrayList);
        ArrayList<Budget> tmp = (ArrayList<Budget>) budgetService.budgets(user.getId());
        verify(budgetRepository, times(1)).findBudgetByUserId(user.getId());
        assertEquals(tmp.size(), 3);
    }
}
