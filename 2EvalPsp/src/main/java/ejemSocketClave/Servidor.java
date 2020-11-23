package ejemSocketClave;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	private final int PUERTO = 5000;
	private final String clave = "abc";

	public void iniciar() {
		ServerSocket servidor = null;
		Socket cliente = null;
		ObjectInputStream entrada = null;
		ObjectOutputStream salida = null;
		try {
			servidor = new ServerSocket(PUERTO);
			System.out.println("Esperando conexiones del cliente...");
			cliente = servidor.accept();
			System.out.println("Cliente conectado");
			salida = new ObjectOutputStream(cliente.getOutputStream());
			entrada = new ObjectInputStream(cliente.getInputStream());
			salida.writeObject("Introduce contraseña:");
			String mensaje = (String) entrada.readObject();
			if (mensaje.equals(clave)) {
				salida.writeObject("Bienvenido.\nQué desear hacer?\n1)Sumar a b\n2)Multiplicar a b");
				mensaje = (String) entrada.readObject();
				System.out.println("Recibido: " + mensaje);
				String[] partes = mensaje.split(" ");
				if (((String) partes[0]).equalsIgnoreCase("sumar")) {
					int a = Integer.parseInt(partes[1]);
					int b = Integer.parseInt(partes[2]);
					salida.writeObject(String.valueOf(a + b));
				} else if (((String) partes[0]).equalsIgnoreCase("multiplicar")) {
					int a = Integer.parseInt(partes[1]);
					int b = Integer.parseInt(partes[2]);
					salida.writeObject(String.valueOf(a * b));
				} else {
					salida.writeObject("ERROR Opción incorrecta");
				}
			} else {
				salida.writeObject("ERROR Contraseña incorrecta.");
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			try {
				if (servidor != null)
					servidor.close();
				if (cliente != null)
					cliente.close();
				if (entrada != null)
					entrada.close();
				if (salida != null)
					salida.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Fin servidor");
		}
	}

	public static void main(String[] args) {
		Servidor s1 = new Servidor();
		s1.iniciar();
	}
}
