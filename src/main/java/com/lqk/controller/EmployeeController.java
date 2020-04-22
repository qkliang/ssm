package com.lqk.controller;

import com.lqk.bean.Employee;
import com.lqk.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    @RequestMapping("/emps")
    public String getEmployees(){
        List<Employee> employees = employeeService.getAll();
        System.out.println(employees.size());
        return "list";
    }
}
