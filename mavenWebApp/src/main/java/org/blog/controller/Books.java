package org.blog.controller;
public class Books
{
    private int id;
    private String title;
    private String author;
    private int year;
    private String genre;
    public Books(int id1, String title1, String author1, int year1, String genre1)
    {
        id = id1;
        title = title1;
        author = author1;
        year = year1;
        genre= genre1;
    }
    public int getId()
    {
        return id;
    }
    public String getTitle()
    {
        return title;
    }
    public String getAuthor()
    {
        return author;
    }
    public int getYear()
    {
        return year;
    }
    public String getGenre()
    {
        return genre;
    }
}
