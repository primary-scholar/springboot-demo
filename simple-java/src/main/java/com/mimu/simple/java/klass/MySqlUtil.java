package com.mimu.simple.java.klass;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * author: mimu
 * date: 2019/2/12
 */
public class MySqlUtil {

    private static String userName = "root";
    private static String password = "root";
    private static String url = "jdbc:mysql://localhost:3306/test";
    private static Connection connection;

    /*
    here we didn't need use the static code to load the com.mysql.jdbc.Driver
    more information visit https://blog.csdn.net/yangcheng33/article/details/52631940;
     */
    /*static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }*/


    public static Connection getConnection() throws SQLException {
        connection = DriverManager.getConnection(url, userName, password);
        return connection;
    }

    public void close() throws SQLException {
        if (!connection.isClosed()) {
            connection.close();
        }
    }

    public void info(){
        ServiceLoader<Driver> serviceLoader = ServiceLoader.load(Driver.class);
        for (Driver driver : serviceLoader) {
            System.out.println("driver:" + driver.getClass() + " :loader: " + driver.getClass().getClassLoader());
        }
        System.out.println("currentThreadLoader: "+Thread.currentThread().getContextClassLoader());
        System.out.println("serviceLoadClassloader: "+ServiceLoader.class.getClassLoader());
    }

    public void info1(){
        ServiceLoader<Driver> serviceLoader = ServiceLoader.load(Driver.class,this.getClass().getClassLoader().getParent());
        for (Driver driver : serviceLoader) {
            System.out.println("driver:" + driver.getClass() + " :loader: " + driver.getClass().getClassLoader());
        }
        System.out.println("currentThreadLoader: "+Thread.currentThread().getContextClassLoader());
        System.out.println("serviceLoadClassloader: "+ServiceLoader.class.getClassLoader());
    }
}
