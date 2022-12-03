package ejercicio9;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/* COMPRUEBA TU APRENDIZAJE - Ejercicio 9
 * Realiza una pantalla gráfica para iniciar dos hilos y finalizar su ejecución usando interrupciones.
 * Se deben mostrar varios botones.
 * El botón "Comenzar Proceso" crea los dos hilos y lanza su ejecución, los hilos sólo se crean una vez, el botón se
 * desactivará al pulsarle.
 * Cada hilo tendrá su botón para interrumpir su ejecución.
 * Se debe mostrar un mensaje en pantalla que indique si el hilo está corriendo o ha sido interrumpida su ejecución.
 * El botón "Finalizar Proceso" detiene los dos hilos y muestra en consola el valor final de cada contador.
 * El cierre de la ventana hace lo mismo.
 * El constructor del hilo recibe dos parámetros, uno con el nombre del hilo y el segundo la cantidad de milisegundos
 * que permanece el hilo dormido.
 */

public class Ejercicio9HilosInterrumpir extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private static JTextField contador1;
    private static JTextField contador2;
    private static HiloInterrumpir hilo1;
    private static HiloInterrumpir hilo2;
    private static JLabel labelHilo1;
    private static JLabel labelHilo2;

    public static void main(String[] args) {
        hilo1 = new HiloInterrumpir("UNO", 400);
        hilo2 = new HiloInterrumpir("DOS", 200);

        EventQueue.invokeLater(() -> {
            try {
                Ejercicio9HilosInterrumpir frame = new Ejercicio9HilosInterrumpir();
                frame.setVisible(true);

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        while (true) {

            if (hilo1.isAlive()) {
                contador1.setText(String.valueOf(hilo1.getContador()));
            }

            if (hilo2.isAlive()) {
                contador2.setText(String.valueOf(hilo2.getContador()));
            }
        }
    }

    public Ejercicio9HilosInterrumpir() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                finalizarProceso();
            }
        });

        setTitle("EJECUTAR E INTERRUMPIR HILOS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnComenzarProceso = new JButton("Comenzar Proceso");
        btnComenzarProceso.setBounds(94, 67, 78, -31);
        contentPane.add(btnComenzarProceso);

        JButton btnComenzar = new JButton("Comenzar Proceso");
        btnComenzar.addActionListener(e -> {

            hilo1.start();
            hilo2.start();

            labelHilo1.setText("Hilo1 Corriendo");
            labelHilo2.setText("Hilo2 Corriendo");

            btnComenzar.setEnabled(false);

        });
        btnComenzar.setBounds(138, 23, 178, 23);
        contentPane.add(btnComenzar);

        contador1 = new JTextField();
        contador1.setEditable(false);
        contador1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        contador1.setHorizontalAlignment(SwingConstants.CENTER);
        contador1.setBounds(68, 143, 86, 20);
        contentPane.add(contador1);
        contador1.setColumns(10);

        JButton Suspender = new JButton("Interrumpir");
        Suspender.addActionListener(arg0 -> {
            hilo1.interrumpirHilo();
            labelHilo1.setText("Hilo1 interrumpido");

        });
        Suspender.setBounds(51, 96, 133, 23);
        contentPane.add(Suspender);

        JButton Suspender2 = new JButton("Interrumpir");
        Suspender2.addActionListener(arg0 -> {
            hilo2.interrumpirHilo();
            labelHilo2.setText("Hilo2 interrumpido");
        });
        Suspender2.setBounds(259, 96, 133, 23);
        contentPane.add(Suspender2);

        contador2 = new JTextField();
        contador2.setHorizontalAlignment(SwingConstants.CENTER);
        contador2.setFont(new Font("Tahoma", Font.PLAIN, 13));
        contador2.setEditable(false);
        contador2.setColumns(10);
        contador2.setBounds(281, 143, 86, 20);
        contentPane.add(contador2);

        labelHilo1 = new JLabel("HILO 1");
        labelHilo1.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelHilo1.setHorizontalAlignment(SwingConstants.CENTER);
        labelHilo1.setForeground(Color.RED);
        labelHilo1.setBounds(38, 184, 146, 14);
        contentPane.add(labelHilo1);

        labelHilo2 = new JLabel("HILO 2");
        labelHilo2.setHorizontalAlignment(SwingConstants.CENTER);
        labelHilo2.setForeground(Color.RED);
        labelHilo2.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelHilo2.setBounds(259, 184, 147, 14);
        contentPane.add(labelHilo2);

        JButton button = new JButton("Finalizar Proceso");
        button.addActionListener(arg0 -> finalizarProceso());
        button.setBounds(138, 227, 178, 23);
        contentPane.add(button);

    }

    private void finalizarProceso() {

        hilo1.interrumpirHilo();
        hilo2.interrumpirHilo();

        System.out.println("Contador hilo 1 => " + hilo1.getContador());
        System.out.println("Contador hilo 2 => " + hilo2.getContador());
        System.out.println("Fin de proceso...");

        System.exit(0);
    }
}
