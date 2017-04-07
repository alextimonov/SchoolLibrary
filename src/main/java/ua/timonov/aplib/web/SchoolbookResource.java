package ua.timonov.aplib.web;

import org.glassfish.jersey.server.mvc.Template;
import org.springframework.beans.factory.annotation.Autowired;
import ua.timonov.aplib.exceptions.ForbidToAddException;
import ua.timonov.aplib.exceptions.ForbidToDeleteException;
import ua.timonov.aplib.exceptions.NoItemInDatabaseException;
import ua.timonov.aplib.model.BookInClass;
import ua.timonov.aplib.model.SchoolClass;
import ua.timonov.aplib.model.Schoolbook;
import ua.timonov.aplib.service.EmployeeService;
import ua.timonov.aplib.service.SchoolClassService;
import ua.timonov.aplib.service.SchoolbookService;

import javax.annotation.security.RolesAllowed;
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
    private static final int NO_ITEM_IN_DB = -1;
    public static final int FORBID_TO_DELETE = -2;
    public static final int FORBID_TO_HANDOUT = -1;
    public static final int FORBID_TO_RETURN = -2;
    private SchoolbookService schoolbookService;
    private EmployeeService employeeService;
    private SchoolClassService schoolClassService;

    @Autowired
    public void setSchoolbookService(SchoolbookService schoolbookService) {
        this.schoolbookService = schoolbookService;
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setSchoolClassService(SchoolClassService schoolClassService) {
        this.schoolClassService = schoolClassService;
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
    public Response getSchoolbookById(@PathParam("id") int id, @QueryParam("classesSelection") String classesSelection) {
        Map<String, Object> map = new HashMap<>();
        Schoolbook schoolbook = schoolbookService.getById(id);
        List<BookInClass> booksInClass = schoolbookService.getBooksInClass(schoolbook);
        int residue = getBookBalance(schoolbook, booksInClass);
        List<SchoolClass> schoolClassesByCourse = schoolClassService.getClassesByCourse(schoolbook.getCourse());
        List<SchoolClass> schoolClassesAll = schoolClassService.getAll();
        map.put("schoolbook", schoolbook);
        map.put("booksInClass", booksInClass);
        map.put("residue", residue);
        if (classesSelection != null && classesSelection.equals("all")) {
            map.put("schoolClasses", schoolClassesAll);
            map.put("classesSelectionAll", true);
        }
        else {
            map.put("schoolClasses", schoolClassesByCourse);
            map.put("classesSelectionAll", false);
        }
        return Response.ok(map).build();
    }

    private int getBookBalance(Schoolbook schoolbook, List<BookInClass> booksInClass) {
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
    @RolesAllowed("ROLE_ADMIN")
    public Schoolbook addSchoolbook(Schoolbook schoolbook) {
        return schoolbookService.add(schoolbook);
    }

    @PUT
    @RolesAllowed("ROLE_ADMIN")
    @Path("/{id}")
    public Schoolbook updateSchoolbook(@PathParam("id") int id, Schoolbook schoolbook) {
        return schoolbookService.update(id, schoolbook);
    }

    @DELETE
    @RolesAllowed("ROLE_ADMIN")
    @Path("/{id}")
    public Schoolbook deleteSchoolbook(@PathParam("id") int id) {
        return schoolbookService.delete(id);
    }

    @GET
    @RolesAllowed("ROLE_ADMIN")
    @Path("/addForm")
    @Template(name = "/schoolbookAddForm.jsp")
    public Response formAddSchoolbook() {
        Map<String, Object> map = new HashMap<>();
        map.put("schoolbook", new Schoolbook());
        map.put("librarians", employeeService.getLibrarians());
        return Response.ok(map).build();
    }

    @GET
    @RolesAllowed("ROLE_ADMIN")
    @Path("/editForm")
    @Template(name = "/schoolbookEditForm.jsp")
    public Response formEditSchoolbook(@QueryParam("id") int id) {
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
            map.put("errorId", NO_ITEM_IN_DB);
            map.put("errorMessage", e.getMessage());
            return Response.ok(map).build();
        }
    }

    @GET
    @RolesAllowed("ROLE_ADMIN")
    @Path("/deleteForm")
    @Template(name = "/schoolbookDeleteForm.jsp")
    public Response formDeleteSchoolbook(@QueryParam("id") int id) {
        Map<String, Object> map = new HashMap<>();
        try {
            Schoolbook schoolbook = schoolbookService.delete(id);
            map.put("schoolbook", schoolbook);
            return Response.ok(map).build();
        }
        catch (NoItemInDatabaseException e) {
            Schoolbook schoolbook = new Schoolbook(id);
            map.put("schoolbook", schoolbook);
            map.put("errorId", NO_ITEM_IN_DB);
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

    @GET
    @RolesAllowed("ROLE_ADMIN")
    @Path("/handoutForm")
    @Template(name = "/schoolbookHandoutForm.jsp")
    public Response formHandoutSchoolbook(@QueryParam("bookId") int id, @QueryParam("classId") int classId,
                                          @QueryParam("booksAmount") int amountToHandout) {
        Map<String, Object> map = new HashMap<>();
        Schoolbook schoolbook = schoolbookService.getById(id);
        SchoolClass schoolClass = schoolClassService.getById(classId);
        try {
            BookInClass bookInClass = schoolbookService.handoutSchoolbooks(schoolClass, schoolbook, amountToHandout);
            map.put("bookInClass", bookInClass);
            map.put("amountToHandout", amountToHandout);
            return Response.ok(map).build();
        }
        catch (ForbidToAddException e) {
            List<BookInClass> booksInClass = schoolbookService.getBooksInClass(schoolbook);
            int balance = getBookBalance(schoolbook, booksInClass);
            BookInClass bookInClass = new BookInClass(schoolbook, schoolClass);
            map.put("bookInClass", bookInClass);
            map.put("amountToHandout", amountToHandout);
            map.put("balance", balance);
            map.put("errorId", FORBID_TO_HANDOUT);
            map.put("errorMessage", e.getMessage());
            return Response.ok(map).build();
        }
    }

    @GET
    @RolesAllowed("ROLE_ADMIN")
    @Path("/returnForm")
    @Template(name = "/schoolbookReturnForm.jsp")
    public Response formReturnSchoolbook(@QueryParam("bookId") int id, @QueryParam("classId") int classId,
                                         @QueryParam("booksAmount") int amountToReturn) {
        Map<String, Object> map = new HashMap<>();
        Schoolbook schoolbook = schoolbookService.getById(id);
        SchoolClass schoolClass = schoolClassService.getById(classId);
        try {
            BookInClass bookInClass = schoolbookService.returnSchoolbooks(schoolClass, schoolbook, amountToReturn);
            map.put("bookInClass", bookInClass);
            map.put("amountToReturn", amountToReturn);
            return Response.ok(map).build();
        }
        catch (ForbidToAddException e) {
            BookInClass bookInClass = schoolbookService.getByClassAndBook(schoolClass, schoolbook);
            int currentAmount = bookInClass.getBooksNumber();
            map.put("bookInClass", bookInClass);
            map.put("amountToReturn", amountToReturn);
            map.put("currentAmount", currentAmount);
            map.put("errorId", FORBID_TO_RETURN);
            map.put("errorMessage", e.getMessage());
            return Response.ok(map).build();
        }
    }
}
