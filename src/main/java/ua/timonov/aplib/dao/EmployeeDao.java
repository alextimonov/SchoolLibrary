package ua.timonov.aplib.dao;

import ua.timonov.aplib.model.Employee;

import java.util.List;

/**
 * Created by Alex on 23.12.2016.
 */
public interface EmployeeDao {
    void add(Employee employee);

    void update(Employee employee);

    void delete(int id);

    List<Employee> getAll();

    Employee getById(int id);

    Employee getByName(String name);

    Employee getBySurname(String surname);
}
