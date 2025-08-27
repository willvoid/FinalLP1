package tabla;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import modelo.Tarifa;

public class TarifaTablaModel extends AbstractTableModel {
		List<Tarifa> lista = new ArrayList<>();

	    private String[] columnas = {"ID", "RANGO MIN", "RANGO MAX","PRECIO M3", "CARGO FIJO"};

	    public void setLista(List<Tarifa> lista) {
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
	        Tarifa tarifa = lista.get(fila);
	        switch (columna) {
	            case 0:
	                return tarifa.getId();
	            case 1:
	                return tarifa.getRango_min();
	            case 2:
	                return tarifa.getRango_max();
	            case 3:
	                return tarifa.getPrecio_m3();
	            case 4:
	                return tarifa.getCargo_fijo();  
	            default:
	                return null;
	        }
	    }

	    public String getColumnName(int column) {
	        return columnas[column];
	    }

	    public Tarifa getTarifaByRow(int index) {
	        return lista.get(index);
	    }

	}

