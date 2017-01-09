package ua.timonov.aplib.dao.dbStub;

import ua.timonov.aplib.model.Employee;

import java.util.HashMap;
import java.util.Map;

public class DatabaseClass {
    private static Map<Long, Employee> employees = new HashMap<>();

    public static Map<Long, Employee> getEmployees() {
        return employees;
    }
}
