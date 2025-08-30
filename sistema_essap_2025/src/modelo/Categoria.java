package modelo;

import java.util.Objects;

public class Categoria {
	private Integer idcategoria;
	private String categoria;

	
	
	public Integer getIdcategoria() {
		return idcategoria;
	}
	public void setIdcategoria(Integer idcategoria) {
		this.idcategoria = idcategoria;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	@Override
	public String toString() {
		return  idcategoria + " - " + categoria;
	}
	@Override
	public boolean equals(Object obj) {
		Integer cod1 = getIdcategoria();
        Integer cod2 = ((Categoria)obj).getIdcategoria();
        if(cod1.equals(cod2)) return true;
        return false;
	}
	
	
	
}
