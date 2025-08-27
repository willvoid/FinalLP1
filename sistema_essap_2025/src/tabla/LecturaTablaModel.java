package tabla;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import modelo.Lectura;

public class LecturaTablaModel extends AbstractTableModel {
		List<Lectura> lista = new ArrayList<>();

	    private String[] columnas = {"ID LECTURA", "ID MEDIDOR","INICIO CICLO","FIN CICLO", "LECTURA INICIAL", "LECTURA CIERRE", "CONSUMO", "DIAS FACTURADOS"};

	    public void setLista(List<Lectura> lista) {
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
	        Lectura lectura = lista.get(fila);
	        switch (columna) {
	            case 0:
	                return lectura.getId();
	            case 1:
	                return lectura.getMedidor().getId();
	            case 2:
	                return lectura.getFechaInicio();
	            case 3:
	                return lectura.getFechaFin();
	            case 4:
	                return lectura.getLecturaInicio();  
	            case 5:
	                return lectura.getLecturaCierre();
	            case 6:
	                return lectura.getConsumo();
	            case 7:
	                return lectura.getDiasFacturados();
	            default:
	                return null;
	        }
	    }

	    public String getColumnName(int column) {
	        return columnas[column];
	    }

	    public Lectura getLecturaByRow(int index) {
	        return lista.get(index);
	    }

	}

