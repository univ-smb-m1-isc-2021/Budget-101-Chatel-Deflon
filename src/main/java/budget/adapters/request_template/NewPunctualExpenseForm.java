package budget.adapters.request_template;

import budget.persistence.expense.ExpenseType;

import java.sql.Date;

public class NewPunctualExpenseForm {
    private String label;
    private Float amount;
    private Long budgetId;
    private Date date;

    public String getLabel() {
        return label;
    }

    public Float getAmount() {
        return amount;
    }

    public Long getBudgetId() {
        return budgetId;
    }

    public Date getDate() {
        return date;
    }
}
