package ua.timonov.aplib.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * Provides curator's data
 */
@Table
public class SchoolClass {

    @Id
    @GeneratedValue(generator = "increment")  //, strategy = GenerationType.IDENTITY
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column
    private int id;

    @Column
    private String name;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee teacher;

    @Column
    @OneToMany
    @JoinColumn(name = "book_id")
    private List<Schoolbook> bookList;

    public SchoolClass() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getTeacher() {
        return teacher;
    }

    public void setTeacher(Employee teacher) {
        this.teacher = teacher;
    }

    public List<Schoolbook> getBookList() {
        return bookList;
    }

    public void setBookList(List<Schoolbook> bookList) {
        this.bookList = bookList;
    }

    @Override
    public String toString() {
        return "SchoolClass{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", teacher=" + teacher +
                ", bookList=" + bookList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SchoolClass)) return false;

        SchoolClass that = (SchoolClass) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (teacher != null ? !teacher.equals(that.teacher) : that.teacher != null) return false;
        return bookList != null ? bookList.equals(that.bookList) : that.bookList == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (teacher != null ? teacher.hashCode() : 0);
        result = 31 * result + (bookList != null ? bookList.hashCode() : 0);
        return result;
    }
}
