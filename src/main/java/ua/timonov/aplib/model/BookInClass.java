package ua.timonov.aplib.model;

import ua.timonov.aplib.dto.BookInClassDto;

/**
 *
 */
public class BookInClass {
    private int id;
    private Schoolbook schoolbook;
    private int schoolClassId;
    private int schoolClassCourse;
    private char schoolClassLetter;
    private String schoolClassTeacherName;
    private String schoolClassTeacherSurname;
    private int booksNumber;

    public BookInClass() {
    }

    public BookInClass(BookInClassDto bookInClassDto) {
        this.id = bookInClassDto.getId();
        this.schoolbook = new Schoolbook(bookInClassDto.getSchoolbook());
        this.schoolClassId = bookInClassDto.getSchoolClass().getId();
        this.schoolClassCourse = bookInClassDto.getSchoolClass().getCourse();
        this.schoolClassLetter = bookInClassDto.getSchoolClass().getLetter();
        this.schoolClassTeacherName = bookInClassDto.getSchoolClass().getTeacher().getName();
        this.schoolClassTeacherSurname = bookInClassDto.getSchoolClass().getTeacher().getSurname();
        this.booksNumber = bookInClassDto.getBooksNumber();
    }

    public BookInClass(Schoolbook schoolbook, SchoolClass schoolClass) {
        this.schoolbook = schoolbook;
        this.schoolClassId = schoolClass.getId();
        this.schoolClassCourse = schoolClass.getCourse();
        this.schoolClassLetter = schoolClass.getLetter();
        this.schoolClassTeacherName = schoolClass.getTeacher().getName();
        this.schoolClassTeacherSurname = schoolClass.getTeacher().getSurname();
    }

    public BookInClass(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Schoolbook getSchoolbook() {
        return schoolbook;
    }

    public void setSchoolbook(Schoolbook schoolbook) {
        this.schoolbook = schoolbook;
    }

    public int getBooksNumber() {
        return booksNumber;
    }

    public void setBooksNumber(int booksNumber) {
        this.booksNumber = booksNumber;
    }

    public int getSchoolClassId() {
        return schoolClassId;
    }

    public void setSchoolClassId(int schoolClassId) {
        this.schoolClassId = schoolClassId;
    }

    public int getSchoolClassCourse() {
        return schoolClassCourse;
    }

    public void setSchoolClassCourse(int schoolClassCourse) {
        this.schoolClassCourse = schoolClassCourse;
    }

    public char getSchoolClassLetter() {
        return schoolClassLetter;
    }

    public void setSchoolClassLetter(char schoolClassLetter) {
        this.schoolClassLetter = schoolClassLetter;
    }

    public String getSchoolClassTeacherName() {
        return schoolClassTeacherName;
    }

    public void setSchoolClassTeacherName(String schoolClassTeacherName) {
        this.schoolClassTeacherName = schoolClassTeacherName;
    }

    public String getSchoolClassTeacherSurname() {
        return schoolClassTeacherSurname;
    }

    public void setSchoolClassTeacherSurname(String schoolClassTeacherSurname) {
        this.schoolClassTeacherSurname = schoolClassTeacherSurname;
    }

    @Override
    public String toString() {
        return "BookInClass{" +
                "schoolClassId=" + schoolClassId +
                ", schoolClassCourse=" + schoolClassCourse +
                ", schoolClassLetter=" + schoolClassLetter +
                ", schoolClassTeacherName='" + schoolClassTeacherName + '\'' +
                ", schoolClassTeacherSurname='" + schoolClassTeacherSurname + '\'' +
                ", booksNumber=" + booksNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookInClass)) return false;

        BookInClass that = (BookInClass) o;

        if (schoolClassId != that.schoolClassId) return false;
        if (schoolClassCourse != that.schoolClassCourse) return false;
        if (schoolClassLetter != that.schoolClassLetter) return false;
        if (booksNumber != that.booksNumber) return false;
        if (schoolClassTeacherName != null ? !schoolClassTeacherName.equals(that.schoolClassTeacherName) : that.schoolClassTeacherName != null)
            return false;
        return schoolClassTeacherSurname != null ? schoolClassTeacherSurname.equals(that.schoolClassTeacherSurname) : that.schoolClassTeacherSurname == null;

    }

    @Override
    public int hashCode() {
        int result = schoolClassId;
        result = 31 * result + schoolClassCourse;
        result = 31 * result + (int) schoolClassLetter;
        result = 31 * result + (schoolClassTeacherName != null ? schoolClassTeacherName.hashCode() : 0);
        result = 31 * result + (schoolClassTeacherSurname != null ? schoolClassTeacherSurname.hashCode() : 0);
        result = 31 * result + booksNumber;
        return result;
    }
}
