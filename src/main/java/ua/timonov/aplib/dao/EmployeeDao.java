package ua.timonov.aplib.dao;

import ua.timonov.aplib.model.Employee;

import java.util.List;

/**
 * DAO interface for Employee
 */
public interface EmployeeDao {
    Employee add(Employee employee);

    Employee update(Employee employee);

    Employee delete(int id);

    List<Employee> getAll();

    Employee getById(int id);

    Employee getByName(String name);

    Employee getBySurname(String surname);
}
