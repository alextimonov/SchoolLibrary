package ua.timonov.aplib.web;

import org.glassfish.jersey.server.mvc.Template;
import org.springframework.beans.factory.annotation.Autowired;
import ua.timonov.aplib.model.Employee;
import ua.timonov.aplib.service.EmployeeService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * REST resource class for Employee
 */
@Path("/employees")
//@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN, MediaType.APPLICATION_FORM_URLENCODED})
//@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_HTML})

@Consumes(MediaType.APPLICATION_JSON)
@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_HTML})
public class EmployeeResource {
    private EmployeeService employeeService = new EmployeeService();

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @OPTIONS
    @Produces(MediaType.TEXT_PLAIN)
//    @Produces(MediaType.TEXT_PLAIN + "; charset=utf-8")
    public Response checkOptions() throws URISyntaxException {
        return Response.status(200)
                .contentLocation(new URI("http://localhost:8080/library/employees"))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, GET, OPTIONS") //CAN BE ENHANCED WITH OTHER HTTP CALL METHODS
                .header("Access-Control-Allow-Headers", "Content-Type")
                .build();
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
        Map<String, Object> map = new HashMap<>();
        map.put("message", "Found employee by ID:");
        map.put("employee", employeeService.getById(id));
        return Response.ok(map).build();
    }

    /*@GET
    @Path("/{surname}")
    public Employee getEmployeeBySurname(@PathParam("surname") String surname) {
        return employeeService.getBySurname(surname);
    }*/

    @POST
//    @Template(name = "/employee.jsp")
    public Employee addEmployee(Employee employee) {
        return employeeService.add(employee);
//        Map<String, Object> map = new HashMap<>();
//        map.put("message", "Added employee:");
//        map.put("employee", );
//        Viewable view = new Viewable("/employee.jsp", map);
//        Response build = Response.ok(map).build();
//        return build;

//        Employee employeeAdded = employeeService.add(employee);
//        URI employeeId = UriBuilder.fromResource(EmployeeResource.class).build(employee.getId());
//        return Response.created(employeeId).build();

    }

    @PUT
    @Path("/{id}")
    @Template(name = "/employee.jsp")
    public Response updateEmployee(@PathParam("id") int id, Employee employee) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", "Updated employee:");
        map.put("employee", employeeService.update(id, employee));
        return Response.ok(map).build();
    }

    @DELETE
    @Path("/{id}")
    @Template(name = "/employee.jsp")
    public Response deleteEmployee(@PathParam("id") int id) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", "Deleted employee:");
        map.put("employee", employeeService.delete(id));
        return Response.ok(map).build();
    }

    @GET
    @Path("/addForm")
    @Template(name = "/formAddEmployee.jsp")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response formAddEmployee() {
        Map<String, Object> map = new HashMap<>();
        map.put("employee", new Employee());
        return Response.ok(map).build();
    }

    @GET
    @Path("/editForm")
    @Template(name = "/formEditEmployee.jsp")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response formEditEmployee(@PathParam("id") int id) {
        Employee employee = employeeService.getById(id);
        return Response.ok(employee).build();
    }
}

/*return Response
            .status(Response.Status.CREATED)
            // 201
            .entity("A new podcast has been created AT THE LOCATION you specified")
    .header("Location",
                    "http://localhost:8888/demo-rest-jersey-spring/podcasts/"
                    + String.valueOf(createPodcastId)).build();*/