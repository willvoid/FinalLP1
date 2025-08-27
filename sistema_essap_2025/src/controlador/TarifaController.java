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
import dao.TarifaCrudImpl;
import modelo.Tarifa;
import tabla.TarifaTablaModel;
import vista.GUITarifa;

public class TarifaController implements ActionListener, KeyListener {
	
	private GUITarifa gui;
    private TarifaCrudImpl crud;

    private char operacion;
    Tarifa tarifa = new Tarifa();

    TarifaTablaModel modelo = new TarifaTablaModel();
    
    public TarifaController(GUITarifa gui, TarifaCrudImpl crud) {
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
                TarifaTablaModel model = (TarifaTablaModel) tabla.getModel();
                //Devolver el objeto seleccionado en la fila

                setTarifaForm(model.getTarifaByRow(row));
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
        List<Tarifa> lista = crud.listar(valorBuscado);
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
            gui.txt_rangoMin.requestFocus();
        }
        if (e.getSource() == gui.btn_editar) {
            operacion = 'E';
            habilitarCampos(true);
            habilitarBoton(true);
            gui.txt_rangoMin.requestFocus();
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
                    crud.eliminar(modelo.getTarifaByRow(fila));
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
            
            System.out.println("Evento click de guardar");
            if (operacion == 'N') {
                crud.insertar(getTarifaForm());

                gui.txt_rangoMin.requestFocus();
            }

            if (operacion == 'E') {
                crud.actualizar(getTarifaForm());
                habilitarCampos(false);
            }

            listar("");
            limpiar();

        }
        System.out.println(operacion);
		
	}
	
	
	//Metodo encargado de habilitar o deshabilitar los campos
    private void habilitarCampos(Boolean estado) {
        gui.txt_rangoMin.setEnabled(estado);
        gui.txt_rangoMax.setEnabled(estado);
        gui.txt_precioM3.setEnabled(estado);
        gui.txt_cargoFijo.setEnabled(estado);
    }

    private void habilitarBoton(Boolean estado) {
        gui.btn_guardar.setEnabled(estado);
        gui.btn_cancelar.setEnabled(estado);
    }

    private void limpiar() {
        gui.txt_rangoMin.setText("");
        gui.txt_rangoMax.setText("");
        gui.txt_precioM3.setText("");
        gui.txt_cargoFijo.setText("");
    }

    // funcion o metodo encargado de recuperrar los valores de los JTextField en un objeto
    private Tarifa getTarifaForm() {
        tarifa.setRango_min(Integer.valueOf(gui.txt_rangoMin.getText()));
        tarifa.setRango_max(Integer.valueOf(gui.txt_rangoMax.getText()));
        tarifa.setPrecio_m3(Integer.valueOf(gui.txt_precioM3.getText()));
        tarifa.setCargo_fijo(Integer.valueOf(gui.txt_cargoFijo.getText()));
        return tarifa;
    }
    
    private boolean validarDatos() {
        boolean vacio = false;
        if (gui.txt_cargoFijo.getText().isEmpty()) {
            vacio = true;
        }
        if (gui.txt_precioM3.getText().isEmpty()) {
            vacio = true;
        }
        if (gui.txt_rangoMax.getText().isEmpty()) {
            vacio = true;
        }
        if (gui.txt_rangoMax.getText().isEmpty()) {
            vacio = true;
        }
        if (gui.txt_rangoMin.getText().isEmpty()) {
            vacio = true;
        }
        return vacio;
    }

    //Funcion o metodo encargado asignar valor los JTextField
    private void setTarifaForm(Tarifa item) {
        System.out.println(item);
        tarifa.setId(item.getId());
        gui.txt_cargoFijo.setText(item.getCargo_fijo().toString());
        gui.txt_precioM3.setText(item.getPrecio_m3().toString());
        gui.txt_rangoMax.setText(item.getRango_max().toString());
        gui.txt_rangoMin.setText(item.getRango_min().toString());

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
