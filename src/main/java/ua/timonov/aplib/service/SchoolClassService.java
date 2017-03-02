package ua.timonov.aplib.service;

import org.springframework.transaction.annotation.Transactional;
import ua.timonov.aplib.dao.SchoolClassDao;
import ua.timonov.aplib.model.SchoolClass;

import java.util.List;

/**
 * Web service for SchoolClassDao
 */
public class SchoolClassService {
    private SchoolClassDao schoolClassDao;

    public void setSchoolClassDao(SchoolClassDao schoolClassDao) {
        this.schoolClassDao = schoolClassDao;
    }

    @Transactional
    public SchoolClass add(SchoolClass schoolClass) {
        return schoolClassDao.add(schoolClass);
    }

    @Transactional
    public SchoolClass update(int id, SchoolClass schoolClass) {
        schoolClass.setId(id);
        return schoolClassDao.update(schoolClass);
    }

    @Transactional
    public SchoolClass delete(int id) {
        return schoolClassDao.delete(id);
    }

    @Transactional
    public List<SchoolClass> getAll() {
        return schoolClassDao.getAll();
    }

    @Transactional
    public SchoolClass getById(int id) {
        return schoolClassDao.getById(id);
    }

    @Transactional
    public SchoolClass getByName(String name) {
        int course = (int) name.charAt(0);
        char letter = name.charAt(1);
        return schoolClassDao.getByName(course, letter);
    }
}
