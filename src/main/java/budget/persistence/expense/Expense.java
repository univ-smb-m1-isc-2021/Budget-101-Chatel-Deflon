package budget.persistence.expense;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Expense {
    @Id
    @GeneratedValue
    private Long id;
    private ExpenseType type;
    private String label;
    private Float amount;
    private Long userId;
    private Long budgetId;

    public Expense() {
        // JPA
    }

    public Expense(Long userId, Float amount, String label, Long budgetId) {
        this.userId = userId;
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

    public ExpenseType getType() {
        return type;
    }

    public void setType(ExpenseType type) {
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", type=" + type +
                ", label='" + label + '\'' +
                ", amount=" + amount +
                ", userId=" + userId +
                ", budgetId=" + budgetId +
                '}';
    }

    public String mailRecap() {
        return "erreur";
    }
}
