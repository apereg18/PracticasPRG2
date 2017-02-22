
public class Mensajeros {
	
	public static Mensaje[] furgoneta;
	public static int cantidad=0;
	public static int nDptos;
	
	public static void nuevaFurgoneta(int nDptos1){
		furgoneta = new Mensaje[nDptos1*10];
		nDptos=nDptos1*10;
		cantidad=0;
	}
	
	public synchronized static Mensaje getMensaje(){
		Mensaje misiva = furgoneta[0];
		if(cantidad>0)cantidad--;
		for (int i = 0; i < furgoneta.length-1; i++) {
			furgoneta[i]=furgoneta[i+1];
			
		}
		furgoneta[nDptos-1]=null;
		return misiva;
	}
	
	public synchronized static void setMensaje(Mensaje misiva){
		furgoneta[cantidad]=misiva;
		cantidad++;
	}

	public static int espacio() {
		int espacio = 0;
		for (int i = 0; i < furgoneta.length; i++) {
			if(furgoneta[i]==null) espacio++;
			
		}
		return espacio;
	}
}
