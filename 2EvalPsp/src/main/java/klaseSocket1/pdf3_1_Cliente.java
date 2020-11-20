package klaseSocket1;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;

public class pdf3_1_Cliente {
	static final String HOST = "localhost";
	static final int PUERTO = 5000;

	public pdf3_1_Cliente() {
		try {
			Socket skCliente = new Socket(HOST, PUERTO);
			InputStream aux = skCliente.getInputStream();
			DataInputStream flujo = new DataInputStream(aux);
			System.out.println(flujo.readUTF());
			skCliente.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] arg) {
		new pdf3_1_Cliente();
	}
}
