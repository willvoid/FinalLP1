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

import modelo.Cliente;
import modelo.Propiedad;
import vista.GUILogin;

public class PropiedadCrudImpl implements crud <Propiedad>{
	
	Connection conec;
    PreparedStatement sentencia;
    //lo primero que se ejecuta cuando se crea un objeto
    public PropiedadCrudImpl() {
        Conexion conectar = new Conexion();
        conec = conectar.conectarBD();
    }

	public void insertar(Propiedad obj) {
		try {
            String sql = "INSERT INTO propiedades (tipopropiedad,nivel,dimension,fkidcliente,ubicacion) VALUES (?,?,?,?,?)";
            sentencia = conec.prepareStatement(sql);
            //Dar valor a los parametros "?"
            sentencia.setString(1, obj.getTipoPropiedad());
            sentencia.setString(2, obj.getNivel());
            sentencia.setString(3, obj.getDimension());
            sentencia.setInt(4, obj.getCliente().getId());
            sentencia.setString(5, obj.getUbicacion());

            sentencia.executeUpdate();//Ejecutar Sentencia
        } catch (SQLException e) {
            //Logger.getLogger(UsuarioCrudImpl.class.getName()).log(Level.SEVERE, null, e);
        	System.out.print("Error al crear Propiedad: " + e);
        }
	}

	public void actualizar(Propiedad obj) {
		try {
			String sql = "UPDATE propiedades SET tipopropiedad=?,nivel=?,dimension=?,fkidcliente=?,ubicacion=? WHERE id=?";
			sentencia = conec.prepareStatement(sql);
            //Dar valor a los parametros "?"
			sentencia.setString(1, obj.getTipoPropiedad());
            sentencia.setString(2, obj.getNivel());
            sentencia.setString(3, obj.getDimension());
            sentencia.setInt(4, obj.getCliente().getId());
            sentencia.setString(5, obj.getUbicacion());
            sentencia.setInt(6, obj.getId());
            
            sentencia.executeUpdate();//Ejecutar Sentencia
		} catch (SQLException e) {
			System.out.print("Error al crear editar usuario: " + e);
		}
	}

	public void eliminar(Propiedad obj) {
		try {
            String sql = "DELETE FROM propiedades WHERE id=?";
            sentencia = conec.prepareStatement(sql);
            //Dar valor a los parametros "?"
            sentencia.setInt(1, obj.getId());
            
            sentencia.executeUpdate();//Ejecutar Sentencia
        } catch (SQLException e) {
        	System.out.print("Error al eliminar propiedad: " + e);
        } 
	}
	

	public List<Propiedad> listar(String textoBuscado) {
		ArrayList<Propiedad> lista = new ArrayList<>();
		
		try {
            String sql = "select p.*, c.ruc as ruc from propiedades p "
            		+ "inner join clientes c on c.id=p.fkidcliente "
            		+ "where dimension ||' '|| tipopropiedad ||' '|| c.ruc ilike ? order by id asc";
            sentencia = conec.prepareStatement(sql);
            sentencia.setString(1, "%" + textoBuscado + "%");
            ResultSet rs = sentencia.executeQuery();

            //recorrer una lista
            while (rs.next()) {
                Propiedad propiedad = new Propiedad();
                Cliente cliente = new Cliente();
                
                propiedad.setId(rs.getInt("id"));
                propiedad.setTipoPropiedad(rs.getString("tipopropiedad"));
                propiedad.setNivel(rs.getString("nivel"));
                propiedad.setDimension(rs.getString("dimension"));
                propiedad.setUbicacion(rs.getString("ubicacion"));
                
                //Dar valor a variable cliente
                cliente.setId(rs.getInt("fkidcliente"));
                cliente.setRuc(rs.getString("ruc"));
                propiedad.setCliente(cliente);
                
                lista.add(propiedad);
            }

        } catch (SQLException ex) {
        	System.out.print("Error al crear listar propiedad: " + ex);
        }
		
		return lista;
	}
	
	
}
