package ejerChatAvanzado;

import java.io.ObjectInputStream;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class HiloRecibirC extends Thread {
	private ObjectInputStream entrada;
	private JTextArea textarea;
	private JButton btnEnviar;

	public HiloRecibirC(ObjectInputStream entrada, JTextArea textarea, JTextField texto) {
		this.entrada = entrada;
		this.textarea = textarea;
	}

	public void run() {

	}
}
