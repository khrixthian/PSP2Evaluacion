package ejerEnvioObjeto;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	static final int PUERTO = 5000;
	Persona p = new Persona();

	public Servidor() {
		ServerSocket skServidor;
		Socket socketKliente;
		p.setNif("12345678U");
		p.setNombre("Maria");
		p.setApellido("Juana Zubiri");
		p.setGenero('H');
		p.setFechaNacimiento("1990, 12, 12");
		try {
			skServidor = new ServerSocket(PUERTO);
			System.out.println("Escutxando el puerto " + PUERTO);

			while ((socketKliente = skServidor.accept()) != null) {
				System.out.println("Conexion desde: " + socketKliente.getInetAddress());
				// envio del objeto
				ObjectOutputStream os = new ObjectOutputStream(socketKliente.getOutputStream());

				os.writeObject(p);
				os.close();
				// recibir el objeto modificado

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
//		ObjectInputStream ois = new ObjectInputStream(socketKliente.getInputStream());
//		try {
//			p = (Persona) ois.readObject();
//		} catch (ClassNotFoundException e) {
//			// TODO Bloque catch generado automáticamente
//			e.printStackTrace();
//		} // guardar el objeto recibido
//		System.out.println(p.toString());
	}

	public static void main(String[] args) {
		new Servidor();

	}

}
