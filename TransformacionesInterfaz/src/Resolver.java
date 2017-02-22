import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

//VENTANA QUE SE ABRE PARA RESOLVER
public class Resolver {

	private JFrame frame;
	private ArrayList<String> pasatiempo = new ArrayList<String>();
	JTextArea textArea;
	JTextArea textAreagained;
	String[] solucion = null;
	char caracteres;
	JTextArea[][] resolucionAuto;
	String[] PalabrasDicc= new String[25143];
	private JTextField textField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Resolver window = new Resolver();
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
	public Resolver() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel lblDiccionario = new JLabel("Diccionario");
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblAyuda = new JLabel("Ayuda");
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("True");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				textArea.setText("");
				if(chckbxNewCheckBox.isSelected()==true){
					char []filtrado= textField.getText().toCharArray();
					for(int i=0; i<25143;i++){
						if(PalabrasDicc[i]!=null){
							for(int j=0; j<PalabrasDicc[i].length(); j++){
								for(int k=0; k<filtrado.length; k++){
									if(PalabrasDicc[i].charAt(j)==filtrado[k]){
										j=50;
										k=26;
										textArea.append(PalabrasDicc[i]+"\r\n");
									}
								}
							}
						}
					}
				}else{
					for (int q = 0; q < PalabrasDicc.length; q++) {
						if(PalabrasDicc[q]!=null){
							textArea.append(PalabrasDicc[q]+"\n");
						}
					}
				}
			}
		});
		
		JLabel lblFiltrarPorLetras = new JLabel("Filtrar por Letras");
		
		textField = new JTextField();
		textField.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDiccionario, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblFiltrarPorLetras)
							.addGap(91)
							.addComponent(lblAyuda, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
						.addComponent(chckbxNewCheckBox)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(98, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDiccionario)
						.addComponent(lblAyuda)
						.addComponent(lblFiltrarPorLetras))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(76, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGap(6)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(chckbxNewCheckBox)
							.addGap(55))))
		);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		frame.getContentPane().setLayout(groupLayout);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnCrear = new JMenu("Crear");
		menuBar.add(mnCrear);
		
		JMenuItem mntmNuevo = new JMenuItem("Nuevo");
		mntmNuevo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				NuevoJuego.main(null);
			}
		});
		mnCrear.add(mntmNuevo);
		
		JMenu mnResolver = new JMenu("Resolver");
		menuBar.add(mnResolver);
		
		JMenuItem mntmCargar = new JMenuItem("Cargar");
		mnResolver.add(mntmCargar);
		
		JMenuItem mntmAyuda = new JMenuItem("Ayuda");
		mntmAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textAreagained.setText(String.valueOf(caracteres));
			}
		});
		mnResolver.add(mntmAyuda);
		
		JMenuItem mntmResolucionAutomatica = new JMenuItem("Resolucion Automatica");
		mnResolver.add(mntmResolucionAutomatica);
		
		JMenuItem menuItem = new JMenuItem("");
		mnResolver.add(menuItem);
		
		JMenu mnDiccionario = new JMenu("Diccionario");
		menuBar.add(mnDiccionario);
		
		JMenuItem mntmCargar_1 = new JMenuItem("Cargar");
		mntmCargar_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				cargaDiccionario();
				
			}

			private void cargaDiccionario() {
				// TODO Auto-generated method stub
				JFileChooser Diccionarioc = new JFileChooser();

				if(Diccionarioc.showOpenDialog(null) == Diccionarioc.APPROVE_OPTION){
					String ruta = Diccionarioc.getSelectedFile().getAbsolutePath();
					System.out.println(ruta);
					try {
						FileReader fw = new FileReader(ruta);
						BufferedReader bf = new BufferedReader(fw); 
						String fline =bf.readLine();
						String [] first = fline.split(" ");
						pasatiempo.add(first[0]);
						pasatiempo.add(first[1]);
						pasatiempo.add(first[2]);
						resolucionAuto = new JTextArea[Integer.parseInt(first[2])][first[1].length()];
						
						String leido = bf.readLine();
						int contador=0;
						while(!leido.equals("")){
							System.out.println(leido);
							textArea.append(leido+"\n");
							PalabrasDicc[contador]=leido;
							contador++;
							pasatiempo.add(leido);
							leido=bf.readLine();
							if(leido==null) break;
							
						}
						
						leido = bf.readLine();

						if(leido!=null){
							solucion=leido.split(" ");
						}else{
							CreaDiccionario();							
						}
						
						fw.close();
						
						for (int j = 0; j < pasatiempo.get(1).length(); j++) {
							JLabel label =new JLabel();
							label.setBounds(250+j*25,30,20,20);
							frame.getContentPane().add(label);
							label.setBackground(new Color(0,255,0));
							frame.getContentPane().repaint();
							label.setText(String.valueOf(pasatiempo.get(0).charAt(j)));
							
						}
						int i;
						//Matriz Resolucion
						for (i = 0; i <Integer.parseInt(pasatiempo.get(2)); i++) {//+2 para palabra inicial y palabra inicial
							for (int j = 0; j < pasatiempo.get(1).length(); j++) {
								final int row=i;
								final int colum=j;
								JTextArea textArea_1 =new JTextArea();
								textArea_1.setBounds(250+j*25,55+i*25,20,20);
								frame.getContentPane().add(textArea_1);
								textArea_1.addKeyListener(new KeyListener() {
									
									@Override
									public void keyTyped(KeyEvent e) {
										// TODO Auto-generated method stub
										String letraCuadro=textArea_1.getText();
										if(letraCuadro.length()==1){
											e.consume();
										}
									}
									
									@Override
									public void keyReleased(KeyEvent e) {
										// TODO Auto-generated method stub
										
									}
									
									@Override
									public void keyPressed(KeyEvent e) {
										// TODO Auto-generated method stub
										
									}
								});
								frame.getContentPane().repaint();
								textArea_1.addFocusListener(new FocusListener() {
									
									@Override
									public void focusLost(FocusEvent e) {
										// TODO Auto-generated method stub
										
									}
									
									@Override
									public void focusGained(FocusEvent e) {
										// TODO Auto-generated method stub
										textAreagained=textArea_1;
										caracteres=solucion[row+1].charAt(colum);
										
									}
								});
								mntmResolucionAutomatica.addActionListener(new ActionListener() {
									
									@Override
									public void actionPerformed(ActionEvent e) {
										
										textArea_1.setText(String.valueOf(solucion[row+1].charAt(colum)));
										
									}
								});
								
								
							}
						}
						
						for (int j = 0; j < pasatiempo.get(0).length(); j++) {
							JLabel label =new JLabel();
							label.setBounds(250+j*25,25+i*30,20,20);
							frame.getContentPane().add(label);
							label.setBackground(new Color(0,255,0));
							frame.getContentPane().repaint();
							label.setText(String.valueOf(pasatiempo.get(1).charAt(j)));
							
						}
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			
		});
		mnDiccionario.add(mntmCargar_1);
	}

	protected void CreaDiccionario() {
		Palabras q = new Palabras();
		String lectura= textArea.getText();
		String[] vectorleido=lectura.split(" ");
		
		for (int i = 0; i < vectorleido.length-1; i++) {
			System.out.println("++++++++++++++++++++++++++"+vectorleido[i]);
			q.vector[q.contador]=vectorleido[i];
			q.contador++;
			
		}
		Main resu = new Main();
		resu.setPalabras(q, Integer.parseInt(pasatiempo.get(2)));
		solucion=resu.getSolucion();
		
	}
}
