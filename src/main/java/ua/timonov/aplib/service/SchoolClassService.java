package ua.timonov.aplib.service;

import org.springframework.transaction.annotation.Transactional;
import ua.timonov.aplib.dao.BookInClassDao;
import ua.timonov.aplib.dao.SchoolClassDao;
import ua.timonov.aplib.dto.BookInClassDto;
import ua.timonov.aplib.dto.EmployeeDto;
import ua.timonov.aplib.dto.SchoolClassDto;
import ua.timonov.aplib.exceptions.NoItemInDatabaseException;
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
        SchoolClassDto schoolClassDto = getSchoolClassDto(schoolClass);
        return new SchoolClass(schoolClassDao.add(schoolClassDto));
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
    public SchoolClass update(SchoolClass schoolClass) {
        SchoolClassDto schoolClassDto = getSchoolClassDto(schoolClass);
        return new SchoolClass(schoolClassDao.update(schoolClassDto));
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
        SchoolClassDto schoolClassDto = schoolClassDao.getSchoolClassById(classId);
        if (schoolClassDto != null) {
            List<BookInClassDto> booksInClassDto = bookInClassDao.getByClass(schoolClassDto);
            schoolClassDto.setBooksInClass(booksInClassDto);
            return new SchoolClass(schoolClassDto);
        }
        else // TODO
            throw new NoItemInDatabaseException("There is no class with id = " + classId + " in database!");
    }

    @Transactional
    public SchoolClass getByName(int course, char letter) {
        return new SchoolClass(schoolClassDao.getSchoolClassByName(course, letter));
    }

    @Transactional
    public SchoolClass getByTeacherId(int teacherId) {
        return new SchoolClass(schoolClassDao.getSchoolClassByTeacherId(teacherId));
    }
}
