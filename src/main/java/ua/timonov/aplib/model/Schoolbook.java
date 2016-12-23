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
    private int amount;

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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Schoolbook{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", course=" + course +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Schoolbook)) return false;

        Schoolbook that = (Schoolbook) o;

        if (course != that.course) return false;
        if (amount != that.amount) return false;
        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + course;
        result = 31 * result + amount;
        return result;
    }
}
