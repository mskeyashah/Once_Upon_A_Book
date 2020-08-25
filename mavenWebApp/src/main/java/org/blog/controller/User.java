package org.blog.controller;

public class User {
    private int id;
    private String first;
    private String last;
    private String username;
    private String passphrase;
    private Boolean is_admin;
    public User(int id1, String first1, String last1, String username1, String passphrase1, boolean is_admin1)
    {
        id = id1;
        first = first1;
        last = last1;
        username = username1;
        passphrase = passphrase1;
        is_admin = is_admin1;
    }
    public int getId()
    {
        return id;
    }
    public String getFirst()
    {
        return first;
    }
    public String getLast()
    {
        return last;
    }
    public String getUsername()
    {
        return username;
    }
    public String getPassphrase()
    {
        return passphrase;
    }
    public boolean getIsAdmin()
    {
        return is_admin;
    }
    public String toString()
    {
        String result = id+" "+first+" "+last+" "+username+" "+passphrase+" "+is_admin;
        return result;
    }
}