package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import tabla.TarifaTablaModel;

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

public class GUITarifa extends JDialog {

	private static final long serialVersionUID = 1L;
	public JTextField txt_rangoMin;
	public JTextField txt_rangoMax;
	public JTextField txt_precioM3;
	public JTextField txt_cargoFijo;
	public JButton btn_guardar;
	public JButton btn_cancelar;
	public JButton btn_nuevo;
	public JButton btn_editar;
	public JButton btn_eliminar;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JDesktopPane desktopPane_1;
	public JTextField txt_buscar;
	private JLabel lblNewLabel_7;
	public JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GUITarifa dialog = new GUITarifa();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GUITarifa() {
		setBounds(100, 100, 1233, 544);
		getContentPane().setLayout(null);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(10, 11, 346, 458);
		getContentPane().add(desktopPane);
		
		txt_rangoMin = new JTextField();
		txt_rangoMin.setBounds(80, 95, 195, 25);
		desktopPane.add(txt_rangoMin);
		txt_rangoMin.setColumns(10);
		
		txt_rangoMax = new JTextField();
		txt_rangoMax.setBounds(80, 156, 195, 25);
		desktopPane.add(txt_rangoMax);
		txt_rangoMax.setColumns(10);
		
		txt_precioM3 = new JTextField();
		txt_precioM3.setBounds(80, 216, 195, 25);
		desktopPane.add(txt_precioM3);
		txt_precioM3.setColumns(10);
		
		txt_cargoFijo = new JTextField();
		txt_cargoFijo.setBounds(80, 275, 195, 25);
		desktopPane.add(txt_cargoFijo);
		txt_cargoFijo.setColumns(10);
		
		btn_guardar = new JButton("Guardar");
		btn_guardar.setBounds(80, 344, 89, 23);
		desktopPane.add(btn_guardar);
		
		btn_cancelar = new JButton("Cancelar");
		btn_cancelar.setBounds(179, 344, 89, 23);
		desktopPane.add(btn_cancelar);
		
		lblNewLabel_1 = new JLabel("Rango Min");
		lblNewLabel_1.setBounds(80, 70, 106, 14);
		desktopPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Rango Max");
		lblNewLabel_2.setBounds(80, 131, 81, 14);
		desktopPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Precio M3");
		lblNewLabel_3.setBounds(80, 191, 81, 14);
		desktopPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Cargo Fijo");
		lblNewLabel_4.setBounds(80, 250, 81, 14);
		desktopPane.add(lblNewLabel_4);
		
		desktopPane_1 = new JDesktopPane();
		desktopPane_1.setBounds(366, 11, 841, 458);
		getContentPane().add(desktopPane_1);
		
		txt_buscar = new JTextField();
		txt_buscar.setBounds(263, 11, 212, 20);
		desktopPane_1.add(txt_buscar);
		txt_buscar.setColumns(10);
		
		lblNewLabel_7 = new JLabel("Buscar");
		lblNewLabel_7.setBounds(207, 14, 46, 14);
		desktopPane_1.add(lblNewLabel_7);
		
		TarifaTablaModel modelo = new TarifaTablaModel();
		table = new JTable(modelo);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 42, 821, 398);
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
