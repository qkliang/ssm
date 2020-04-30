package com.lqk.service;

import com.lqk.bean.Department;
import com.lqk.dao.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;
    public List<Department> getAllDepts(){
        List<Department> departments = departmentMapper.selectByExample(null);
        return departments;
    }
}
