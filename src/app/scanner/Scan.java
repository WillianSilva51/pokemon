package app.scanner;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Scan {
    private Scan() {
        throw new IllegalStateException("Utility class");
    }

    private static final Scanner s = new Scanner(System.in);

    public static int lerInt() {
        while (true) {
            try {
                return s.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("Coloque um número válido");
                s.next();
            }
        }
    }

    public static double lerDouble() {
        while (true) {
            try {
                return s.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Coloque um número válido");
                s.next();
            }
        }
    }

    public static String lerString() {
            return s.next();
    }

    public static void close() {
        s.close();
    }
}