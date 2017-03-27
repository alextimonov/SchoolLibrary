package ua.timonov.aplib.web;

import org.glassfish.jersey.server.mvc.Template;
import org.springframework.beans.factory.annotation.Autowired;
import ua.timonov.aplib.exceptions.ForbidToDeleteException;
import ua.timonov.aplib.exceptions.NoItemInDatabaseException;
import ua.timonov.aplib.model.BookInClass;
import ua.timonov.aplib.model.Schoolbook;
import ua.timonov.aplib.service.EmployeeService;
import ua.timonov.aplib.service.SchoolbookService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * REST resource class for Schoolbook
 */
@Path("/books")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_HTML})
public class SchoolbookResource {
    private static final int NO_SCHOOLBOOK_IN_DB = -1;
    public static final int FORBID_TO_DELETE = -2;
    private SchoolbookService schoolbookService;
    private EmployeeService employeeService;

    @Autowired
    public void setSchoolbookService(SchoolbookService schoolbookService) {
        this.schoolbookService = schoolbookService;
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GET
    @Template(name = "/schoolbooks.jsp")
    public Response getAll() {
        Map<String, Object> map = new HashMap<>();
        map.put("message", "Schoolbooks:");
        map.put("schoolbooks", schoolbookService.getAll());
        return Response.ok(map).build();
    }

    @GET
    @Path("/{id}")
    @Template(name = "/schoolbook.jsp")
    public Response getSchoolbookById(@PathParam("id") int id) {
        Map<String, Object> map = new HashMap<>();
        Schoolbook schoolbook = schoolbookService.getById(id);
        List<BookInClass> booksInClass = schoolbookService.getBooksInClass(schoolbook);
        int residue = getBookResidue(schoolbook, booksInClass);
        map.put("schoolbook", schoolbook);
        map.put("booksInClass", booksInClass);
        map.put("residue", residue);
        return Response.ok(map).build();
    }

    private int getBookResidue(Schoolbook schoolbook, List<BookInClass> booksInClass) {
        int amountTotal = schoolbook.getAmountTotal();
        int amountInClasses = 0;
        for (BookInClass bookInClass : booksInClass) {
            amountInClasses += bookInClass.getBooksNumber();
        }
        return amountTotal - amountInClasses;
    }

    /*@GET
    @Path("/{name}")
    @Transactional
    public Schoolbook getSchoolClassByName(@PathParam("name") String name) {
        return schoolbookService.getSchoolClassByName(name);
    }*/

    @POST
    public Schoolbook addSchoolbook(Schoolbook schoolbook) {
        return schoolbookService.add(schoolbook);
    }

    @PUT
    @Path("/{id}")
    public Schoolbook updateSchoolbook(@PathParam("id") int id, Schoolbook schoolbook) {
        return schoolbookService.update(id, schoolbook);
    }

    @DELETE
    @Path("/{id}")
    public Schoolbook deleteSchoolbook(@PathParam("id") int id) {
        return schoolbookService.delete(id);
    }

    @GET
    @Path("/addForm")
    @Template(name = "/schoolbookAddForm.jsp")
    public Response formAddSchoolbook() {
        Map<String, Object> map = new HashMap<>();
        map.put("schoolbook", new Schoolbook());
        map.put("librarians", employeeService.getLibrarians());
        return Response.ok(map).build();
    }

    @GET
    @Path("/editForm")
    @Template(name = "/schoolbookEditForm.jsp")
    public Response formEditEmployee(@QueryParam("id") int id) {
        Map<String, Object> map = new HashMap<>();
        try {
            Schoolbook schoolbook = schoolbookService.getById(id);
            map.put("schoolbook", schoolbook);
            map.put("librarians", employeeService.getLibrarians());
            return Response.ok(map).build();
        }
        catch (NoItemInDatabaseException e) {
            Schoolbook schoolbook = new Schoolbook(id);
            map.put("schoolbook", schoolbook);
            map.put("errorId", NO_SCHOOLBOOK_IN_DB);
            return Response.ok(map).build();
        }
    }

    @GET
    @Path("/deleteForm")
    @Template(name = "/schoolbookDeleteForm.jsp")
    public Response formDeleteEmployee(@QueryParam("id") int id) {
        Map<String, Object> map = new HashMap<>();
        try {
            Schoolbook schoolbook = schoolbookService.delete(id);
            map.put("schoolbook", schoolbook);
            return Response.ok(map).build();
        }
        catch (NoItemInDatabaseException e) {
            Schoolbook schoolbook = new Schoolbook(id);
            map.put("schoolbook", schoolbook);
            map.put("errorId", NO_SCHOOLBOOK_IN_DB);
            map.put("errorMessage", e.getMessage());
            return Response.ok(map).build();
        }
        catch (ForbidToDeleteException e) {
            Schoolbook schoolbook = schoolbookService.getById(id);
            map.put("schoolbook", schoolbook);
            map.put("errorId", FORBID_TO_DELETE);
            map.put("errorMessage", e.getMessage());
            return Response.ok(map).build();
        }
    }
}
