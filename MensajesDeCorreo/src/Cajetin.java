import org.apache.commons.math3.optimization.linear.SimplexSolver;

public class Cajetin{
	public Mensaje[] casillero;
	public int capacidad;
	public int entrego=0;
	
	public Cajetin(int capacidad){
		casillero = new Mensaje[capacidad];
		this.capacidad=capacidad;
	}
	
	public synchronized int getDisponibles(){
		int disponibles=0;
		for (int i = 0; i < casillero.length; i++) {
			if(casillero[i]==null) disponibles=disponibles+1;
		}
		return disponibles;
	}
	
	public synchronized void setMensaje(Mensaje misiva){
		int i=0;
		do{
			if(casillero[i]==null){
				casillero[i]=misiva;
				break;
			}
			i++;
		}while(i<casillero.length);
		
	}
}