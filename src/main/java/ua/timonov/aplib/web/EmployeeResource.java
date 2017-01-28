package ua.timonov.aplib.web;

import org.glassfish.jersey.server.mvc.Viewable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.timonov.aplib.model.Employee;
import ua.timonov.aplib.service.EmployeeService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * REST resource class for Employee
 */
@Path("/employees")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_HTML})
//@Produces(MediaType.TEXT_HTML)
public class EmployeeResource {
    private EmployeeService employeeService = new EmployeeService();

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GET
//    @Template(name = "employees.jsp")
//    @ErrorTemplate(name = "employeeId.jsp")
    public Viewable getEmployees() {
        return new Viewable("/employees.jsp", employeeService.getAll());
    }

    @GET
    @Path("/{id}")
//    @Template(name = "employeeId.jsp")
    public Viewable getEmployeeById(@PathParam("id") int id) {
        return new Viewable("/employeeId.jsp", employeeService.getById(id));
//        return employeeService.getById(id);
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
    @Transactional
    public Employee addEmployee(Employee employee) {
        return employeeService.add(employee);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Employee updateEmployee(@PathParam("id") int id, Employee employee) {
        return employeeService.update(id, employee);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Employee deleteEmployee(@PathParam("id") int id) {
        return employeeService.delete(id);
    }

}
