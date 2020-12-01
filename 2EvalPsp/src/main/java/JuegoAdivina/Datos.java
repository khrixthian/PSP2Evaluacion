package JuegoAdivina;

public class Datos {
	String cadena;
	int intentos = 5;
	int idetificador;
	boolean gana;
	boolean juega;

	public String getCadena() {
		return cadena;
	}

	public void setCadena(String cadena) {
		this.cadena = cadena;
	}

	public int getIntentos() {
		return intentos;
	}

	public void setIntentos(int intentos) {
		this.intentos = intentos;
	}

	public int getIdetificador() {
		return idetificador;
	}

	public void setIdetificador(int idetificador) {
		this.idetificador = idetificador;
	}

	public boolean isGana() {
		return gana;
	}

	public void setGana(boolean gana) {
		this.gana = gana;
	}

	public boolean isJuega() {
		return juega;
	}

	public void setJuega(boolean juega) {
		this.juega = juega;
	}

	public Datos() {
		super();
	}

}
