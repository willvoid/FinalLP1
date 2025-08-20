package sistema_essap_2025;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarculaLaf;

import vista.GUILogin;

public class sistema_essap_2025 {

	public static void main(String[] args) {
		
		try {
            UIManager.setLookAndFeel(new FlatDarculaLaf()); //Libreria FLatlaf para ponerle temas a la app
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
	
		GUILogin gui = new GUILogin();
        
        gui.setVisible(true);
	}

}
