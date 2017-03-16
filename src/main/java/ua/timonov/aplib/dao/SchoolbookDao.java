package ua.timonov.aplib.dao;

import ua.timonov.aplib.model.SchoolbookDb;

import java.util.List;

/**
 * DAO interface for Schoolbook
 */
public interface SchoolbookDao {
    SchoolbookDb add(SchoolbookDb schoolbook);

    SchoolbookDb update(SchoolbookDb schoolbook);

    SchoolbookDb delete(int id);

    List<SchoolbookDb> getAll();

    SchoolbookDb getById(int id);

    SchoolbookDb getByName(String name);
}
