package ua.timonov.aplib.dao;

import ua.timonov.aplib.model.SchoolClassDb;

import java.util.List;

/**
 * Created by Alex on 23.12.2016.
 */
public interface SchoolClassDao {
    SchoolClassDb add(SchoolClassDb schoolClass);

    SchoolClassDb update(SchoolClassDb schoolClass);

    SchoolClassDb delete(int id);

    List<SchoolClassDb> getAll();

    SchoolClassDb getById(int id);

    SchoolClassDb getByName(int course, char letter);
}
