package ejerChatAvanzado;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class HiloRecibirS extends Thread {
	private ObjectOutputStream salida;
	private ObjectInputStream entrada;
	private JTextArea textarea;
	private JTextField texto;
	private ArrayList<ObjectOutputStream> lista;

	public HiloRecibirS(ObjectOutputStream salida, ObjectInputStream entrada, JTextArea textarea, JTextField texto,
			ArrayList<ObjectOutputStream> lista) {
		this.salida = salida;
		this.entrada = entrada;
		this.textarea = textarea;
		this.texto = texto;
		this.lista = lista;
	}

	public void run() {
		while (true) {
			try {
				String mensaje = (String) entrada.readObject();
				System.out.println(mensaje);
				for (int i = 0; i < lista.size(); i++) {
					lista.get(i).writeObject(mensaje);
				}
				String textoo = textarea.getText();
				System.out.println(textoo);
				mensaje = textoo + "\n" + mensaje;
				textarea.setText(mensaje);

			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				try {
					if (entrada != null)
						entrada.close();
					if (salida != null)
						salida.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
