package com.lqk.test;

import com.lqk.bean.Department;
import com.lqk.bean.Employee;
import com.lqk.dao.DepartmentMapper;
import com.lqk.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@EnableSpringConfigured
public class MapperTest {
    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    SqlSession sqlSession;

    @Test
    public void testGetEmpWithDept(){
        Employee employee = employeeMapper.selectByPrimaryKeyWithDept(1);
        System.out.println(employee.toString());
        List<Employee> employees = employeeMapper.selectByExampleWithDept(null);
        System.out.println(employees.size());
    }
    @Test
    public void testSqlSession(){
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        for(int i =0;i<1000;i++){
            String uuid = UUID.randomUUID().toString().substring(0,5);
            mapper.insertSelective(new Employee(null,uuid,"M",uuid+"@foxmail.com",1));
        }
    }
    @Test
    public void TestEmp(){
        String uuid = UUID.randomUUID().toString().substring(0,5);
        employeeMapper.insertSelective(new Employee(null,"lqk","F","qkliang@foxmail.com",1));

    }
    @Test
    public void test(){
        System.out.println(departmentMapper);
        Department department = new Department();
//        department.setDeptName("开发部");
//        departmentMapper.insertSelective(department);
//        department.setDeptName("测试部");
//        departmentMapper.insertSelective(department);
        department = departmentMapper.selectByPrimaryKey(1);
        System.out.println(department.toString());
    }
}
