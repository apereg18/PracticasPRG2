import java.util.Random;

import org.apache.commons.math3.distribution.ExponentialDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;

public class Departamento implements Runnable {

	public static NormalDistribution distribucionNormal;
	public static ExponentialDistribution distribucionExponencial;
	public static int nDptos;
	public char nombre;
	public int tamano;
	public Thread hiloDep;
	public int contador=0;
	public Mensaje misiva;
	public char destino;
	public int numMsg=1;
	public boolean detenido=false;
	public Cajetin casillero;
	public boolean parado=false;
	

	public Departamento(char c, int tamCajetines) {
		nombre=c;
		tamano=tamCajetines;
		casillero= new Cajetin(tamano);
	}

	public void start() {
		System.out.println("El departamento " +nombre+ " se ha creado");//SE CREA EL NOMBRE DEL DEPARTAMENTO
		hiloDep= new Thread(this);
		hiloDep.start();//iniciamos el hilo 
		Mensajeros.nuevaFurgoneta(nDptos);
	}
	public void interrupt() {
		System.out.println("El departamento " +nombre+ " se ha destruido");//Destruimos el departamento en este metodo para que primero aparezca que se destruye uno u otro
		contador=1;
		detenido=false;
		parado=false;
		hiloDep.interrupt();//interrumpimos el hilo
	}
	public void join() {
		try {
			hiloDep.join();
		} catch (InterruptedException e) {
		}

	}
	@Override
	public void run() {
		long tiempo;
		for(contador=0;contador<1;contador=contador){//iniciamos un contador infinito
			tiempo= (long) temporizador();
			try {
				if(parado==false){
					hiloDep.sleep(tiempo);//el hilo duerme el tiempo calculado con la distribucion normal
					detenido=true;
					msgOut();
					numMsg= numMsg+1;
					do {
						hiloDep.sleep(1);
					} while (detenido==true);
					tiempo=(long) distribucionExponencial.sample();
					hiloDep.sleep(tiempo);
					parado=true;
				}
			} catch (InterruptedException e) {

			}

		}

	}
	public void msgOut() {
		Random num= new Random();
		destino=nombre;
		while(destino==nombre){
			int n;
			n=num.nextInt(nDptos);
			destino=(char)(n+65);//65 es el numero del codigo de la A mayuscula.
		}
		misiva= new Mensaje(numMsg, destino, nombre);
		if(contador==0) System.out.println("El departamento "+nombre+" desea enviar el mensaje "+numMsg+" al departamento "+destino+".");
	}

	public void msgIn() {
		for (int i = 0; i < tamano; i++) {
			if(casillero.casillero[i]==null){

				break;
			}else{
				if(contador==0) System.out.println("El departamento "+nombre+" recibe el mensaje "+casillero.casillero[i].numMsg+" del departamento "+casillero.casillero[i].nombre+".");
				casillero.casillero[i]=null;
			}
		}

	}

	public long temporizador() {
		int tiempo;
		tiempo=(int) distribucionNormal.sample();//Saca un valor de la distribucion de la distribucion, el cast int es para que el valor sea mayor que cero
		if (tiempo<0) tiempo=-tiempo;
		return tiempo;


	}
	public synchronized boolean disponibles(){
		if(casillero.getDisponibles()>=1){
			return true;
		}else{
			return false;
		}
	}
	public synchronized void activa() {
		parado=false;	
		msgIn();
	}

}
