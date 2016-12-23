package ua.timonov.aplib.service;

import ua.timonov.aplib.dao.EmployeeDao;
import ua.timonov.aplib.model.Employee;

import java.util.List;

/**
 * Web service for EmployeeDao
 */
public class EmployeeService {
    private EmployeeDao employeeDao;

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    void add(Employee employee) {
        employeeDao.add(employee);
    }

    void update(Employee employee) {
        employeeDao.update(employee);
    }

    void delete(int id) {
        employeeDao.delete(id);
    }

    List<Employee> getAll() {
        return employeeDao.getAll();
    }

    Employee getById(int id) {
        return employeeDao.getById(id);
    }

    Employee getByName(String name) {
        return employeeDao.getByName(name);
    }

    Employee getBySurname(String surname) {
        return employeeDao.getByName(surname);
    }
}
