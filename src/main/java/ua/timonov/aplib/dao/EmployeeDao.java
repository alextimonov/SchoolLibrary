package ua.timonov.aplib.dao;

import ua.timonov.aplib.dto.EmployeeDto;
import ua.timonov.aplib.dto.Position;

import java.util.List;

/**
 * DAO interface for Employee
 */
public interface EmployeeDao {
    EmployeeDto add(EmployeeDto employee);

    EmployeeDto update(EmployeeDto employee);

    EmployeeDto delete(int id);

    List<EmployeeDto> getAll();

    EmployeeDto getEmployeeById(int id);

    EmployeeDto getBySurname(String surname);

    List<EmployeeDto> getTeachers();

    List<EmployeeDto> getEmployeesByPosition(Position position);
}
