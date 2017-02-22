import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.math3.distribution.ExponentialDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;

public class Main {

	public static int tiempoSimulacion;
	
	public static Logger logger = Logger.getLogger("ccia.labarberia");
	static {
		logger.setLevel(Level.OFF)
		//logger.setLevel(Level.WARNING)
		;}

	
	public static void main(String[] args) throws InterruptedException {
		Scanner sc = new Scanner(System.in);
		
		int nDepartamentos = sc.nextInt();
		int mCarteros = sc.nextInt();
		int tamCajetines = sc.nextInt();
		tiempoSimulacion = sc.nextInt();
		
		Departamento.distribucionNormal = new NormalDistribution(sc.nextInt(),sc.nextInt());
		Departamento.distribucionExponencial = new ExponentialDistribution(sc.nextInt());
		
		sc.close();
		
		Departamento.nDptos = nDepartamentos;
		
		Departamento[] departamento = new Departamento[nDepartamentos];
		for (int i=0; i<nDepartamentos; i++){
			departamento[i] = new Departamento((char) (i+'A'),tamCajetines);
			departamento[i].start();
		}

		Cartero[] cartero = new Cartero[mCarteros];
		for (int i=0; i<mCarteros; i++){
			cartero[i] = new Cartero(i+1,departamento);
			cartero[i].start();
		}
		
		Thread.sleep(tiempoSimulacion*1000);
		
		for (int j=0; j<mCarteros; j++){
			cartero[j].interrupt();
		}
	
		for (int i=0; i<nDepartamentos; i++){
			departamento[i].interrupt();
		}
	
		for (int i=0; i<nDepartamentos; i++){
			departamento[i].join();
		}
		
		for (int j=0; j<mCarteros; j++){
			cartero[j].join();
		}
	}

}

