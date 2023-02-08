package util;

import java.util.Scanner;

public class Teclado {
    static Scanner sc = new Scanner(System.in);

    public static String getString(String message) {
        System.out.println(message);
        return sc.nextLine();
    }

    public static Integer getNum(String message) {
        System.out.println(message);
        return sc.nextInt();
    }
}
