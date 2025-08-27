package modelo;

public class Tarifa {
	private Integer id;
	private Integer rango_min;
	private Integer rango_max;
	private Integer precio_m3;
	private Integer cargo_fijo;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRango_min() {
		return rango_min;
	}
	public void setRango_min(Integer rango_min) {
		this.rango_min = rango_min;
	}
	public Integer getRango_max() {
		return rango_max;
	}
	public void setRango_max(Integer rango_max) {
		this.rango_max = rango_max;
	}
	public Integer getPrecio_m3() {
		return precio_m3;
	}
	public void setPrecio_m3(Integer precio_m3) {
		this.precio_m3 = precio_m3;
	}
	public Integer getCargo_fijo() {
		return cargo_fijo;
	}
	public void setCargo_fijo(Integer cargo_fijo) {
		this.cargo_fijo = cargo_fijo;
	}
	
	
	
}
