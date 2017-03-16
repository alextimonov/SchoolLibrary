package ua.timonov.aplib.service;

import org.springframework.transaction.annotation.Transactional;
import ua.timonov.aplib.dao.EmployeeDao;
import ua.timonov.aplib.dao.JobDao;
import ua.timonov.aplib.model.Employee;
import ua.timonov.aplib.model.EmployeeDb;
import ua.timonov.aplib.model.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Web service for EmployeeDao
 */
public class EmployeeService {
    private EmployeeDao employeeDao;
    private JobDao jobDao;

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public void setJobDao(JobDao jobDao) {
        this.jobDao = jobDao;
    }

    @Transactional
    public Employee add(Employee employee) {
        EmployeeDb employeeDb = getEmployeeDb(employee);
        return new Employee(employeeDao.add(employeeDb));
    }

    @Transactional
    public Employee update(int id, Employee employee) {
        employee.setId(id);
        EmployeeDb employeeDb = getEmployeeDb(employee);
        return new Employee(employeeDao.update(employeeDb));
    }

    @Transactional
    public EmployeeDb getEmployeeDb(Employee employee) {
        EmployeeDb employeeDb = new EmployeeDb();
        employeeDb.setId(employee.getId());
        employeeDb.setName(employee.getName());
        employeeDb.setSurname(employee.getSurname());
        employeeDb.setJob(jobDao.getJobByPosition(employee.getPosition()));
        return employeeDb;
    }

    @Transactional
    public Employee delete(int id) {
        return new Employee(employeeDao.delete(id));
    }

    @Transactional
    public List<Employee> getAll() {
        List<Employee> employees = new ArrayList<>();
        for (EmployeeDb employeeDb : employeeDao.getAll()) {
            employees.add(new Employee(employeeDb));
        }
        return employees;
    }

    @Transactional
    public List<Employee> getLibrarians() {
        List<Employee> librarians = getAll();
        // TODO
        return librarians;
    }

    @Transactional
    public Employee getById(int id) {
        return new Employee(employeeDao.getById(id));
    }

    @Transactional
    public Employee getBySurname(String surname) {
        return new Employee(employeeDao.getBySurname(surname));
    }

    @Transactional
    public List<String> getAllPositions() {
        return jobDao.getAllPositions();
    }
}
