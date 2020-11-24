package Ejercicio_Foto;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Cliente extends JFrame {
	private Socket skCliente;
	static final String HOST = "localhost";
	static final int PUERTO = 5000;
	private JLabel label;

	public Cliente() throws ClassNotFoundException {
		setSize(516, 465);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		try {
			skCliente = new Socket(HOST, PUERTO);

			DataInputStream entrada = new DataInputStream(skCliente.getInputStream());
			int tamanyo = entrada.readInt(); // guarda el tamaño del fichero que recibira
			byte recibido[] = new byte[tamanyo]; // array donde guardare los bytes del fichero
			FileOutputStream f = new FileOutputStream(".//copia.jpg");// donde guardara el fichero recibido y de paso le
																		// cambio el nombre
			entrada.read(recibido);// guarda en recibido los bytes enviados por el servidor
			f.write(recibido);// convierte los bytes en la nueva foto

			entrada.close();
			skCliente.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		// pegar la foto a la label
		label = new JLabel(new ImageIcon(".//copia.jpg"));

		label.setBounds(80, 46, 370, 322);
		getContentPane().add(label);
	}

	public static void main(String[] args) throws ClassNotFoundException {
		Cliente cli = new Cliente();
		cli.setVisible(true);
	}
}
