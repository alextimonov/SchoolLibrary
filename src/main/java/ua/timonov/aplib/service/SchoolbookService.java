package ua.timonov.aplib.service;

import org.springframework.transaction.annotation.Transactional;
import ua.timonov.aplib.dao.SchoolbookDao;
import ua.timonov.aplib.model.Employee;
import ua.timonov.aplib.model.Schoolbook;
import ua.timonov.aplib.model.SchoolbookDb;

import java.util.ArrayList;
import java.util.List;

/**
 * Web service for SchoolbookDao
 */
public class SchoolbookService {
    private SchoolbookDao schoolbookDao;
    private EmployeeService employeeService;

    public void setSchoolbookDao(SchoolbookDao schoolbookDao) {
        this.schoolbookDao = schoolbookDao;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Transactional
    public Schoolbook add(Schoolbook schoolbook) {
        SchoolbookDb schoolbookDb = getSchoolbookDb(schoolbook);
        schoolbookDao.add(schoolbookDb);
        return schoolbook;
    }

    @Transactional
    private SchoolbookDb getSchoolbookDb(Schoolbook schoolbook) {
        SchoolbookDb schoolbookDb = new SchoolbookDb();
        schoolbookDb.setId(schoolbook.getId());
        schoolbookDb.setName(schoolbook.getName());
        schoolbookDb.setCourse(schoolbook.getCourse());
        schoolbookDb.setAmountTotal(schoolbook.getCourse());
        int librarianId = schoolbook.getLibrarian().getId();
        Employee librarian = employeeService.getById(librarianId);
        schoolbookDb.setLibrarian(employeeService.getEmployeeDb(librarian));
        return schoolbookDb;
    }

    @Transactional
    public Schoolbook update(int id, Schoolbook schoolbook) {
        schoolbook.setId(id);
        SchoolbookDb schoolbookDb = getSchoolbookDb(schoolbook);
        return new Schoolbook(schoolbookDao.update(schoolbookDb));
    }

    @Transactional
    public Schoolbook delete(int id) {
        return new Schoolbook(schoolbookDao.delete(id));
    }

    @Transactional
    public List<Schoolbook> getAll() {
        List<Schoolbook> schoolbooks = new ArrayList<>();
        for (SchoolbookDb schoolbookDb : schoolbookDao.getAll()) {
            schoolbooks.add(new Schoolbook(schoolbookDb));
        }
        return schoolbooks;
    }

    @Transactional
    public Schoolbook getById(int id) {
        SchoolbookDb schoolbookDb = schoolbookDao.getById(id);
        return new Schoolbook(schoolbookDb);
    }

    @Transactional
    public Schoolbook getByName(String name) {
        SchoolbookDb schoolbookDb = schoolbookDao.getByName(name);
        return new Schoolbook(schoolbookDb);
    }
}