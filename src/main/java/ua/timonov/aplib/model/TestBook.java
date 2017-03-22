package ua.timonov.aplib.model;

/**
 * Created by Alex on 22.03.2017.
 */
public class TestBook {
    private Schoolbook schoolbook;
    private int schoolClassId;
    private int schoolClassCourse;
    private char schoolClassLetter;
    private String schoolClassTeacherName;
    private String schoolClassTeacherSurname;
    private int booksNumber;

    public Schoolbook getSchoolbook() {
        return schoolbook;
    }

    public void setSchoolbook(Schoolbook schoolbook) {
        this.schoolbook = schoolbook;
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

    public int getBooksNumber() {
        return booksNumber;
    }

    public void setBooksNumber(int booksNumber) {
        this.booksNumber = booksNumber;
    }

    @Override
    public String toString() {
        return "TestBook{" +
                "schoolbook=" + schoolbook +
                ", schoolClassId=" + schoolClassId +
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
        if (!(o instanceof TestBook)) return false;

        TestBook testBook = (TestBook) o;

        if (schoolClassId != testBook.schoolClassId) return false;
        if (schoolClassCourse != testBook.schoolClassCourse) return false;
        if (schoolClassLetter != testBook.schoolClassLetter) return false;
        if (booksNumber != testBook.booksNumber) return false;
        if (schoolbook != null ? !schoolbook.equals(testBook.schoolbook) : testBook.schoolbook != null) return false;
        if (schoolClassTeacherName != null ? !schoolClassTeacherName.equals(testBook.schoolClassTeacherName) : testBook.schoolClassTeacherName != null)
            return false;
        return schoolClassTeacherSurname != null ? schoolClassTeacherSurname.equals(testBook.schoolClassTeacherSurname) : testBook.schoolClassTeacherSurname == null;

    }

    @Override
    public int hashCode() {
        int result = schoolbook != null ? schoolbook.hashCode() : 0;
        result = 31 * result + schoolClassId;
        result = 31 * result + schoolClassCourse;
        result = 31 * result + (int) schoolClassLetter;
        result = 31 * result + (schoolClassTeacherName != null ? schoolClassTeacherName.hashCode() : 0);
        result = 31 * result + (schoolClassTeacherSurname != null ? schoolClassTeacherSurname.hashCode() : 0);
        result = 31 * result + booksNumber;
        return result;
    }
}
