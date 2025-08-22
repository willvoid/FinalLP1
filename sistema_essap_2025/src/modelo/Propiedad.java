package modelo;

public class Propiedad {
	private Integer id;
	private String tipoPropiedad;
	private String nivel;
	private String dimension;
	private String ubicacion;
	private Cliente cliente;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTipoPropiedad() {
		return tipoPropiedad;
	}
	public void setTipoPropiedad(String tipoPropiedad) {
		this.tipoPropiedad = tipoPropiedad;
	}
	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	public String getDimension() {
		return dimension;
	}
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	@Override
	public String toString() {
		return id + " - " + tipoPropiedad + " - " + cliente.getRuc();
	}
	
	public boolean equals(Object obj) {
        Integer cod1 = getId();
        Integer cod2 = ((Propiedad)obj).getId();
        if(cod1.equals(cod2)) return true;
        return false;
    }
	
}
