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

    BookInClassDto getByClassAndBook(SchoolClassDto schoolClassDto, SchoolbookDto schoolbookDto);

    BookInClassDto handoutSchoolbooks(SchoolClassDto schoolClassDto, SchoolbookDto schoolbookDto, int amountToHandout);

    BookInClassDto collectSchoolbooks(SchoolClassDto schoolClassDto, SchoolbookDto schoolbookDto, int amountToCollect);
}
