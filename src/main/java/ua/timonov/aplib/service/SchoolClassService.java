package ua.timonov.aplib.service;

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

    void add(SchoolClassDao schoolClass) {
        schoolClass.add(schoolClass);
    }

    void update(SchoolClassDao schoolClass) {
        schoolClass.update(schoolClass);
    }

    void delete(int id) {
        schoolClassDao.delete(id);
    }

    List<SchoolClassDao> getAll() {
        return schoolClassDao.getAll();
    }

    SchoolClass getById(int id) {
        return schoolClassDao.getById(id);
    }

    SchoolClass getByName(String name) {
        return schoolClassDao.getByName(name);
    }
}
