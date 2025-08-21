package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import tabla.PropiedadTablaModel;

import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JMenuBar;
import javax.swing.DefaultComboBoxModel;

public class GUIPropiedad extends JDialog {

	private static final long serialVersionUID = 1L;
	public JTextField txt_dimension;
	public JTextField txt_ubicacion;
	public JButton btn_guardar;
	public JButton btn_cancelar;
	public JButton btn_nuevo;
	public JButton btn_editar;
	public JButton btn_eliminar;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_5;
	private JDesktopPane desktopPane_1;
	public JTextField txt_buscar;
	private JLabel lblNewLabel_7;
	public JTable table;
	public JComboBox cbo_nivel;
	public JComboBox cbo_propietario;
	public JComboBox cbo_propiedad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GUIClientes dialog = new GUIClientes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GUIPropiedad() {
		setBounds(100, 100, 1233, 544);
		getContentPane().setLayout(null);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(10, 11, 346, 483);
		getContentPane().add(desktopPane);
		
		txt_dimension = new JTextField();
		txt_dimension.setBounds(78, 154, 195, 25);
		desktopPane.add(txt_dimension);
		txt_dimension.setColumns(10);
		
		txt_ubicacion = new JTextField();
		txt_ubicacion.setBounds(78, 276, 195, 25);
		desktopPane.add(txt_ubicacion);
		txt_ubicacion.setColumns(10);
		
		btn_guardar = new JButton("Guardar");
		btn_guardar.setBounds(78, 351, 89, 23);
		desktopPane.add(btn_guardar);
		
		btn_cancelar = new JButton("Cancelar");
		btn_cancelar.setBounds(177, 351, 89, 23);
		desktopPane.add(btn_cancelar);
		
		lblNewLabel = new JLabel("Propiedad");
		lblNewLabel.setBounds(78, 11, 68, 14);
		desktopPane.add(lblNewLabel);
		
		lblNewLabel_2 = new JLabel("Nivel");
		lblNewLabel_2.setBounds(78, 69, 46, 14);
		desktopPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Dimensión");
		lblNewLabel_3.setBounds(78, 129, 81, 14);
		desktopPane.add(lblNewLabel_3);
		
		lblNewLabel_5 = new JLabel("Ubicación");
		lblNewLabel_5.setBounds(78, 249, 124, 14);
		desktopPane.add(lblNewLabel_5);
		
		cbo_propiedad = new JComboBox();
		cbo_propiedad.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar", "Residencia"}));
		cbo_propiedad.setBounds(79, 36, 195, 22);
		desktopPane.add(cbo_propiedad);
		
		cbo_nivel = new JComboBox();
		cbo_nivel.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar Nivel", "Mínimo", "Medio", "Intermedio", "Alto"}));
		cbo_nivel.setBounds(78, 94, 195, 22);
		desktopPane.add(cbo_nivel);
		
		cbo_propietario = new JComboBox();
		cbo_propietario.setBounds(78, 216, 195, 22);
		desktopPane.add(cbo_propietario);
		
		JLabel lblNewLabel_2_1 = new JLabel("Propietario");
		lblNewLabel_2_1.setBounds(78, 191, 89, 14);
		desktopPane.add(lblNewLabel_2_1);
		
		desktopPane_1 = new JDesktopPane();
		desktopPane_1.setBounds(366, 11, 841, 483);
		getContentPane().add(desktopPane_1);
		
		txt_buscar = new JTextField();
		txt_buscar.setBounds(263, 11, 212, 20);
		desktopPane_1.add(txt_buscar);
		txt_buscar.setColumns(10);
		
		lblNewLabel_7 = new JLabel("Buscar");
		lblNewLabel_7.setBounds(207, 14, 46, 14);
		desktopPane_1.add(lblNewLabel_7);
		
		PropiedadTablaModel modelo = new PropiedadTablaModel();
		table = new JTable(modelo);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 42, 821, 417);
		desktopPane_1.add(scrollPane);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		btn_nuevo = new JButton("Nuevo");
		menuBar.add(btn_nuevo);
		
		btn_editar = new JButton("Editar");
		menuBar.add(btn_editar);
		
		btn_eliminar = new JButton("Eliminar");
		menuBar.add(btn_eliminar);
	}
}
