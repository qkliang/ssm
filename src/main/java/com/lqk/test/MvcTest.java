package com.lqk.test;


import com.github.pagehelper.PageInfo;
import com.lqk.bean.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration
        ;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml",
        "file:C:\\Users\\lqk\\IdeaProjects\\ssm\\src\\main\\webapp\\WEB-INF\\dispatcher-servlet.xml"})
public class MvcTest {
//src/main/webapp/WEB-INF/dispatcher-servlet.xml
    @Autowired
    WebApplicationContext webApplicationContext;
    MockMvc mockMvc;

    @Before
    public void initMokcMvc(){
        mockMvc =  MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    }
    @Test
    public void testPage() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pn", "1")).andReturn();
        PageInfo pi = (PageInfo)result.getRequest().getAttribute("pageInfo");
        System.out.println("当前页码："+ pi.getPageNum());
        System.out.println("总页码:" + pi.getPages());
        System.out.println("总记录数:" + pi.getTotal());
        System.out.println("在页面需要连续显示的页码：");
        int[] navigatepageNums = pi.getNavigatepageNums();
        for (int i : navigatepageNums){
            System.out.println(" "+ i);
        }
        List<Employee> list = pi.getList();
        list.get(0).getEmpName();
        for(Employee e : list){
            System.out.println("员工："+e.getEmpId()+e.getEmpName());
        }
    }
}
