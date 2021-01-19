package ejerSeguridad;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
	private Socket skCliente;
	private final String HOST = "localhost";
	private final int PUERTO = 4000;

	public Cliente() throws ClassNotFoundException {

		try {
			skCliente = new Socket(HOST, PUERTO);
			// preparacion de la entrada y salida, 1 tiene que estar la entrada
			ObjectInputStream entrada = new ObjectInputStream(skCliente.getInputStream());
			ObjectOutputStream salida = new ObjectOutputStream(skCliente.getOutputStream());

			// recibir el 1 mensaje
			String pregrecib = (String) entrada.readObject();
			System.out.println(pregrecib);

//			// escribo en consola la frase que envio al servidor
			Scanner input = new Scanner(System.in);
			String mensaje = input.nextLine();
			salida.writeObject(mensaje);

			// recibe la frase codificada del servidor
			String pregfuncionrecib = (String) entrada.readObject();
// pinta la frase codificada
			System.out.println(pregfuncionrecib);

			// espera a que escriba en consola una nueva frase y la envia al servidor
			String resumenUsuario = input.nextLine();
			salida.writeObject(resumenUsuario);

			// recibe la respuesta de la comparacion de las 2 frases y la pinta
			String ultimoMensaje = (String) entrada.readObject();
			System.out.println(ultimoMensaje);

			entrada.close();
			// entrada2.close();
			salida.close();
			skCliente.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws ClassNotFoundException {
		new Cliente();

	}

}
