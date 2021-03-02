package org.geektimes.projects.user.service;

import org.geektimes.projects.user.domain.User;

import javax.servlet.ServletException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class UserServiceImpl implements UserService{

    public static final String DROP_USERS_TABLE_DDL_SQL = "DROP TABLE users";

    public static final String CREATE_USERS_TABLE_DDL_SQL = "CREATE TABLE users(" +
            "id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
            "name VARCHAR(16) NOT NULL, " +
            "password VARCHAR(64) NOT NULL, " +
            "email VARCHAR(64) NOT NULL, " +
            "phoneNumber VARCHAR(64) NOT NULL" +
            ")";

    @Override
    public boolean register(User user) throws ServletException {
        try{
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            String databaseURL = "jdbc:derby:testdb/user-platform;create=true";
            Driver driver = DriverManager.getDriver(databaseURL);
            Connection connection = driver.connect(databaseURL, new Properties());

            Statement statement = connection.createStatement();
            try{
                // 创建 users 表
                System.out.println(statement.execute(CREATE_USERS_TABLE_DDL_SQL)); // false
            }catch (Exception e){
                System.out.println("User Table has been created, ready to write in.");
            }
            String INSERT_USER_DDL  =  "INSERT INTO USERS(name,password,email,phoneNumber) VALUES ( 'default','"+ user.getPassword()+"',"
                    + "'"+ user.getEmail() + "', '0')";
            statement.executeUpdate(INSERT_USER_DDL);

            return true;

        }catch (Exception e){
            throw new ServletException(e);
        }

    }

    @Override
    public boolean deregister(User user) {
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public User queryUserById(Long id) {
        return null;
    }

    @Override
    public User queryUserByNameAndPassword(String name, String password) {
        return null;
    }
}
