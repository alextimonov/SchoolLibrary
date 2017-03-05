package ua.timonov.aplib.web;

import org.glassfish.jersey.server.mvc.ErrorTemplate;
import org.glassfish.jersey.server.mvc.Template;
import org.springframework.beans.factory.annotation.Autowired;
import ua.timonov.aplib.model.SchoolClass;
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
@Consumes(MediaType.APPLICATION_JSON)
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_HTML})
public class SchoolClassResource {
    private SchoolClassService schoolClassService;

    @Autowired
    public void setSchoolClassService(SchoolClassService schoolClassService) {
        this.schoolClassService = schoolClassService;
    }
    
    @GET
    @Template(name = "/schoolclasses.jsp")
    @ErrorTemplate(name = "/error.jsp")
    public Response getAllSchoolClasses() {
        Map<String, Object> map = new HashMap<>();
        map.put("message", "Classes:");
        map.put("schoolClasses", schoolClassService.getAll());
        return Response.ok(map).build();
    }

    @GET
    @Path("/{id}")
    @Template(name = "/schoolclass.jsp")
    @ErrorTemplate(name = "/error.jsp")
    public Response getSchoolClassById(@PathParam("id") int id) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", "Found class by ID:");
        map.put("schoolClass", schoolClassService.getAll());
        return Response.ok(map).build();
    }

    /*@GET
    @Path("/{name}")
    @Transactional
    public SchoolClass getSchoolClassByName(@PathParam("name") String name) {
        return schoolClassService.getByName(name);
    }*/
    
    @POST
    @Template(name = "/schoolclass.jsp")
    @ErrorTemplate(name = "/error.jsp")
    public Response addSchoolClass(SchoolClass schoolClass) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", "Added class:");
        map.put("schoolClass", schoolClassService.add(schoolClass));
        return Response.status(Response.Status.CREATED).entity(map).build();
    }

    @PUT
    @Path("/{id}")
    @Template(name = "/schoolclass.jsp")
    @ErrorTemplate(name = "/error.jsp")
    public Response updateSchoolClass(@PathParam("id") int id, SchoolClass schoolClass) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", "Updated class:");
        map.put("schoolClass", schoolClassService.update(id, schoolClass));
        return Response.ok(map).build();
    }

    @DELETE
    @Path("/{id}")
    @Template(name = "/schoolclass.jsp")
    @ErrorTemplate(name = "/error.jsp")
    public Response deleteSchoolClass(@PathParam("id") int id) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", "Deleted class:");
        map.put("schoolClass", schoolClassService.delete(id));
        return Response.ok(map).build();
    }
    
}
