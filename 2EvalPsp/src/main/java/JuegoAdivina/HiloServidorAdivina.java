package JuegoAdivina;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class HiloServidorAdivina extends Thread {
	Datos datos;
	ObjetoCompartido objCompar;
	static final int PUERTO = 5000;
	static final String IP = "localhost";
	Scanner sc;

	public HiloServidorAdivina(ObjetoCompartido x) {
		this.objCompar = x;
	}

	@Override
	public void run() {
		try {
			Socket socket = new Socket(IP, PUERTO);
			ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());

			datos = (Datos) entrada.readObject();
			System.out.println("Dime un numero?");
			int resp = sc.nextInt();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}
}
