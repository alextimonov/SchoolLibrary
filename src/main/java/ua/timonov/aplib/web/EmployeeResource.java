package ua.timonov.aplib.web;

import org.glassfish.jersey.server.mvc.Template;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ua.timonov.aplib.model.Employee;
import ua.timonov.aplib.model.SchoolClass;
import ua.timonov.aplib.service.EmployeeService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * REST resource class for Employee
 */
@Path("/employees")

@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_HTML})
public class EmployeeResource {
    public static final int ERROR_ID = -1;
    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GET
    @Template(name = "/employees.jsp")
//    @ErrorTemplate(name = "/error.jsp") // to all methods
    public Response getEmployees() {
        Map<String, Object> map = new HashMap<>();
        map.put("message", "Employees:");
        map.put("employees", employeeService.getAll());
        return Response.ok(map).build();
    }

    @GET
    @Path("/{id}")
    @Template(name = "/employee.jsp")
    public Response getEmployeeById(@PathParam("id") int id) {
        Employee employee = employeeService.getById(id);
        SchoolClass schoolClass = employeeService.getSchoolClass(employee);
        Map<String, Object> map = new HashMap<>();
        map.put("employee", employee);
        map.put("schoolClass", schoolClass);
        return Response.ok(map).build();
    }

    /*@GET
    @Path("/{surname}")
    public Employee getEmployeeBySurname(@PathParam("surname") String surname) {
        return employeeService.getBySurname(surname);
    }*/

    @POST
    public Employee addEmployee(Employee employee) {
        return employeeService.add(employee);
    }

    @PUT
    @Path("/{id}")
    public Employee updateEmployee(@PathParam("id") int id, Employee employee) {
        return employeeService.update(id, employee);
    }

    @DELETE
    @Path("/{id}")
    public Employee deleteEmployee(@PathParam("id") int id) {
        return employeeService.delete(id);
    }

    @GET
    @Path("/addForm")
    @Template(name = "/employeeAddForm.jsp")
    public Response formAddEmployee() {
        Map<String, Object> map = new HashMap<>();
        map.put("employee", new Employee());
        return Response.ok(map).build();
    }

    @GET
    @Path("/editForm")
    @Template(name = "/employeeEditForm.jsp")
    public Response formEditEmployee(@QueryParam("id") int id) {
        Employee employee = employeeService.getById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("employee", employee);
        map.put("positions", employeeService.getAllPositions());
        return Response.ok(map).build();
    }

    @GET
    @Path("/deleteForm")
    @Template(name = "/employeeDeleteForm.jsp")
    public Response formDeleteEmployee(@QueryParam("id") int id) {
        try {
            Employee employee = employeeService.delete(id);
            return Response.ok(employee).build();
        }
        catch (HibernateException | DataAccessException e) {
            Employee employee = employeeService.getById(id);
            employee.setId(ERROR_ID);
            return Response.ok(employee).build();
        }
    }
}