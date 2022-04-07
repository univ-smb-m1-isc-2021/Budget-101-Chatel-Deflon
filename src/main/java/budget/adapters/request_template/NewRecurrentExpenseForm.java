package budget.adapters.request_template;

import budget.persistence.expense.ExpenseRepetition;


import java.sql.Date;

public class NewRecurrentExpenseForm {
    private String label;
    private Float amount;
    private Long budgetId;
    private Date date;
    private ExpenseRepetition repetition;

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

    public ExpenseRepetition getRepetition() {
        return repetition;
    }
}
