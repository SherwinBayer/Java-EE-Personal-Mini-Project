package essentials;


import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sherwin
 */
public class BookBean implements Serializable
{
    private String bookName;
    private String oneYearOrNot;
    private String recommend;
    
    public BookBean()
    {
        bookName = null;
        oneYearOrNot = null;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getOneYearOrNot() {
        return oneYearOrNot;
    }

    public void setOneYearOrNot(String oneYearOrNot) {
        this.oneYearOrNot = oneYearOrNot;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }
    
}
