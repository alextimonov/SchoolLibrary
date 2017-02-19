package ua.timonov.aplib.model;

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
    private List<Schoolbook> schoolbooks;

    public SchoolClass() {
    }

    public SchoolClass(SchoolClassDb schoolClassDb) {
        this.id = schoolClassDb.getId();
        this.course = schoolClassDb.getCourse();
        this.letter = schoolClassDb.getLetter();
        this.teacher = new Employee(schoolClassDb.getTeacher());
        this.schoolbooks = new ArrayList<>();
        List<SchoolbookDb> schoolbooksDb = schoolClassDb.getBookList();
        for (SchoolbookDb schoolbookDb : schoolbooksDb) {
            this.schoolbooks.add(new Schoolbook(schoolbookDb));
        }
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

    public List<Schoolbook> getSchoolbooks() {
        return schoolbooks;
    }

    public void setSchoolbooks(List<Schoolbook> schoolbooks) {
        this.schoolbooks = schoolbooks;
    }

    @Override
    public String toString() {
        return "SchoolClass{" +
                "id=" + id +
                ", course=" + course +
                ", letter=" + letter +
                ", teacher=" + teacher +
                ", schoolbooks=" + schoolbooks +
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
