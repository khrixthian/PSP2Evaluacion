package ejerChatAvanzado;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Servidor extends Thread {
	private final int PUERTO1 = 5000;
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

	private ServerSocket serverSocket2;
	private final int PUERTO2 = 5100;
	private HiloRecibirS recibir;
	private Socket socket2;
	private ObjectOutputStream enviar;
	private OutputStream auxenviar;

	public Servidor(JTextArea textarea, JTextField texto) {
		this.textarea = textarea;
		this.texto = texto;
		activo = true;
	}

	public void run() {
		if (activo == true) {
			try {
				serverSocket = new ServerSocket(PUERTO1);
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

					texto.setText("");
					texto.setText("Conexiones actuales= " + conexiones);
					msjConexiones = msjConexiones + "\n" + "Un cliente se ha conectado";
					textarea.setText(msjConexiones);
//					System.out.println(msjConexiones);
					recibir = new HiloRecibirS(salida, entrada, textarea, texto, lista);
					recibir.start();

				}
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
					if (enviar != null)
						enviar.close();
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
