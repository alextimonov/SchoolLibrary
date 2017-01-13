package ua.timonov.aplib.dao.dbStub;

import ua.timonov.aplib.dao.EmployeeDao;
import ua.timonov.aplib.model.Employee;

import javax.annotation.PostConstruct;
import java.util.*;

public class DbStubEmployeeDao implements EmployeeDao {
    private Map<Long, Employee> employees;


    public DbStubEmployeeDao() {
        initialize();
    }

    @PostConstruct
    public void initialize() {
        employees.put(1L, new Employee(1L, "John", "Dou"));
        employees.put(2L, new Employee(2L, "Max", "Still"));
        employees.put(3L, new Employee(3L, "Edward", "Rainbow"));
    }

    public void setEmployees(Map<Long, Employee> employees) {
        this.employees = employees;
    }

    @Override
    public Employee add(Employee employee) {
        employee.setId(employees.size() + 1);
        employees.put(employee.getId(), employee);
        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        if (employee.getId() <= 0) {
            return null;
        }
        employees.put(employee.getId(), employee);
        return employee;
    }

    @Override
    public Employee delete(int id) {
        return employees.remove(id);
    }

    @Override
    public List<Employee> getAll() {
        initialize();
        return new ArrayList<>(employees.values());
    }

    @Override
    public Employee getById(int id) {
        return employees.get(id);
    }

    @Override
    public Employee getByName(String name) {
        for (Employee employee : employees.values()) {
            if (name.equals(employee.getName()))
                return employee;
        }
        return null;
    }

    @Override
    public Employee getBySurname(String surname) {
        for (Employee employee : employees.values()) {
            if (surname.equals(employee.getSurname()))
                return employee;
        }
        return null;
    }
}
