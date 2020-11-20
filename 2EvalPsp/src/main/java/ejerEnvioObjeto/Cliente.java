package ejerEnvioObjeto;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Cliente {

	static final String HOST = "localhost";
	static final int PUERTO = 5000;
	private Persona p;
	private Socket skCliente;

	public Cliente() {
		try {
			skCliente = new Socket(HOST, PUERTO);

			ObjectInputStream ois = new ObjectInputStream(skCliente.getInputStream());

			try {
				p = (Persona) ois.readObject();// guardar el objeto recibido
				p.setNombre("Carmen");
				p.setFechaNacimiento("1983, 1, 1");
				System.out.println(p.toString());

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// enviar persona modificado
//		ObjectOutputStream os;
//		try {
//			os = new ObjectOutputStream(skCliente.getOutputStream());
//			os.writeObject(p);
//			os.close();
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

	}

	public static void main(String[] args) {
		new Cliente();

	}

}
