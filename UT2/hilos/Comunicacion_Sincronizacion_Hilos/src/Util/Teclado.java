package Util;

import java.util.Scanner;

public class Teclado {
    public static Integer getInt(String message) {
        Scanner sc = new Scanner(System.in);
        System.out.println(message);
        return sc.nextInt();
    }
}
