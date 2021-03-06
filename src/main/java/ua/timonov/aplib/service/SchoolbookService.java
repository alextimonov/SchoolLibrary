package ua.timonov.aplib.service;

import org.springframework.transaction.annotation.Transactional;
import ua.timonov.aplib.dao.BookInClassDao;
import ua.timonov.aplib.dao.EmployeeDao;
import ua.timonov.aplib.dao.SchoolbookDao;
import ua.timonov.aplib.dto.BookInClassDto;
import ua.timonov.aplib.dto.EmployeeDto;
import ua.timonov.aplib.dto.SchoolClassDto;
import ua.timonov.aplib.dto.SchoolbookDto;
import ua.timonov.aplib.model.BookInClass;
import ua.timonov.aplib.model.SchoolClass;
import ua.timonov.aplib.model.Schoolbook;

import java.util.ArrayList;
import java.util.List;

/**
 * Web service for SchoolbookDao
 */
public class SchoolbookService {
    private SchoolClassService schoolClassService;
    private SchoolbookDao schoolbookDao;
    private BookInClassDao bookInClassDao;
    private EmployeeDao employeeDao;

    public void setSchoolClassService(SchoolClassService schoolClassService) {
        this.schoolClassService = schoolClassService;
    }

    public void setSchoolbookDao(SchoolbookDao schoolbookDao) {
        this.schoolbookDao = schoolbookDao;
    }

    public void setBookInClassDao(BookInClassDao bookInClassDao) {
        this.bookInClassDao = bookInClassDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
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
        schoolbookDto.setCourse(schoolbook.getCourse());
        schoolbookDto.setName(schoolbook.getName());
        schoolbookDto.setAuthor(schoolbook.getAuthor());
        schoolbookDto.setPublisher(schoolbook.getPublisher());
        schoolbookDto.setAmountTotal(schoolbook.getAmountTotal());
        int librarianId = schoolbook.getLibrarian().getId();
        EmployeeDto librarian = employeeDao.getEmployeeById(librarianId);
        schoolbookDto.setLibrarian(librarian);
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
    public List<Schoolbook> getBookByCourse(int course) {
        List<Schoolbook> schoolbooks = new ArrayList<>();
        for (SchoolbookDto schoolbookDto : schoolbookDao.getSchoolbooksByCourse(course)) {
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
        bookInClassDto.setId(bookInClass.getId());
        bookInClassDto.setBooksNumber(bookInClass.getBooksNumber());
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

    @Transactional
    public BookInClass handoutSchoolbooks(SchoolClass schoolClass, Schoolbook schoolbook, int amountToHandout) {
        SchoolbookDto schoolbookDto = getSchoolbookDto(schoolbook);
        SchoolClassDto schoolClassDto = schoolClassService.getSchoolClassDto(schoolClass);
        return new BookInClass(bookInClassDao.handoutSchoolbooks(schoolClassDto, schoolbookDto, amountToHandout));
    }

    @Transactional
    public BookInClass returnSchoolbooks(SchoolClass schoolClass, Schoolbook schoolbook, int amountToReturn) {
        SchoolbookDto schoolbookDto = getSchoolbookDto(schoolbook);
        SchoolClassDto schoolClassDto = schoolClassService.getSchoolClassDto(schoolClass);
        return new BookInClass(bookInClassDao.returnSchoolbooks(schoolClassDto, schoolbookDto, amountToReturn));
    }

    @Transactional
    public BookInClass getByClassAndBook(SchoolClass schoolClass, Schoolbook schoolbook) {
        SchoolbookDto schoolbookDto = getSchoolbookDto(schoolbook);
        SchoolClassDto schoolClassDto = schoolClassService.getSchoolClassDto(schoolClass);
        return new BookInClass(bookInClassDao.getByClassAndBook(schoolClassDto, schoolbookDto));
    }

    @Transactional
    public BookInClass deleteBookInClass(int bookInClassId) {
        return new BookInClass(bookInClassDao.deleteBookInClass(bookInClassId));
    }
}