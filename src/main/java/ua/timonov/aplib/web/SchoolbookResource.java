package ua.timonov.aplib.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.timonov.aplib.model.Schoolbook;
import ua.timonov.aplib.service.SchoolbookService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * REST resource class for Schoolbook
 */
@Path("/books")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class SchoolbookResource {
    private SchoolbookService schoolbookService;

    @Autowired
    public void setSchoolbookService(SchoolbookService schoolbookService) {
        this.schoolbookService = schoolbookService;
    }

    @GET
    @Transactional
    public List<Schoolbook> getAll() {
        return schoolbookService.getAll();
    }

    @GET
    @Path("/{id}")
    @Transactional
    public Schoolbook getSchoolbookById(@PathParam("id") int id) {
        return schoolbookService.getById(id);
    }

    /*@GET
    @Path("/{name}")
    @Transactional
    public Schoolbook getByName(@PathParam("name") String name) {
        return schoolbookService.getByName(name);
    }*/

    @POST
    @Transactional
    public Schoolbook addSchoolbook(Schoolbook schoolbook) {
        return schoolbookService.add(schoolbook);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Schoolbook updateSchoolbook(@PathParam("id") int id, Schoolbook schoolbook) {
        return schoolbookService.update(id, schoolbook);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Schoolbook deleteSchoolbook(@PathParam("id") int id) {
        return schoolbookService.delete(id);
    }
}
