import java.util.Scanner;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		String cadena=s.nextLine();
		Stack<String> pila=new Stack<String>();
		int i=0;
		
		if(correccion(cadena, pila, i )==true){
			System.out.println("Delimitadores Balanceados");
		}
		else{
			pila.pop();
			System.out.println("Delimitadores sin balancear");
		}

	}
	public static boolean correccion(String cadena2, Stack<String> pila2, int posicion){
		
		
		if(cadena2.charAt(posicion)=='$' && pila2.empty()){
			return true;
		}else if(cadena2.charAt(posicion)=='$' && !pila2.empty()) {
			return false;
		}
			
		if((pila2.empty()) && (cadena2.charAt(posicion)==')' || cadena2.charAt(posicion)==']' || cadena2.charAt(posicion)=='}')){
			return false;
		}
		
		if(cadena2.charAt(posicion)=='('){
			pila2.push("(");
		}
		
		if(cadena2.charAt(posicion)=='{'){
			pila2.push("{");
		}
		
		if(cadena2.charAt(posicion)=='['){
			pila2.push("[");
		}
		
		if(cadena2.charAt(posicion)==')' || cadena2.charAt(posicion)=='}' || cadena2.charAt(posicion)==']'){
			
			if(cadena2.charAt(posicion)==')' && pila2.peek()!="("){
				return false;
			}else if(cadena2.charAt(posicion)=='('){
				pila2.pop();
		}
			if(cadena2.charAt(posicion)=='}' && pila2.peek()!="{"){
				return false;
			}else if(cadena2.charAt(posicion)=='{'){
				pila2.pop();
		}
		
			if(cadena2.charAt(posicion)==']' && pila2.peek()!="["){
				return false;
			}else if(cadena2.charAt(posicion)=='['){
				pila2.pop();
		}
		
			
		}
		return correccion (cadena2, pila2, posicion+1);
	}
}
