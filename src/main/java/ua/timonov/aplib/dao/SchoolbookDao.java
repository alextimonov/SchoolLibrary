package ua.timonov.aplib.dao;

import ua.timonov.aplib.dto.SchoolbookDto;

import java.util.List;

/**
 * DAO interface for Schoolbook
 */
public interface SchoolbookDao {
    SchoolbookDto add(SchoolbookDto schoolbook);

    SchoolbookDto update(SchoolbookDto schoolbook);

    SchoolbookDto delete(int id);

    List<SchoolbookDto> getAll();

    SchoolbookDto getById(int id);

    SchoolbookDto getByName(String name);

    List<SchoolbookDto> getSchoolbooksByEmployeeId(int librarianId);

    List<SchoolbookDto> getSchoolbooksByCourse(int course);
}
