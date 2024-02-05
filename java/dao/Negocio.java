package dao;

import conexion.MySQLConexion;
import model.*;
import java.util.*;
import java.sql.*;

public class Negocio {
    public List<raza> lisRaza(){
        List<raza> lis = new ArrayList<>();
        Connection conn = null;
        try {
            conn = MySQLConexion.getConexion();
            String sql = "SELECT * FROM raza";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {                
                raza r = new raza();
                r.setRazaid(rs.getString(1));
                r.setRazanom(rs.getString(2));
                lis.add(r);
            }
        } catch (Exception e) {
        }
        return lis;
    }
    public List<aspecto> lisAsp(){
        List<aspecto> lis = new ArrayList<>();
        Connection conn = null;
        try {
            conn = MySQLConexion.getConexion();
            String sql  = "Select * from aspecto";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {                
                aspecto a = new aspecto();
                a.setAspid(rs.getString(1));
                a.setAspnom(rs.getString(2));
                lis.add(a);
            }
        } catch (Exception e) {
        }
        return lis;
    }
    public List<Gato> lisGat(){
        Connection cn = MySQLConexion.getConexion();
        List<Gato> lista = new ArrayList();
        try {
            String sql = "call spGato()";
            CallableStatement st = cn.prepareCall(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Gato g = new Gato();
                g.setGatid(rs.getString(1));
                g.setGatnom(rs.getString(2));
                g.setRazanom(rs.getString(3));
                g.setAspnom(rs.getString(4));
                lista.add(g);
            }
        } catch (Exception e) {
        }
        return lista;
    }
    public List<contrato> lisCon(){
        List<contrato> lis = new ArrayList<>();
        Connection conn = null;
        try {
            conn = MySQLConexion.getConexion();
            String sql = "SELECT C.cont_id, A.adop_nombre, A.adop_apellido, "
                    + "G.gat_nom, L.local_nombre, C.cont_fecha\n" +
                        "FROM contrato C\n" +
                        "INNER JOIN gato G\n" +
                        "ON C.gat_id = G.gat_id\n" +
                        "INNER JOIN adoptador A\n" +
                        "ON C.adop_id = A.adop_id\n" +
                        "INNER JOIN local L\n" +
                        "ON C.local_id = L.local_id";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
               contrato c = new contrato();
                c.setContid(rs.getString(1));
                c.setAdopnom(rs.getString(2));
                c.setAdopape(rs.getString(3));
                c.setGatnom(rs.getString(4));
                c.setLocalnom(rs.getString(5));
                c.setContfecha(rs.getString(6));
                lis.add(c);
            }
        } catch (Exception e) {
        }
        return lis;
    }
    public List<adoptador> lisAdop(){
        List<adoptador> lis = new ArrayList<>();
        Connection conn = null;
        try {
            conn = MySQLConexion.getConexion();
            String sql = "SELECT * FROM adoptador";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {                
                adoptador a = new adoptador();
                a.setAdopid(rs.getString(1));
                a.setAdopdni(rs.getString(2));
                a.setAdopnom(rs.getString(3));
                a.setAdopape(rs.getString(4));
                lis.add(a);
            }
        } catch (Exception e) {
        }
        return lis;
    }
    public List<local> lisLocal(){
        List<local> lis = new ArrayList<>();
        Connection conn = null;
        try {
            conn = MySQLConexion.getConexion();
            String sql = "SELECT * FROM local";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {                
                local l = new local();
                l.setLocalid(rs.getInt(1));
                l.setLocalnom(rs.getString(2));
                l.setLocaldirec(rs.getString(3));
                lis.add(l);
            }
        } catch (Exception e) {
        }
        return lis;
    }
    public List<registro> lisReg(String id){
        Connection cn = MySQLConexion.getConexion();
        List<registro> lista = new ArrayList();
        try {
            String sql ="call spReg(?)";
            CallableStatement st = cn.prepareCall(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                registro r = new registro();
                r.setRegid(rs.getString(1));
                r.setVacnom(rs.getString(2));
                r.setRegfecha(rs.getString(3));
                lista.add(r);
            }
        } catch (Exception e) {
        }
        return lista;
    }
    public List<contrato> lisCont(String id){
        Connection cn = MySQLConexion.getConexion();
        List<contrato> lista = new ArrayList();
        try {
            String sql ="call spContrato(?)";
            CallableStatement st = cn.prepareCall(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                contrato c = new contrato();
                c.setContid(rs.getString(1));
                c.setAdopnom(rs.getString(2));
                c.setAdopape(rs.getString(3));
                c.setLocalnom(rs.getString(4));
                c.setLocaldirec(rs.getString(5));
                c.setContfecha(rs.getString(6));
                lista.add(c);
            }
        } catch (Exception e) {
        }
        return lista;
    }
    public List<Gato> filtraGato(String nom) {
        List<Gato> lis = new ArrayList<>();
        Connection conn = null;
        try {
            conn = MySQLConexion.getConexion();
            String sql = "SELECT G.gat_id, G.gat_nom, R.raza, A.aspecto\n" +
                        "FROM gato G \n" +
                        "INNER JOIN raza R ON G.raza_id = R.raza_id\n" +
                        "INNER JOIN aspecto A ON G.asp_id = A.asp_id\n" +
                        "WHERE G.gat_nom LIKE ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, "%"+nom+"%");
            ResultSet rs = st.executeQuery();
            
            while (rs.next()) {
               Gato g = new Gato();
                g.setGatid(rs.getString(1));
                g.setGatnom(rs.getString(2));
                g.setRazanom(rs.getString(3));
                g.setAspnom(rs.getString(4));
                lis.add(g);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e2) {
            }
        }
        return lis;
    }
    public List<adoptador> filtraAdoptador(String nom) {
        List<adoptador> lis = new ArrayList<>();
        Connection conn = null;
        try {
            conn = MySQLConexion.getConexion();
            String sql ="SELECT adop_id,adop_dni,adop_nombre,adop_apellido\n" +
                        "FROM adoptador\n" +
                        "WHERE adop_nombre LIKE ?\n" +
                        "OR adop_apellido LIKE ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, "%"+nom+"%");
            st.setString(2, "%"+nom+"%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
               adoptador a = new adoptador();
               a.setAdopid(rs.getString(1));
               a.setAdopdni(rs.getString(2));
               a.setAdopnom(rs.getString(3));
               a.setAdopape(rs.getString(4));
               lis.add(a);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e2) {
            }
        }
        return lis;
    }
    
    public List<contrato> consultaContAdop(String id){
        List<contrato> lis = new ArrayList<>();
        Connection conn = null;
        try {
            conn = MySQLConexion.getConexion();
            String sql= "SELECT c.cont_id, G.gat_nom, L.local_nombre, L.local_direc, c.cont_fecha\n" +
                        "FROM contrato C\n" +
                        "INNER JOIN Gato G\n" +
                        "ON g.gat_id = c.gat_id\n" +
                        "INNER JOIN Local L\n" +
                        "ON L.local_id = C.local_id\n" +
                        "WHERE c.adop_id LIKE ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, "%"+id+"%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                contrato c = new contrato();
                c.setContid(rs.getString(1));
                c.setGatnom(rs.getString(2));
                c.setLocalnom(rs.getString(3));
                c.setLocaldirec(rs.getString(4));
                c.setContfecha(rs.getString(5));
                lis.add(c);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lis;
    }
    
    
    public List<contrato> filtraContrato(String id) {
        List<contrato> lis = new ArrayList<>();
        Connection conn = null;
        try {
            conn = MySQLConexion.getConexion();
            String sql = "SELECT C.cont_id, A.adop_nombre, A.adop_apellido, "
                    + "G.gat_nom, L.local_nombre, C.cont_fecha\n" +
                        "FROM contrato C\n" +
                        "INNER JOIN gato G\n" +
                        "ON C.gat_id = G.gat_id\n" +
                        "INNER JOIN adoptador A\n" +
                        "ON C.adop_id = A.adop_id\n" +
                        "INNER JOIN local L\n" +
                        "ON C.local_id = L.local_id\n" +
                        "WHERE A.adop_nombre LIKE ?\n" +
                        "OR A.adop_apellido LIKE ?\n" +
                        "OR G.gat_nom LIKE ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, "%"+id+"%");
            st.setString(2, "%"+id+"%");
            st.setString(3, "%"+id+"%");
            ResultSet rs = st.executeQuery();
            
            while (rs.next()) {
               contrato c = new contrato();
                c.setContid(rs.getString(1));
                c.setAdopnom(rs.getString(2));
                c.setAdopape(rs.getString(3));
                c.setGatnom(rs.getString(4));
                c.setLocalnom(rs.getString(5));
                c.setContfecha(rs.getString(6));
                lis.add(c);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e2) {
            }
        }
        return lis;
    }
    public void InsertarGato(String nom, String raz, String asp){
        Connection cn = null;
        CallableStatement st = null;
        
        try {
            cn = MySQLConexion.getConexion();
            String sql ="call InsertarGato(?,?,?)";
            st = cn.prepareCall(sql);
            st.setString(1, nom);
            st.setString(2, raz);
            st.setString(3, asp);
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void InsertarAdoptador(String dni, String nom, String ape) {
    Connection cn = null;
    CallableStatement st = null;
        try {
            cn = MySQLConexion.getConexion();
            String sql = "call InsertarAdoptador(?,?,?)";
            st = cn.prepareCall(sql);
            st.setString(1, dni);
            st.setString(2, nom);
            st.setString(3, ape);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void InsertarLocal(String nom, String dir){
        Connection cn = null;
        PreparedStatement st = null;
        try {
            cn = MySQLConexion.getConexion();
            String sql = "INSERT INTO local (local_id, local_nombre, local_direc) "
                    + "VALUES (NULL, ?, ?)";
            st = cn.prepareStatement(sql);
            st.setString(1, nom);
            st.setString(2, dir);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }
    public void ModGato (String id, String nom, String raz, String asp){
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = MySQLConexion.getConexion();
            String sql = "UPDATE gato\n" +
                        "SET gat_nom = ?,\n" +
                        "    raza_id = ?,\n" +
                        "    asp_id = ?\n" +
                        "WHERE gat_id = ?;";
            st = conn.prepareStatement(sql);
            st.setString(1, nom);
            st.setString(2, raz);
            st.setString(3, asp);
            st.setString(4, id);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }
    public void ModAdop(String id, String dni, String nom, String ape){
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = MySQLConexion.getConexion();
            String sql = "UPDATE adoptador\n" +
                        "SET adop_dni = ?,\n" +
                        "    adop_nombre = ?,\n" +
                        "    adop_apellido = ?\n" +
                        "WHERE adop_id = ?;";
            st = conn.prepareStatement(sql);
            st.setString(1, dni);
            st.setString(2, nom);
            st.setString(3, ape);
            st.setString(4, id);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }
    public void ModLocal(int id, String nom, String dir){
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = MySQLConexion.getConexion();
            String sql = "UPDATE local\n" +
                        "SET local_nombre = ?,\n" +
                        "    local_direc = ?,\n" +
                        "WHERE local_id = ?;";
            st = conn.prepareStatement(sql);
            st.setString(1, nom);
            st.setString(2, dir);
            st.setInt(3, id);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }
    public void delGato(String id){
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = MySQLConexion.getConexion();
            String sql ="DELETE FROM gato\n" +
                        "WHERE gat_id = ?";
            st = conn.prepareStatement(sql);
            st.setString(1, id);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }
    public void delAdop(String id){
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = MySQLConexion.getConexion();
            String sql ="DELETE FROM adoptador\n" +
                        "WHERE adop_id = ?";
            st = conn.prepareStatement(sql);
            st.setString(1, id);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }
    public void delCont(String id){
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = MySQLConexion.getConexion();
            String sql ="DELETE FROM contrato\n" +
                        "WHERE cont_id = ?";
            st = conn.prepareStatement(sql);
            st.setString(1, id);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }
    public void delLocal(String id){
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = MySQLConexion.getConexion();
            String sql ="DELETE FROM local\n" +
                        "WHERE local_id = ?";
            st = conn.prepareStatement(sql);
            st.setString(1, id);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }
    public List<Gato> grafGato(){
        List<Gato> lis = new ArrayList<>();
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = MySQLConexion.getConexion();
            String sql = "SELECT R.raza, COUNT(G.raza_id) AS cantidad\n" +
                        "FROM gato G\n" +
                        "INNER JOIN raza R ON G.raza_id = R.raza_id\n" +
                        "GROUP BY R.raza;";
            st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {                
                Gato g = new Gato();
                g.setRazanom(rs.getString(1));
                g.setCantidad(rs.getInt(2));
                lis.add(g);
            }
        } catch (Exception e) {
        }
        return lis;
    }
}
