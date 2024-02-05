
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
public class control2 implements Serializable{
    private String texto;
    private String nombre;
    private List<contrato> liscon;
    private List<adoptador> lisadop;
    
    @PostConstruct
    public void init(){
        setLisadop((List<adoptador>) new ArrayList());
        setLisadop(new Negocio().lisAdop());
    }
    
    public control2() {
        texto="";
    }
    
    public void consulta(adoptador a){
        setNombre(a.getAdopape()+", "+a.getAdopnom());
        setLiscon(new ArrayList());
        setLiscon(new Negocio().consultaContAdop(a.getAdopid()));
    }
    
    public void filtra(){
        lisadop = new Negocio().filtraAdoptador(texto);
    }

    public List<adoptador> getLisadop() {
        return lisadop;
    }

    public List<contrato> getLiscon() {
        return liscon;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTexto() {
        return texto;
    }

    public void setLisadop(List<adoptador> lisadop) {
        this.lisadop = lisadop;
    }

    public void setLiscon(List<contrato> liscon) {
        this.liscon = liscon;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
    
}
