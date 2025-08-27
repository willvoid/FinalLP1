package modelo;

import java.util.Date;

public class Lectura {
	private Integer id;
	private Medidor medidor;
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
	public Medidor getMedidor() {
		return medidor;
	}
	public void setMedidor(Medidor medidor) {
		this.medidor = medidor;
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
