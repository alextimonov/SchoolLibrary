package ua.timonov.aplib.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

/**
 * Provides curator's data
 */
@Table
public class SchoolClass {

    @Id
    @GeneratedValue(generator = "increment", strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String name;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee teacher;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    @JoinTable(name = "book_to_class",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Schoolbook> schoolbooks;

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
        return schoolbooks;
    }

    public void setBookList(List<Schoolbook> schoolbooks) {
        this.schoolbooks = schoolbooks;
    }

    @Override
    public String toString() {
        return "SchoolClass{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", teacher=" + teacher +
                ", bookList=" + schoolbooks +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SchoolClass)) return false;

        SchoolClass that = (SchoolClass) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (teacher != null ? !teacher.equals(that.teacher) : that.teacher != null) return false;
        return schoolbooks != null ? schoolbooks.equals(that.schoolbooks) : that.schoolbooks == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (teacher != null ? teacher.hashCode() : 0);
        result = 31 * result + (schoolbooks != null ? schoolbooks.hashCode() : 0);
        return result;
    }
}
