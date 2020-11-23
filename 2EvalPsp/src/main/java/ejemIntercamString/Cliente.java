package ejemIntercamString;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
	private final int PUERTO = 5000;
	private final String IP = "127.0.0.1";

	public void iniciar() {
		Socket cliente = null;
		ObjectInputStream entrada = null;
		ObjectOutputStream salida = null;
		try {
			cliente = new Socket(IP, PUERTO);
			System.out.println("Conexi�n realizada con servidor");
			entrada = new ObjectInputStream(cliente.getInputStream());
			salida = new ObjectOutputStream(cliente.getOutputStream());
			String mensaje = (String) entrada.readObject();
			System.out.println(mensaje);
			Scanner input = new Scanner(System.in);
			String clave = input.nextLine();
			salida.writeObject(clave);
			mensaje = (String) entrada.readObject();
			System.out.println(mensaje);
			if (!mensaje.substring(0, 5).equalsIgnoreCase("ERROR")) {
				String opcion = input.nextLine();
				salida.writeObject(opcion);
				mensaje = (String) entrada.readObject();
				System.out.println(mensaje);
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			try {
				if (cliente != null)
					cliente.close();
				if (entrada != null)
					entrada.close();
				if (salida != null)
					salida.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Fin cliente");
		}
	}

	public static void main(String[] args) {
		Cliente c1 = new Cliente();
		c1.iniciar();
	}
}// Cliente___