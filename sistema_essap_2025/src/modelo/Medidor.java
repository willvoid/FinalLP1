package modelo;

import java.util.Date;

public class Medidor {
	private Integer id;
	private Propiedad propiedad;
	private Date fechaInicio;
	private Date fechaFin;
	private Integer lecturaInicio;
	private Integer lecturaCierre;
	private Integer consumo;
	private Integer diasFacturados;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Propiedad getPropiedad() {
		return propiedad;
	}
	public void setPropiedad(Propiedad propiedad) {
		this.propiedad = propiedad;
	}
	
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public Integer getLecturaInicio() {
		return lecturaInicio;
	}
	public void setLecturaInicio(Integer lecturaInicio) {
		this.lecturaInicio = lecturaInicio;
	}
	public Integer getLecturaCierre() {
		return lecturaCierre;
	}
	public void setLecturaCierre(Integer lecturaCierre) {
		this.lecturaCierre = lecturaCierre;
	}
	public Integer getConsumo() {
		return consumo;
	}
	public void setConsumo(Integer consumo) {
		this.consumo = consumo;
	}
	public Integer getDiasFacturados() {
		return diasFacturados;
	}
	public void setDiasFacturados(Integer diasFacturados) {
		this.diasFacturados = diasFacturados;
	}
	
	
	
}
