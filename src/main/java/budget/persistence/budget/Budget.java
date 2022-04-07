package budget.persistence.budget;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

@Entity
public class Budget {
    @Id
    @GeneratedValue
    private long id;
    private String name;

    public Budget() {
    }

    public Budget(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return String.format("Budget : {name : %s, id : %d}", this.getName(), this.getId());
    }
}
