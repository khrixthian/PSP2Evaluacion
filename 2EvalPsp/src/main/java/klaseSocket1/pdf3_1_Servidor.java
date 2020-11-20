package klaseSocket1;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class pdf3_1_Servidor {
	static final int PUERTO = 5000;

	public pdf3_1_Servidor() {
		ServerSocket skServidor;
		try {
			skServidor = new ServerSocket(PUERTO);
			System.out.println("Escutxando el puerto " + PUERTO);

			for (int numCli = 0; numCli < 3; numCli++) {
				Socket skCliente = skServidor.accept();// krea el obj cliente
				System.out.println("Sirviendo al cliente  " + numCli);
				OutputStream aux = skCliente.getOutputStream();
				DataOutputStream flujo = new DataOutputStream(aux);
				flujo.writeUTF("Hola cliente " + numCli);
				skCliente.close();
			}
			System.out.println("Por hoy no atiendo a mas clientes.");

		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new pdf3_1_Servidor();

	}

}
