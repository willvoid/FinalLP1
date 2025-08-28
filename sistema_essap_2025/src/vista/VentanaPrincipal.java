package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;

import controlador.ClienteController;
import controlador.LecturaController;
import controlador.MedidorController;
import controlador.NuevoUsuarioController;
import controlador.PropiedadController;
import controlador.TarifaController;
import dao.ClienteCrudImpl;
import dao.LecturaCrudImpl;
import dao.MedidorCrudImpl;
import dao.PropiedadCrudImpl;
import dao.TarifaCrudImpl;
import dao.UsuarioCrudImpl;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

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
		
		JLayeredPane layeredPane = new JLayeredPane();
		getContentPane().add(layeredPane, "name_779560909941400");
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Windows 11\\Documents\\Facultad\\LP 1\\img\\logoessap.png"));
		lblNewLabel.setBounds(444, 239, 406, 165);
		layeredPane.add(lblNewLabel);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Usuarios");
		mnNewMenu.setIcon(new ImageIcon("C:\\Users\\Windows 11\\Documents\\Facultad\\LP 1\\img\\usuario.png"));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Nuevo Usuario");
		mntmNewMenuItem.setIcon(new ImageIcon("C:\\Users\\Windows 11\\Documents\\Facultad\\LP 1\\img\\nuevo-cliente.png"));
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
		mnNewMenu_1.setIcon(new ImageIcon("C:\\Users\\Windows 11\\Documents\\Facultad\\LP 1\\img\\cliente.png"));
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Administrar Clientes");
		mntmNewMenuItem_1.setIcon(new ImageIcon("C:\\Users\\Windows 11\\Documents\\Facultad\\LP 1\\img\\nuevo-cliente.png"));
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
		mntmNewMenuItem_2.setIcon(new ImageIcon("C:\\Users\\Windows 11\\Documents\\Facultad\\LP 1\\img\\icons8-casa-batlló-48.png"));
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIPropiedad propiedad = new GUIPropiedad();
		        PropiedadCrudImpl crud = new PropiedadCrudImpl();
		        PropiedadController ctrl = new PropiedadController(propiedad, crud);
		        ctrl.mostrarVentana();
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		JMenu mnNewMenu_3 = new JMenu("Medidores");
		mnNewMenu_3.setIcon(new ImageIcon("C:\\Users\\Windows 11\\Documents\\Facultad\\LP 1\\img\\categorias.png"));
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Gestionar Medidores");
		mntmNewMenuItem_3.setIcon(new ImageIcon("C:\\Users\\Windows 11\\Documents\\Facultad\\LP 1\\img\\nuevo.png"));
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIMedidor medidor = new GUIMedidor();
		        MedidorCrudImpl crud = new MedidorCrudImpl();
		        MedidorController ctrl = new MedidorController(medidor, crud);
		        ctrl.mostrarVentana();
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Lecturas");
		mntmNewMenuItem_5.setIcon(new ImageIcon("C:\\Users\\Windows 11\\Documents\\Facultad\\LP 1\\img\\historial1.png"));
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUILectura medidor = new GUILectura();
		        LecturaCrudImpl crud = new LecturaCrudImpl();
		        LecturaController ctrl = new LecturaController(medidor, crud);
		        ctrl.mostrarVentana();
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_5);
		
		JMenu mnNewMenu_2 = new JMenu("Facturacion");
		mnNewMenu_2.setIcon(new ImageIcon("C:\\Users\\Windows 11\\Documents\\Facultad\\LP 1\\img\\anadir.png"));
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Tarifas");
		mntmNewMenuItem_4.setIcon(new ImageIcon("C:\\Users\\Windows 11\\Documents\\Facultad\\LP 1\\img\\producto.png"));
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUITarifa tarifa = new GUITarifa();
		        TarifaCrudImpl crud = new TarifaCrudImpl();
		        TarifaController ctrl = new TarifaController(tarifa, crud);
		        ctrl.mostrarVentana();
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_4);

	}
}

