package entity;

import java.util.Set;
import lombok.Data;

@Data
public class Project {
    private Long id;
    private String title;
    private Set<Employee> employees;
}
