package tabla;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import modelo.Medidor;

public class MedidorTablaModel extends AbstractTableModel {
		List<Medidor> lista = new ArrayList<>();

	    private String[] columnas = {"NRO MEDIDOR", "ID PROPIEDAD", "INICIO CICLO","FIN CICLO", "LECTURA INICIAL", "LECTURA CIERRE", "CONSUMO", "DIAS FACTURADOS"};

	    public void setLista(List<Medidor> lista) {
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
	        Medidor medidor = lista.get(fila);
	        switch (columna) {
	            case 0:
	                return medidor.getId();
	            case 1:
	                return medidor.getPropiedad().getId();
	            case 2:
	                return medidor.getFechaInicio();
	            case 3:
	                return medidor.getFechaFin();
	            case 4:
	                return medidor.getLecturaInicio();  
	            case 5:
	                return medidor.getLecturaCierre();
	            case 6:
	                return medidor.getConsumo();
	            case 7:
	                return medidor.getDiasFacturados();
	            default:
	                return null;
	        }
	    }

	    public String getColumnName(int column) {
	        return columnas[column];
	    }

	    public Medidor getClienteByRow(int index) {
	        return lista.get(index);
	    }

	}

