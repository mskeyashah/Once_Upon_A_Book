package org.blog.controller;
public class Status
{
    private int code;
    private String query;
    private int count;
    public Status(int code1, String query1, int count1)
    {
        code = code1;
        query= query1;
        count = count1;
    }
    public String getQuery()
    {
        return query;
    }
    public int getCode()
    {
        return code;
    }
    public int getCount()
    {
        return count;
    }
}
