package ua.timonov.aplib.model;

import ua.timonov.aplib.dto.BookInClassDto;
import ua.timonov.aplib.dto.SchoolClassDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides school class's data
 */
public class SchoolClass {
    private int id;
    private int course;
    private char letter;
    private Employee teacher;
    private int pupilsNumber;
    private List<BookInClass> booksInClass = new ArrayList<>();

    public SchoolClass() {
    }

    public SchoolClass(int id) {
        this.id = id;
    }

    public SchoolClass(SchoolClassDto schoolClassDto) {
        if (schoolClassDto != null) {
            this.id = schoolClassDto.getId();
            this.course = schoolClassDto.getCourse();
            this.letter = schoolClassDto.getLetter();
            this.teacher = new Employee(schoolClassDto.getTeacher());
            List<BookInClassDto> booksInClassDto = schoolClassDto.getBooksInClass();
            for (BookInClassDto bookInClassDto : booksInClassDto) {
                this.booksInClass.add(new BookInClass(bookInClassDto));
            }
        }
    }

    public List<BookInClass> getBooksInClass() {
        return booksInClass;
    }

    public void setBooksInClass(List<BookInClass> booksInClass) {
        this.booksInClass = booksInClass;
    }

    public int getPupilsNumber() {
        return pupilsNumber;
    }

    public void setPupilsNumber(int pupilsNumber) {
        this.pupilsNumber = pupilsNumber;
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

    public Employee getTeacher() {
        return teacher;
    }

    public void setTeacher(Employee teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "SchoolClass{" +
                "id=" + id +
                ", course=" + course +
                ", letter=" + letter +
                ", teacher=" + teacher +
                ", pupilsNumber=" + pupilsNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SchoolClass)) return false;

        SchoolClass that = (SchoolClass) o;

        if (course != that.course) return false;
        if (letter != that.letter) return false;
        if (pupilsNumber != that.pupilsNumber) return false;
        if (teacher != null ? !teacher.equals(that.teacher) : that.teacher != null) return false;
        return booksInClass != null ? booksInClass.equals(that.booksInClass) : that.booksInClass == null;

    }

    @Override
    public int hashCode() {
        int result = course;
        result = 31 * result + (int) letter;
        result = 31 * result + (teacher != null ? teacher.hashCode() : 0);
        result = 31 * result + pupilsNumber;
        result = 31 * result + (booksInClass != null ? booksInClass.hashCode() : 0);
        return result;
    }
}
