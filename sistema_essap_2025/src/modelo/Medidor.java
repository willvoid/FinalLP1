package modelo;


public class Medidor {
	private Integer id;
	private Propiedad propiedad;
	
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
	@Override
	public String toString() {
		return "Número de Medidor=" + id;
	}
	
	@Override
	public boolean equals(Object obj) {
		Integer cod1 = getId();
        Integer cod2 = ((Medidor)obj).getId();
        if(cod1.equals(cod2)) return true;
        return false;
	}
	
	
}
