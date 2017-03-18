package ua.timonov.aplib.dto;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Provides job positions in database
 */
@Entity
@Table(name = "jobs")
public class JobDto {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "position")
    private Position position;

    public JobDto() {
    }

    public JobDto(Position position) {
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return position.name();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JobDto)) return false;

        JobDto job = (JobDto) o;

        return position == job.position;

    }

    @Override
    public int hashCode() {
        return position != null ? position.hashCode() : 0;
    }
}