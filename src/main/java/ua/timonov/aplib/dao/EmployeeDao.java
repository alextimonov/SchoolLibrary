package ua.timonov.aplib.dao;

import ua.timonov.aplib.model.Employee;

import java.util.List;

/**
 * DAO interface for Employee
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
