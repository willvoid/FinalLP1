package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import tabla.ClienteTablaModel;

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

public class GUIClientes extends JDialog {

	private static final long serialVersionUID = 1L;
	public JTextField txt_nombre;
	public JTextField txt_apellido;
	public JTextField txt_ruc;
	public JTextField txt_direccion;
	public JTextField txt_telefono;
	public JTextField txt_correo;
	public JButton btn_guardar;
	public JButton btn_cancelar;
	public JButton btn_nuevo;
	public JButton btn_editar;
	public JButton btn_eliminar;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	public JComboBox cbo_categoria;
	private JLabel lblNewLabel_6;
	private JDesktopPane desktopPane_1;
	public JTextField txt_buscar;
	private JLabel lblNewLabel_7;
	public JTable table;

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
	public GUIClientes() {
		setBounds(100, 100, 1233, 544);
		getContentPane().setLayout(null);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(10, 11, 346, 483);
		getContentPane().add(desktopPane);
		
		txt_nombre = new JTextField();
		txt_nombre.setBounds(79, 33, 195, 25);
		desktopPane.add(txt_nombre);
		txt_nombre.setColumns(10);
		
		txt_apellido = new JTextField();
		txt_apellido.setBounds(79, 94, 195, 25);
		desktopPane.add(txt_apellido);
		txt_apellido.setColumns(10);
		
		txt_ruc = new JTextField();
		txt_ruc.setBounds(79, 155, 195, 25);
		desktopPane.add(txt_ruc);
		txt_ruc.setColumns(10);
		
		txt_direccion = new JTextField();
		txt_direccion.setBounds(79, 215, 195, 25);
		desktopPane.add(txt_direccion);
		txt_direccion.setColumns(10);
		
		txt_telefono = new JTextField();
		txt_telefono.setBounds(79, 274, 195, 25);
		desktopPane.add(txt_telefono);
		txt_telefono.setColumns(10);
		
		txt_correo = new JTextField();
		txt_correo.setBounds(79, 332, 195, 25);
		desktopPane.add(txt_correo);
		txt_correo.setColumns(10);
		
		btn_guardar = new JButton("Guardar");
		btn_guardar.setBounds(86, 432, 89, 23);
		desktopPane.add(btn_guardar);
		
		btn_cancelar = new JButton("Cancelar");
		btn_cancelar.setBounds(185, 432, 89, 23);
		desktopPane.add(btn_cancelar);
		
		lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(78, 11, 46, 14);
		desktopPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Apellido");
		lblNewLabel_1.setBounds(79, 69, 46, 14);
		desktopPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("CI/RUC");
		lblNewLabel_2.setBounds(79, 130, 46, 14);
		desktopPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Dirección");
		lblNewLabel_3.setBounds(79, 190, 81, 14);
		desktopPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Teléfono");
		lblNewLabel_4.setBounds(79, 249, 81, 14);
		desktopPane.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Correo");
		lblNewLabel_5.setBounds(79, 305, 46, 14);
		desktopPane.add(lblNewLabel_5);
		
		cbo_categoria = new JComboBox();
		cbo_categoria.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar", "Residencial", "Subsidiada", "No Redisencial"}));
		cbo_categoria.setBounds(79, 399, 195, 22);
		desktopPane.add(cbo_categoria);
		
		lblNewLabel_6 = new JLabel("Categoría");
		lblNewLabel_6.setBounds(79, 374, 123, 14);
		desktopPane.add(lblNewLabel_6);
		
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
		
		ClienteTablaModel modelo = new ClienteTablaModel();
		table = new JTable(modelo);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 42, 821, 430);
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
