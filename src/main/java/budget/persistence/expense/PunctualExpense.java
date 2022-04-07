package budget.persistence.expense;

import budget.persistence.expense.Expense;

import javax.persistence.Entity;
import java.sql.Date;

@Entity
public class PunctualExpense extends Expense {
    private Date date;

    public PunctualExpense() {
        super();
        this.setType(ExpenseType.PUNCTUAL);
        this.date = new Date(System.currentTimeMillis());
    }

    public PunctualExpense(Long userId, String label, Float amount, Long budgetId, Date date) {
        super();
        this.setUserId(userId);
        this.setType(ExpenseType.PUNCTUAL);
        this.setLabel(label);
        this.setAmount(amount);
        this.setBudgetId(budgetId);
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String toString() {
        return String.format("PunctualExpense : {label : %s, amount : %f, date : %s, budgetId : %d}", getLabel(), getAmount(), getDate().toString(), getBudgetId());
    }
}
