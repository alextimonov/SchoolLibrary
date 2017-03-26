package ua.timonov.aplib.dao;

import ua.timonov.aplib.dto.SchoolClassDto;

import java.util.List;

/**
 * Created by Alex on 23.12.2016.
 */
public interface SchoolClassDao {
    SchoolClassDto add(SchoolClassDto schoolClass);

    SchoolClassDto update(SchoolClassDto schoolClass);

    SchoolClassDto delete(int id);

    List<SchoolClassDto> getAll();

    SchoolClassDto getSchoolClassById(int id);

    SchoolClassDto getSchoolClassByName(int course, char letter);

    SchoolClassDto getSchoolClassByTeacherId(int teacherId);
}
