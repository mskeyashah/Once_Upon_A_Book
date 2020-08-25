package org.blog.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
 
 
@RestController
public class SpringRestController {
	@RequestMapping("/hello")
	public String hello(@RequestParam(value="name", defaultValue="World") String name) {
		String result="Hello "+name; 
		return result;
	}
	@RequestMapping("/select")
    public ArrayList<Object> selectAll(@RequestParam(value="id", defaultValue="all") String id)
    {
        ArrayList<Object> users = RestMethods.select(id);
        return users;
    }
    @RequestMapping("/delete")
    public ArrayList<Object> delete(@RequestParam(value="id") String id)
    {
        ArrayList<Object> users = RestMethods.delete(id);
        if(users.get(0).equals("Error: No such record exists with id = "+id))
        {
            users.add(0,new Status(200,"delete",0));
        }
        else
            users.add(0,new Status(200,"delete",users.size()));
        return users;
    }

    @RequestMapping("/insert")
    public ArrayList<Object> insert(@RequestParam(value="first") String first,@RequestParam(value="last") String last,@RequestParam(value="username") String username,@RequestParam(value="passphrase") String passphrase,@RequestParam(value="isadmin") String is_admin)
    {
        ArrayList<Object> users = RestMethods.insert(first,last,username,passphrase,is_admin);
        if(users.get(0).equals("The username is not available"))
        {
            users.add(0,new Status(200,"insert",0));
        }
        else
            users.add(0,new Status(200,"insert",users.size()));
        return users;
    }

    @RequestMapping("/update")
    public ArrayList<Object> update(@RequestParam(value="column") String column,@RequestParam(value="value") String value,@RequestParam(value="id") String id,@RequestParam(value="modified_by") String modified)
    {
        ArrayList<Object> users = RestMethods.update(column,value,id,modified);
        if(users.get(0).equals("Sorry you cannot change the id") || users.get(0).equals("Error: The value you chose for username is not unique"))
        {
            users.add(0,new Status(200,"update",0));
        }
        else
            users.add(0,new Status(200,"update",users.size()));
        return users;
    }

    @RequestMapping("/bookselect")
    public ArrayList<Object> selectAllBooks(@RequestParam(value="title", defaultValue="all") String title)
    {
        ArrayList<Object> books = RestMethods.selectBooks(title);
        return books;
    }

    @RequestMapping("/bookinsert")
    public ArrayList<Object> insertingbooks(@RequestParam(value="title") String title,@RequestParam(value="author") String author,@RequestParam(value="year") String year,@RequestParam(value="genre") String genre,@RequestParam(value="added_by") String added_by)
    {
        ArrayList<Object> books = RestMethods.insertbooks(title,author,year,genre,added_by);
        if(books.get(0).equals("The book has already been added"))
        {
            books.add(0,new Status(200,"insert",0));
        }
        else
            books.add(0,new Status(200,"insert",books.size()));
        return books;
    }

    @RequestMapping("/bookdelete")
    public ArrayList<Object> deleteBooks(@RequestParam(value="id") String id)
    {
        ArrayList<Object> books = RestMethods.deletebooks(id);
        if(books.get(0).equals("Error: No such record exists with id = "+id))
        {
            books.add(0,new Status(200,"delete",0));
        }
        else
            books.add(0,new Status(200,"delete",books.size()));
        return books;
    }
    @RequestMapping("/bookupdate")
    public ArrayList<Object> updateBooks(@RequestParam(value="column") String column,@RequestParam(value="value") String value,@RequestParam(value="id") String id,@RequestParam(value="modified") String modified)
    {
        ArrayList<Object> books = RestMethods.updateBooks(column,value,id,modified);
        if(books.get(0).equals("The book has already been added"))
        {
            books.add(0,new Status(200,"delete",0));
        }
        else
            books.add(0,new Status(200,"delete",books.size()));
        return books;
    }
    @RequestMapping("/reservationselect")
    public ArrayList<Object> selectAllReservations(@RequestParam(value="title", defaultValue="all") String title)
    {
        ArrayList<Object> books = RestMethods.selectReservation(title);
        return books;
    }
    @RequestMapping("/reservingbook")
    public ArrayList<Object> reservingBook(@RequestParam(value="title") String title, @RequestParam(value="name") String name)
    {
        ArrayList<Object> books = RestMethods.reserving(title,name);
        return books;
    }
    @RequestMapping("/cancelingbook")
    public ArrayList<Object> cancelingBook(@RequestParam(value="title") String title, @RequestParam(value="name") String name)
    {
        ArrayList<Object> books = RestMethods.cancelingBook(title,name);
        return books;
    }
    @RequestMapping("/returningbook")
    public ArrayList<Object> returningBook(@RequestParam(value="title") String title, @RequestParam(value="name") String name)
    {
        ArrayList<Object> books = RestMethods.returningBook(title,name);
        return books;
    }
    
    @RequestMapping("/login")
    public ArrayList<Object> loginResult(@RequestParam(value="username") String username,@RequestParam(value="passphrase") String passphrase)
    {
    	ArrayList<Object> result = RestMethods.loginResult(username,passphrase);
    	return result;
    }
}