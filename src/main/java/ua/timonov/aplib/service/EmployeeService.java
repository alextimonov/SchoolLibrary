package ua.timonov.aplib.service;

import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
    public List<Employee> getAll() {
        return employeeDao.getAll();
    }

    @Transactional
    public Employee getById(int id) {
        return employeeDao.getById(id);
    }

    public Employee getByName(String name) {
        return employeeDao.getByName(name);
    }

    public Employee getBySurname(String surname) {
        return employeeDao.getByName(surname);
    }
}
