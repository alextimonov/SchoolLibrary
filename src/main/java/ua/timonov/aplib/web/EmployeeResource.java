package ua.timonov.aplib.web;

import org.glassfish.jersey.server.mvc.ErrorTemplate;
import org.glassfish.jersey.server.mvc.Template;
import org.springframework.beans.factory.annotation.Autowired;
import ua.timonov.aplib.model.Employee;
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
@Consumes(MediaType.APPLICATION_JSON)
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_HTML})
public class EmployeeResource {
    private EmployeeService employeeService = new EmployeeService();

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GET
    @Template(name = "/employees.jsp")
    @ErrorTemplate(name = "/error.jsp")
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
        Map<String, Object> map = new HashMap<>();
        map.put("message", "Found employee by ID:");
        map.put("employee", employeeService.getById(id));
        return Response.ok(map).build();
    }

    /*return Response
            .status(Response.Status.CREATED)
            // 201
            .entity("A new podcast has been created AT THE LOCATION you specified")
    .header("Location",
                    "http://localhost:8888/demo-rest-jersey-spring/podcasts/"
                    + String.valueOf(createPodcastId)).build();*/

    /*@GET
    @Path("/{surname}")
    public Employee getEmployeeBySurname(@PathParam("surname") String surname) {
        return employeeService.getBySurname(surname);
    }*/

    @POST
    public Response addEmployee(Employee employee) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", "Added employee:");
        map.put("employee", employeeService.add(employee));
        return Response.status(Response.Status.CREATED).entity(map).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateEmployee(@PathParam("id") int id, Employee employee) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", "Updated employee:");
        map.put("employee", employeeService.update(id, employee));
        return Response.ok(map).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteEmployee(@PathParam("id") int id) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", "Deleted employee:");
        map.put("employee", employeeService.delete(id));
        return Response.ok(map).build();
    }
}
