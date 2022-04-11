package budget.application;

import budget.persistence.budget.Budget;
import budget.persistence.expense.*;
import budget.persistence.user.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class ExpenseServiceTest {
    @Mock
    private ExpenseRepository expenseRepository;

    @InjectMocks
    private ExpenseService expenseService;
    private User user;
    private Budget budget;
    private Expense exp1;
    private Expense exp2;
    private Expense exp3;
    private ArrayList<Expense> expenseArrayList;

    @BeforeEach
    public void setUp() {
        user = new User("userTest", "passUserTest", "test@test.coom");
        budget = new Budget("budgetTest", user.getId());
        expenseArrayList = new ArrayList<>();
        exp1 = new PunctualExpense(user.getId(), "puncExpTest", 100.0f, budget.getId(), new Date(System.currentTimeMillis()));
        exp2 = new RecurrentExpense(user.getId(), "RecExpTest", 200.0f, budget.getId(), new Date(System.currentTimeMillis()), ExpenseRepetition.MONTHLY);
        exp3 = new SpreadExpense(user.getId(), "spreadExpTest", 500.0f, budget.getId(), new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + 50000));
        expenseArrayList.add(exp1);
        expenseArrayList.add(exp2);
        expenseArrayList.add(exp3);
    }

    @AfterEach
    public void tearDown() {
        expenseRepository.deleteAll();
        exp1 = exp2 = exp3 = null;
    }

    @Test
    void createAndSaveAnyExp() {
        when(expenseRepository.saveAndFlush(any())).thenReturn(exp1);
        expenseService.create(exp1);
        expenseService.create(exp2);
        expenseService.create(exp3);
        verify(expenseRepository, times(3)).saveAndFlush(any());
    }

    @Test
    void deleteAnyExp() {
        when(expenseRepository.findById(exp1.getId())).thenReturn(Optional.ofNullable(exp1));
        when(expenseRepository.findById(exp2.getId())).thenReturn(Optional.ofNullable(exp2));
        when(expenseRepository.findById(exp3.getId())).thenReturn(Optional.ofNullable(exp3));
        expenseService.delete(exp1.getId());
        expenseService.delete(exp2.getId());
        expenseService.delete(exp3.getId());
        verify(expenseRepository, times(3)).delete(any());
    }

    @Test
    void editAnyExp() {
        when(expenseRepository.saveAndFlush(exp1)).thenReturn(exp1);
        when(expenseRepository.saveAndFlush(exp2)).thenReturn(exp2);
        when(expenseRepository.saveAndFlush(exp3)).thenReturn(exp3);
        expenseService.edit(exp1);
        expenseService.edit(exp2);
        expenseService.edit(exp3);
        verify(expenseRepository, times(3)).saveAndFlush(any());
    }

    @Test
    void getExpFromBudgetId() {
        when(expenseRepository.findExpensesByBudgetId(budget.getId())).thenReturn(expenseArrayList);
        expenseService.expensesOfBudget(budget.getId());
        verify(expenseRepository, times(1)).findExpensesByBudgetId(any());
    }

    @Test
    void getExpFromUserId() {
        when(expenseRepository.findExpensesByUserId(user.getId())).thenReturn(expenseArrayList);
        ArrayList<Expense> tmp = (ArrayList<Expense>) expenseService.expenses(user.getId());
        verify(expenseRepository, times(1)).findExpensesByUserId(any());
        assertEquals(tmp.size(),3);
    }
}
