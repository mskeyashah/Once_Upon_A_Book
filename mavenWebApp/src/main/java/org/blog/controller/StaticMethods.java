package org.blog.controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: keyashah
 * Date: 8/5/19
 * Time: 10:00 AM
 * To change this template use File | Settings | File Templates.
 */
public class StaticMethods {
    public static ArrayList<Object> select(String id)
    {
        ArrayList<Object> result = new ArrayList<Object>();
        Statement stmt = openConnection();
        try
        {
            if(id.equals("all"))
            {
                ResultSet rs = stmt.executeQuery("select * from users");
                while (rs.next())
                {
                    int ids = rs.getInt("id");
                    String first = rs.getString("first_name");
                    String last = rs.getString("last_name");
                    String username = rs.getString("username");
                    String passphrase = rs.getString("passphrase");
                    Boolean is_admin = rs.getBoolean("is_admin");
                    result.add(new User(ids,first,last,username,passphrase,is_admin));
                }
            }
            else
            {

                ResultSet count = stmt.executeQuery("select count(*) from users where id = "+id);
                int count1 = count.getInt(1);
                if(count1>0)
                {
                    ResultSet rs = stmt.executeQuery("select * from users where id = "+id );
                    while (rs.next())
                    {
                        int ids = rs.getInt("id");
                        String first = rs.getString("first_name");
                        String last = rs.getString("last_name");
                        String username = rs.getString("username");
                        String passphrase = rs.getString("passphrase");
                        Boolean is_admin = rs.getBoolean("is_admin");
                        result.add(new User(ids,first,last,username,passphrase,is_admin));
                    }
                }
                else
                    result.add("No such record exists with id = "+id);
            }
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        return result;
    }
    public static void delete(String id)
    {
        try
        {
            Statement stats = openConnection();
            ResultSet count = stats.executeQuery("select count(*) from users where id = "+id);
            int count1 = count.getInt(1);
            if(count1>0)
            {
                String state = "delete from users where id = " + id;
                stats.executeUpdate(state);
            }

            stats.close();
        }
        catch (Exception e){

            System.out.println("Error: No such record exists with id = " + e.getMessage());
        }
    }
    public static ArrayList<Object> insert(String first, String last, String username, String passphrase, String is_admin)
    {
        ArrayList<Object> u1 = new ArrayList<Object>();
        try
        {
            Statement stmt = openConnection();
            ResultSet userExists = stmt.executeQuery("select count(*) from users where username = '"+username+"'");
            int isNew = userExists.getInt(1);
            if(isNew<1)
            {
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                ResultSet r1 = stmt.executeQuery("select Max(id) from users");
                int max = r1.getInt(1) +1;
                String state = "insert into users(id,first_name,last_name,username,passphrase, is_admin,created_by,created_on,modified_by,modified_on) values ( "+ max+",'";
                state += first+"','"+last+"','"+username+"','"+passphrase+"','"+is_admin +"','";
                state += first +"', '"+ dateFormat.format(date)+"', '" +first +"', '"+ dateFormat.format(date)+"')";
                stmt.executeUpdate(state);
                u1 = select("all");
            }
            else
                u1.add("The username is not available");
            stmt.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return u1;
    }
    public static ArrayList<Object> update(String column, String value, String id)
    {
        ArrayList<Object> u1 = new ArrayList<Object>();
        if(column.equals("id"))
        {
            u1.add("Sorry you cannot change the id");
        }
        else
        {
            try
            {
                Statement stats = openConnection();
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();

                String state = "update users set "+column+" = '" + value+"', modified_on = '"+dateFormat.format(date)+"' where id = " + id;
                stats.executeUpdate(state);
                u1 = select("all");
                stats.close();
            }

            catch (Exception e){
                u1.add("Error: The value you chose for username is not unique");
            }
        }
        return u1;
    }
    public static Statement openConnection()
    {
        Statement stmt = null;
        try
        {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/keya/Database/test.db");
            stmt = conn.createStatement();

        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        return stmt;
    }
}