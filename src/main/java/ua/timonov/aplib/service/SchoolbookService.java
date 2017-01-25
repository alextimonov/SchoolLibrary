package ua.timonov.aplib.service;

import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
    public Schoolbook add(Schoolbook schoolbook) {
        schoolbookDao.add(schoolbook);
        return schoolbook;
    }

    @Transactional
    public Schoolbook update(int id, Schoolbook schoolbook) {
        schoolbook.setId(id);
        schoolbookDao.update(schoolbook);
        return schoolbook;
    }

    @Transactional
    public Schoolbook delete(int id) {
        return schoolbookDao.delete(id);
    }

    @Transactional
    public List<Schoolbook> getAll() {
        return schoolbookDao.getAll();
    }

    @Transactional
    public Schoolbook getById(int id) {
        return schoolbookDao.getById(id);
    }

    @Transactional
    public Schoolbook getByName(String name) {
        return schoolbookDao.getByName(name);
    }
}