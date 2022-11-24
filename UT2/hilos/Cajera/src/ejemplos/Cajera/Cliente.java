package ejemplos.Cajera;

public class Cliente {
	private String nombre;
	private int[] carroCompra;

	public Cliente(String nombre, int[] carroCompra) {
		this.nombre = nombre;
		this.carroCompra = carroCompra;
	}

	public Cliente() {}

	public String getNombre() {
		return nombre;
	}

	public int[] getCarroCompra() {
		return carroCompra;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setCarroCompra(int[] carroCompra) {
		this.carroCompra = carroCompra;
	}
}
