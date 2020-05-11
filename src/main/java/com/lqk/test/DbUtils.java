package com.lqk.test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DbUtils {

    public static void main(String[] args) {

        try {
            Connection conn = getConnection();
            String sql = "select * from tbl_dept";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                String string = rs.getString(2);
                System.out.println(string);
            }
           preparedStatement.close();
//            preparedStatement.getConnection().close();
            sql = "select * from tbl_dept";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs1 = ps.executeQuery();
            while (rs1.next()){
                String string = rs1.getString(2);
                System.out.println(string);
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection(){
        Connection conn = null;
        Properties prop = new Properties();

        InputStream stream = DbUtils.class.getClassLoader().getResourceAsStream("dbconfig.properties");
        try {
            prop.load(stream);
            String driver = prop.getProperty("jdbc.driver");
            String url = prop.getProperty("jdbc.jdbcUrl");
            String username = prop.getProperty("jdbc.username");
            String password = prop.getProperty("jdbc.password");
            Class.forName(driver);
            conn = DriverManager.getConnection(url,username,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
