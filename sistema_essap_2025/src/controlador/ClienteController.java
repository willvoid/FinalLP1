package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import dao.ClienteCrudImpl;
import modelo.Cliente;
import tabla.ClienteTablaModel;
import vista.GUIClientes;

public class ClienteController implements ActionListener, KeyListener {
	
	private GUIClientes gui;
    private ClienteCrudImpl crud;

    private char operacion;
    Cliente cliente = new Cliente();

    ClienteTablaModel modelo = new ClienteTablaModel();
    
    public ClienteController(GUIClientes gui, ClienteCrudImpl crud) {
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
                ClienteTablaModel model = (ClienteTablaModel) tabla.getModel();
                //Devolver el objeto seleccionado en la fila

                setClienteForm(model.getClienteByRow(row));
            }
        });

        habilitarCampos(false);
        habilitarBoton(false);

        listar("");
    }
    
    public void mostrarVentana() {
        gui.setLocationRelativeTo(gui);
        gui.setVisible(true);
    }

    public void listar(String valorBuscado) {
        List<Cliente> lista = crud.listar(valorBuscado);
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
            gui.txt_ruc.requestFocus();
        }
        if (e.getSource() == gui.btn_editar) {
            operacion = 'E';
            habilitarCampos(true);
            habilitarBoton(true);
            gui.txt_ruc.requestFocus();
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
            Integer indexSeleccionado = gui.cbo_categoria.getSelectedIndex();
            
            if (indexSeleccionado==0) {
            	JOptionPane.showMessageDialog(gui, "Seleccione una categoría válida.");
                return;
            }
            
            System.out.println("Evento click de guardar");
            if (operacion == 'N') {
                crud.insertar(getClienteForm());

                gui.txt_ruc.requestFocus();
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
        gui.txt_ruc.setEnabled(estado);
        gui.txt_nombre.setEnabled(estado);
        gui.txt_apellido.setEnabled(estado);
        gui.txt_direccion.setEnabled(estado);
        gui.txt_telefono.setEnabled(estado);
        gui.txt_correo.setEnabled(estado);
        gui.cbo_categoria.setEnabled(estado);
    }

    private void habilitarBoton(Boolean estado) {
        gui.btn_guardar.setEnabled(estado);
        gui.btn_cancelar.setEnabled(estado);
    }

    private void limpiar() {
        gui.txt_ruc.setText("");
        gui.txt_nombre.setText("");
        gui.txt_apellido.setText("");
        gui.txt_direccion.setText("");
        gui.txt_telefono.setText("");
        gui.txt_correo.setText("");
        gui.cbo_categoria.setSelectedIndex(0);
    }

    // funcion o metodo encargado de recuperrar los valores de los JTextField en un objeto
    private Cliente getClienteForm() {
        cliente.setRuc(gui.txt_ruc.getText());
        cliente.setNombre(gui.txt_nombre.getText());
        cliente.setApellido(gui.txt_apellido.getText());
        cliente.setDireccion(gui.txt_direccion.getText());
        cliente.setEmail(gui.txt_correo.getText());
        cliente.setTelefono(gui.txt_telefono.getText());
        cliente.setCategoria(gui.cbo_categoria.getSelectedItem().toString());
        return cliente;
    }
    
    private boolean validarDatos() {
        boolean vacio = false;
        if (gui.txt_ruc.getText().isEmpty()) {
            vacio = true;
        }
        if (gui.txt_nombre.getText().isEmpty()) {
            vacio = true;
        }
        if (gui.txt_apellido.getText().isEmpty()) {
            vacio = true;
        }
        if (gui.txt_telefono.getText().isEmpty()) {
            vacio = true;
        }
        if (gui.txt_direccion.getText().isEmpty()) {
            vacio = true;
        }
        return vacio;
    }

    //Funcion o metodo encargado asignar valor los JTextField
    private void setClienteForm(Cliente item) {
        System.out.println(item);
        cliente.setId(item.getId());
        gui.txt_ruc.setText(item.getRuc());
        gui.txt_nombre.setText(item.getNombre());
        gui.txt_apellido.setText(item.getApellido());
        gui.txt_direccion.setText(item.getDireccion());
        gui.txt_telefono.setText(item.getTelefono());
        gui.txt_correo.setText(item.getEmail());
        gui.cbo_categoria.setSelectedItem(item.getCategoria());

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
