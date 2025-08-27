package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import modelo.Propiedad;
import modelo.Cliente;
import modelo.Tarifa;

public class TarifaCrudImpl implements crud <Tarifa>{
	
	Connection conec;
    PreparedStatement sentencia;
    //lo primero que se ejecuta cuando se crea un objeto
    public TarifaCrudImpl() {
        Conexion conectar = new Conexion();
        conec = conectar.conectarBD();
    }

	public void insertar(Tarifa obj) {
		try {
            String sql = "INSERT INTO tarifas (rango_min,rango_max,precio_m3,cargo_fijo) VALUES (?,?,?,?)";
            sentencia = conec.prepareStatement(sql);
            //Dar valor a los parametros "?"
            sentencia.setInt(1, obj.getRango_min());
            sentencia.setInt(2, obj.getRango_max());
            sentencia.setInt(3, obj.getPrecio_m3());
            sentencia.setInt(4, obj.getCargo_fijo());

            sentencia.executeUpdate();//Ejecutar Sentencia
        } catch (SQLException e) {
            //Logger.getLogger(UsuarioCrudImpl.class.getName()).log(Level.SEVERE, null, e);
        	System.out.print("Error al crear tarifa: " + e);
        }
	}

	public void actualizar(Tarifa obj) {
		try {
			String sql = "UPDATE tarifas SET rango_min=?,rango_max=?,precio_m3=?,cargo_fijo=? WHERE id=?";
			sentencia = conec.prepareStatement(sql);
            //Dar valor a los parametros "?"
			sentencia.setInt(1, obj.getRango_min());
            sentencia.setInt(2, obj.getRango_max());
            sentencia.setInt(3, obj.getPrecio_m3());
            sentencia.setInt(4, obj.getCargo_fijo());
            sentencia.setInt(5, obj.getId());
            
            sentencia.executeUpdate();//Ejecutar Sentencia
		} catch (SQLException e) {
			System.out.print("Error al editar tarifa " + e);
		}
	}

	public void eliminar(Tarifa obj) {
		try {
            String sql = "DELETE FROM tarifas WHERE id=?";
            sentencia = conec.prepareStatement(sql);
            //Dar valor a los parametros "?"
            sentencia.setInt(1, obj.getId());
            
            sentencia.executeUpdate();//Ejecutar Sentencia
        } catch (SQLException e) {
        	System.out.print("Error al eliminar tarifa: " + e);
        } 
	}
	

	public List<Tarifa> listar(String textoBuscado) {
		ArrayList<Tarifa> lista = new ArrayList<>();
		
		try {
            String sql = "select * from tarifas where rango_min ||' '|| rango_max ilike ? order by id asc";
            sentencia = conec.prepareStatement(sql);
            sentencia.setString(1, "%" + textoBuscado + "%");
            ResultSet rs = sentencia.executeQuery();

            //recorrer una lista
            while (rs.next()) {
                Tarifa tarifa = new Tarifa();
                
                tarifa.setId(rs.getInt("id"));
                tarifa.setRango_min(rs.getInt("rango_min"));
                tarifa.setRango_max(rs.getInt("rango_max"));
                tarifa.setPrecio_m3(rs.getInt("precio_m3"));
                tarifa.setCargo_fijo(rs.getInt("cargo_fijo"));
                
                lista.add(tarifa);
                System.out.println("Listado correctamente");
            }

        } catch (SQLException ex) {
        	System.out.print("Error al listar tarifa: " + ex);
        }
		
		return lista;
	}
	
	
	
}
