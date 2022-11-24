package ejemplos.CajeraHilos;

public class CajeraThread extends Thread {
	private String nombre;
	private ClienteThread cliente;
	private long initialTime;

	public CajeraThread(String nombre, ClienteThread cliente, long initialTime) {
		this.nombre = nombre;
		this.cliente = cliente;
		this.initialTime = initialTime;
	}

	@Override
	public void run() {
		System.out.println("La cajera " + this.nombre +
				" comienza a procesar la compra del cliente " + this.cliente.getNombre() +
				" en el tiempo: " + (System.currentTimeMillis() - this.initialTime) / 1000 +
				" segundos.");

		for (int i = 0; i < this.cliente.getCarroCompra().length; i++) {
			this.esperarXsegundos(cliente.getCarroCompra()[i]);
			System.out.println("Procesado el producto " + (i + 1) +
					" del cliente " + this.cliente.getNombre() +
					" -> Tiempo: " + (System.currentTimeMillis() - this.initialTime) / 1000 +
					" segundos.");

			}

			System.out.println("La cajera " + this.nombre +
					" ha terminado de procesar " + this.cliente.getNombre() +
					" en el tiempo: " + (System.currentTimeMillis() - this.initialTime) / 1000 +
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
