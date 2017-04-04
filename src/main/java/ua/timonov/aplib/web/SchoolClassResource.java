package ua.timonov.aplib.web;

import org.glassfish.jersey.server.mvc.ErrorTemplate;
import org.glassfish.jersey.server.mvc.Template;
import org.springframework.beans.factory.annotation.Autowired;
import ua.timonov.aplib.exceptions.ForbidToAddException;
import ua.timonov.aplib.exceptions.ForbidToDeleteException;
import ua.timonov.aplib.exceptions.NoItemInDatabaseException;
import ua.timonov.aplib.model.Employee;
import ua.timonov.aplib.model.SchoolClass;
import ua.timonov.aplib.service.EmployeeService;
import ua.timonov.aplib.service.SchoolClassService;

import javax.annotation.security.RolesAllowed;
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
    public static final int NO_SCHOOLCLASS_IN_DB = -1;
    public static final int FORBID_TO_DELETE = -2;
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
    public Response getSchoolClassById(@PathParam("id") int id) {
        Map<String, Object> map = new HashMap<>();
        SchoolClass schoolClass = schoolClassService.getById(id);
        map.put("schoolClass", schoolClass);
        return Response.ok(map).build();
    }

    /*@GET
    @Path("/{name}")
    @Transactional
    public SchoolClass getSchoolClassByName(@PathParam("name") String name) {
        return schoolClassService.getSchoolClassByName(name);
    }*/
    
    @POST
    @RolesAllowed("ROLE_ADMIN")
    @ErrorTemplate(name = "/errorMessage.jsp")
    public SchoolClass addSchoolClass(SchoolClass schoolClass) {
        return schoolClassService.add(schoolClass);
//        return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage());
    }

    @PUT
    @RolesAllowed("ROLE_ADMIN")
    @Path("/{id}")
    @ErrorTemplate(name = "/errorMessage.jsp")
    public SchoolClass updateSchoolClass(@PathParam("id") int id, SchoolClass schoolClass) {
        schoolClass.setId(id);
        if (foundClassWithSameName(schoolClass) || foundClassWithSameTeacher(schoolClass)) {
            throw new ForbidToAddException("PUT. Class is already in school or teacher is already curator of another class.");
        }
        else {
            return schoolClassService.update(schoolClass);
        }
    }

    private boolean foundClassWithSameName(SchoolClass newSchoolClass) {
        SchoolClass foundSchoolClass = schoolClassService.getByName(newSchoolClass.getCourse(), newSchoolClass.getLetter());
        return foundClassHasAnotherId(newSchoolClass, foundSchoolClass);
    }

    private boolean foundClassHasAnotherId(SchoolClass newSchoolClass, SchoolClass foundSchoolClass) {
        return foundSchoolClass.getId() != 0 && newSchoolClass.getId() != foundSchoolClass.getId();
    }

    private boolean foundClassWithSameTeacher(SchoolClass newSchoolClass) {
        SchoolClass foundSchoolClass = schoolClassService.getByTeacherId(newSchoolClass.getTeacher().getId());
        return foundClassHasSameTeacher(newSchoolClass, foundSchoolClass);
    }

    private boolean foundClassHasSameTeacher(SchoolClass newClass, SchoolClass foundClass) {
        Employee newClassTeacher = newClass.getTeacher();
        Employee foundClassTeacher = foundClass.getTeacher();
        return foundClassTeacher != null && newClass.getId() != foundClass.getId() &&
                newClassTeacher.getId() == foundClassTeacher.getId();
    }

    @DELETE
    @RolesAllowed("ROLE_ADMIN")
    @Path("/{id}")
    @ErrorTemplate(name = "/errorMessage.jsp")
    public SchoolClass deleteSchoolClass(@PathParam("id") int id) {
        return schoolClassService.delete(id);
    }

    @GET
    @RolesAllowed("ROLE_ADMIN")
    @Path("/addForm")
    @Template(name = "/schoolClassAddForm.jsp")
    public Response formAddSchoolClass() {
        Map<String, Object> map = new HashMap<>();
        map.put("schoolClass", new SchoolClass());
        map.put("teachers", employeeService.getTeachers());
        return Response.ok(map).build();
    }

    @GET
    @RolesAllowed("ROLE_ADMIN")
    @Path("/editForm")
    @Template(name = "/schoolClassEditForm.jsp")
    public Response formEditSchoolClass(@QueryParam("id") int id) {
        Map<String, Object> map = new HashMap<>();
        try {
            SchoolClass schoolClass = schoolClassService.getById(id);
            map.put("schoolClass", schoolClass);
            map.put("teachers", employeeService.getTeachers());
            return Response.ok(map).build();
        }
        catch (NoItemInDatabaseException e) {
            SchoolClass schoolClass = new SchoolClass(id);
            map.put("schoolClass", schoolClass);
            return Response.ok(map).build();
        }
    }

    @GET
    @RolesAllowed("ROLE_ADMIN")
    @Path("/deleteForm")
    @Template(name = "/schoolClassDeleteForm.jsp")
    public Response formDeleteSchoolClass(@QueryParam("id") int id) {
        Map<String, Object> map = new HashMap<>();
        try {
            SchoolClass schoolClass = schoolClassService.delete(id);
            map.put("schoolClass", schoolClass);
            return Response.ok(map).build();
        }
        catch (NoItemInDatabaseException e) {
            SchoolClass schoolClass = new SchoolClass(id);
            map.put("schoolClass", schoolClass);
            map.put("errorId", NO_SCHOOLCLASS_IN_DB);
            map.put("errorMessage", e.getMessage());
            return Response.ok(map).build();
        }
        catch (ForbidToDeleteException e) {
            SchoolClass schoolClass = schoolClassService.getById(id);
            map.put("schoolClass", schoolClass);
            map.put("errorId", FORBID_TO_DELETE);
            map.put("errorMessage", e.getMessage());
            return Response.ok(map).build();
        }
    }
}
