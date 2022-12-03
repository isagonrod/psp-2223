package ejercicio8;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/* COMPRUEBA TU APRENDIZAJE - Ejercicio 8
 * Partiendo de la actividad 2.4, realiza la misma operación para suspender y reanudar el hilo, pero ahora se usará
 * una pantalla gráfica con varios botones y se lanzarán dos hilos.
 * El botón "Comenzar Proceso" crea los dos hilos y lanza su ejecución, los hilos sólo se crean una vez, el botón se
 * desactivará al pulsarle.
 * Cada hilo tendrá su botón para suspenderlo o reanudarlo.
 * El botón "Finalizar Proceso" detiene los dos hilos y muestra en consola el valor final de cada contador.
 * El cierre de la ventana hace lo mismo.
 * El constructor del hilo recibe dos parámetros, uno con el nombre del hilo y el segundo la cantidad de milisegundos
 * que permanece el hilo dormido.
 */

public class Ejercicio8Hilos extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private static JTextField contador1;
    private static Hilo hilo1;
    private static Hilo hilo2;
    private static JTextField contador2;
    private static JLabel labelHilo1;
    private static JLabel labelHilo2;

    public static void main(String[] args) {
        hilo1 = new Hilo("uno", 400);
        hilo2 = new Hilo("dos", 200);

        Ejercicio8Hilos frame = new Ejercicio8Hilos();
        frame.setVisible(true);

        while (true) {
            if (hilo1.isAlive()) {
                contador1.setText(String.valueOf(hilo1.getContador()));
            }

            if (hilo2.isAlive()) {
                contador2.setText(String.valueOf(hilo2.getContador()));
            }
        }
    }

    public Ejercicio8Hilos() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                finalizarProceso();
            }
        });

        setTitle("EJECUTAR, SUSPENDER Y REANUDAR HILOS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton buttonComenzarProceso1 = new JButton("Comenzar Proceso");
        buttonComenzarProceso1.setBounds(94, 67, 78, -31);
        contentPane.add(buttonComenzarProceso1);

        JButton buttonComenzarProceso2 = new JButton("Comenzar Proceso");
        buttonComenzarProceso2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!hilo1.isAlive()) {
                    hilo1.start();
                }
                if (!hilo2.isAlive()) {
                    hilo2.start();
                }

                labelHilo1.setText("Hilo 1 corriendo");
                labelHilo2.setText("Hilo 2 corriendo");
                buttonComenzarProceso2.setEnabled(false);
            }
        });

        buttonComenzarProceso2.setBounds(138, 23, 178, 23);
        contentPane.add(buttonComenzarProceso2);

        contador1 = new JTextField();
        contador1.setEditable(false);
        contador1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        contador1.setHorizontalAlignment(SwingConstants.CENTER);
        contador1.setBounds(68, 143, 86, 20);
        contentPane.add(contador1);
        contador1.setColumns(10);

        JButton buttonReanudar = new JButton("Reanudar");
        buttonReanudar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hilo1.isAlive()) {
                    hilo1.peticionContinuar();
                    labelHilo1.setText("Hilo 1 corriendo");
                }
            }
        });

        buttonReanudar.setBounds(65, 57, 89, 23);
        contentPane.add(buttonReanudar);

        JButton buttonSuspender = new JButton("Suspender");
        buttonSuspender.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hilo1.isAlive()) {
                    hilo1.peticionSuspender();
                    labelHilo1.setText("Hilo 1 suspendido");
                }
            }
        });

        buttonSuspender.setBounds(51, 96, 133, 23);
        contentPane.add(buttonSuspender);

        JButton buttonReanudar2 = new JButton("Reanudar");
        buttonReanudar2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (hilo2.isAlive()) {
                    hilo2.peticionContinuar();
                    labelHilo2.setText("Hilo 2 corriendo");
                }
            }
        });
        buttonReanudar2.setBounds(278, 57, 89, 23);
        contentPane.add(buttonReanudar2);

        JButton Suspender2 = new JButton("Suspender");
        Suspender2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (hilo2.isAlive()) {
                    hilo2.peticionSuspender();
                    labelHilo2.setText("Hilo 2 suspendido");
                }
            }
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
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                finalizarProceso();
            }

        });
        button.setBounds(138, 227, 178, 23);
        contentPane.add(button);
    }

    private void finalizarProceso() {
        hilo1.peticionContinuar();
        hilo1.pararHilo();

        hilo2.peticionContinuar();
        hilo2.pararHilo();

        System.out.println("Contador hilo 1: " + hilo1.getContador());
        System.out.println("Contador hilo 2: " + hilo2.getContador());
        System.out.println("Fin de proceso...");

        System.exit(0);
    }
}
