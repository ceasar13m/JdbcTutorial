package entity;

import java.sql.Date;
import lombok.Data;

@Data
public class Employee {
    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDay;
    private Long addressId;
}
