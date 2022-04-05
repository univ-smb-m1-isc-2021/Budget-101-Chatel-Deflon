package budget.application;

import budget.ExpenseRepetition;
import budget.persistence.*;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {
    private final ExpenseRepository repository;

    public ExpenseService(ExpenseRepository repository) {
        this.repository = repository;
    }

    public List<Expense> expenses(){return this.repository.findAll();}

    public void delete(Long expenseId){
        Optional<Expense> expense = repository.findById(expenseId);
        expense.ifPresent(repository::delete);
    }

    public Expense createPunctualExpense(String label, Float amount, Long budgetId, Date date){
        return repository.save(new PunctualExpense(label,amount,budgetId,date));
    }

    public Expense createRecurrentExpense(String label, Float amount, Long budgetId, Date date, ExpenseRepetition repetition){
        return repository.save(new RecurrentExpense(label,amount,budgetId,date,repetition));
    }

    public Expense createSpreadExpense(String label, Float amount, Long budgetId, Date end){
        return repository.save(new SpreadExpense(label,amount,budgetId,end));
    }
}
