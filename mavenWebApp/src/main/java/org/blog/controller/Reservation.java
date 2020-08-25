package org.blog.controller;
public class Reservation {
    private int reservation_id;
    private String book_name;
    private String author;
    private int year;
    private String genre;
    private boolean available;
    private int num_holds;

    public Reservation(int reservation_id1, String book_name1,String auth, int yr, String gen, boolean avail,int holds)
    {
        reservation_id = reservation_id1;
        book_name = book_name1;
        author = auth;
        year = yr;
        genre = gen;
        available = avail;
        num_holds = holds;
    }
    public int getReservation_id()
    {
        return reservation_id;
    }
    public String getBook_name()
    {
        return book_name;
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
    public boolean getAvailable()
    {
        return available;
    }
    public int getNum_holds()
    {
        return num_holds;
    }
}
