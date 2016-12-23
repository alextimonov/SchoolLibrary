package ua.timonov.aplib.service;

import ua.timonov.aplib.dao.SchoolbookDao;
import ua.timonov.aplib.model.Schoolbook;

import java.util.List;

/**
 * Web service for SchoolbookDao
 */
public class SchoolbookService {
    private SchoolbookDao schoolbookDao;

    public void setSchoolbookDao(SchoolbookDao schoolbookDao) {
        this.schoolbookDao = schoolbookDao;
    }

    void add(Schoolbook schoolbook) {
        schoolbookDao.add(schoolbook);
    }

    void update(Schoolbook schoolbook) {
        schoolbookDao.update(schoolbook);
    }

    void delete(int id) {
        schoolbookDao.delete(id);
    }

    List<Schoolbook> getAll() {
        return schoolbookDao.getAll();
    }

    Schoolbook getById(int id) {
        return schoolbookDao.getById(id);
    }

    Schoolbook getByName(String name) {
        return schoolbookDao.getByName(name);
    }
}
