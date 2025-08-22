package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import dao.ClienteCrudImpl;
import dao.PropiedadCrudImpl;
import modelo.Cliente;
import modelo.Propiedad;
import tabla.PropiedadTablaModel;
import vista.GUIPropiedad;

public class PropiedadController implements ActionListener, KeyListener {
	
	private GUIPropiedad gui;
    private PropiedadCrudImpl crud;
    
    private ClienteCrudImpl crudCliente = new ClienteCrudImpl();

    private char operacion;
    Propiedad cliente = new Propiedad();

    PropiedadTablaModel modelo = new PropiedadTablaModel();
    
    public PropiedadController(GUIPropiedad gui, PropiedadCrudImpl crud) {
        this.gui = gui;
        this.crud = crud;
        this.gui.btn_guardar.addActionListener(this);
        this.gui.btn_cancelar.addActionListener(this);
        this.gui.btn_nuevo.addActionListener(this);
        this.gui.btn_editar.addActionListener(this);
        this.gui.btn_eliminar.addActionListener(this);
        this.gui.txt_buscar.addKeyListener(this);
        //AutoCompleteDecorator.decorate(guiv.cbo_cliente);

        gui.table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JTable tabla = (JTable) e.getSource();
                int row = tabla.rowAtPoint(e.getPoint());
                PropiedadTablaModel model = (PropiedadTablaModel) tabla.getModel();
                //Devolver el objeto seleccionado en la fila

                setClienteForm(model.getClienteByRow(row));
            }
        });

        habilitarCampos(false);
        habilitarBoton(false);
        llenarComboCliente(gui.cbo_propietario);
        listar("");
    }
    
    public void mostrarVentana() {
        gui.setLocationRelativeTo(gui);
        gui.setVisible(true);
    }

    public void listar(String valorBuscado) {
        List<Propiedad> lista = crud.listar(valorBuscado);
        modelo.setLista(lista);
        gui.table.setModel(modelo);
        gui.table.updateUI();
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Evento click");

        if (e.getSource() == gui.txt_buscar) {
            String valor = gui.txt_buscar.getText();
            listar(valor);
        }
        if (e.getSource() == gui.btn_nuevo) {
            operacion = 'N';
            limpiar();
            habilitarCampos(true);
            habilitarBoton(true);
            gui.cbo_propiedad.requestFocus();
        }
        if (e.getSource() == gui.btn_editar) {
            operacion = 'E';
            habilitarCampos(true);
            habilitarBoton(true);
            gui.cbo_propiedad.requestFocus();
        }

        if (e.getSource() == gui.btn_eliminar) {
            int fila = gui.table.getSelectedRow();
            if (fila >= 0) {
                int ok = JOptionPane.showConfirmDialog(gui,
                        "Realmente desea elimnar el registro?",
                        "Confirmar operacion",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (ok == 0) {
                    crud.eliminar(modelo.getClienteByRow(fila));
                    listar("");
                    limpiar();
                }
            } else {
                JOptionPane.showMessageDialog(gui, "Debe seleccionar una fila");
            }
        }
        if (e.getSource() == gui.btn_cancelar) {
            habilitarCampos(false);
            habilitarBoton(false);
            limpiar();
        }

        if (e.getSource() == gui.btn_guardar) {
            boolean v_control = validarDatos();
            if (v_control == true) {
                JOptionPane.showMessageDialog(gui, "favor completar los datos");
                return;
            }
            Integer indexSeleccionado = gui.cbo_propiedad.getSelectedIndex();
            
            if (indexSeleccionado==0) {
            	JOptionPane.showMessageDialog(gui, "Seleccione una categoría válida.");
                return;
            }
            
            System.out.println("Evento click de guardar");
            if (operacion == 'N') {
                crud.insertar(getClienteForm());

                gui.cbo_propiedad.requestFocus();
            }

            if (operacion == 'E') {
                crud.actualizar(getClienteForm());
                habilitarCampos(false);
            }

            listar("");
            limpiar();

        }
        System.out.println(operacion);
		
	}
	
	
	//Metodo encargado de habilitar o deshabilitar los campos
    private void habilitarCampos(Boolean estado) {
        gui.cbo_nivel.setEnabled(estado);
        gui.txt_dimension.setEnabled(estado);
        gui.cbo_propietario.setEnabled(estado);
        gui.txt_ubicacion.setEnabled(estado);
        gui.cbo_propiedad.setEnabled(estado);
    }

    private void habilitarBoton(Boolean estado) {
        gui.btn_guardar.setEnabled(estado);
        gui.btn_cancelar.setEnabled(estado);
    }

    private void limpiar() {
        //gui.cbo_nivel.setSelectedItem(Clie);
        gui.txt_dimension.setText("");
        gui.cbo_propietario.setSelectedIndex(0);
        gui.txt_ubicacion.setText("");
        gui.cbo_propiedad.setSelectedIndex(0);
        gui.cbo_propiedad.setSelectedIndex(0);
    }

    // funcion o metodo encargado de recuperrar los valores de los JTextField en un objeto
    private Propiedad getClienteForm() {
        cliente.setTipoPropiedad(gui.cbo_propiedad.getSelectedItem().toString());
        cliente.setNivel(gui.cbo_nivel.getSelectedItem().toString());
        cliente.setDimension(gui.txt_dimension.getText());
        cliente.setCliente((Cliente) gui.cbo_propietario.getSelectedItem());
        cliente.setUbicacion(gui.txt_ubicacion.getText());
        return cliente;
    }
    
    private boolean validarDatos() {
        boolean vacio = false;
        if (gui.cbo_propiedad.getSelectedIndex() == 0) {
            vacio = true;
        }
        if (gui.cbo_nivel.getSelectedIndex() == 0) {
            vacio = true;
        }
        if (gui.txt_dimension.getText().isEmpty()) {
            vacio = true;
        }
        if (gui.cbo_propietario.getSelectedIndex() == 0) {
            vacio = true;
        }
        if (gui.txt_ubicacion.getText().isEmpty()) {
            vacio = true;
        }
        return vacio;
    }

    //Funcion o metodo encargado asignar valor los JTextField
    private void setClienteForm(Propiedad item) {
        System.out.println(item);
        cliente.setId(item.getId());
        gui.cbo_nivel.setSelectedItem(item.getNivel());
        gui.txt_dimension.setText(item.getDimension());
        gui.cbo_propietario.setSelectedItem(item.getCliente());
        gui.txt_ubicacion.setText(item.getUbicacion());
        gui.cbo_propiedad.setSelectedItem(item.getTipoPropiedad());

    }
    
    private void llenarComboCliente(JComboBox cbo){
        DefaultComboBoxModel<Cliente> model = new DefaultComboBoxModel();
        
        // Agregar el item "Seleccionar Producto"
        Cliente seleccionar = new Cliente();
        seleccionar.setId(-1);
        seleccionar.setRuc("0"); // Ruc especial para distinguir
        seleccionar.setNombre("Seleccionar");
        seleccionar.setApellido("Propietario");
        model.addElement(seleccionar);
        //AutoCompleteDecorator.decorate(cbo);
        List<Cliente> lista = crudCliente.listar("");
        for (int i = 0; i < lista.size(); i++) {
            Cliente cliente = lista.get(i);
            model.addElement(cliente);
        }
        cbo.setModel(model);
    }

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		this.listar(gui.txt_buscar.getText());
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
