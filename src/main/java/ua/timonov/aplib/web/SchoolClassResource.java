package ua.timonov.aplib.web;

import org.glassfish.jersey.server.mvc.Template;
import org.springframework.beans.factory.annotation.Autowired;
import ua.timonov.aplib.model.SchoolClass;
import ua.timonov.aplib.model.SchoolClassDb;
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
    private SchoolClassService schoolClassService;

    @Autowired
    public void setSchoolClassService(SchoolClassService schoolClassService) {
        this.schoolClassService = schoolClassService;
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
    @Template(name = "/schoolclass.jsp")
    public SchoolClass addSchoolClass(SchoolClass schoolClass) {
        return schoolClassService.add(schoolClass);
    }

    @PUT
    @Path("/{id}")
    @Template(name = "/schoolclass.jsp")
    public SchoolClass updateSchoolClass(@PathParam("id") int id, SchoolClass schoolClass) {
        return schoolClassService.update(id, schoolClass);
    }

    @DELETE
    @Path("/{id}")
    @Template(name = "/schoolclass.jsp")
    public SchoolClass deleteSchoolClass(@PathParam("id") int id) {
        return schoolClassService.delete(id);
    }

    @GET
    @Path("/addForm")
    @Template(name = "/schoolbookAddForm.jsp")
    public Response formAddSchoolbook() {
        Map<String, Object> map = new HashMap<>();
        map.put("schoolbook", new SchoolClassDb());
//        map.put("librarians", schoolClassService.getLibrarians());
        return Response.ok(map).build();
    }

    @GET
    @Path("/editForm")
    @Template(name = "/schoolbookEditForm.jsp")
    public Response formEditEmployee(@QueryParam("id") int id) {
        SchoolClass schoolClass = schoolClassService.getById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("schoolbook", schoolClass);
//        map.put("librarians", schoolClassService.getLibrarians());
        return Response.ok(map).build();
    }

    @GET
    @Path("/deleteForm")
    @Template(name = "/schoolbookDeleteForm.jsp")
    public Response formDeleteEmployee(@QueryParam("id") int id) {
        SchoolClass schoolClass = schoolClassService.delete(id);
        return Response.ok(schoolClass).build();
    }
}
