import java.util.Random;

public class Cartero implements Runnable { //Implementamos el metodo Runnable para las clases 
	public int nombre;
	public Departamento[] array;
	public Thread hiloCar;
	public int contador=0;
	public Mensaje nota;
	public Random aleatorio = new Random();

	public Cartero() {

	}

	public Cartero(int i, Departamento[] departamento) {
		nombre= i;
		array=departamento;
	}

	public void start() {
		 System.out.println("El cartero "+nombre+" se ha creado.");
		 System.out.println("El cartero "+nombre+" está descansando");
		hiloCar= new Thread(this);
		hiloCar.start(); //iniciamos el hilo
	}

	public void interrupt() {
		System.out.println("El cartero "+nombre+" se ha destruido.");//Destruimos en interrupt en vez de en join para que siga el orden.
		contador=1;
		hiloCar.interrupt();//interrumpimos el hilo
	}

	public void join() {
		try {
			hiloCar.join();
		} catch (InterruptedException e) {
		}

	}

	@Override
	public void run() {

		for(contador=0;contador<1;contador=contador){//iniciamos un contador infinito
			System.out.print("");
			nota=null;
			buscaMisiva();
			if(nota!=null) entrega();
			if(array!=null) {
				recoge(0);
			}
		}

	}

	public void buscaMisiva() {
		nota=null;
		for (int i = 0; i < array.length; i++) {
			if(array[i].detenido==true){
				nota=array[i].misiva;
				array[i].misiva=null;
				array[i].detenido=false;
				break;
			}
		}
	}
	
	public void entrega(){
		char destino=nota.destino;
		Departamento d = array[(int)nota.destino-'A'];
		if(d.disponibles()==true){
			if(contador==0) System.out.println("El cartero " +nombre+ " deposita el mensaje /Mensaje " +nota.numMsg+ " de "+nota.nombre+" a "+destino+ "/en el cajetín");
			if(contador==0) System.out.println("El cartero " +nombre+ " está descansando");
			d.casillero.setMensaje(nota);
			nota=null;
		}else{
			if(contador==0) System.out.println("El cartero "+nombre+" está esperando a entregar el mensaje /Mensaje " +nota.numMsg+ " de "+nota.nombre+" a "+destino+ "/");
			Random aleatorio = new Random();
			long tiempo= (long) aleatorio.nextInt(6);
			try {
				hiloCar.sleep(tiempo);
				
				if(contador==0) entrega();
			} catch (InterruptedException e) {
			}
			recoge(1);
		}
	}

	private void recoge(int j) {
		Departamento d = null;
		for (int i = 0; i < array.length; i++) {
			if(array[i].parado==true){
				d=array[i];
				if(contador==0) System.out.println("El cartero "+nombre+" recoge los mensajes del cajetín del departamento "+d.nombre+".");
				if(j==0){
					if(contador==0) System.out.println("El cartero "+nombre+" está descansando");
				}
				
				d.activa();
				break;
			}
		}
		
	}
}


