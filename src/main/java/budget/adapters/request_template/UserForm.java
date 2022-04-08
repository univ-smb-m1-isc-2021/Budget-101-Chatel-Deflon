package budget.adapters.request_template;

public class UserForm {
    private String username;
    private String email;

    public UserForm(String username, String email) {
        this.username = username;
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserForm{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
