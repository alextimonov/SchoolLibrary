package ua.timonov.aplib.model;

import ua.timonov.aplib.dto.SchoolbookDto;

public class Schoolbook {
    private int id;
    private int course;
    private String name;
    private String author;
    private String publisher;
    private int amountTotal;
    private Employee librarian;

    public Schoolbook() {
    }

    public Schoolbook(int id) {
        this.id = id;
    }

    public Schoolbook(SchoolbookDto schoolbookDb) {
        this.id = schoolbookDb.getId();
        this.name = schoolbookDb.getName();
        this.course = schoolbookDb.getCourse();
        this.amountTotal = schoolbookDb.getAmountTotal();
        this.librarian = new Employee(schoolbookDb.getLibrarian());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(int amountTotal) {
        this.amountTotal = amountTotal;
    }

    public Employee getLibrarian() {
        return librarian;
    }

    public void setLibrarian(Employee librarian) {
        this.librarian = librarian;
    }

    @Override
    public String toString() {
        return "Schoolbook{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", course=" + course +
                ", amountTotal=" + amountTotal +
                ", librarian=" + librarian +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Schoolbook)) return false;

        Schoolbook that = (Schoolbook) o;

        if (course != that.course) return false;
        if (amountTotal != that.amountTotal) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return librarian != null ? librarian.equals(that.librarian) : that.librarian == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + course;
        result = 31 * result + amountTotal;
        result = 31 * result + (librarian != null ? librarian.hashCode() : 0);
        return result;
    }
}
