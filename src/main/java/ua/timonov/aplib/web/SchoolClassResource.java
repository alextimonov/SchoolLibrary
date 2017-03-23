package ua.timonov.aplib.web;

import org.glassfish.jersey.server.mvc.ErrorTemplate;
import org.glassfish.jersey.server.mvc.Template;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ua.timonov.aplib.model.SchoolClass;
import ua.timonov.aplib.service.EmployeeService;
import ua.timonov.aplib.service.SchoolClassService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * REST resource for SchoolClass
 */
@Path("/classes")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_HTML})
public class SchoolClassResource {
    private static final int ERROR_ID = -1;
    private SchoolClassService schoolClassService;
    private EmployeeService employeeService;

    @Autowired
    public void setSchoolClassService(SchoolClassService schoolClassService) {
        this.schoolClassService = schoolClassService;
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GET
    @Template(name = "/schoolclasses.jsp")
//    @ErrorTemplate(name = "/error.jsp")
    public Response getAllSchoolClasses() {
        Map<String, Object> map = new HashMap<>();
        map.put("message", "Classes:");
        map.put("schoolClasses", schoolClassService.getAll());
        return Response.ok(map).build();
    }

    @GET
    @Path("/{id}")
    @Template(name = "/schoolclass.jsp")
    public SchoolClass getSchoolClassById(@PathParam("id") int id) {
        return schoolClassService.getById(id);
    }

    /*@GET
    @Path("/{name}")
    @Transactional
    public SchoolClass getSchoolClassByName(@PathParam("name") String name) {
        return schoolClassService.getByName(name);
    }*/
    
    @POST
    @ErrorTemplate(name = "/errorAdd.jsp")
    public SchoolClass addSchoolClass(SchoolClass schoolClass) {
        return schoolClassService.add(schoolClass);
//        return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage());
    }

    @PUT
    @Path("/{id}")
    public SchoolClass updateSchoolClass(@PathParam("id") int id, SchoolClass schoolClass) {
        return schoolClassService.update(id, schoolClass);
    }

    @DELETE
    @Path("/{id}")
    public SchoolClass deleteSchoolClass(@PathParam("id") int id) {
        return schoolClassService.delete(id);
    }

    @GET
    @Path("/addForm")
    @Template(name = "/schoolClassAddForm.jsp")
    public Response formAddSchoolbook() {
        Map<String, Object> map = new HashMap<>();
        map.put("schoolClass", new SchoolClass());
        map.put("teachers", employeeService.getTeachers());
        return Response.ok(map).build();
    }

    @GET
    @Path("/editForm")
    @Template(name = "/schoolClassEditForm.jsp")
    public Response formEditEmployee(@QueryParam("id") int id) {
        SchoolClass schoolClass = schoolClassService.getById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("schoolClass", schoolClass);
        map.put("teachers", employeeService.getTeachers());
        return Response.ok(map).build();
    }

    @GET
    @Path("/deleteForm")
    @Template(name = "/schoolClassDeleteForm.jsp")
    public Response formDeleteEmployee(@QueryParam("id") int id) {
        try {
            SchoolClass schoolClass = schoolClassService.delete(id);
            return Response.ok(schoolClass).build();
        }
        catch (HibernateException | DataAccessException e) {
            SchoolClass schoolClass = schoolClassService.getById(id);
            schoolClass.setId(ERROR_ID);
            return Response.ok(schoolClass).build();
        }
    }
}
