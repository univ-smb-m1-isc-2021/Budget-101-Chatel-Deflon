package budget.persistence;

import javax.persistence.Entity;
import java.sql.Date;

@Entity
public class SpreadExpense extends Expense {
    private Date start;
    private Date end;

    public SpreadExpense() {
        super();
    }

    public SpreadExpense(String label, Float amount, Long budgetId, Date end) {
        super();
        this.setLabel(label);
        this.setAmount(amount);
        this.setBudgetId(budgetId);
        this.start = new Date(System.currentTimeMillis());
        this.end = end;
    }

    public SpreadExpense(String label, Float amount, Long budgetId, Date start, Date end) {
        super();
        this.setLabel(label);
        this.setAmount(amount);
        this.setBudgetId(budgetId);
        this.start = start;
        this.end = end;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String toString() {
        return String.format("SpreadExpense : {label : %s, amount : %f, start : %s, end : %s, budgetId : %d}", getLabel(), getAmount(), getStart().toString(), getEnd().toString(), getBudgetId());
    }
}