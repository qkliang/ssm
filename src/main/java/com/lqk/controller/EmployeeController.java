package com.lqk.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lqk.bean.Employee;
import com.lqk.bean.Message;
import com.lqk.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

    /**
     * 通过ID保存
     */
    @ResponseBody
    @RequestMapping(value = "/emp/{empId}",method = RequestMethod.PUT)
    public Message updateEmpById(Employee employee){
        System.out.println("11111111111111");
        System.out.println(employee);
        employeeService.updateEmpById(employee);
        return Message.success();
    }
    /**
     * 通过id查询emp
     */
    @ResponseBody
    @RequestMapping(value = "/emp/{id}",method = RequestMethod.GET)
    public Message getEmpById(@PathVariable("id") Integer id){
        Employee employee = employeeService.getEmpById(id);
        return Message.success().add("emp",employee);
    }

    /**
     * 通过empName检查是否存在
     * */
    @ResponseBody
    @RequestMapping(value = "/checkUser",method = RequestMethod.POST)
    public Message checkExistUser(@RequestParam("empName") String empName){
        //true代表不存在
        boolean b = employeeService.checkUser(empName);
        if(b){
            return Message.success();
        }else {
            return Message.fail();
        }
    }

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
