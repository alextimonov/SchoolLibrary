package ua.timonov.aplib.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.timonov.aplib.service.EmployeeService;

/**
 * Created by Alex on 23.12.2016.
 */

@Controller
public class MainController {
    public static final String INDEX = "index";
    public static final String SLASH_INDEX = "/index";
    public static final String EMPLOYEES = "employees";
    public static final String SLASH_EMPLOYEES = "/employees";

    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = SLASH_INDEX, method = RequestMethod.GET)
    public String getMainPage() {
        return INDEX;
    }

    /*@RequestMapping(value = SLASH_EMPLOYEES, method = RequestMethod.GET)
    public String getAllMenus(Map<String, Object> model) {
        List<Employee> employees = employeeService.getAll();
        model.put(EMPLOYEES, employees);
        return EMPLOYEES;
    }*/
}
