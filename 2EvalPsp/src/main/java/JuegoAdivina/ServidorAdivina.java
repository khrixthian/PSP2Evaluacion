package JuegoAdivina;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorAdivina {
	static final int PUERTO = 5000;
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	private int numJugador = 1;
	Datos datos;

	public ServidorAdivina() {

		ServerSocket skServidor;
		Socket socket;
		int numeroAleatorio = (int) (Math.floor(Math.random() * (25 - 1 + 1)) + 1);
		datos = new Datos();
		datos.setCadena("Adivina un numero entre el 1 y el 25");
		datos.setIntentos(5);
		datos.setIdetificador(numJugador);
		datos.setJuega(true);
		numJugador += 1;

		try {
			skServidor = new ServerSocket(PUERTO);
			while ((socket = skServidor.accept()) != null) {
				entrada = new ObjectInputStream(socket.getInputStream());
				salida = new ObjectOutputStream(socket.getOutputStream());
				salida.writeObject(datos);

				salida.close();
				socket.close();

			}

		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		// TODO Esbozo de método generado automáticamente

	}

}
