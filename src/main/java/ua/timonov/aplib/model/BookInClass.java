package ua.timonov.aplib.model;

import ua.timonov.aplib.dto.BookInClassDto;

/**
 *
 */
public class BookInClass {
    private Schoolbook schoolbook;
    private int schoolClassId;
    private int schoolClassCourse;
    private char schoolClassLetter;
    private String schoolClassTeacherName;
    private String schoolClassTeacherSurname;
    private int nBooksInClass;

    public BookInClass(BookInClassDto bookInClassDto) {
        this.schoolbook = new Schoolbook(bookInClassDto.getSchoolbook());
        this.schoolClassId = bookInClassDto.getSchoolClass().getId();
        this.schoolClassCourse = bookInClassDto.getSchoolClass().getCourse();
        this.schoolClassLetter = bookInClassDto.getSchoolClass().getLetter();
        this.schoolClassTeacherName = bookInClassDto.getSchoolClass().getTeacher().getName();
        this.schoolClassTeacherSurname = bookInClassDto.getSchoolClass().getTeacher().getSurname();
        this.nBooksInClass = bookInClassDto.getnBooksInClass();
    }

    public Schoolbook getSchoolbook() {
        return schoolbook;
    }

    public void setSchoolbook(Schoolbook schoolbook) {
        this.schoolbook = schoolbook;
    }

    public int getnBooksInClass() {
        return nBooksInClass;
    }

    public void setnBooksInClass(int nBooksInClass) {
        this.nBooksInClass = nBooksInClass;
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
}
