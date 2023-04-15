package com.ooadjproject.appofapi.Models;

import java.sql.*;
public class APIManagement {
    public boolean insAPI(String aname,String aurl,String acreator)
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
                    "INSERT INTO API (aname,aurl,acreator) VALUES(?,?,?)");
            statement.setString(1,aname);
            statement.setString(2,aurl);
            statement.setString(3,acreator);
            statement.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
        return true;
    }
    public boolean delAPI(String aname)
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
                    "DELETE FROM API WHERE aname=?");
            statement.setString(1,aname);
            statement.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
        return true;
    }
    public ResultSet dispAllAPI()
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
                    "SELECT * FROM API");
            resultSet=statement.executeQuery();
            return resultSet;
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return resultSet;
    }
    public static void main(String[] args) throws SQLException
    {
        APIManagement o=new APIManagement();
        System.out.println(o.insAPI("kontests","https://kontests.net/api/v1/all","grayblade"));
//        System.out.println(o.delAPI("kontests"));
        ResultSet r=o.dispAllAPI();
        while(r.next())
        {
            System.out.println("API_NAME: "+r.getString("aname"));
            System.out.println("API_URL: "+r.getString("aurl"));
            System.out.println("API_CREATOR: "+r.getString("acreator"));
            System.out.println();
        }
    }
}
