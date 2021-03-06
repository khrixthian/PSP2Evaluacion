package ejerEnvioObjeto;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	static final int PUERTO = 5000;
	private Persona p = new Persona();
	private ObjectInputStream ois;
	private Persona pRecibida = new Persona();

	public Servidor() throws ClassNotFoundException, IOException {
		ServerSocket skServidor;
		Socket socket;
		p.setNif("12345678U");
		p.setNombre("Maria");
		p.setApellido("Juana Zubiri");
		p.setGenero('H');
		p.setFechaNacimiento("1990, 12, 12");
		System.out.println(p.toString());
		try {
			skServidor = new ServerSocket(PUERTO);
			System.out.println("Escutxando el puerto " + PUERTO);

			while ((socket = skServidor.accept()) != null) {
				System.out.println("Conexion desde: " + socket.getInetAddress());
				// envio del objeto
				ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
				salida.writeObject(p);

				// recibir el objeto modificado
				pRecibida = (Persona) entrada.readObject();
				System.out.println(pRecibida.toString());

				socket.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		new Servidor();

	}

}
