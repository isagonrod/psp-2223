package util;

import java.util.Scanner;

public class Teclado {
    static Scanner sc = new Scanner(System.in);

    public static String getString(String message) {
        System.out.println(message);
        return sc.nextLine();
    }

    public static String getLongString(String message) {
        System.out.println(message);
        String nextLine = "";
        while (!nextLine.contains("*")) {
            nextLine += sc.nextLine();
        }
        return nextLine;
    }

    public static Integer getNumber(String message) {
        System.out.println(message);
        return sc.nextInt();
    }
}
