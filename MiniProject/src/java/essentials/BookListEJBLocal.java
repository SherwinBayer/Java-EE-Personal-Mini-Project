/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package essentials;

import java.util.ArrayList;
import javax.ejb.Local;
import javax.ejb.Remote;

/**
 *
 * @author Sherwin
 */
@Local
public interface BookListEJBLocal 
{
   public ArrayList getBookNames();
   public void setBookNames(ArrayList bookNames);
   public ArrayList getReleases();
   public void setReleases(ArrayList releases);
   public ArrayList getRecommendations();
   public void setRecommendations(ArrayList recommendations);
}
