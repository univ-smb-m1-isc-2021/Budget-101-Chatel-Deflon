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
    private Long userId;

    public Budget() {
    }

    public Budget(String name) {
        this.name = name;
    }

    public Budget(String name, Long owner) {
        this.name = name;
        userId = owner;
    }

    public Budget(Long id, String name, Long owner){
        this.id = id;
        this.name = name;
        this.userId = owner;
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

    public Long getUserId() {
        return userId;
    }

    public String toString() {
        return String.format("Budget : {id : %d, name : %s, userId : %d}", this.getId(), this.getName(), userId);
    }
}
