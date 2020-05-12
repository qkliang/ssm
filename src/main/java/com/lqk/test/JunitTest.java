package com.lqk.test;

import com.lqk.juc.ThreadDemo;
import com.lqk.tools.Arith;
import org.junit.Test;

public class JunitTest {

    @Test
    public void test(){
        ThreadDemo threadDemo = new ThreadDemo();
        threadDemo.start();
    }
}
