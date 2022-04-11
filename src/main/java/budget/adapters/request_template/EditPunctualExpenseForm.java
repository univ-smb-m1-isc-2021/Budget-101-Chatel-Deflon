package budget.adapters.request_template;

import budget.persistence.expense.ExpenseType;

import java.sql.Date;

public class EditPunctualExpenseForm {
    private Long id;
    private String label;
    private Float amount;
    private Long budgetId;
    private ExpenseType type;
    private Date date;

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
}
