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
public class AdminBean implements Serializable 
{
    private String userName;
    private String passWord;
    private boolean validAdmin;
    
    public AdminBean()
    {
        userName = null;
        passWord = null;
        validAdmin = false;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public boolean isValidAdmin() {
        return validAdmin;
    }

    public void setValidAdmin(boolean validAdmin) {
        this.validAdmin = validAdmin;
    }
    
    
}
