package budget.adapters.request_template;

public class NewBudgetForm {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "newBudgetForm{" +
                "name='" + name + '\'' +
                '}';
    }
}
