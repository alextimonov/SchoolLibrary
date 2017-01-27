package ua.timonov.aplib.dao;

import ua.timonov.aplib.model.SchoolClass;

import java.util.List;

/**
 * Created by Alex on 23.12.2016.
 */
public interface SchoolClassDao {
    SchoolClass add(SchoolClass schoolClass);

    SchoolClass update(SchoolClass schoolClass);

    SchoolClass delete(int id);

    List<SchoolClass> getAll();

    SchoolClass getById(int id);

    SchoolClass getByName(int course, char letter);
}
