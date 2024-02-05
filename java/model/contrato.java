package model;

import java.sql.*;

public class contrato extends Gato{
    String contid;
    String contfecha;
    String adopnom,adopape,localnom,localdirec;

    public String getContid() {
        return contid;
    }

    public void setContid(String contid) {
        this.contid = contid;
    }

    public String getContfecha() {
        return contfecha;
    }

    public void setContfecha(String contfecha) {
        this.contfecha = contfecha;
    }

    public String getAdopnom() {
        return adopnom;
    }

    public void setAdopnom(String adopnom) {
        this.adopnom = adopnom;
    }

    public String getAdopape() {
        return adopape;
    }

    public void setAdopape(String adopape) {
        this.adopape = adopape;
    }

    public String getLocalnom() {
        return localnom;
    }

    public void setLocalnom(String localnom) {
        this.localnom = localnom;
    }

    public String getLocaldirec() {
        return localdirec;
    }

    public void setLocaldirec(String localdirec) {
        this.localdirec = localdirec;
    }
    
    
}
