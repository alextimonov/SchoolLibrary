package ua.timonov.aplib.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

/**
 * Provides curator's data
 */
@Entity
@Table(name = "class")
public class SchoolClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private int course;

    @Column
    private char letter;

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

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
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
                "schoolbooks=" + schoolbooks +
                ", teacher=" + teacher +
                ", letter=" + letter +
                ", course=" + course +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SchoolClass)) return false;

        SchoolClass that = (SchoolClass) o;

        if (course != that.course) return false;
        if (letter != that.letter) return false;
        if (teacher != null ? !teacher.equals(that.teacher) : that.teacher != null) return false;
        return schoolbooks != null ? schoolbooks.equals(that.schoolbooks) : that.schoolbooks == null;

    }

    @Override
    public int hashCode() {
        int result = course;
        result = 31 * result + (int) letter;
        result = 31 * result + (teacher != null ? teacher.hashCode() : 0);
        result = 31 * result + (schoolbooks != null ? schoolbooks.hashCode() : 0);
        return result;
    }
}
