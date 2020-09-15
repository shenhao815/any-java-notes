package com.it.contextClassLoader;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author CH
 * @description
 * @date 2020-9-2
 */
public class JdbcTest {

    public static void main(String[] args) throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jbdc:mysql://localhost:3306/testdb", "username", "passwrod");
    }
}
