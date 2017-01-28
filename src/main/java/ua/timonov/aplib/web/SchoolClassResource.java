package ua.timonov.aplib.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.timonov.aplib.model.SchoolClass;
import ua.timonov.aplib.service.SchoolClassService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * REST resource for SchoolClass
 */
@Path("/classes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class SchoolClassResource {
    private SchoolClassService schoolClassService;

    @Autowired
    public void setSchoolClassService(SchoolClassService schoolClassService) {
        this.schoolClassService = schoolClassService;
    }
    
    @GET
    public List<SchoolClass> getAllSchoolClasses() {
        return schoolClassService.getAll();
    }

    @GET
    @Path("/{id}")
    @Transactional
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
    @Transactional
    public SchoolClass addSchoolClass(SchoolClass schoolClass) {
        return schoolClassService.add(schoolClass);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public SchoolClass updateSchoolClass(@PathParam("id") int id, SchoolClass schoolClass) {
        return schoolClassService.update(id, schoolClass);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public SchoolClass deleteSchoolClass(@PathParam("id") int id) {
        return schoolClassService.delete(id);
    }
    
}
