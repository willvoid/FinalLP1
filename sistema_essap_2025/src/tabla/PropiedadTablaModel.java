package tabla;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import modelo.Propiedad;

public class PropiedadTablaModel extends AbstractTableModel {
		List<Propiedad> lista = new ArrayList<>();

	    private String[] columnas = {"ID", "TIPO DE PROPIEDAD", "NIVEL","DIMENSION", "UBICACION", "RUC / CLIENTE"};

	    public void setLista(List<Propiedad> lista) {
	        // Inicializamos las lista de marcas
	    	this.lista = (lista != null) ? lista : new ArrayList<>();
	    }

	    public int getRowCount() {
	        // El tamaño de la lista
	    	return (lista == null) ? 0 : lista.size();
	    }

	    public int getColumnCount() {
	        // El numero de columnass
	        return columnas.length;
	    }

	    public Object getValueAt(int fila, int columna) {
	        Propiedad propiedad = lista.get(fila);
	        switch (columna) {
	            case 0:
	                return propiedad.getId();
	            case 1:
	                return propiedad.getTipoPropiedad();
	            case 2:
	                return propiedad.getNivel();
	            case 3:
	                return propiedad.getDimension();
	            case 4:
	                return propiedad.getUbicacion();  
	            case 5:
	                return propiedad.getCliente().getRuc();
	            default:
	                return null;
	        }
	    }

	    public String getColumnName(int column) {
	        return columnas[column];
	    }

	    public Propiedad getClienteByRow(int index) {
	        return lista.get(index);
	    }

	}

