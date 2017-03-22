package ua.timonov.aplib.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 21.03.2017.
 */
public class ListEmployees {
    private List<Employee> employees = new ArrayList<>();

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
