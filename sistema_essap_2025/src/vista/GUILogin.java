package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.UsuarioCrudImpl;
import modelo.Usuario;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class GUILogin extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	public JTextField txt_usuario;
	public JPasswordField txt_contraseña;
	public JButton btn_login = new JButton("Iniciar Sesión");
	private final JLabel lblNewLabel_2 = new JLabel("");

	/**
	 * Launch the application.
	 */
	public static void main(String args[]) {
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUILogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUILogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUILogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUILogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUILogin().setVisible(true);
            }
        });
	}

	/**
	 * Create the dialog.
	 */
	public GUILogin() {
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\Windows 11\\Documents\\Facultad\\LP 1\\img\\user1.png"));
		setBounds(100, 100, 779, 528);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		txt_usuario = new JTextField();
		txt_usuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == e.VK_ENTER){
		            txt_contraseña.requestFocus();
		        }
			}
		});
		
		txt_usuario.setColumns(10);
		
		txt_contraseña = new JPasswordField();
		txt_contraseña.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		
		JLabel lblNewLabel = new JLabel("Usuario");
		
		JLabel lblNewLabel_1 = new JLabel("Contraseña");
		
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(324)
							.addComponent(lblNewLabel_2))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(262)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblNewLabel)
								.addComponent(lblNewLabel_1)
								.addComponent(btn_login, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txt_contraseña, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
								.addComponent(txt_usuario))))
					.addContainerGap(280, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
					.addGap(65)
					.addComponent(lblNewLabel_2)
					.addGap(37)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txt_usuario, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txt_contraseña, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btn_login, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(179, Short.MAX_VALUE))
		);
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		contentPanel.setLayout(gl_contentPanel);
	}
		private void login(){
		    UsuarioCrudImpl dao = new UsuarioCrudImpl();
		    Usuario usuario = new Usuario();
		    if (txt_usuario.getText().isEmpty() && txt_contraseña.getText().isEmpty()){
		        JOptionPane.showMessageDialog(this, "Complete los Campos");
		        return;
		    }
		    
		    if (txt_usuario.getText().isEmpty()){
		        JOptionPane.showMessageDialog(this, "Complete el Usuario");
		        return;
		    }
		    
		    if (txt_contraseña.getText().isEmpty()){
		        JOptionPane.showMessageDialog(this, "Complete la Clave");
		        return;
		    }
		    
		    usuario.setUsuario(txt_usuario.getText().trim());
		    usuario.setContraseña(txt_contraseña.getText().trim());
		    
		    if (dao.loginUser(usuario)){
		        //dao.ObtenerUser(usuario);
		        VentanaPrincipal guimain = new VentanaPrincipal();
		        JOptionPane.showMessageDialog(this, "Sesión iniciada correctamente");
		        this.setVisible(false);
		        guimain.setExtendedState(JFrame.MAXIMIZED_BOTH);
		        guimain.setVisible(true);
		    }else{
		        JOptionPane.showMessageDialog(this, "Usuario o Clave Incorrectos");
		    }
		    
		}
	}


	
