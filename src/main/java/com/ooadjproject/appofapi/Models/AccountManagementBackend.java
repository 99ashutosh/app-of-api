package com.ooadjproject.appofapi.Models;

import java.sql.*;
public class AccountManagementBackend {
    public boolean checkUserPass(String uname, String pass)
    {
        Connection connection = null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/API",
                    "APIroot","APIroot");
            PreparedStatement statement;
            statement=connection.prepareStatement(
                    "SELECT * FROM USER WHERE uname=? AND upass=?");
            statement.setString(1,uname);
            statement.setString(2,pass);
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
    public ResultSet dispAllAccounts()
    {
        Connection connection=null;
        ResultSet resultSet=null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/API",
                    "APIroot","APIroot");
            PreparedStatement statement;
            statement=connection.prepareStatement(
                    "SELECT * FROM USER");
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
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/API",
                    "APIroot","APIroot");
            PreparedStatement statement;
            statement=connection.prepareStatement(
                    "DELETE FROM USER WHERE uname=?");
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
    public static void main(String[] args) throws SQLException
    {
        AccountManagementBackend o=new AccountManagementBackend();
//        System.out.println(o.checkUserPass("grayblade","idlp"));

//        ResultSet r=o.dispAllAccounts();
//        while(r.next())
//        {
//            System.out.println("UNAME: "+r.getString("uname"));
//            System.out.println("UPASS: "+r.getString("upass"));
//            System.out.println("FNAME: "+r.getString("fname"));
//            System.out.println("LNAME: "+r.getString("lname"));
//            System.out.println("EMAIL: "+r.getString("email"));
//            System.out.println("UTYPE: "+r.getInt("utype"));
//            System.out.println();
//        }

//        o.delAccount("cr7");
    }
}
