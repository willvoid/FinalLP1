package modelo;

import java.util.Objects;

public class Cliente {
	private Integer id;
	private String nombre;
	private String apellido;
	private String ruc;
	private String direccion;
	private String telefono;
	private String email;
	private String categoria;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getRuc() {
		return ruc;
	}
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	@Override
	public String toString() {
		return  id + " - " + nombre + " " + apellido + "-" + ruc;
	}
	@Override
	public boolean equals(Object obj) {
		Integer cod1 = getId();
        Integer cod2 = ((Cliente)obj).getId();
        if(cod1.equals(cod2)) return true;
        return false;
	}
	
	
	
}
