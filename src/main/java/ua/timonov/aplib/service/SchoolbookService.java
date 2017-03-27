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
        SchoolbookDto schoolbookDto = getSchoolbookDto(schoolbook);
        return new Schoolbook(schoolbookDao.add(schoolbookDto));
    }

    @Transactional
    public SchoolbookDto getSchoolbookDto(Schoolbook schoolbook) {
        SchoolbookDto schoolbookDto = new SchoolbookDto();
        schoolbookDto.setId(schoolbook.getId());
        schoolbookDto.setName(schoolbook.getName());
        schoolbookDto.setCourse(schoolbook.getCourse());
        schoolbookDto.setAmountTotal(schoolbook.getAmountTotal());
        int librarianId = schoolbook.getLibrarian().getId();
        Employee librarian = employeeService.getById(librarianId);
        schoolbookDto.setLibrarian(employeeService.getEmployeeDto(librarian));
        return schoolbookDto;
    }

    @Transactional
    public Schoolbook update(int id, Schoolbook schoolbook) {
        schoolbook.setId(id);
        SchoolbookDto schoolbookDto = getSchoolbookDto(schoolbook);
        return new Schoolbook(schoolbookDao.update(schoolbookDto));
    }

    @Transactional
    public Schoolbook delete(int id) {
        return new Schoolbook(schoolbookDao.delete(id));
    }

    @Transactional
    public List<Schoolbook> getAll() {
        List<Schoolbook> schoolbooks = new ArrayList<>();
        for (SchoolbookDto schoolbookDto : schoolbookDao.getAll()) {
            schoolbooks.add(new Schoolbook(schoolbookDto));
        }
        return schoolbooks;
    }

    @Transactional
    public Schoolbook getById(int id) {
        SchoolbookDto schoolbookDto = schoolbookDao.getById(id);
        return new Schoolbook(schoolbookDto);
    }

    @Transactional
    public Schoolbook getByName(String name) {
        SchoolbookDto schoolbookDto = schoolbookDao.getByName(name);
        return new Schoolbook(schoolbookDto);
    }

    @Transactional
    public BookInClassDto getBookInClassDto(BookInClass bookInClass) {
        BookInClassDto bookInClassDto = new BookInClassDto();
        bookInClassDto.setnBooksInClass(bookInClass.getBooksNumber());
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