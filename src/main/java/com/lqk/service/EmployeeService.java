package com.lqk.service;

import com.lqk.bean.Employee;
import com.lqk.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;
    public List<Employee> getAll(){
        return employeeMapper.selectByExampleWithDept(null);
    }

    public void addEmp(Employee employee){
        employeeMapper.insert(employee);
    }

}
