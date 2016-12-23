package ua.timonov.aplib.model;

import javax.persistence.*;
import java.util.List;

/**
 * Provides curator's data
 */
@Table
public class Curator extends Employee {

    @Column
    private String schoolClass;

    @Column
    @OneToMany
    @JoinColumn(name = "book_id")
    private List<Schoolbook> bookList;

    public Curator() {
    }

    public String getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(String schoolClass) {
        this.schoolClass = schoolClass;
    }

    public List<Schoolbook> getBookList() {
        return bookList;
    }

    public void setBookList(List<Schoolbook> bookList) {
        this.bookList = bookList;
    }

    @Override
    public String toString() {
        return "Curator{" +
                super.toString() +
                "schoolClass='" + schoolClass + '\'' +
                ", bookList=" + bookList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Curator)) return false;
        if (!super.equals(o)) return false;

        Curator curator = (Curator) o;

        if (schoolClass != null ? !schoolClass.equals(curator.schoolClass) : curator.schoolClass != null) return false;
        return bookList != null ? bookList.equals(curator.bookList) : curator.bookList == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (schoolClass != null ? schoolClass.hashCode() : 0);
        result = 31 * result + (bookList != null ? bookList.hashCode() : 0);
        return result;
    }
}
