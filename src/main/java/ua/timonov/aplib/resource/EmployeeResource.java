package ua.timonov.aplib.resource;

import ua.timonov.aplib.model.Employee;
import ua.timonov.aplib.service.EmployeeService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * REST resource class for Employee
 */
@Path(EmployeeResource.EMPLOYEES)
public class EmployeeResource {
    public static final String EMPLOYEES = "/employees";

    private EmployeeService employeeService = new EmployeeService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Employee> getEmployees() {
        return employeeService.getAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Employee getEmployeeById(int id) {
        return employeeService.getById(id);
    }

    /*@GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Employee getEmployeeByName(String name) {
        return employeeService.getByName(name);
    }*/

    /*@GET
    @Path("/{surname}")
    @Produces(MediaType.APPLICATION_JSON)
    public Employee getEmployeeBySurname(String surname) {
        return employeeService.getBySurname(surname);
    }*/
}
