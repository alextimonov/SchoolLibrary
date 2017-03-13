package ua.timonov.aplib.dao;

import ua.timonov.aplib.model.EmployeeDb;

import java.util.List;

/**
 * DAO interface for Employee
 */
public interface EmployeeDao {
    EmployeeDb add(EmployeeDb employee);

    EmployeeDb update(EmployeeDb employee);

    EmployeeDb delete(int id);

    List<EmployeeDb> getAll();

    EmployeeDb getById(int id);

    EmployeeDb getBySurname(String surname);
}
