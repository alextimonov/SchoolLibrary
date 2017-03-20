package ua.timonov.aplib.model;

import ua.timonov.aplib.dto.BookInClassDto;
import ua.timonov.aplib.dto.SchoolClassDto;

import java.util.List;

/**
 * Provides school class's data
 */
public class SchoolClass {
    private int id;
    private int course;
    private char letter;
    private Employee teacher;
//    private int pupilsNumber;
    private ListOfBooksInClass booksList;

    public SchoolClass() {
    }

    public SchoolClass(SchoolClassDto schoolClassDto) {
        // TODO
        if (schoolClassDto != null) {
            this.id = schoolClassDto.getId();
            this.course = schoolClassDto.getCourse();
            this.letter = schoolClassDto.getLetter();
            this.teacher = new Employee(schoolClassDto.getTeacher());
            List<BookInClassDto> booksInClassDto = schoolClassDto.getBooksInClass();
//            for (BookInClassDto bookInClassDto : booksInClassDto) {
//                this.booksList.add(new BookInClass(bookInClassDto));
//            }
        }
    }

//    public int getPupilsNumber() {
//        return pupilsNumber;
//    }
//
//    public void setPupilsNumber(int pupilsNumber) {
//        this.pupilsNumber = pupilsNumber;
//    }

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

    public ListOfBooksInClass getBooksList() {
        return booksList;
    }

    public void setBooksList(ListOfBooksInClass booksList) {
        this.booksList = booksList;
    }

    @Override
    public String toString() {
        return "SchoolClass{" +
                "id=" + id +
                ", course=" + course +
                ", letter=" + letter +
                ", teacher=" + teacher +
//                ", booksList=" + booksList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SchoolClass)) return false;

        SchoolClass that = (SchoolClass) o;

        if (course != that.course) return false;
        if (letter != that.letter) return false;
        return teacher != null ? !teacher.equals(that.teacher) : that.teacher != null; //) return false;
//        return booksList != null ? booksList.equals(that.booksList) : that.booksList == null;

    }

    @Override
    public int hashCode() {
        int result = course;
        result = 31 * result + (int) letter;
        result = 31 * result + (teacher != null ? teacher.hashCode() : 0);
//        result = 31 * result + (booksList != null ? booksList.hashCode() : 0);
        return result;
    }
}
