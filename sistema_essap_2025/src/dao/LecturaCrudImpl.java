package dao;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import modelo.Medidor;
import modelo.Cliente;
import modelo.Lectura;
import vista.GUILogin;

public class LecturaCrudImpl implements crud <Lectura>{
	
	Connection conec;
    PreparedStatement sentencia;
    //lo primero que se ejecuta cuando se crea un objeto
    public LecturaCrudImpl() {
        Conexion conectar = new Conexion();
        conec = conectar.conectarBD();
    }

	public void insertar(Lectura obj) {
		try {
            String sql = "INSERT INTO lecturas (fkidmedidor,fecha_inicio,fecha_fin,lectura_inicio,lectura_cierre,consumo,dias_facturados) VALUES (?,?,?,?,?,?,?)";
            sentencia = conec.prepareStatement(sql);
            //Dar valor a los parametros "?"
            sentencia.setInt(1, obj.getMedidor().getId());
            sentencia.setDate(2, dateToSqlDate(obj.getFechaInicio()));
            sentencia.setDate(3, dateToSqlDate(obj.getFechaFin()));
            sentencia.setInt(4, obj.getLecturaInicio());
            sentencia.setInt(5, obj.getLecturaCierre());
            sentencia.setInt(6, obj.getConsumo());
            sentencia.setInt(7, obj.getDiasFacturados());

            sentencia.executeUpdate();//Ejecutar Sentencia
        } catch (SQLException e) {
            //Logger.getLogger(UsuarioCrudImpl.class.getName()).log(Level.SEVERE, null, e);
        	System.out.print("Error al crear Medidor: " + e);
        }
	}

	public void actualizar(Lectura obj) {
		try {
			String sql = "UPDATE lecturas SET fkidmedidor=?,fecha_inicio=?,fecha_fin=?,lectura_inicio=?,lectura_cierre=?,consumo=?,dias_facturados=? WHERE id=?";
			sentencia = conec.prepareStatement(sql);
            //Dar valor a los parametros "?"
			sentencia.setInt(1, obj.getMedidor().getId());
            sentencia.setDate(2, dateToSqlDate(obj.getFechaInicio()));
            sentencia.setDate(3, dateToSqlDate(obj.getFechaFin()));
            sentencia.setInt(4, obj.getLecturaInicio());
            sentencia.setInt(5, obj.getLecturaCierre());
            sentencia.setInt(6, obj.getConsumo());
            sentencia.setInt(7, obj.getDiasFacturados());
            sentencia.setInt(8, obj.getId());
            
            sentencia.executeUpdate();//Ejecutar Sentencia
		} catch (SQLException e) {
			System.out.print("Error al editar Medidor " + e);
		}
	}

	public void eliminar(Lectura obj) {
		try {
            String sql = "DELETE FROM lecturas WHERE id=?";
            sentencia = conec.prepareStatement(sql);
            //Dar valor a los parametros "?"
            sentencia.setInt(1, obj.getId());
            
            sentencia.executeUpdate();//Ejecutar Sentencia
        } catch (SQLException e) {
        	System.out.print("Error al eliminar medidor: " + e);
        } 
	}
	

	public List<Lectura> listar(String textoBuscado) {
		ArrayList<Lectura> lista = new ArrayList<>();
		
		try {
            String sql = "select l.*, m.id as id_medidor, c.ruc as ruc from lecturas l "
            		+ "inner join medidores m on m.id=l.fkidmedidor "
            		+ "where m.id ||' '|| l.fecha_fin ilike ? order by l.id asc";
            sentencia = conec.prepareStatement(sql);
            sentencia.setString(1, "%" + textoBuscado + "%");
            ResultSet rs = sentencia.executeQuery();

            //recorrer una lista
            while (rs.next()) {
                Lectura lectura = new Lectura();
                Medidor medidor = new Medidor();
                Cliente cliente = new Cliente();
                
                lectura.setId(rs.getInt("id"));
                lectura.setFechaInicio(rs.getDate("fecha_inicio"));
                lectura.setFechaFin(rs.getDate("fecha_fin"));
                lectura.setLecturaInicio(rs.getInt("lectura_inicio"));
                lectura.setLecturaCierre(rs.getInt("lectura_cierre"));
                lectura.setDiasFacturados(rs.getInt("dias_facturados"));
                lectura.setConsumo(rs.getInt("consumo"));
                
                
                //Dar valor a variable medidor
                medidor.setId(rs.getInt("id_medidor"));
                lectura.setMedidor(medidor);
                
                
                lista.add(lectura);
            }

        } catch (SQLException ex) {
        	System.out.print("Error al listar medidor: " + ex);
        }
		
		return lista;
	}
	private java.sql.Date dateToSqlDate(java.util.Date date) {
		// Convertir java.util.Date a java.sql.Date
	    java.util.Date fechaVenta = date; // Obtener fecha como java.util.Date
	    java.sql.Date fechaSQL = new java.sql.Date(fechaVenta.getTime()); // Convertir a java.sql.Date
	    return fechaSQL;
	}
	
	
	
}
