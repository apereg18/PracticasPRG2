import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class NuevoJuego {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuevoJuego window = new NuevoJuego();
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
	public NuevoJuego() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//cuadros de texto usuario
		textField = new JTextField();
		textField.setBounds(228, 43, 130, 26);
		frame.getContentPane().add(textField);//palabra inicial
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(228, 72, 130, 26);
		frame.getContentPane().add(textField_1);//palabra final
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(228, 98, 130, 26);
		frame.getContentPane().add(textField_2);//numero de palabras
		
		JLabel lblPalabrasIntermedias = new JLabel("Palabras Intermedias");
		lblPalabrasIntermedias.setBounds(204, 132, 154, 19);
		frame.getContentPane().add(lblPalabrasIntermedias);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(214, 165, 130, 107);
		frame.getContentPane().add(scrollPane_1);
		
		JTextArea textArea_1 = new JTextArea();
		scrollPane_1.setViewportView(textArea_1);
		
		//labels 
		JLabel lblPalabraInicial = new JLabel("Palabra Inicial");
		lblPalabraInicial.setBounds(31, 46, 108, 21);
		frame.getContentPane().add(lblPalabraInicial);
		
		JLabel lblPalabraFinal = new JLabel("Palabra Final");
		lblPalabraFinal.setBounds(31, 75, 108, 21);
		frame.getContentPane().add(lblPalabraFinal);
		
		JLabel lblNumeroPalabras = new JLabel("Numero Palabras");
		lblNumeroPalabras.setBounds(31, 103, 108, 16);
		frame.getContentPane().add(lblNumeroPalabras);
		
		JLabel lblDiccionario = new JLabel("Diccionario");
		lblDiccionario.setBounds(31, 131, 108, 21);
		frame.getContentPane().add(lblDiccionario);
		
		JTextArea textArea = new JTextArea();	//text area dicc	
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(31, 165, 130, 107);
		frame.getContentPane().add(scrollPane);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(12, 12, 432, 19);
		frame.getContentPane().add(menuBar);
		
		JMenu mnCrear = new JMenu("Crear");
		menuBar.add(mnCrear);
		
		JMenuItem mntmNuevo = new JMenuItem("Nuevo");
		mntmNuevo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				NuevoJuego.main(null);
			}
		});
		
		JMenuItem mntmGuardarPasatiempo = new JMenuItem("Guardar Pasatiempo");
		mntmGuardarPasatiempo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				GuardaPasatiempo();
			}

			private void GuardaPasatiempo() {
				// TODO Auto-generated method stub
				JFileChooser Pasatiempog =new JFileChooser();
				if(Pasatiempog.showSaveDialog(null) == Pasatiempog.APPROVE_OPTION){
					String ruta = Pasatiempog.getSelectedFile().getAbsolutePath();
					System.out.println(ruta);
					String pasag;
					
					try {
						FileWriter fw = new FileWriter(ruta);
						pasag = textField.getText()+" "+ textField_1.getText()+" "+textField_2.getText()+"\n"+textArea.getText()+"\n\n"+textArea_1.getText();
						fw.write(pasag);
						
						
						fw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				}
				
				
			}
		});
		mnCrear.add(mntmGuardarPasatiempo);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Cargar Pasatiempo");
		mntmNewMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				CargarPasatiempo();
				
			}

			private void CargarPasatiempo() {
				// TODO Auto-generated method stub
				JFileChooser pasac = new JFileChooser();

				if(pasac.showOpenDialog(null) == pasac.APPROVE_OPTION){
					String ruta =pasac.getSelectedFile().getAbsolutePath();
					System.out.println(ruta);
//					String diccionariocargado= "casa pasa adolfo exagerado";
//					String[] vectordiccionariocargado = diccionariocargado.split(" ");
					try {
						FileReader fw = new FileReader(ruta);
						BufferedReader bf = new BufferedReader(fw); 
						String fline =bf.readLine();
						String [] first = fline.split(" ");
						textField.setText(first[0]);
						textField_1.setText(first[1]);
						textField_2.setText(first[2]);
						String leido = bf.readLine();
						while(!leido.equals("")){
							
							System.out.println(leido);
							textArea.append(leido+"\n");
							leido=bf.readLine();
						}
						
						fw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		mnCrear.add(mntmNewMenuItem);
		mnCrear.add(mntmNuevo);
		
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
		
		JMenu mnDiccionario = new JMenu("Diccionario");
		menuBar.add(mnDiccionario);
		
		JMenuItem mntmCargar = new JMenuItem("Cargar");
		mntmCargar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				CargaDiccionario();
			}

			private void CargaDiccionario() {
				JFileChooser Diccionarioc = new JFileChooser();

				if(Diccionarioc.showOpenDialog(null) == Diccionarioc.APPROVE_OPTION){
					String ruta = Diccionarioc.getSelectedFile().getAbsolutePath();
					System.out.println(ruta);
//					String diccionariocargado= "casa pasa adolfo exagerado";
//					String[] vectordiccionariocargado = diccionariocargado.split(" ");
					try {
						FileReader fw = new FileReader(ruta);
						BufferedReader bf = new BufferedReader(fw); 
						
						String leido = bf.readLine();
						while(!leido.equals("")){
							System.out.println(leido);
							textArea.append(leido+"\n");
							leido=bf.readLine();
						}
						
						fw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
			}
		});
		mnDiccionario.add(mntmCargar);
		
		JMenuItem mntmGuardar = new JMenuItem("Guardar");
		mntmGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				GuardaDiccionario();
			}

			private void GuardaDiccionario() {
				JFileChooser Diccionariog =new JFileChooser();
				if(Diccionariog.showSaveDialog(null) == Diccionariog.APPROVE_OPTION){
					String ruta = Diccionariog.getSelectedFile().getAbsolutePath();
					System.out.println(ruta);
					String diccionarioguardado= textArea.getText();
					String[] vectordiccionarioguardado = diccionarioguardado.split(" ");
					try {
						FileWriter fw = new FileWriter(ruta);
						for (int i = 0; i < vectordiccionarioguardado.length; i++) {
							fw.write(vectordiccionarioguardado[i]+ "\n");
						}
						fw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				}
			}
		});
		mnDiccionario.add(mntmGuardar);
		
//		JLabel lblPalabrasIntermedias = new JLabel("Palabras Intermedias");
//		lblPalabrasIntermedias.setBounds(204, 132, 154, 19);
//		frame.getContentPane().add(lblPalabrasIntermedias);
//		
//		JScrollPane scrollPane_1 = new JScrollPane();
//		scrollPane_1.setBounds(214, 165, 130, 107);
//		frame.getContentPane().add(scrollPane_1);
//		
//		JTextArea textArea_1 = new JTextArea();
//		scrollPane_1.setViewportView(textArea_1);
	}
}
