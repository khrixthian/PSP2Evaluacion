package ejerSeguridad;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Servidor {
	static final int PUERTO = 4000;

	ServerSocket skServidor;
	Socket socket;
	String preg = "¿Cual es la frase?";

	public Servidor() throws NoSuchAlgorithmException, ClassNotFoundException {
		try {
			skServidor = new ServerSocket(PUERTO);
			System.out.println("esperando conexiones");

			while ((socket = skServidor.accept()) != null) {

				// preparacion de la entrada y salida, 1 tiene que estar la salida
				ObjectOutputStream salida3 = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());

				// envia la pregunta inicial
				salida3.writeObject(preg);

				// recibe la frase del cliente
				String mensajerecibido = (String) entrada.readObject();
				System.out.println(mensajerecibido);

				// codifica la frase recibida del cliente
				MessageDigest md = MessageDigest.getInstance("SHA");
				byte dataBytes[] = mensajerecibido.getBytes();
				md.update(dataBytes);
				byte resumen[] = md.digest();

				// envia la frase codificada al cliente
				salida3.writeObject(new String(resumen));

				// recibe la 2 frase del cliente
				String resumenUsuario = (String) entrada.readObject();

				// compara las 2 frases recibidas del cliente y envia el resultado
				if (resumenUsuario.equals(new String(resumen))) {
					salida3.writeObject("es lo mismo");
				} else
					salida3.writeObject("No es lo mismo");
				salida3.close();
				socket.close();
				skServidor.close(); // cierra el servidor
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws NoSuchAlgorithmException, ClassNotFoundException {
		new Servidor();

	}

}
