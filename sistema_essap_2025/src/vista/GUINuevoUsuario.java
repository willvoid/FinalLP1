package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.JLayeredPane;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;

public class GUINuevoUsuario extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	public JTextField txt_nombre;
	public JTextField txt_apellido;
	public JTextField txt_usuario;
	public JPasswordField txt_clave;
	public JPasswordField txt_repetirClave;
	public JButton btn_cancelar;
	public JButton btn_guardar;
	public JComboBox cbo_rol;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GUINuevoUsuario dialog = new GUINuevoUsuario();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GUINuevoUsuario() {
		setBounds(100, 100, 789, 489);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new CardLayout(0, 0));
		
		JLayeredPane layeredPane = new JLayeredPane();
		contentPanel.add(layeredPane, "name_1576467480113700");
		
		txt_nombre = new JTextField();
		txt_nombre.setBounds(261, 38, 265, 28);
		layeredPane.add(txt_nombre);
		txt_nombre.setColumns(10);
		
		txt_apellido = new JTextField();
		txt_apellido.setBounds(261, 92, 265, 33);
		layeredPane.add(txt_apellido);
		txt_apellido.setColumns(10);
		
		txt_usuario = new JTextField();
		txt_usuario.setBounds(261, 150, 265, 31);
		layeredPane.add(txt_usuario);
		txt_usuario.setColumns(10);
		
		txt_clave = new JPasswordField();
		txt_clave.setBounds(261, 208, 265, 33);
		layeredPane.add(txt_clave);
		
		cbo_rol = new JComboBox();
		cbo_rol.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar", "Gerencia General", "Atención al Cliente", "ADMIN"}));
		cbo_rol.setBounds(261, 331, 265, 22);
		layeredPane.add(cbo_rol);
		
		btn_guardar = new JButton("Guardar");
		btn_guardar.setBounds(289, 384, 89, 23);
		layeredPane.add(btn_guardar);
		
		btn_cancelar = new JButton("Cancelar");
		btn_cancelar.setBounds(416, 384, 89, 23);
		layeredPane.add(btn_cancelar);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(261, 24, 46, 14);
		layeredPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Apellido");
		lblNewLabel_1.setBounds(261, 77, 46, 14);
		layeredPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Usuario");
		lblNewLabel_2.setBounds(261, 136, 46, 14);
		layeredPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Repetir Contraseña");
		lblNewLabel_3.setBounds(261, 247, 117, 14);
		layeredPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Rol");
		lblNewLabel_4.setBounds(261, 306, 46, 14);
		layeredPane.add(lblNewLabel_4);
		
		txt_repetirClave = new JPasswordField();
		txt_repetirClave.setBounds(261, 260, 265, 33);
		layeredPane.add(txt_repetirClave);
		
		JLabel lblNewLabel_3_1 = new JLabel("Contraseña");
		lblNewLabel_3_1.setBounds(261, 192, 77, 14);
		layeredPane.add(lblNewLabel_3_1);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}
}
