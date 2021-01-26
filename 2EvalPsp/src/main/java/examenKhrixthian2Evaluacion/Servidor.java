package examenKhrixthian2Evaluacion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

public class Servidor {

	// IKER YO PASO DEL HILO POR QUE NO LO SE HACER

	static final int PUERTO = 5000;

	static ServerSocket skServidor;
	static Socket socket;
	static String url;
	static ArrayList<Peticion> objetos = new ArrayList<Peticion>();

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException, ClassNotFoundException {
		String preguntaClave = null;
		Scanner sc = new Scanner(System.in);
		String clave = null;
		Peticion peticionAuxiliar = new Peticion();
		String res = "";
		String cifradoStr = null;
		do {
			System.out.println("¿Desea dar de alta una petición? S/N");
			res = sc.nextLine();
			if (res.equalsIgnoreCase("S")) {

				System.out.println("ID: ");
				int iD = sc.nextInt();
				sc.nextLine();

				System.out.println("Clave: ");
				clave = sc.nextLine();

				System.out.println("URL: ");
				url = sc.nextLine();

				Peticion peticion = new Peticion();
				peticion.setID(iD);
				peticion.setClave(clave);
				peticion.setImagen(url);

				objetos.add(peticion);

			}

		} while (!res.equalsIgnoreCase("N"));

		sc.close();
		// esto lo pongo para tener aki los datos ke voy a poner sobre todo la imagen
//		iD = 1;
//		clave = "1234";
//		url = "H:\\DAM\\PSP\\psp2evaluacion\\2EvalPSP\\src\\main\\java\\examenKhrixthian2Evaluacion\\cnt.jpg";

		skServidor = new ServerSocket(PUERTO);
		System.out.println("esperando conexiones");

		while ((socket = skServidor.accept()) != null) {
			ObjectOutputStream fsalida = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream fentrada = new ObjectInputStream(socket.getInputStream());

			DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
			DataInputStream entrada = new DataInputStream(socket.getInputStream());

			String pregunta1 = "¿ke imagen quiere obtener? 0 es salir del programa";
			salida.writeUTF(pregunta1);

			// recibe la respuesta
			int numImagen = entrada.readInt();
			System.out.println(numImagen);

			if (numImagen == 0) {
				fsalida.close();
				fentrada.close();
				salida.close();
				entrada.close();
				socket.close();
				skServidor.close();
			} else {
				for (int i = 0; i < objetos.size(); i++) {
					if (objetos.get(i).getID() == numImagen) {
						preguntaClave = "¿Cual es la clave?";
						clave = objetos.get(i).getClave();
						peticionAuxiliar = objetos.get(i);
						i = objetos.size(); // kon esto me salgo del for cuando lo encuentre
					}
				}
				// envia la pregunta de la clave
				System.out.println(preguntaClave);
				salida.writeUTF(preguntaClave);

				// codifico la clave del objeto que busca el cliente
				MessageDigest md = MessageDigest.getInstance("SHA");
				byte dataBytes[] = clave.getBytes();
				md.update(dataBytes);
				byte resumen[] = md.digest();
				for (int i = 0; i < resumen.length; i++) {
					cifradoStr += resumen[i];
				}

				// recibo la clave codificada
				String claveRecibida = (String) fentrada.readObject();

				// las comparo
				if (claveRecibida.equals(cifradoStr)) {
					System.out.println("las claves son iguales");
					fsalida.writeObject(peticionAuxiliar);
				} else {
					System.out.println("No coinciden las contraeñas");
					fsalida.close();
					fentrada.close();
					salida.close();
					entrada.close();
					socket.close();
					skServidor.close();
				}
				// guardo en otro objeto auxiliar el objeto recibido
				Peticion peti2 = new Peticion();
				peti2 = (Peticion) fentrada.readObject();

				if (peti2.getImagen().equals("0")) {

					File fich = new File(url);
					int tam = (int) fich.length();
					salida.writeInt(tam);
					InputStream is = new FileInputStream(fich);

					byte leido[] = new byte[tam];
					is.read(leido);

					salida.write(leido);
				}

			}

		}

	}

}
