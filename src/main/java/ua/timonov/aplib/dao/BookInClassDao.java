package ua.timonov.aplib.dao;

import ua.timonov.aplib.dto.BookInClassDto;
import ua.timonov.aplib.dto.SchoolClassDto;
import ua.timonov.aplib.dto.SchoolbookDto;

import java.util.List;

/**
 *
 */
public interface BookInClassDao {
    List<BookInClassDto> getAll();

    List<BookInClassDto> getByClass(SchoolClassDto schoolClassDto);

    List<BookInClassDto> getByBook(SchoolbookDto schoolbookDto);
}
