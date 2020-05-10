package com.lqk.service;

import com.lqk.bean.Employee;
import com.lqk.bean.EmployeeExample;
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

    public boolean checkUser(String empName) {
        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        criteria.andEmpNameEqualTo(empName);
        long count = employeeMapper.countByExample(employeeExample);
        return count == 0;
    }

    public Employee getEmpById(Integer id) {
        Employee employee = employeeMapper.selectByPrimaryKey(id);
        return employee;
    }

    public void updateEmpById(Employee employee) {
        employeeMapper.updateByPrimaryKeySelective(employee);
    }
}
