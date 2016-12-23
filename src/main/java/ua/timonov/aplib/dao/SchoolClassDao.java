package ua.timonov.aplib.dao;

import ua.timonov.aplib.model.SchoolClass;

import java.util.List;

/**
 * Created by Alex on 23.12.2016.
 */
public interface SchoolClassDao {
    void add(SchoolClassDao schoolClass);

    void update(SchoolClassDao schoolClass);

    void delete(int id);

    List<SchoolClassDao> getAll();

    SchoolClass getById(int id);

    SchoolClass getByName(String name);
}
