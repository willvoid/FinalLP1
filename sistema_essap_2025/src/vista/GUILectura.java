package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import tabla.LecturaTablaModel;

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

public class GUILectura extends JDialog {

	private static final long serialVersionUID = 1L;
	public JTextField txt_dias_facturados;
	public JTextField txt_fecha_inicio;
	public JTextField txt_fecha_fin;
	public JTextField txt_lectura_inicio;
	public JTextField txt_lectura_cierre;
	public JTextField txt_consumo;
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
	public JComboBox cbo_propiedad;
	private JLabel lblNewLabel_6;
	private JDesktopPane desktopPane_1;
	public JTextField txt_buscar;
	private JLabel lblNewLabel_7;
	public JTable table;
	public JButton btn_calendario1;
	public JButton btn_calendario2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GUILectura dialog = new GUILectura();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GUILectura() {
		setBounds(100, 100, 1233, 544);
		getContentPane().setLayout(null);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(10, 11, 346, 458);
		getContentPane().add(desktopPane);
		
		txt_dias_facturados = new JTextField();
		txt_dias_facturados.setBounds(79, 384, 195, 25);
		desktopPane.add(txt_dias_facturados);
		txt_dias_facturados.setColumns(10);
		
		txt_fecha_inicio = new JTextField();
		txt_fecha_inicio.setBounds(79, 94, 195, 25);
		desktopPane.add(txt_fecha_inicio);
		txt_fecha_inicio.setColumns(10);
		
		txt_fecha_fin = new JTextField();
		txt_fecha_fin.setBounds(79, 155, 195, 25);
		desktopPane.add(txt_fecha_fin);
		txt_fecha_fin.setColumns(10);
		
		txt_lectura_inicio = new JTextField();
		txt_lectura_inicio.setBounds(79, 215, 195, 25);
		desktopPane.add(txt_lectura_inicio);
		txt_lectura_inicio.setColumns(10);
		
		txt_lectura_cierre = new JTextField();
		txt_lectura_cierre.setBounds(79, 274, 195, 25);
		desktopPane.add(txt_lectura_cierre);
		txt_lectura_cierre.setColumns(10);
		
		txt_consumo = new JTextField();
		txt_consumo.setBounds(79, 332, 195, 25);
		desktopPane.add(txt_consumo);
		txt_consumo.setColumns(10);
		
		btn_guardar = new JButton("Guardar");
		btn_guardar.setBounds(79, 424, 89, 23);
		desktopPane.add(btn_guardar);
		
		btn_cancelar = new JButton("Cancelar");
		btn_cancelar.setBounds(178, 424, 89, 23);
		desktopPane.add(btn_cancelar);
		
		lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(78, 11, 46, 14);
		desktopPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Inicio Ciclo");
		lblNewLabel_1.setBounds(79, 69, 106, 14);
		desktopPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Fin ciclo");
		lblNewLabel_2.setBounds(79, 130, 81, 14);
		desktopPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Lectura inicio");
		lblNewLabel_3.setBounds(79, 190, 81, 14);
		desktopPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Lectura Cierre");
		lblNewLabel_4.setBounds(79, 249, 81, 14);
		desktopPane.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Consumo (M3)");
		lblNewLabel_5.setBounds(79, 305, 89, 14);
		desktopPane.add(lblNewLabel_5);
		
		cbo_propiedad = new JComboBox();
		cbo_propiedad.setBounds(79, 34, 195, 22);
		desktopPane.add(cbo_propiedad);
		
		lblNewLabel_6 = new JLabel("Días Facturados");
		lblNewLabel_6.setBounds(79, 368, 123, 14);
		desktopPane.add(lblNewLabel_6);
		
		btn_calendario2 = new JButton("");
		btn_calendario2.setBounds(284, 157, 51, 23);
		desktopPane.add(btn_calendario2);
		
		btn_calendario1 = new JButton("");
		btn_calendario1.setBounds(284, 95, 51, 23);
		desktopPane.add(btn_calendario1);
		
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
		
		LecturaTablaModel modelo = new LecturaTablaModel();
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
