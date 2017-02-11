package ua.timonov.aplib.web;

import org.glassfish.jersey.server.mvc.ErrorTemplate;
import org.glassfish.jersey.server.mvc.Template;
import org.springframework.beans.factory.annotation.Autowired;
import ua.timonov.aplib.model.Schoolbook;
import ua.timonov.aplib.service.SchoolbookService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * REST resource class for Schoolbook
 */
@Path("/books")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_HTML})
public class SchoolbookResource {
    private SchoolbookService schoolbookService;

    @Autowired
    public void setSchoolbookService(SchoolbookService schoolbookService) {
        this.schoolbookService = schoolbookService;
    }

    @GET
    @Template(name = "/schoolbooks.jsp")
    @ErrorTemplate(name = "/error.jsp")
    public Response getAll() {
        Map<String, Object> map = new HashMap<>();
        map.put("message", "Schoolbooks:");
        map.put("schoolbooks", schoolbookService.getAll());
        return Response.ok(map).build();
    }

    @GET
    @Path("/{id}")
    @Template(name = "/schoolbook.jsp")
    @ErrorTemplate(name = "/error.jsp")
    public Response getSchoolbookById(@PathParam("id") int id) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", "Found schoolbook by ID:");
        map.put("schoolbook", schoolbookService.getById(id));
        return Response.ok(map).build();
    }

    /*@GET
    @Path("/{name}")
    @Transactional
    public Schoolbook getByName(@PathParam("name") String name) {
        return schoolbookService.getByName(name);
    }*/

    @POST
    @Template(name = "/schoolbook.jsp")
    @ErrorTemplate(name = "/error.jsp")
    public Response addSchoolbook(Schoolbook schoolbook) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", "Added schoolbook:");
        map.put("schoolbook", schoolbookService.add(schoolbook));
        return Response.status(Response.Status.CREATED).entity(map).build();
    }

    @PUT
    @Path("/{id}")
    @Template(name = "/schoolbook.jsp")
    @ErrorTemplate(name = "/error.jsp")
    public Response updateSchoolbook(@PathParam("id") int id, Schoolbook schoolbook) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", "Updated schoolbook:");
        map.put("schoolbook", schoolbookService.update(id, schoolbook));
        return Response.ok(map).build();
    }

    @DELETE
    @Path("/{id}")
    @Template(name = "/schoolbook.jsp")
    @ErrorTemplate(name = "/error.jsp")
    public Response deleteSchoolbook(@PathParam("id") int id) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", "Deleted schoolbook:");
        map.put("schoolbook", schoolbookService.delete(id));
        return Response.ok(map).build();
    }
}
