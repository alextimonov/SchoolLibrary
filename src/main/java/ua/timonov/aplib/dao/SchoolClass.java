package ua.timonov.aplib.dao;

import java.util.List;

/**
 * Created by Alex on 23.12.2016.
 */
public interface SchoolClass {
    void add(SchoolClass schoolClass);

    void update(SchoolClass schoolClass);

    void delete(int id);

    List<SchoolClass> getAll();

    SchoolClass getById(int id);

    SchoolClass getByName(String name);
}
