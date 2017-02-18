package ua.timonov.aplib.service;

import org.springframework.transaction.annotation.Transactional;
import ua.timonov.aplib.dao.SchoolClassDao;
import ua.timonov.aplib.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Web service for SchoolClassDao
 */
public class SchoolClassService {
    private SchoolClassDao schoolClassDao;
    private EmployeeService employeeService;
    private SchoolbookService schoolbookService;

    public void setSchoolClassDao(SchoolClassDao schoolClassDao) {
        this.schoolClassDao = schoolClassDao;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void setSchoolbookService(SchoolbookService schoolbookService) {
        this.schoolbookService = schoolbookService;
    }

    @Transactional
    public SchoolClass add(SchoolClass schoolClass) {
        SchoolClassDb schoolClassDb = getSchoolClassDb(schoolClass);
        schoolClassDao.add(schoolClassDb);
        return schoolClass;
    }

    @Transactional
    private SchoolClassDb getSchoolClassDb(SchoolClass schoolClass) {
        SchoolClassDb schoolClassDb = new SchoolClassDb();
        schoolClassDb.setId(schoolClass.getId());
        schoolClassDb.setCourse(schoolClass.getCourse());
        schoolClassDb.setLetter(schoolClass.getLetter());

        Employee teacher = schoolClass.getTeacher();
        EmployeeDb teacherDb = employeeService.getEmployeeDb(teacher);
        schoolClassDb.setTeacher(teacherDb);

        List<Schoolbook> schoolbooks = schoolClass.getSchoolbooks();
        List<SchoolbookDb> schoolbookDbs = new ArrayList<>();
        for (Schoolbook schoolbook : schoolbooks) {
            schoolbookDbs.add(schoolbookService.getSchoolbookDb(schoolbook));
        }
        schoolClassDb.setBookList(schoolbookDbs);
        return schoolClassDb;
    }

    @Transactional
    public SchoolClass update(int id, SchoolClass schoolClass) {
        schoolClass.setId(id);
        SchoolClassDb schoolClassDb = getSchoolClassDb(schoolClass);
        return new SchoolClass(schoolClassDao.update(schoolClassDb));
    }

    @Transactional
    public SchoolClass delete(int id) {
        return new SchoolClass(schoolClassDao.delete(id));
    }

    @Transactional
    public List<SchoolClass> getAll() {
        List<SchoolClassDb> schoolClassDbs = schoolClassDao.getAll();
        List<SchoolClass> schoolClasses = new ArrayList<>();
        for (SchoolClassDb schoolClassDb : schoolClassDbs) {
            schoolClasses.add(new SchoolClass(schoolClassDb));
        }
        return schoolClasses;
    }

    @Transactional
    public SchoolClass getById(int id) {
        return new SchoolClass(schoolClassDao.getById(id));
    }

    @Transactional
    public SchoolClass getByName(String name) {
        int course = (int) name.charAt(0);
        char letter = name.charAt(1);
        return new SchoolClass(schoolClassDao.getByName(course, letter));
    }
}
