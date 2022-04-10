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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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

    @BeforeEach
    public void setUp(){
        user = new User("userTest", "passUserTest", "test@test.coom");
        budget = new Budget("budgetTest", user.getId());
        exp1 = new PunctualExpense(user.getId(), "puncExpTest",  200.0f, budget.getId(), new Date(System.currentTimeMillis()));
        exp1 = new RecurrentExpense(user.getId(), "RecExpTest",  100.0f, budget.getId(), new Date(System.currentTimeMillis()), ExpenseRepetition.MONTHLY);
        exp1 = new SpreadExpense(user.getId(), "spreadExpTest",  200.0f, budget.getId(), new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()+50000));
    }

    @AfterEach
    public void tearDown() {
        expenseRepository.deleteAll();
        exp1 = exp2 = exp3 = null;
    }

//    @Test
//    void createAndPuncExp(){
//        when(expenseRepository.save(any())).thenReturn(exp1);
//        expenseService.createPunctualExpense(exp1);
//
//    }
}
