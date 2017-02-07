package ua.timonov.aplib.model;

import javax.persistence.Column;
import java.util.List;

/**
 * Created by Alex on 22.12.2016.
 */
@Deprecated
public class Library {

    private EmployeeDb librarian;

    @Column
    private List<Schoolbook> bookList;

    public Library() {
    }

    public EmployeeDb getLibrarian() {
        return librarian;
    }

    public void setLibrarian(EmployeeDb librarian) {
        this.librarian = librarian;
    }

    public List<Schoolbook> getBookList() {
        return bookList;
    }

    public void setBookList(List<Schoolbook> bookList) {
        this.bookList = bookList;
    }

    @Override
    public String toString() {
        return "Library{" +
                "librarian=" + librarian +
                ", bookList=" + bookList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Library)) return false;
        if (!super.equals(o)) return false;

        Library library = (Library) o;

        if (librarian != null ? !librarian.equals(library.librarian) : library.librarian != null) return false;
        return bookList != null ? bookList.equals(library.bookList) : library.bookList == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (librarian != null ? librarian.hashCode() : 0);
        result = 31 * result + (bookList != null ? bookList.hashCode() : 0);
        return result;
    }
}
