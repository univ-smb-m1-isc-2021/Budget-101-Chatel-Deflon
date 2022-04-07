package budget.adapters.request_template;

import java.sql.Date;

public class NewSpreadExpenseForm {
    private String label;
    private Float amount;
    private Long budgetId;
    private Date start;
    private Date end;

    public String getLabel() {
        return label;
    }

    public Float getAmount() {
        return amount;
    }

    public Long getBudgetId() {
        return budgetId;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }
}
