package ua.timonov.aplib.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.timonov.aplib.model.Employee;
import ua.timonov.aplib.service.EmployeeService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * REST resource class for Employee
 */
@Path("/employees")
public class EmployeeResource {
    private EmployeeService employeeService = new EmployeeService();

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<Employee> getEmployees() {
        return employeeService.getAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Employee getEmployeeById(@PathParam("id") int id) {
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
