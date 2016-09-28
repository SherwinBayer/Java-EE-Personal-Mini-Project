/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package essentials;

import java.util.ArrayList;
import javax.ejb.Stateful;

@Stateful
public class BookListEJBLocalImpl implements BookListEJBLocal {

    private ArrayList bookNames;
    private ArrayList releases;
    private ArrayList recommendations;

    @Override
    public ArrayList getBookNames() {
        return bookNames;
    }

    @Override
    public void setBookNames(ArrayList bookNames) {
        this.bookNames = bookNames;
    }

    @Override
    public ArrayList getReleases() {
        return releases;
    }

    @Override
    public void setReleases(ArrayList releases) {
        this.releases = releases;
    }

    @Override
    public ArrayList getRecommendations() {
        return recommendations;
    }

    @Override
    public void setRecommendations(ArrayList recommendations) {
        this.recommendations = recommendations;
    }
    
}
