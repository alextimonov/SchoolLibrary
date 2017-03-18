package ua.timonov.aplib.dto;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Provides curator's data
 */
@XmlRootElement
@Entity
@Table(name = "class")
public class SchoolClassDto {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column
    private int id;

    @Column
    private int course;

    @Column
    private char letter;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private EmployeeDto teacher;
    private List<BookInClassDto> booksInClass;

    public SchoolClassDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public EmployeeDto getTeacher() {
        return teacher;
    }

    public void setTeacher(EmployeeDto teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "SchoolClass{" +
                ", teacher=" + teacher +
                ", letter=" + letter +
                ", course=" + course +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SchoolClassDto)) return false;

        SchoolClassDto that = (SchoolClassDto) o;

        if (course != that.course) return false;
        if (letter != that.letter) return false;
        return teacher != null ? teacher.equals(that.teacher) : that.teacher == null;

    }

    @Override
    public int hashCode() {
        int result = course;
        result = 31 * result + (int) letter;
        result = 31 * result + (teacher != null ? teacher.hashCode() : 0);
        return result;
    }

    public void setBooksInClass(List<BookInClassDto> booksInClass) {
        this.booksInClass = booksInClass;
    }

    public List<BookInClassDto> getBooksInClass() {
        return booksInClass;
    }
}
