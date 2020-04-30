package com.lqk.controller;

import com.lqk.bean.Department;
import com.lqk.bean.Message;
import com.lqk.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;
    @ResponseBody
    @RequestMapping("/depts")
    public Message getDepts(){
        List<Department> depts = departmentService.getAllDepts();
        return Message.success().add("depts",depts);
    }
}
