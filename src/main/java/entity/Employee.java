package entity;

import java.sql.Date;
import java.util.Set;
import lombok.Data;

@Data
public class Employee {
    private Long id;
    private String firstName;
    private String lastName;
    private Date birthday;
    private Address address;
    private Set<Project> projects;
}
