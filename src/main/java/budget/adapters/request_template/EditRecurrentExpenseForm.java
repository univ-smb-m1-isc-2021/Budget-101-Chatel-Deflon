package budget.adapters.request_template;

import budget.persistence.expense.ExpenseRepetition;
import budget.persistence.expense.ExpenseType;

import java.sql.Date;

public class EditRecurrentExpenseForm {
    private Long id;
    private String label;
    private Float amount;
    private Long budgetId;
    private ExpenseType type;
    private Date date;
    private ExpenseRepetition repetition;

    public Long getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public Float getAmount() {
        return amount;
    }

    public Long getBudgetId() {
        return budgetId;
    }

    public ExpenseType getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }

    public ExpenseRepetition getRepetition() {
        return repetition;
    }
}
