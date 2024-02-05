package controlador;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;;
import java.util.*;
import model.*;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import dao.Negocio;

@ManagedBean
@ViewScoped
public class control1 implements Serializable{
    
    private String gatonom;
    private List<Gato> lisgato;
    private List<registro> lisreg;
    private String texto;
    
    @PostConstruct
    public void init(){
        setLisgato((List<Gato>) new ArrayList());
        setLisgato(new Negocio().lisGat());
    }
    
    public void filtra(){
        setLisgato(new Negocio().filtraGato(getTexto()));
    }
    
    public void consulta(Gato g){
        gatonom = g.getGatnom();
        lisreg = new ArrayList();
        lisreg = new Negocio().lisReg(g.getGatid());
    }
    
    public control1() {
        texto="";
    }

    public String getGatonom() {
        return gatonom;
    }

    public List<Gato> getLisgato() {
        return lisgato;
    }

    public List<registro> getLisreg() {
        return lisreg;
    }

    public String getTexto() {
        return texto;
    }

    public void setGatonom(String gatonom) {
        this.gatonom = gatonom;
    }

    public void setLisgato(List<Gato> lisgato) {
        this.lisgato = lisgato;
    }

    public void setLisreg(List<registro> lisreg) {
        this.lisreg = lisreg;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
    
}
