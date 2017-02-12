package ua.timonov.aplib.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Provides schoolbook's data
 */
@XmlRootElement
@Entity
@Table(name = "book")
public class SchoolbookDb {

    /* unique id in DB table */
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column
    private int id;

    @Column
    private String name;

    @Column
    private int course;

    @Column
    private int amountTotal;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployeeDb librarian;

    public SchoolbookDb() {
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

    public int getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(int amountTotal) {
        this.amountTotal = amountTotal;
    }

    public EmployeeDb getLibrarian() {
        return librarian;
    }

    public void setLibrarian(EmployeeDb librarian) {
        this.librarian = librarian;
    }

    @Override
    public String toString() {
        return "Schoolbook{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", course=" + course +
                ", amountTotal=" + amountTotal +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SchoolbookDb)) return false;

        SchoolbookDb that = (SchoolbookDb) o;

        if (course != that.course) return false;
        if (amountTotal != that.amountTotal) return false;
        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + course;
        result = 31 * result + amountTotal;
        return result;
    }
}
