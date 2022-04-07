package budget.persistence.expense;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Expense {
    @Id
    @GeneratedValue
    private Long id;
    private Float amount;
    private String label;
    private Long budgetId;

    public Expense() {
        // JPA
    }

    public Expense(Float amount, String label, Long budgetId) {
        this.amount = amount;
        this.label = label;
        this.budgetId = budgetId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
    }
}
