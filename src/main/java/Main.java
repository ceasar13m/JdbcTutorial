import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import entity.Address;
import entity.EmplProj;
import entity.Employee;
import entity.Project;
import service.AddressService;
import service.EmplProjService;
import service.EmployeeService;
import service.ProjectService;

public class Main {
    public static void main(String[] args) {
        AddressService addressService = new AddressService();
        EmployeeService employeeService = new EmployeeService();
        EmplProjService emplProjService = new EmplProjService();
        ProjectService projectService = new ProjectService();

        Address address = new Address();
        address.setId(1L);
        address.setCountry("Russia");
        address.setCity("Kazan");
        address.setStreet("Big Red");
        address.setPostCode("456356");

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("Ainur");
        employee.setLastName("Muharlyamov");

        Calendar calendar = Calendar.getInstance();
        calendar.set(1991, Calendar.MARCH, 14);
        employee.setBirthDay(new Date(calendar.getTime().getTime()));
        employee.setAddressId(address.getId());

        Project project = new Project();
        project.setId(1L);
        project.setTitle("Java development");

        EmplProj emplProj = new EmplProj();
        emplProj.setEmployeeId(employee.getId());
        emplProj.setProjectId(project.getId());

        try {
//            addressService.add(address);
//            employeeService.add(employee);
//            projectService.add(project);
//            emplProjService.add(emplProj);

            List<Address> addressList = addressService.getAll();
            for (Address a : addressList) {
                System.out.println(a);
            }
            List<Employee> employeeList = employeeService.getAll();
            for (Employee e : employeeList) {
                System.out.println(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
