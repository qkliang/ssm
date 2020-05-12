package com.lqk.tools;

import java.math.BigDecimal;

public class Arith {
    private static final int DEF_DIV_SCALE = 10;
    private Arith(){}
    public static String add(String m ,String n){
        BigDecimal a = new BigDecimal(m);
        BigDecimal b = new BigDecimal(n);
        BigDecimal sum = a.add(b);
        return sum.toString();
    }
    public static String sub(String m ,String n){
        BigDecimal a = new BigDecimal(m);
        BigDecimal b = new BigDecimal(n);
        return a.subtract(b).toString();
    }

    public static String mul(String m ,String n){
        BigDecimal a = new BigDecimal(m);
        BigDecimal b = new BigDecimal(n);
        return a.multiply(b).toString();
    }
    public static String div(String m ,String n){
        BigDecimal a = new BigDecimal(m);
        BigDecimal b = new BigDecimal(n);
        return a.divide(b, DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP).toString();
    }
}
