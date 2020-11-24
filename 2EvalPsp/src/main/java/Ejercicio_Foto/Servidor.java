package Ejercicio_Foto;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	static final int PUERTO = 5000;

	ServerSocket skServidor;
	Socket socket;
	String urlFoto = ".//cnt.jpg";
	// ImageIcon fotoE = new ImageIcon(urlFoto);

//ver pdf 3.2 es lo del tamaño del fichero
	public Servidor() {
		try {
			skServidor = new ServerSocket(PUERTO);
			System.out.println("Escutxando el puerto " + PUERTO);

			while ((socket = skServidor.accept()) != null) {
				System.out.println("Conexion desde: " + socket.getInetAddress());
				// envio del objeto
				DataOutputStream salida = new DataOutputStream(socket.getOutputStream());

				File fich = new File(urlFoto);
				int tam = (int) fich.length();// tamaño del fichero
				salida.writeInt(tam);// envio del tamaño del fichero para que lo vaya reservando
				InputStream is = new FileInputStream(fich);// guardar en el input el fichero para guardarlo en el array
															// de bytes
				byte leido[] = new byte[tam]; // array de bytes del tamaño del fichero, aun esta vacio
				is.read(leido);// guardar en leido los bytes del fichero

				salida.write(leido); // envio del array de bytes
				is.close();
				socket.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Servidor();
	}
}
