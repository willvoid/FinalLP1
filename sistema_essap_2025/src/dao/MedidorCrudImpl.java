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
import modelo.Propiedad;
import modelo.Cliente;
import modelo.Medidor;
import vista.GUILogin;

public class MedidorCrudImpl implements crud <Medidor>{
	
	Connection conec;
    PreparedStatement sentencia;
    //lo primero que se ejecuta cuando se crea un objeto
    public MedidorCrudImpl() {
        Conexion conectar = new Conexion();
        conec = conectar.conectarBD();
    }

	public void insertar(Medidor obj) {
		try {
            String sql = "INSERT INTO medidores (fkidpropiedades) VALUES (?)";
            sentencia = conec.prepareStatement(sql);
            //Dar valor a los parametros "?"
            sentencia.setInt(1, obj.getPropiedad().getId());
            

            sentencia.executeUpdate();//Ejecutar Sentencia
        } catch (SQLException e) {
            //Logger.getLogger(UsuarioCrudImpl.class.getName()).log(Level.SEVERE, null, e);
        	System.out.print("Error al crear Medidor: " + e);
        }
	}

	public void actualizar(Medidor obj) {
		try {
			String sql = "UPDATE medidores SET fkidpropiedades=? WHERE id=?";
			sentencia = conec.prepareStatement(sql);
            //Dar valor a los parametros "?"
			sentencia.setInt(1, obj.getPropiedad().getId());
            sentencia.setInt(2, obj.getId());
            
            sentencia.executeUpdate();//Ejecutar Sentencia
		} catch (SQLException e) {
			System.out.print("Error al editar Medidor " + e);
		}
	}

	public void eliminar(Medidor obj) {
		try {
            String sql = "DELETE FROM medidores WHERE id=?";
            sentencia = conec.prepareStatement(sql);
            //Dar valor a los parametros "?"
            sentencia.setInt(1, obj.getId());
            
            sentencia.executeUpdate();//Ejecutar Sentencia
        } catch (SQLException e) {
        	System.out.print("Error al eliminar medidor: " + e);
        } 
	}
	

	public List<Medidor> listar(String textoBuscado) {
		ArrayList<Medidor> lista = new ArrayList<>();
		
		try {
            String sql = "select m.*, p.tipopropiedad as tipo, c.ruc as ruc from medidores m "
            		+ "inner join propiedades p on m.fkidpropiedades = p.id "
            		+ "inner join clientes c on c.id=p.fkidcliente "
            		+ "where m.id ||' '|| c.ruc ||' '|| p.tipopropiedad ilike ?";
            sentencia = conec.prepareStatement(sql);
            sentencia.setString(1, "%" + textoBuscado + "%");
            ResultSet rs = sentencia.executeQuery();

            //recorrer una lista
            while (rs.next()) {
                Medidor medidor = new Medidor();
                Propiedad propiedad = new Propiedad();
                Cliente cliente = new Cliente();
                
                medidor.setId(rs.getInt("id"));
                
                
                //Dar valor a variable propiedad
                propiedad.setId(rs.getInt("fkidpropiedades"));
                propiedad.setTipoPropiedad(rs.getString("tipo"));
                
                
                
                cliente.setRuc(rs.getString("ruc"));
                propiedad.setCliente(cliente);
                medidor.setPropiedad(propiedad);
                
                
                lista.add(medidor);
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
