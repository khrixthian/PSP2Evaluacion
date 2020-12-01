package ejerEnvioObjeto;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {

	static final String HOST = "localhost";
	static final int PUERTO = 5000;
	private Persona p;
	private Socket skCliente;

	public Cliente() throws ClassNotFoundException, UnknownHostException, IOException {

		try {
			skCliente = new Socket(HOST, PUERTO);

			ObjectInputStream entrada = new ObjectInputStream(skCliente.getInputStream());
			ObjectOutputStream salida = new ObjectOutputStream(skCliente.getOutputStream());
			p = (Persona) entrada.readObject();// guardar el objeto recibido
			System.out.println(p.toString());

			p.setNombre("Carmen");
			p.setFechaNacimiento("1983, 1, 1");
			// envio del objeto modificado
			salida.writeObject(p);
			salida.close();
			skCliente.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws ClassNotFoundException, UnknownHostException, IOException {
		new Cliente();

	}

}
