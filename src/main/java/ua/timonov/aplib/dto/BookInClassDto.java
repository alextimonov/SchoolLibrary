package ua.timonov.aplib.dto;

import javax.persistence.*;

/**
 * Provides book for some class and it's data for database
 */
@Entity
@Table(name = "book_to_class")
public class BookInClassDto {
    @ManyToOne
    @JoinColumn(name = "class_id")
    private SchoolClassDto schoolClass;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private SchoolbookDto schoolbook;

    @Column(name = "number_books")
    private int nBooksInClass;

    public BookInClassDto() {
    }

    public SchoolClassDto getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClassDto schoolClass) {
        this.schoolClass = schoolClass;
    }

    public SchoolbookDto getSchoolbook() {
        return schoolbook;
    }

    public void setSchoolbook(SchoolbookDto schoolbook) {
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
        return "BookInClassDto{" +
                "schoolClass=" + schoolClass +
                ", schoolbook=" + schoolbook +
                ", nBooksInClass=" + nBooksInClass +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookInClassDto)) return false;

        BookInClassDto that = (BookInClassDto) o;

        if (nBooksInClass != that.nBooksInClass) return false;
        if (schoolClass != null ? !schoolClass.equals(that.schoolClass) : that.schoolClass != null) return false;
        return schoolbook != null ? schoolbook.equals(that.schoolbook) : that.schoolbook == null;

    }

    @Override
    public int hashCode() {
        int result = schoolClass != null ? schoolClass.hashCode() : 0;
        result = 31 * result + (schoolbook != null ? schoolbook.hashCode() : 0);
        result = 31 * result + nBooksInClass;
        return result;
    }
}
