package ua.timonov.aplib.model;

import ua.timonov.aplib.dto.EmployeeDto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Provides employee's data for web resources
 */
@XmlRootElement
public class Employee {
    private int id;
    private String name;
    private String surname;
    private String position;

    public Employee() {
    }

    public Employee(EmployeeDto employeeDb) {
        this.id = employeeDb.getId();
        this.name = employeeDb.getName();
        this.surname = employeeDb.getSurname();
        this.position = employeeDb.getJob().getPosition().toString().toLowerCase();
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", position=" + position +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;

        Employee employee = (Employee) o;

        if (name != null ? !name.equals(employee.name) : employee.name != null) return false;
        if (surname != null ? !surname.equals(employee.surname) : employee.surname != null) return false;
        return position == employee.position;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        return result;
    }
}
