package com.ooadjproject.appofapi.Models;

import java.sql.*;

/*
* The Singleton Pattern
* */

public class AccountManagementBackend {
    private static AccountManagementBackend instance;
    public boolean addUser(String uname, String fname, String lname, String pass, String email, int type)
    {
        Connection connection = null;
        try
        {
            //Class.forName("com.mysql.cj.jdbc.Driver");
            Class.forName("org.mariadb.jdbc.Driver");
            connection=DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/API",
                    "APIroot","APIroot");
            PreparedStatement statement;
            statement=connection.prepareStatement(
                    "INSERT INTO user VALUES (?, ?, ?, ?, ?, ?)");
            statement.setString(1,uname);
            statement.setString(2,pass);
            statement.setString(3,fname);
            statement.setString(4,lname);
            statement.setString(5,email);
            statement.setString(6, String.valueOf(type));

            ResultSet resultSet=statement.executeQuery();
            while(resultSet.next()) {
                return true;
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return false;
    }
    public int checkUserPass(String uname, String pass)
    {
        Connection connection = null;
        try
        {
            //Class.forName("com.mysql.cj.jdbc.Driver");
            Class.forName("org.mariadb.jdbc.Driver");
            connection=DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/API",
                    "APIroot","APIroot");
            PreparedStatement statement;
            statement=connection.prepareStatement(
                    "SELECT * FROM user WHERE uname=? AND upass=?");
            statement.setString(1,uname);
            statement.setString(2,pass);
            ResultSet resultSet=statement.executeQuery();
            while(resultSet.next()) {
                return resultSet.getInt(6);
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return -9999;
    }
    public ResultSet dispAllAccounts()
    {
        Connection connection=null;
        ResultSet resultSet=null;
        try
        {
            //Class.forName("com.mysql.cj.jdbc.Driver");
            Class.forName("org.mariadb.jdbc.Driver");
            connection=DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/API",
                    "APIroot","APIroot");
            PreparedStatement statement;
            statement=connection.prepareStatement(
                    "SELECT * FROM user");
            resultSet=statement.executeQuery();
            return resultSet;
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return resultSet;
    }
    public boolean delAccount(String uname)
    {
        Connection connection=null;
        try
        {
            //Class.forName("com.mysql.cj.jdbc.Driver");
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/API",
                    "APIroot","APIroot");
            PreparedStatement statement;
            statement=connection.prepareStatement(
                    "DELETE FROM user WHERE uname=?");
            statement.setString(1,uname);
            statement.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public static AccountManagementBackend getInstance() {
        if (instance == null) {
            instance = new AccountManagementBackend();
        }
        return instance;
    }


/*
    public static void main(String[] args) throws SQLException
    {
        AccountManagementBackend o=new AccountManagementBackend();
        System.out.println(o.checkUserPass("grayblade","idlp"));

        ResultSet r=o.dispAllAccounts();
        while(r.next())
        {
            System.out.println("UNAME: "+r.getString("uname"));
            System.out.println("UPASS: "+r.getString("upass"));
            System.out.println("FNAME: "+r.getString("fname"));
            System.out.println("LNAME: "+r.getString("lname"));
            System.out.println("EMAIL: "+r.getString("email"));
            System.out.println("UTYPE: "+r.getInt("utype"));
            System.out.println();
        }

        o.delAccount("cr7");
    }
*/

}
