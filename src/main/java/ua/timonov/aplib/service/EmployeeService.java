package ua.timonov.aplib.service;

import org.springframework.transaction.annotation.Transactional;
import ua.timonov.aplib.dao.EmployeeDao;
import ua.timonov.aplib.dao.JobDao;
import ua.timonov.aplib.dao.SchoolClassDao;
import ua.timonov.aplib.dto.EmployeeDto;
import ua.timonov.aplib.dto.JobDto;
import ua.timonov.aplib.dto.Position;
import ua.timonov.aplib.dto.SchoolClassDto;
import ua.timonov.aplib.model.Employee;
import ua.timonov.aplib.model.SchoolClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Web service for EmployeeDao
 */
public class EmployeeService {
    private EmployeeDao employeeDao;
    private SchoolClassDao schoolClassDao;
    private JobDao jobDao;

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public void setJobDao(JobDao jobDao) {
        this.jobDao = jobDao;
    }

    public void setSchoolClassDao(SchoolClassDao schoolClassDao) {
        this.schoolClassDao = schoolClassDao;
    }

    @Transactional
    public Employee add(Employee employee) {
        EmployeeDto employeeDb = getEmployeeDto(employee);
        return new Employee(employeeDao.add(employeeDb));
    }

    @Transactional
    public Employee update(int id, Employee employee) {
        employee.setId(id);
        EmployeeDto employeeDb = getEmployeeDto(employee);
        return new Employee(employeeDao.update(employeeDb));
    }

    @Transactional
    public EmployeeDto getEmployeeDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setName(employee.getName());
        employeeDto.setSurname(employee.getSurname());
        String employeePosition = employee.getPosition();
        employeeDto.setJob(new JobDto(Position.byName(employeePosition.toUpperCase())));
        return employeeDto;
    }

    @Transactional
    public Employee delete(int id) {
        return new Employee(employeeDao.delete(id));
    }

    @Transactional
    public List<Employee> getAll() {
        List<Employee> employees = new ArrayList<>();
        for (EmployeeDto employeeDb : employeeDao.getAll()) {
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
    public List<Employee> getTeachers() {
        List<Employee> teachers = getAll();
        // TODO
        return teachers;
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

    @Transactional
    public SchoolClass getSchoolClass(int employeeId) {
        SchoolClassDto schoolClassDto = schoolClassDao.getSchoolClassByTeacherId(employeeId);
        return new SchoolClass(schoolClassDto);
    }
}
