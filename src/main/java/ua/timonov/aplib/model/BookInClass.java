package ua.timonov.aplib.model;

import ua.timonov.aplib.dto.BookInClassDto;

/**
 *
 */
public class BookInClass {
    private Schoolbook schoolbook;
    private int nBooksInClass;

    public BookInClass(BookInClassDto bookInClassDto) {
        this.schoolbook = new Schoolbook(bookInClassDto.getSchoolbook());
        this.nBooksInClass = bookInClassDto.getnBooksInClass();
    }

    public Schoolbook getSchoolbook() {
        return schoolbook;
    }

    public void setSchoolbook(Schoolbook schoolbook) {
        this.schoolbook = schoolbook;
    }

    public int getnBooksInClass() {
        return nBooksInClass;
    }

    public void setnBooksInClass(int nBooksInClass) {
        this.nBooksInClass = nBooksInClass;
    }

    @Override
    public String toString() {
        return "BookInClass{" +
                "schoolbook=" + schoolbook +
                ", nBooksInClass=" + nBooksInClass +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookInClass)) return false;

        BookInClass that = (BookInClass) o;

        if (nBooksInClass != that.nBooksInClass) return false;
        return schoolbook != null ? schoolbook.equals(that.schoolbook) : that.schoolbook == null;

    }

    @Override
    public int hashCode() {
        int result = schoolbook != null ? schoolbook.hashCode() : 0;
        result = 31 * result + nBooksInClass;
        return result;
    }
}
