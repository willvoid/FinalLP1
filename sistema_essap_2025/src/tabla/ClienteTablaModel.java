package tabla;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import modelo.Cliente;

public class ClienteTablaModel extends AbstractTableModel {
		List<Cliente> lista = new ArrayList<>();

	    private String[] columnas = {"ID", "CI / RUC", "NOMBRE","APELLIDO", "TELEFONO", "DIRECCION","CORREO","CATEGORIA"};

	    public void setLista(List<Cliente> lista) {
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
	        Cliente cliente = lista.get(fila);
	        switch (columna) {
	            case 0:
	                return cliente.getId();
	            case 1:
	                return cliente.getRuc();
	            case 2:
	                return cliente.getNombre();
	            case 3:
	                return cliente.getApellido();
	            case 4:
	                return cliente.getTelefono();  
	            case 5:
	                return cliente.getDireccion();
	            case 6:
	                return cliente.getEmail();
	            case 7:
	                return cliente.getCategoria();
	            default:
	                return null;
	        }
	    }

	    public String getColumnName(int column) {
	        return columnas[column];
	    }

	    public Cliente getClienteByRow(int index) {
	        return lista.get(index);
	    }

	}

