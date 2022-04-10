package budget.adapters.request_template;

import budget.persistence.expense.ExpenseType;

import java.sql.Date;

public class EditSpreadExpenseForm {
    private Long id;
    private String label;
    private Float amount;
    private Long budgetId;
    private ExpenseType type;
    private Date start;
    private Date end;

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

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }
}
