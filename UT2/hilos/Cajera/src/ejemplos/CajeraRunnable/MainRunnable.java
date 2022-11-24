package ejemplos.CajeraRunnable;

public class MainRunnable {
	public static void main(String[] args) {
		ClienteRunnable cliente1 = new ClienteRunnable("Cliente 1", new int[] {2, 2, 1, 5, 2, 3});
		ClienteRunnable cliente2 = new ClienteRunnable("Cliente 2", new int[] {1, 3, 5, 1, 1});

		long initialTime = System.currentTimeMillis();

		CajeraRunnable cajera1 = new CajeraRunnable("Cajera 1", cliente1, initialTime);
		CajeraRunnable cajera2 = new CajeraRunnable("Cajera 2", cliente1, initialTime);

		Thread hilocajera1 = new Thread(cajera1);
		Thread hilocajera2 = new Thread(cajera2);

		hilocajera1.start();
		hilocajera2.start();
	}
}
