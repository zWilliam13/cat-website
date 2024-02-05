package model;

import java.sql.*;


public class registro extends vacuna{ 
    String regid;
    String regfecha;

    public String getRegid() {
        return regid;
    }
    public void setRegid(String regid) {
        this.regid = regid;
    }

    public String getRegfecha() {
        return regfecha;
    }

    public void setRegfecha(String regfecha) {
        this.regfecha = regfecha;
    }
    
    
}
