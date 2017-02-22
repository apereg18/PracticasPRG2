import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//VENTANA PRINCIPAL
public class Inicializar {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicializar window = new Inicializar();
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
	public Inicializar() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//CLOSE LO CIERRA TODO, DISPOSE MANTIENE ABIERTAS
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Crear");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Crear.main(null);//llamamos a la clase crear para abrir una ventana nueva
			}
		});
		btnNewButton.setBounds(41, 119, 145, 38);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Resolver");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Resolver.main(null);//llamamos a la clase resolver
			}
		});
		btnNewButton_1.setBounds(251, 119, 145, 38);
		frame.getContentPane().add(btnNewButton_1);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(12, 12, 432, 22);
		frame.getContentPane().add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Crear");
		menuBar.add(mnNewMenu);
		
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Nuevo");
		mntmNewMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				NuevoJuego.main(null);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		
		JMenu mnResolver = new JMenu("Resolver");
		menuBar.add(mnResolver);
		
		JMenuItem mntmResolucion = new JMenuItem("Resolucion");
		mntmResolucion.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Resolver.main(null);
			}
		});
		mnResolver.add(mntmResolucion);
	}
}
