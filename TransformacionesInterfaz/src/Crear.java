import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//VENTANA CREAR POSTERIOR A INICIALIZAR
public class Crear {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Crear window = new Crear();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Crear() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNuevoJuego = new JButton("Nuevo Juego");
		btnNuevoJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NuevoJuego.main(null);
			}
		});
		btnNuevoJuego.setBounds(6, 39, 117, 29);
		frame.getContentPane().add(btnNuevoJuego);
		
		JButton btnNewButton = new JButton("Definir Diccionario");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(122, 39, 163, 29);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cargar Diccionario");
		btnNewButton_1.setBounds(281, 39, 163, 29);
		frame.getContentPane().add(btnNewButton_1);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 450, 27);
		frame.getContentPane().add(menuBar);
		
		JMenu mnNuevoJuego = new JMenu("Nuevo Juego");
		mnNuevoJuego.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				NuevoJuego.main(null);
			}
		});
		menuBar.add(mnNuevoJuego);
		
		JMenu mnDefinirDiccionario = new JMenu("Definir Diccionario");
		menuBar.add(mnDefinirDiccionario);
		
		JMenu mnCargarDiccionario = new JMenu("Cargar Diccionario");
		menuBar.add(mnCargarDiccionario);
	}
}
