package ua.timonov.aplib.service;

import org.springframework.transaction.annotation.Transactional;
import ua.timonov.aplib.dao.BookInClassDao;
import ua.timonov.aplib.dao.SchoolClassDao;
import ua.timonov.aplib.dto.BookInClassDto;
import ua.timonov.aplib.dto.EmployeeDto;
import ua.timonov.aplib.dto.SchoolClassDto;
import ua.timonov.aplib.model.BookInClass;
import ua.timonov.aplib.model.Employee;
import ua.timonov.aplib.model.SchoolClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Web service for SchoolClassDao
 */
public class SchoolClassService {
    private EmployeeService employeeService;
    private SchoolbookService schoolbookService;
    private SchoolClassDao schoolClassDao;
    private BookInClassDao bookInClassDao;

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void setSchoolbookService(SchoolbookService schoolbookService) {
        this.schoolbookService = schoolbookService;
    }

    public void setSchoolClassDao(SchoolClassDao schoolClassDao) {
        this.schoolClassDao = schoolClassDao;
    }

    public void setBookInClassDao(BookInClassDao bookInClassDao) {
        this.bookInClassDao = bookInClassDao;
    }

    @Transactional
    public SchoolClass add(SchoolClass schoolClass) {
        SchoolClassDto schoolClassDb = getSchoolClassDto(schoolClass);
        schoolClassDao.add(schoolClassDb);
        return schoolClass;
    }

    @Transactional
    private SchoolClassDto getSchoolClassDto(SchoolClass schoolClass) {
        SchoolClassDto schoolClassDto = new SchoolClassDto();
        schoolClassDto.setId(schoolClass.getId());
        schoolClassDto.setCourse(schoolClass.getCourse());
        schoolClassDto.setLetter(schoolClass.getLetter());

        int teacherId = schoolClass.getTeacher().getId();
        Employee teacher = employeeService.getById(teacherId);
        EmployeeDto teacherDto = employeeService.getEmployeeDto(teacher);
        schoolClassDto.setTeacher(teacherDto);

        List<BookInClass> booksInClass = schoolClass.getBooksInClass();
        List<BookInClassDto> booksInClassDto = new ArrayList<>();
        for (BookInClass bookInClass : booksInClass) {
            booksInClassDto.add(schoolbookService.getBookInClassDto(bookInClass));
        }
        schoolClassDto.setBooksInClass(booksInClassDto);
        return schoolClassDto;
    }

    @Transactional
    public SchoolClass update(int id, SchoolClass schoolClass) {
        schoolClass.setId(id);
        SchoolClassDto schoolClassDb = getSchoolClassDto(schoolClass);
        return new SchoolClass(schoolClassDao.update(schoolClassDb));
    }

    @Transactional
    public SchoolClass delete(int id) {
        return new SchoolClass(schoolClassDao.delete(id));
    }

    @Transactional
    public List<SchoolClass> getAll() {
        List<SchoolClassDto> schoolClassesDto = schoolClassDao.getAll();
        List<SchoolClass> schoolClasses = new ArrayList<>();
        for (SchoolClassDto schoolClassDto : schoolClassesDto) {
            List<BookInClassDto> booksInClassDto = bookInClassDao.getByClass(schoolClassDto);
            schoolClassDto.setBooksInClass(booksInClassDto);
            schoolClasses.add(new SchoolClass(schoolClassDto));
        }
        return schoolClasses;
    }

    @Transactional
    public SchoolClass getById(int classId) {
        SchoolClassDto schoolClassDto = schoolClassDao.getById(classId);
        List<BookInClassDto> booksInClassDto = bookInClassDao.getByClass(schoolClassDto);
        schoolClassDto.setBooksInClass(booksInClassDto);
        return new SchoolClass(schoolClassDto);
    }

    @Transactional
    public SchoolClass getByName(String name) {
        int course = (int) name.charAt(0);
        char letter = name.charAt(1);
        return new SchoolClass(schoolClassDao.getByName(course, letter));
    }
}
