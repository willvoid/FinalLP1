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
import vista.GUILogin;

public class ClienteCrudImpl implements crud <Cliente>{
	
	Connection conec;
    PreparedStatement sentencia;
    //lo primero que se ejecuta cuando se crea un objeto
    public ClienteCrudImpl() {
        Conexion conectar = new Conexion();
        conec = conectar.conectarBD();
    }

	public void insertar(Cliente obj) {
		try {
            String sql = "INSERT INTO clientes (nombre,apellido,ruc,direccion,telefono,email,categoria) VALUES (?,?,?,?,?,?,?)";
            sentencia = conec.prepareStatement(sql);
            //Dar valor a los parametros "?"
            sentencia.setString(1, obj.getNombre());
            sentencia.setString(2, obj.getApellido());
            sentencia.setString(3, obj.getRuc());
            sentencia.setString(4, obj.getDireccion());
            sentencia.setString(5, obj.getTelefono());
            sentencia.setString(6, obj.getEmail());
            sentencia.setString(7, obj.getCategoria());
            
            sentencia.executeUpdate();//Ejecutar Sentencia
        } catch (SQLException e) {
            //Logger.getLogger(UsuarioCrudImpl.class.getName()).log(Level.SEVERE, null, e);
        	System.out.print("Error al crear Cliente: " + e);
        }
	}

	public void actualizar(Cliente obj) {
		try {
			String sql = "UPDATE clientes SET nombre=?, apellido=?, ruc=?, direccion=?, telefono=?, email=?, categoria=? WHERE id=?";
			sentencia = conec.prepareStatement(sql);
            //Dar valor a los parametros "?"
			sentencia.setString(1, obj.getNombre());
            sentencia.setString(2, obj.getApellido());
            sentencia.setString(3, obj.getRuc());
            sentencia.setString(4, obj.getDireccion());
            sentencia.setString(5, obj.getTelefono());
            sentencia.setString(6, obj.getEmail());
            sentencia.setString(7, obj.getCategoria());
            sentencia.setInt(8, obj.getId());
            
            sentencia.executeUpdate();//Ejecutar Sentencia
		} catch (SQLException e) {
			System.out.print("Error al crear editar usuario: " + e);
		}
	}

	public void eliminar(Cliente obj) {
		try {
            String sql = "DELETE FROM clientes WHERE id=?";
            sentencia = conec.prepareStatement(sql);
            //Dar valor a los parametros "?"
            sentencia.setInt(1, obj.getId());
            
            sentencia.executeUpdate();//Ejecutar Sentencia
        } catch (SQLException e) {
        	System.out.print("Error al crear eliminar usuario: " + e);
        } 
	}
	

	public List<Cliente> listar(String textoBuscado) {
		ArrayList<Cliente> lista = new ArrayList<>();
		
		try {
            String sql = "select * from clientes where nombre ||' '|| apellido ||' '|| ruc ilike ? order by apellido asc";
            sentencia = conec.prepareStatement(sql);
            sentencia.setString(1, "%" + textoBuscado + "%");
            ResultSet rs = sentencia.executeQuery();

            //recorrer una lista
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setRuc(rs.getString("ruc"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setEmail(rs.getString("email"));
                cliente.setCategoria(rs.getString("categoria"));
                lista.add(cliente);
            }

        } catch (SQLException ex) {
        	System.out.print("Error al crear listar clientes: " + ex);
        }
		
		return lista;
	}
	
	
}
