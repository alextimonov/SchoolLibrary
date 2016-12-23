package ua.timonov.aplib.model;

import javax.persistence.*;

/**
 * Provides schoolbook's data
 */
@Entity
@Table(name = "schoolbook")
public class Schoolbook {

    /* unique id in DB table */
    @Id
    @GeneratedValue(generator = "increment", strategy = GenerationType.IDENTITY)
//    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "book_id")
    private int id;

    @Column
    private String name;

    @Column
    private int course;

    @Column
    private int amountTotal;

    @Column
    private int amountRest;


    public Schoolbook() {
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

    public int getAmountRest() {
        return amountRest;
    }

    public void setAmountRest(int amountRest) {
        this.amountRest = amountRest;
    }

    @Override
    public String toString() {
        return "Schoolbook{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", course=" + course +
                ", amountTotal=" + amountTotal +
                ", amountRest=" + amountRest +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Schoolbook)) return false;

        Schoolbook that = (Schoolbook) o;

        if (course != that.course) return false;
        if (amountTotal != that.amountTotal) return false;
        if (amountRest != that.amountRest) return false;
        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + course;
        result = 31 * result + amountTotal;
        result = 31 * result + amountRest;
        return result;
    }
}
