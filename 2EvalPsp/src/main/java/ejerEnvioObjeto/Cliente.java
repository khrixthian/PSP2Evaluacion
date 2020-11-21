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
	private boolean objModificado = false;
	private ObjectOutputStream os;

	public Cliente() throws ClassNotFoundException, UnknownHostException, IOException {

		try {
			skCliente = new Socket(HOST, PUERTO);

			ObjectInputStream ois = new ObjectInputStream(skCliente.getInputStream());
			p = (Persona) ois.readObject();// guardar el objeto recibido
			System.out.println(p.toString());
			// hasta aki funciona, recibe y lo pinta

			p.setNombre("Carmen");
			p.setFechaNacimiento("1983, 1, 1");

			skCliente.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		// para enviarlo hay que hacer otro socket
		Socket xaEnvio = new Socket(HOST, 5100);
		os = new ObjectOutputStream(xaEnvio.getOutputStream());
		os.writeObject(p);
		System.out.println("ahora lo envio");
		xaEnvio.close();

	}

	public static void main(String[] args) throws ClassNotFoundException, UnknownHostException, IOException {
		new Cliente();

	}

}
