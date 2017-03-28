package ua.timonov.aplib.dto;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Provides book for some class and it's data for database
 */
@Entity
@Table(name = "book_to_class")
public class BookInClassDto {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "class_id")
    private SchoolClassDto schoolClass;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private SchoolbookDto schoolbook;

    @Column(name = "number_books")
    private int booksNumber;

    public BookInClassDto() {
    }

    public BookInClassDto(SchoolClassDto schoolClass, SchoolbookDto schoolbook, int booksNumber) {
        this.schoolClass = schoolClass;
        this.schoolbook = schoolbook;
        this.booksNumber = booksNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getBooksNumber() {
        return booksNumber;
    }

    public void setBooksNumber(int booksNumber) {
        this.booksNumber = booksNumber;
    }

    @Override
    public String toString() {
        return "BookInClassDto{" +
                "id=" + id +
                ", schoolClass=" + schoolClass +
                ", schoolbook=" + schoolbook +
                ", booksNumber=" + booksNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookInClassDto)) return false;

        BookInClassDto that = (BookInClassDto) o;

        if (booksNumber != that.booksNumber) return false;
        if (schoolClass != null ? !schoolClass.equals(that.schoolClass) : that.schoolClass != null) return false;
        return schoolbook != null ? schoolbook.equals(that.schoolbook) : that.schoolbook == null;

    }

    @Override
    public int hashCode() {
        int result = schoolClass != null ? schoolClass.hashCode() : 0;
        result = 31 * result + (schoolbook != null ? schoolbook.hashCode() : 0);
        result = 31 * result + booksNumber;
        return result;
    }
}
