package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.ClienteController;
import controlador.NuevoUsuarioController;
import dao.ClienteCrudImpl;
import dao.UsuarioCrudImpl;

import javax.swing.JLayeredPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 866, 465);
		getContentPane().setLayout(new CardLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Usuarios");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Nuevo Usuario");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUINuevoUsuario nusuario = new GUINuevoUsuario();
		        UsuarioCrudImpl crud = new UsuarioCrudImpl();
		        NuevoUsuarioController ctrl = new NuevoUsuarioController(nusuario, crud);
		        ctrl.mostrarVentana();
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_1 = new JMenu("Clientes");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Administrar Clientes");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIClientes cliente = new GUIClientes();
		        ClienteCrudImpl crud = new ClienteCrudImpl();
		        ClienteController ctrl = new ClienteController(cliente, crud);
		        ctrl.mostrarVentana();
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Residencias");
		mnNewMenu_1.add(mntmNewMenuItem_2);

	}

}
