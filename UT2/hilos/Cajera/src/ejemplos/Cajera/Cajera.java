package ejemplos.Cajera;

public class Cajera {
	private String nombre;

	public Cajera(String nombre) {
		this.nombre = nombre;
	}

	public void procesarCompra(Cliente cliente, long timeStamp) {
		System.out.println("La cajera " + this.nombre +
				" comienza a procesar la compra del cliente " + cliente.getNombre() +
				" en el tiempo: " + (System.currentTimeMillis() - timeStamp) / 1000 +
				" segundos.");

		for (int i = 0; i < cliente.getCarroCompra().length; i++) {
			this.esperarXsegundos(cliente.getCarroCompra()[i]);
			System.out.println("Procesado el producato " + (i + 1) +
					" -> Tiempo: " + (System.currentTimeMillis() - timeStamp) / 1000 +
					" segundos.");
		}

		System.out.println("La cajera " + this.nombre + " ha terminado de procesar " +
				cliente.getNombre() + " en el tiempo: " + (System.currentTimeMillis() - timeStamp) / 1000 +
				" segundos.");
	}

	private void esperarXsegundos(int segundos) {
		try {
			Thread.sleep(segundos * 1000L);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
}
