package budget.adapters.request_template;

public class EditBudgetForm {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "EditBudgetForm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
