package budget.persistence.expense;

import javax.persistence.Entity;
import java.sql.Date;

@Entity
public class RecurrentExpense extends Expense {
    private Date date;
    private ExpenseRepetition repetition;

    public RecurrentExpense() {
        super();
        this.setType(ExpenseType.RECURRENT);
        this.date = new Date(System.currentTimeMillis());
        this.repetition = ExpenseRepetition.MONTHLY;
    }

    public RecurrentExpense(Long userId,String label, Float amount, Long budgetId, Date date, ExpenseRepetition repetition) {
        super();
        this.setUserId(userId);
        this.setType(ExpenseType.RECURRENT);
        this.setLabel(label);
        this.setAmount(amount);
        this.setBudgetId(budgetId);
        this.date = date;
        this.repetition = repetition;
    }

    public RecurrentExpense(Long userId, Long id, String label, Float amount, Long budgetId, Date date, ExpenseRepetition repetition){
        super();
        super.setId(id);
        this.setUserId(userId);
        this.setType(ExpenseType.RECURRENT);
        this.setLabel(label);
        this.setAmount(amount);
        this.setBudgetId(budgetId);
        this.date = date;
        this.repetition = repetition;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ExpenseRepetition getRepetition() {
        return repetition;
    }

    public void setRepetition(ExpenseRepetition repetition) {
        this.repetition = repetition;
    }

    public String toString() {
        return String.format("RecurrentExpense : {label : %s, amount : %f, date : %s, repetition : %s , budgetId : %d}", getLabel(), getAmount(), getDate().toString(), this.getRepetition(), getBudgetId());
    }
}
