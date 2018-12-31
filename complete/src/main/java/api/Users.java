package api;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Users {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer user_id;
    private String user_email;
    private String user_password;
    private Integer user_status_id;
    private Integer user_type_id;
    private String user_fname;
    private String user_lname;

    public Integer getId() {
        return user_id;
    }

    public void setId(Integer id) {
        this.user_id = user_id;
    }

    public String getFirstName() {
        return user_fname;
    }

    public String getLastName() {
        return user_lname;
    }

    public void setFullName(String first_name, String last_name) {
        this.user_fname = first_name;
        this.user_lname = last_name;
    }

    public String getEmail() {
        return user_email;
    }

    public void setEmail(String email) {
        this.user_email = email;
    }
}
