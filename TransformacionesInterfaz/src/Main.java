import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;




public class Main {
	public Resolver resol;
	public static String[] solventacion;
	public static Palabras p;
	public static int max;
	
	public static void main() {
//		Scanner scan = new Scanner(System.in);
		
		ArrayList<int[]> soluciones;
		
	

		/*while(scan.hasNext()){
			String lee = scan.nextLine();
			if(!lee.equals("")){
				p.vector[p.contador]=lee;
				p.contador++;
			}else{
				palabrasCambio(scan, p);
				break;
			}
		}
		scan.close();*/
		p.contador2=0;
		//Sacamos la pareja de palabras del vector

		do{
			String[] pare=new String[2];//pare= parejas, cada cual ira almacenada en una posicion del vector pare
			pare[0]=p.palabrasCambio[p.contador2];
			pare[1]=p.palabrasCambio[p.contador2+1];
			p.contador2+=2;//podriamos hacer un p.contador++ entre las dos lineas anteriores pero para simplificar lo hacemos aqui en una sola linea
			soluciones = new ArrayList<int[]>();
			if(pare[0].length()!=pare[1].length()){//si las palabras tienen distinta longitud no se podran igualar en ningun caso por lo tanto tendran la condicion de que si se cumple eso haga un syso de que no hay solucion
				System.out.println("Sin solución.");
				break;
			}
			else{
				
				for (int i = 0; i < p.contador; i++) {
					int[] indice = new int[25143];//el indice lo guardaremos para saber en que paso de cambio va nuestra palabra, porque de casa a pozo se hacen varios pasos por ejemplo, este indice lo utilizaremos mas tarde para poder hacer backtracking y poder volver atras para obtener diferentes soluciones en nuestras palabras
					
					for (int k = 0; k < p.contador; k++) {
						if(p.vector[k].equals(pare[0])) indice[0]=k;//aqui colocamos la palabra que va en el vector pare con posicion cero en la posicion 0 del vector indice por lo tanto la primera palabra del vector indice sera la que le metamos
					}
					
					for (int j = 1; j < indice.length; j++) {
						indice[j]=-1;
					}
					solucionRecursivo(pare[0], pare[1], i, p, indice, 1);//desde, hasta, posición diccionario, diccionario, indice de palabras, contador del indice
					boolean yata=false;

					for (int j = 0; j < soluciones.size(); j++) {
						int[] q = soluciones.get(j);
						if(soluciones.get(j).equals(indice)==true){
							yata = true;
						}
					}
					
					if(yata==false){
						if(indice[1]!=-1){
							soluciones.add(indice);
						}
					}

					
				}
				int tamano=100000;
				int[] minima=null;
				int[] evaluada=null;
				
				
				for (int i = 0; i < soluciones.size(); i++) {
					evaluada=soluciones.get(i);
					
					int conta=0;
					for (int j = 0; j < evaluada.length; j++) {
						if(evaluada[j]!=-1) conta++;
					}
					if(conta==max){
						minima = soluciones.get(i);
					}
				}
				if(soluciones.size()==0){
					JOptionPane.showMessageDialog(null, "Esto no tiene solucion con los datos propuestos");
				}
				else{
					int contador=0;
					for (int j  = 0; j < minima.length; j++) {
						if(minima[j]!=-1) contador++;
					}
					
					solventacion = new String[contador];
					
					for (int j  = 0; j < contador; j++) {
						//if(minima[j]!=-1) System.out.println(p.vector[minima[j]]);
						solventacion[j]=p.vector[minima[j]];
					}
					//System.out.println();
				}
			}

		}while(p.palabrasCambio[p.contador2+1]!=null);//si no es distinto de null sera porque nuestro diccionario no tiene tantas palabras como huecos de memoria y por lo tanto quedan huecos asignados al vector vacios que si los imprimimos quedaria como null por lo tanto solo queremos que haga el bucle mientras encuentre palabras
	}

	private static String solucionRecursivo(String string, String string2, int i, Palabras p, int[] indice, int k) {//metodo recursivo que utilizaremos para ir sacando solucion a solucion y que vuelva a hacerlo hasta que se cumpla alguna de las condiciones restrictivas que le hemos puesto dentro al metodo
		String palabra = p.vector[i];
		if(palabra!=null){
			boolean repetido = false;
			for (int j = 0; j < k; j++) {
				if(indice[j]==i) repetido=true;
			}
			if(repetido==true && i<p.contador) solucionRecursivo(string, string2, i+1, p, indice, k);
			else if(palabra.length()!=string.length() && i<p.contador) solucionRecursivo(string, string2, i+1, p, indice, k);
			else{
				int cont=0;
				char[] pal1 = palabra.toCharArray();
				char[] pal2 = string.toCharArray();
				for (int j = 0; j < palabra.length(); j++) {
					if(pal1[j]!=pal2[j]) cont++;
				}
				if(cont==1){

					indice[k]=i;
					if(palabra.equals(string2)){
						return string2;
					}
					else return palabra + solucionRecursivo(palabra, string2, 0, p, indice, k+1);
				}
				else if(i<p.contador) solucionRecursivo(string, string2, i+1, p, indice, k);

			}
		}else{
			if(k>2){// si no ponemos mayor que 1 sale de la primera posicion y nos da un indexoutofbounds
				k=k-2;
				indice[k]=-1;
				int posi=indice[k-1];
				solucionRecursivo(p.vector[posi], string2, posi+2, p, indice, k);
			}
		}
		return null;
	}

	private static void palabrasCambio(Scanner scan, Palabras p) {
		while(scan.hasNext()){
			String lee = scan.nextLine();//con nextLine leemos hasta el enter, si utilizaramos next leeriamos solo hasta el espacio
			if(!lee.equals("")){
				String[] pare = lee.split(" ");//utilizamos esta sentencia para separar las palabras que tenemos en el string lee que las separe en dos si hay un espacio entre ellas y que las guarde en posiciones de memoria diferente
				p.palabrasCambio[p.contador2]=pare[0];//guarda la primera antes del split aqui y la segunda en pare[1]
				p.palabrasCambio[p.contador2+1]=pare[1];
				p.contador2+=2;
			}else{
				//p.imprimes();
				break;
			}

		}

	}

	public String[] getSolucion() {
		return solventacion;
		
	}

	public void setPalabras(Palabras q, int n) {
		p=q;
		max=n;
		
	}
}

class Palabras{
	public String[] vector = new String[25143];//para almacenar las palabras del diccionario
	public String[] palabrasCambio= new String[25143];//para almacenar las palabras que iran saliendo al realizar los cambios
	public int contador=0;//para las palabras del diccionario
	public int contador2=0;//para la palabras del cambio

	public void imprimes() {
		for (int i = 0; i < vector.length; i++) {
			if(vector[i]!=null) System.out.println("*** "+vector[i]);
		}
		for (int i = 0; i < palabrasCambio.length; i++) {
			if(palabrasCambio[i]!=null) System.out.println("++++ "+palabrasCambio[i]);
		}

	}
}

class Soluciones{//Aqui transladamos las soluciones desde el metodo recursivo
	public ArrayList<String[]> sol = new ArrayList<String[]>();


}




