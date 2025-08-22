package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
//import com.formdev.flatlaf.json.ParseException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

//import com.formdev.flatlaf.json.ParseException;

import dao.MedidorCrudImpl;
import dao.PropiedadCrudImpl;
import modelo.Cliente;
import modelo.Medidor;
import modelo.Propiedad;
import tabla.MedidorTablaModel;
import vista.GUICalendario;
import vista.GUIMedidor;

public class MedidorController implements ActionListener, KeyListener {
	
	private GUIMedidor gui;
    private MedidorCrudImpl crud;
    private PropiedadCrudImpl crudPropiedad = new PropiedadCrudImpl();
    private char operacion;
    Medidor medidor = new Medidor();
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    MedidorTablaModel modelo = new MedidorTablaModel();
    
    public MedidorController(GUIMedidor gui, MedidorCrudImpl crud) {
        this.gui = gui;
        this.crud = crud;
        this.gui.btn_guardar.addActionListener(this);
        this.gui.btn_cancelar.addActionListener(this);
        this.gui.btn_nuevo.addActionListener(this);
        this.gui.btn_editar.addActionListener(this);
        this.gui.btn_eliminar.addActionListener(this);
        this.gui.txt_buscar.addKeyListener(this);
        this.gui.btn_calendario1.addActionListener(this);
        this.gui.btn_calendario2.addActionListener(this);
        //AutoCompleteDecorator.decorate(guiv.cbo_cliente);

        gui.table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JTable tabla = (JTable) e.getSource();
                int row = tabla.rowAtPoint(e.getPoint());
                MedidorTablaModel model = (MedidorTablaModel) tabla.getModel();
                //Devolver el objeto seleccionado en la fila

                setClienteForm(model.getClienteByRow(row));
            }
        });

        habilitarCampos(false);
        habilitarBoton(false);
        llenarComboPropiedad(gui.cbo_propiedad);
        listar("");
    }
    
    public void mostrarVentana() {
        gui.setLocationRelativeTo(gui);
        gui.setVisible(true);
    }

    public void listar(String valorBuscado) {
        List<Medidor> lista = crud.listar(valorBuscado);
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
        
        if (e.getSource() == gui.btn_calendario1){
	        llamarCalendario(gui.txt_fecha_inicio);
        }
        
        if (e.getSource() == gui.btn_calendario2){
	        llamarCalendario(gui.txt_fecha_fin);
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
                try {
					crud.insertar(getClienteForm());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

            }

            if (operacion == 'E') {
                try {
					crud.actualizar(getClienteForm());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                habilitarCampos(false);
            }

            listar("");
            limpiar();

        }
        System.out.println(operacion);
		
	}
	
	
	//Metodo encargado de habilitar o deshabilitar los campos
    private void habilitarCampos(Boolean estado) {
        gui.cbo_propiedad.setEnabled(estado);
        gui.txt_consumo.setEnabled(estado);
        gui.txt_dias_facturados.setEnabled(estado);
        gui.txt_fecha_fin.setEnabled(false);
        gui.txt_fecha_inicio.setEnabled(false);
        gui.txt_lectura_cierre.setEnabled(estado);
        gui.txt_lectura_inicio.setEnabled(estado);
    }

    private void habilitarBoton(Boolean estado) {
        gui.btn_guardar.setEnabled(estado);
        gui.btn_cancelar.setEnabled(estado);
        gui.btn_calendario1.setEnabled(estado);
        gui.btn_calendario2.setEnabled(estado);
    }

    private void limpiar() {
    	gui.cbo_propiedad.setSelectedIndex(0);
        gui.txt_consumo.setText("");
        gui.txt_dias_facturados.setText("");
        gui.txt_fecha_fin.setText("");
        gui.txt_fecha_inicio.setText("");
        gui.txt_lectura_cierre.setText("");
        gui.txt_lectura_inicio.setText("");
    }

    // funcion o metodo encargado de recuperrar los valores de los JTextField en un objeto
    private Medidor getClienteForm() throws ParseException {
        medidor.setPropiedad((Propiedad) gui.cbo_propiedad.getSelectedItem());
        medidor.setFechaInicio(StringToDate(gui.txt_fecha_inicio, 1));
        medidor.setFechaFin(StringToDate(gui.txt_fecha_fin, 2));
        medidor.setLecturaInicio(Integer.parseInt(gui.txt_lectura_inicio.getText()));
        medidor.setLecturaCierre(Integer.parseInt(gui.txt_lectura_cierre.getText()));
        medidor.setConsumo(Integer.parseInt(gui.txt_consumo.getText()));
        medidor.setDiasFacturados(Integer.parseInt(gui.txt_dias_facturados.getText()));
        return medidor;
    }
    
    private boolean validarDatos() {
        boolean vacio = false;
        if (gui.cbo_propiedad.getSelectedIndex() == 0) {
            vacio = true;
        }
        if (gui.txt_consumo.getText().isEmpty()) {
            vacio = true;
        }
        if (gui.txt_dias_facturados.getText().isEmpty()) {
            vacio = true;
        }
        if (gui.txt_fecha_fin.getText().isEmpty()) {
            vacio = true;
        }
        if (gui.txt_fecha_inicio.getText().isEmpty()) {
            vacio = true;
        }
        if (gui.txt_lectura_cierre.getText().isEmpty()) {
            vacio = true;
        }
        if (gui.txt_lectura_inicio.getText().isEmpty()) {
            vacio = true;
        }
        return vacio;
    }

    //Funcion o metodo encargado asignar valor los JTextField
    private void setClienteForm(Medidor item) {
        System.out.println(item);
        medidor.setId(item.getId());
        gui.txt_consumo.setText(item.getConsumo().toString());
        gui.txt_dias_facturados.setText(item.getDiasFacturados().toString());
        gui.txt_fecha_fin.setText(setTextDate(item.getFechaFin()));
        gui.txt_fecha_inicio.setText(setTextDate(item.getFechaInicio()));
        gui.txt_lectura_cierre.setText(item.getLecturaCierre().toString());
        gui.txt_lectura_inicio.setText(item.getLecturaInicio().toString());
        gui.cbo_propiedad.setSelectedItem(item.getPropiedad());

    }
    
    private java.util.Date StringToDate(JTextField txt, Integer i) throws java.text.ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return sdf.parse(txt.getText());  // ya devuelve java.util.Date
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, 
                "Formato de fecha inválido. Usa el formato dd/MM/yyyy", 
                "Error", JOptionPane.ERROR_MESSAGE);
            if (i == 1) {
                gui.btn_calendario1.requestFocus();
            } else {
                gui.btn_calendario2.requestFocus();
            }
            return null;
        }
    }

    
    private void llamarCalendario(JTextField txt) {
    	GUICalendario guicalen = new GUICalendario (null, true);
        CalendarioController calenCtrl = new CalendarioController(guicalen, txt);
        calenCtrl.mostrarVentana();
    }
    
    private String setTextDate (java.util.Date fechaRecibida) {
    	String fechaStr = "";
    	if (fechaRecibida != null) {
            java.util.Date fecha = new java.util.Date(fechaRecibida.getTime()); // Convertir de java.sql.Date a java.util.Date
            fechaStr = sdf.format(fecha);
    	}
    	return fechaStr;
    }
    
    private void llenarComboPropiedad(JComboBox cbo){
        DefaultComboBoxModel<Propiedad> model = new DefaultComboBoxModel();
        
        // Agregar el item "Seleccionar Producto"
        Propiedad seleccionar = new Propiedad();
        Cliente cliente = new Cliente();
        seleccionar.setId(0); // id especial para distinguir
        seleccionar.setTipoPropiedad("Seleccionar");
        cliente.setRuc(">");
        seleccionar.setCliente(cliente);
        model.addElement(seleccionar);
        //AutoCompleteDecorator.decorate(cbo);
        List<Propiedad> lista = crudPropiedad.listar("");
        for (int i = 0; i < lista.size(); i++) {
            Propiedad propiedad = lista.get(i);
            model.addElement(propiedad);
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
