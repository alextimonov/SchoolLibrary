package ua.timonov.aplib.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ListOfBooksInClass {
    private List<BookInClass> books = new ArrayList<>();

    public boolean add(BookInClass bookInClass) {
//        return books.add(bookInClass);
        return true;
    }

    public List<BookInClass> getBooks() {
//        return books;
        return null;
    }

    public void setBooks(List<BookInClass> books) {
//        this.books = books;
    }
}
