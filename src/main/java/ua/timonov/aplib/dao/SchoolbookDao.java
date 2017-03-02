package ua.timonov.aplib.dao;

import ua.timonov.aplib.model.Schoolbook;

import java.util.List;

/**
 * DAO interface for Schoolbook
 */
public interface SchoolbookDao {
    void add(Schoolbook schoolbook);

    void update(Schoolbook schoolbook);

    Schoolbook delete(int id);

    List<Schoolbook> getAll();

    Schoolbook getById(int id);

    Schoolbook getByName(String name);
}
