package ua.timonov.aplib.service;

import org.springframework.transaction.annotation.Transactional;
import ua.timonov.aplib.dao.BookInClassDao;
import ua.timonov.aplib.dao.SchoolbookDao;
import ua.timonov.aplib.dto.BookInClassDto;
import ua.timonov.aplib.dto.SchoolbookDto;
import ua.timonov.aplib.model.BookInClass;
import ua.timonov.aplib.model.Employee;
import ua.timonov.aplib.model.Schoolbook;

import java.util.ArrayList;
import java.util.List;

/**
 * Web service for SchoolbookDao
 */
public class SchoolbookService {
    private SchoolbookDao schoolbookDao;
    private BookInClassDao bookInClassDao;
    private EmployeeService employeeService;

    public void setSchoolbookDao(SchoolbookDao schoolbookDao) {
        this.schoolbookDao = schoolbookDao;
    }

    public void setBookInClassDao(BookInClassDao bookInClassDao) {
        this.bookInClassDao = bookInClassDao;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Transactional
    public Schoolbook add(Schoolbook schoolbook) {
        SchoolbookDto schoolbookDb = getSchoolbookDto(schoolbook);
        return new Schoolbook(schoolbookDao.add(schoolbookDb));
    }

    @Transactional
    public SchoolbookDto getSchoolbookDto(Schoolbook schoolbook) {
        SchoolbookDto schoolbookDb = new SchoolbookDto();
        schoolbookDb.setId(schoolbook.getId());
        schoolbookDb.setName(schoolbook.getName());
        schoolbookDb.setCourse(schoolbook.getCourse());
        schoolbookDb.setAmountTotal(schoolbook.getAmountTotal());
        int librarianId = schoolbook.getLibrarian().getId();
        Employee librarian = employeeService.getById(librarianId);
        schoolbookDb.setLibrarian(employeeService.getEmployeeDb(librarian));
        return schoolbookDb;
    }

    @Transactional
    public Schoolbook update(int id, Schoolbook schoolbook) {
        schoolbook.setId(id);
        SchoolbookDto schoolbookDb = getSchoolbookDto(schoolbook);
        return new Schoolbook(schoolbookDao.update(schoolbookDb));
    }

    @Transactional
    public Schoolbook delete(int id) {
        return new Schoolbook(schoolbookDao.delete(id));
    }

    @Transactional
    public List<Schoolbook> getAll() {
        List<Schoolbook> schoolbooks = new ArrayList<>();
        for (SchoolbookDto schoolbookDb : schoolbookDao.getAll()) {
            schoolbooks.add(new Schoolbook(schoolbookDb));
        }
        return schoolbooks;
    }

    @Transactional
    public Schoolbook getById(int id) {
        SchoolbookDto schoolbookDb = schoolbookDao.getById(id);
        return new Schoolbook(schoolbookDb);
    }

    @Transactional
    public Schoolbook getByName(String name) {
        SchoolbookDto schoolbookDb = schoolbookDao.getByName(name);
        return new Schoolbook(schoolbookDb);
    }

    @Transactional
    public BookInClassDto getBookInClassDto(BookInClass bookInClass) {
        BookInClassDto bookInClassDto = new BookInClassDto();
        bookInClassDto.setnBooksInClass(bookInClass.getnBooksInClass());
        bookInClassDto.setSchoolbook(getSchoolbookDto(bookInClass.getSchoolbook()));
        return bookInClassDto;
    }

    @Transactional
    public List<BookInClass> getBooksInClass(Schoolbook schoolbook) {
        SchoolbookDto schoolbookDto = getSchoolbookDto(schoolbook);
        List<BookInClassDto> booksInClassDto = bookInClassDao.getByBook(schoolbookDto);
        List<BookInClass> booksInClass = new ArrayList<>();
        for (BookInClassDto bookInClassDto : booksInClassDto) {
            booksInClass.add(new BookInClass(bookInClassDto));
        }
        return booksInClass;
    }
}