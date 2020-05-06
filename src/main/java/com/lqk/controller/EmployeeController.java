package com.lqk.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lqk.bean.Employee;
import com.lqk.bean.Message;
import com.lqk.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @ResponseBody
    @RequestMapping(value="/emp",method = RequestMethod.POST)
    public Message addEmp(Employee employee){
        employeeService.addEmp(employee);
        return Message.success();
    }

    @ResponseBody
    @RequestMapping("/emps")
    public Message getEmployees(@RequestParam(value = "pn",defaultValue = "1") Integer pn){
        PageHelper.startPage(pn,5);
        List<Employee> emps = employeeService.getAll();
        PageInfo pageInfo = new PageInfo(emps, 5);
        return Message.success().add("pageInfo",pageInfo);
    }

//    @RequestMapping("/emps")
    public String getEmployees(@RequestParam(value = "pn",defaultValue = "1") Integer pn,
    Model m){
        PageHelper.startPage(pn,5);
        List<Employee> employees = employeeService.getAll();
        PageInfo pageInfo = new PageInfo(employees, 5);
        m.addAttribute("pageInfo",pageInfo);
        return "list";
    }
}
