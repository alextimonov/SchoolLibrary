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

    @Transactional
    public Employee add(Employee employee) {
        employeeDao.add(employee);
        return employee;
    }

    @Transactional
    public Employee update(int id, Employee employee) {
        employee.setId(id);
        employeeDao.update(employee);
        return employee;
    }

    @Transactional
    public Employee delete(int id) {
        return employeeDao.delete(id);
    }

    @Transactional
    public List<Employee> getAll() {
        return employeeDao.getAll();
    }

    @Transactional
    public Employee getById(int id) {
        return employeeDao.getById(id);
    }

    @Transactional
    public Employee getBySurname(String surname) {
        return employeeDao.getBySurname(surname);
    }
}
