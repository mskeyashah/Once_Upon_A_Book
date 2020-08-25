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
 * Date: 7/25/19
 * Time: 2:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class RestMethods {
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
    public static ArrayList<Object> delete(String id)
    {
        ArrayList<Object> u1 = new ArrayList<Object>();
        try
        {
            Statement stats = openConnection();
            ResultSet count = stats.executeQuery("select count(*) from users where id = "+id);
            int count1 = count.getInt(1);
            if(count1>0)
            {
                String state = "delete from users where id = " + id;
                stats.executeUpdate(state);
                u1 = select("all");
            }
            else
                u1.add("Error: No such record exists with id = " + id);

            stats.close();
        }
        catch (Exception e){

            System.out.println("Error: No such record exists with id = " + e.getMessage());
        }
        return u1;
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
    public static ArrayList<Object> update(String column, String value, String id, String modified_by)
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

                String state = "update users set "+column+" = '" + value+"', modified_by = '"+modified_by+"', modified_on = '"+dateFormat.format(date)+"' where id = " + id;
                stats.executeUpdate(state);
                u1 = select("all");
                stats.close();
            }

            catch (Exception e){
                System.out.println(e.getMessage());
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

    public static ArrayList<Object> selectBooks(String title)
    {
        ArrayList<Object> result = new ArrayList<Object>();
        Statement stmt = openConnection();
        try
        {
            if(title.equals("all"))
            {
                ResultSet rs = stmt.executeQuery("select * from books");
                while (rs.next())
                {
                    int ids = rs.getInt("book_id");
                    String book_name = rs.getString("book_name");
                    String author = rs.getString("author_name");
                    int year = rs.getInt("year_published");
                    String genre = rs.getString("genre");
                    result.add(new Books(ids,book_name,author,year,genre));
                }
            }
            else
            {

                ResultSet count = stmt.executeQuery("select count(*) from books where book_name like \'%"+title+"%\'");
                int count1 = count.getInt(1);
                if(count1>0)
                {
                    ResultSet rs = stmt.executeQuery("select * from books where book_name like '%"+title+"%\'");
                    while (rs.next())
                    {
                        int ids = rs.getInt("book_id");
                        String book_name = rs.getString("book_name");
                        String author = rs.getString("author_name");
                        int year = rs.getInt("year_published");
                        String genre = rs.getString("genre");
                        result.add(new Books(ids,book_name,author,year,genre));
                    }
                }
                else
                    result.add("No such record exists with title = "+title);
            }
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        return result;
    }

    public static ArrayList<Object> insertbooks(String title, String author, String year, String genre, String added_by)
    {
        ArrayList<Object> u1 = new ArrayList<Object>();
        try
        {
            Statement stmt = openConnection();
            ResultSet userExists = stmt.executeQuery("select count(*) from books where book_name = '"+title+"'");
            int isNew = userExists.getInt(1);
            if(isNew<1)
            {
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                ResultSet r1 = stmt.executeQuery("select Max(book_id) from books");
                int max = r1.getInt(1) +1;
                String state = "insert into books(book_id,book_name,author_name,year_published,genre,created_by,created_on,modified_by,modified_on) values ( "+ max+",'";
                state += title+"','"+author+"','"+year+"','"+genre+"','";
                state += added_by +"', '"+ dateFormat.format(date)+"', '" +added_by +"', '"+ dateFormat.format(date)+"')";
                stmt.executeUpdate(state);
                r1 = stmt.executeQuery("select Max(reservation_id) from reservation");
                int max1 = r1.getInt(1) +1;
                state = "insert into reservation(reservation_id,book_id,available,num_holds,book_with,created_by,created_on,modified_by,modified_on) values (" + max1+","+max+",true,0,null,'";
                state += added_by +"', '"+ dateFormat.format(date)+"', '" +added_by +"', '"+ dateFormat.format(date)+"')";
                stmt.executeUpdate(state);
                u1 = selectBooks("all");
            }
            else
                u1.add("The book has already been added");
            stmt.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return u1;
    }

    public static ArrayList<Object> deletebooks(String id)
    {
        ArrayList<Object> u1 = new ArrayList<Object>();
        try
        {
            Statement stats = openConnection();
            ResultSet count = stats.executeQuery("select count(*) from books where book_id = "+id);
            int count1 = count.getInt(1);
            if(count1>0)
            {
                String state = "delete from books where book_id = " + id;
                stats.executeUpdate(state);
                state = "delete from reservation where book_id = " + id;
                stats.executeUpdate(state);
                state = "delete from holds where book_id = " + id;
                stats.executeUpdate(state);
                u1 = selectBooks("all");
            }
            else
                u1.add("Error: No such record exists with id = " + id);

            stats.close();
        }
        catch (Exception e){

            System.out.println("Error: No such record exists with id = " + e.getMessage());
        }
        return u1;
    }

    public static ArrayList<Object> updateBooks(String column, String value, String id,String modified_by)
    {
        ArrayList<Object> u1 = new ArrayList<Object>();

        try
        {
            Statement stats = openConnection();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();

            String state = "update books set "+column+" = '" + value+"', modified_by = '"+modified_by+"', modified_on = '"+dateFormat.format(date)+"' where book_id = " + id;
            stats.executeUpdate(state);
            u1 = selectBooks("all");
            stats.close();
        }

        catch (Exception e){
            System.out.println(e.getMessage());
            u1.add("The book has already been added.");
        }

        return u1;
    }

    public static ArrayList<Object> selectReservation(String title)
    {
        ArrayList<Object> result = new ArrayList<Object>();
        title = title.replace('+',' ');
        Statement stmt = openConnection();
        try
        {
            if(title.equals("all"))
            {
                ResultSet rs = stmt.executeQuery("select r.reservation_id,b.book_name,b.author_name,b.year_published,b.genre,r.available,r.num_holds from books b join reservation r on b.book_id = r.book_id");
                while (rs.next())
                {
                    int ids = rs.getInt("reservation_id");
                    String book_name = rs.getString("book_name");
                    String author = rs.getString("author_name");
                    int year = rs.getInt("year_published");
                    String genre = rs.getString("genre");
                    Boolean available = rs.getBoolean("available");
                    int holds = rs.getInt("num_holds");
                    result.add(new Reservation(ids,book_name,author,year,genre,available,holds));
                }
            }
            else
            {

                ResultSet count = stmt.executeQuery("select count(*) from reservation r join books b on r.book_id = b.book_id where b.book_name like \'%"+title+"%\'");
                int count1 = count.getInt(1);
                if(count1>0)
                {
                    ResultSet rs = stmt.executeQuery("select r.reservation_id,b.book_name,b.author_name,b.year_published,b.genre,r.available,r.num_holds from books b join reservation r on b.book_id = r.book_id where b.book_name like '%"+title+"%\'");
                    while (rs.next())
                    {
                        int ids = rs.getInt("reservation_id");
                        String book_name = rs.getString("book_name");
                        String author = rs.getString("author_name");
                        int year = rs.getInt("year_published");
                        String genre = rs.getString("genre");
                        Boolean available = rs.getBoolean("available");
                        int holds = rs.getInt("num_holds");
                        result.add(new Reservation(ids,book_name,author,year,genre,available,holds));
                    }
                }
                else
                    result.add("No such record exists with title = "+title);
            }
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        return result;
    }

    public static ArrayList<Object> reserving(String title, String name)
    {
        ArrayList<Object> result = new ArrayList<Object>();
        Statement stmt = openConnection();
        title = title.replace('+',' ');
        try
        {
            Boolean available = true;
            int book_id = 1;
            int holds= 0;
            ResultSet rs1 = stmt.executeQuery("SELECT reservation.available,books.book_id,reservation.num_holds from reservation join books on reservation.book_id = books.book_id where books.book_name like \'%"+title+"%\'");
            while (rs1.next())
            {
                available = rs1.getBoolean("available");
                book_id = rs1.getInt("book_id");
                holds = rs1.getInt("num_holds");
            }
            if(available)
            {
                String state = "update reservation set available = false, book_with = \'"+name+"\' where book_id = " + book_id;
                stmt.executeUpdate(state);
                result.add("reserved");
            }
            else
            {
                holds +=1;
                ResultSet rs3 = stmt.executeQuery("select count(holder) from holds where holder = "+name);
                int count = rs3.getInt(1);
                ResultSet rs4 = stmt.executeQuery("select count(book_with) from reservation where book_with = "+name);
                int count1 = rs4.getInt(1);
                if(count>0 || count1>0)
                {
                	result.add("already");
                }
                else
                {
	                ResultSet rs2 = stmt.executeQuery("select max(hold_id) from holds");
	                int max = rs2.getInt(1) +1;
	                String state = "insert into holds(hold_id,book_id,holder) values ("+max+","+book_id+",\'"+name+"\')";
	                stmt.executeUpdate(state);
	                state = "update reservation set num_holds = "+holds+" where book_id = " + book_id;
	                stmt.executeUpdate(state);
	                result.add("hold");
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        return result;
    }

    public static ArrayList<Object> cancelingBook(String title, String name)
    {
        ArrayList<Object> result = new ArrayList<Object>();
        Statement stmt = openConnection();
        title = title.replace('+',' ');
        try
        {
            int book_id = 1;
            int holds= 0;
            ResultSet rs1 = stmt.executeQuery("SELECT books.book_id,reservation.num_holds from reservation join books on reservation.book_id = books.book_id where books.book_name like \'%"+title+"%\'");
            while (rs1.next())
            {
                book_id = rs1.getInt("book_id");
                holds = rs1.getInt("num_holds");
            }
            ResultSet rs = stmt.executeQuery("select count(*) from holds where holder = \'"+name+"\' and book_id = "+book_id);
            int exists = rs.getInt(1);
            if(exists>0)
            {
                String state = "delete from holds where holder = \'"+name+"\' and book_id = "+book_id;
                result.add(state);
                stmt.executeUpdate(state);
                holds--;
                state = "update reservation set num_holds = "+holds+" where book_id = " + book_id;
                stmt.executeUpdate(state);
            }
            else
                result.add("Sorry, there is no hold for " + name + " for book " + title);
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        return result;
    }
    public static ArrayList<Object> returningBook(String title, String name)
    {
        ArrayList<Object> result = new ArrayList<Object>();
        Statement stmt = openConnection();

        title = title.replace('+',' ');

        try
        {
            Boolean available = true;
            int book_id = 1;
            int holds= 0;
            ResultSet rs1 = stmt.executeQuery("SELECT reservation.available, books.book_id,reservation.num_holds from reservation join books on reservation.book_id = books.book_id where books.book_name like \'%"+title+"%\'");
            while (rs1.next())
            {
                available = rs1.getBoolean("available");
                book_id = rs1.getInt("book_id");
                holds = rs1.getInt("num_holds");
            }
            ResultSet rs = stmt.executeQuery("select count(*) from reservation where book_with = \'"+name+"\' and book_id = "+book_id);
            int exists = rs.getInt(1);
            if(exists>0)
            {
                String holder = "";
                ResultSet rs2 = stmt.executeQuery("select count(holder) from holds where book_id = " + book_id + " limit 1");
                int numHolders = rs2.getInt(1);
                String state = "";
                if(numHolders>0)
                {
                    rs2 = stmt.executeQuery("select holder from holds where book_id = " + book_id + " limit 1");
                    while(rs2.next())
                    {
                        holder = rs2.getString("holder");
                    }
                    state = "delete from holds where holder = \'" + holder+"\' and book_id = "+book_id;
                    stmt.executeUpdate(state);
                    state = "update reservation set book_with = \'"+holder+"\' where book_id = " + book_id;
                    stmt.executeUpdate(state);
                    holds--;
                    state = "update reservation set num_holds = "+holds+" where book_id = " + book_id;
                    stmt.executeUpdate(state);
                }
                else
                {
                    state = "update reservation set book_with = '', available = true where book_id = " + book_id;
                    stmt.executeUpdate(state);
                }

            }
            else
                result.add("The holder of this book is not "+ name);
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        return result;
    }

    public static ArrayList<Object> loginResult(String username, String passphrase)
    {
        ArrayList<Object> result = new ArrayList<Object>();
        Statement stmt = openConnection();


        try
        {
            ResultSet rs1 = stmt.executeQuery("SELECT count(*) from users where username= \'"+username+"\' and passphrase = \'"+passphrase+"\'");
            int exists = rs1.getInt(1);
            int id= 0;
            String first = "";
            if(exists>0)
            {
                rs1 = stmt.executeQuery("SELECT id, first_name from users where username= \'"+username+"\' and passphrase = \'"+passphrase+"\'");
                while (rs1.next())
                {
                    id = rs1.getInt("id");
                    first = rs1.getString("first_name");
                    result.add(id);
                    result.add(first);
                }
            }
            else
                result.add("Incorrect username or password");

        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        return result;
    }
    
    
}