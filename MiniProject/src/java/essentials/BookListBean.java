/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package essentials;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Sherwin
 */
public class BookListBean implements Serializable
{
    private ArrayList bookNames;
    private ArrayList releases;
    private ArrayList recommendations;
    
    public BookListBean()
    {
        
    }

    public ArrayList getBookNames() {
        return bookNames;
    }

    public void setBookNames(ArrayList bookNames) {
        this.bookNames = bookNames;
    }

    public ArrayList getReleases() {
        return releases;
    }

    public void setReleases(ArrayList releases) {
        this.releases = releases;
    }

    public ArrayList getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(ArrayList recommendations) {
        this.recommendations = recommendations;
    }
    
    
    
}
