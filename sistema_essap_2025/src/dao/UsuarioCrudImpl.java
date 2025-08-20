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

import org.mindrot.jbcrypt.BCrypt;

import modelo.Usuario;
import vista.GUILogin;

public class UsuarioCrudImpl implements crud <Usuario>{
	
	Connection conec;
    PreparedStatement sentencia;
    public static Integer idusuario=null;
    public static String usuario_actual=null;
    //lo primero que se ejecuta cuando se crea un objeto
    public UsuarioCrudImpl() {
        Conexion conectar = new Conexion();
        conec = conectar.conectarBD();
    }

	public void insertar(Usuario obj) {
		try {
            String sql = "INSERT INTO usuarios (nombre,apellido,usuario,clave,rol) VALUES (?,?,?,?,?)";
            sentencia = conec.prepareStatement(sql);
            
         // Hashear la contraseña antes de guardarla
            String passwordHash = hashPassword(obj.getContraseña());
            
            //Dar valor a los parametros "?"
            sentencia.setString(1, obj.getNombre());
            sentencia.setString(2, obj.getApellido());
            sentencia.setString(3, obj.getUsuario());
            sentencia.setString(4, passwordHash); //Se guarda la contraseña hasheada
            sentencia.setString(5, obj.getRol());
            
            sentencia.executeUpdate();//Ejecutar Sentencia
        } catch (SQLException e) {
            //Logger.getLogger(UsuarioCrudImpl.class.getName()).log(Level.SEVERE, null, e);
        	System.out.print("Error al crear Usuario: " + e);
        }
	}

	public void actualizar(Usuario obj) {
		try {
			String sql = "UPDATE usuarios SET nombre=?, apellido=?, usuario=?, rol=? WHERE id=?";
			sentencia = conec.prepareStatement(sql);
            //Dar valor a los parametros "?"
            sentencia.setString(1, obj.getNombre());
            sentencia.setString(2, obj.getApellido());
            sentencia.setString(3, obj.getUsuario());
            //sentencia.setString(4, obj.getClave());
            sentencia.setString(4, obj.getRol());
            sentencia.setInt(5, obj.getId());
			
		} catch (SQLException e) {
			System.out.print("Error al crear editar usuario: " + e);
		}
	}

	public void eliminar(Usuario obj) {
		try {
            String sql = "DELETE FROM usuarios WHERE id=?";
            sentencia = conec.prepareStatement(sql);
            //Dar valor a los parametros "?"
            sentencia.setInt(1, obj.getId());
            
            sentencia.executeUpdate();//Ejecutar Sentencia
        } catch (SQLException e) {
        	System.out.print("Error al eliminar usuario: " + e);
        } 
	}
	
	public boolean loginUser(Usuario obj) {
	    boolean respuesta = false;
	    try {
	        // Buscar solo el usuario y recuperar la contraseña hasheada
	        String sql = "SELECT clave FROM usuarios WHERE usuario = ?";
	        sentencia = conec.prepareStatement(sql);
	        sentencia.setString(1, obj.getUsuario());
	        ResultSet rs = sentencia.executeQuery();

	        if (rs.next()) {
	            String hashGuardado = rs.getString("clave");

	            // Verificar la contraseña ingresada contra el hash
	            if (checkPassword(obj.getContraseña(), hashGuardado)) {
	                respuesta = true;
	            }
	        }
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error en login: " + e.getMessage());
	    }
	    return respuesta;
	}

	
	public boolean ComprobarUser(Usuario obj){
        boolean respuesta = false;
        try {
            String sql = "SELECT usuario from usuarios where usuario = ?";
            sentencia = conec.prepareStatement(sql);
            sentencia.setString(1, obj.getUsuario());
            ResultSet rs = sentencia.executeQuery();
            //recorrer una lista
            while (rs.next()){ 
                respuesta = true;
            }
            
        } catch (SQLException e) {
            //Logger.getLogger(UsuarioCrudImpl.class.getName()).log(Level.SEVERE, null, e);
        	System.out.print("Error al comprobar usuario: " + e);
        }
        return respuesta;
    }

	public List<Usuario> listar(String textoBuscado) {
		ArrayList<Usuario> lista = new ArrayList<>();
		
		try {
            String sql = "select * from usuario where nombre ||' '|| apellido ||' '|| usuario ilike ? order by nombre asc";
            sentencia = conec.prepareStatement(sql);
            sentencia.setString(1, "%" + textoBuscado + "%");
            ResultSet rs = sentencia.executeQuery();

            //recorrer una lista
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setRol(rs.getString("rol"));
                lista.add(usuario);
            }

        } catch (SQLException ex) {
        	System.out.print("Error al crear listar usuario: " + ex);
        }
		
		return null;
	}
	
	// Hashear contraseña 
    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    
 // Verificar contraseña
    public boolean checkPassword(String password, String hashed) {
        return BCrypt.checkpw(password, hashed);
    }
	
	
}
