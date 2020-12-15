package ejerChatAvanzado;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Servidor extends Thread {
	private final int PUERTO = 5000;
	private ArrayList<ObjectOutputStream> lista;
	private JTextArea textarea;
	private JTextField texto;
	private Boolean activo;
	private Socket socket;
	private ServerSocket serverSocket;
	private int conexiones;
	private ObjectInputStream entrada = null;
	private ObjectOutputStream salida = null;
	private String msjConexiones;
	private HiloRecibirS recibir;

	public Servidor(JTextArea textarea, JTextField texto) {
		this.textarea = textarea;
		this.texto = texto;
		activo = true;
	}

	public void run() {
		if (activo == true) {
			try {
				serverSocket = new ServerSocket(PUERTO);
				texto.setText("Conexiones actuales= " + conexiones);
				msjConexiones = "Esperando conexiones...";
				textarea.setText(msjConexiones);

				while (conexiones <= 3) {
					socket = serverSocket.accept();
					salida = new ObjectOutputStream(socket.getOutputStream());
					entrada = new ObjectInputStream(socket.getInputStream());
					salida.writeObject("Soy evaristo el rey de la baraja");
					lista.add(salida);
					conexiones += 1;
					texto.setText("Conexiones actuales= " + conexiones);
					// msjConexiones = msjConexiones + "\n" + "Un cliente se ha conectado";

					recibir = new HiloRecibirS(salida, entrada, textarea, texto, lista);
					recibir.start();

				}
				// textarea.setText(msjConexiones);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					if (serverSocket != null)
						serverSocket.close();
					if (socket != null)
						socket.close();
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

	public void desconectar() {
		System.out.println("comprobando desconectar de servidor");
		activo = false;
	}
}
