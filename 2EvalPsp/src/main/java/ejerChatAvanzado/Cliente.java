package ejerChatAvanzado;

import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Cliente {
	private int PUERTO = 5000;
	private String HOST = "localhost";
	private ObjectOutputStream salida;

	public Cliente(Socket socket, JTextArea textarea, JTextField texto, JButton btnEnviar) {

	}

	public int getPUERTO() {
		return PUERTO;
	}

	public String getHOST() {
		return HOST;
	}
}
