package examenKhrixthian2Evaluacion;

import java.io.Serializable;

public class Peticion implements Serializable {
	int ID;
	String Clave;
	String Imagen;

	public Peticion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Peticion(int iD, String clave, String imagen) {
		super();
		ID = iD;
		Clave = clave;
		Imagen = imagen;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getClave() {
		return Clave;
	}

	public void setClave(String clave) {
		Clave = clave;
	}

	public String getImagen() {
		return Imagen;
	}

	public void setImagen(String imagen) {
		Imagen = imagen;
	}

}
