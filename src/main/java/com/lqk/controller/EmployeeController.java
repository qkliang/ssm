package com.lqk.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lqk.bean.Employee;
import com.lqk.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    @RequestMapping("/emps")
    public String getEmployees(@RequestParam(value = "pn",defaultValue = "1") Integer pn,
    ModelAndView m){
        PageHelper.startPage(pn,5);
        List<Employee> employees = employeeService.getAll();
        System.out.println(employees.size());
        PageInfo pageInfo = new PageInfo(employees, 5);
        m.addObject("pageInfo",pageInfo);
        System.out.println(pageInfo);
        return "list";
    }
}
