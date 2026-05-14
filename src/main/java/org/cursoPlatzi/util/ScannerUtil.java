package org.cursoPlatzi.util;

import java.util.Scanner;

public class ScannerUtil {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static String capturarTexto(String mensaje){
        System.out.println(mensaje +" :");
        return SCANNER.nextLine();
    }

    public static int capturarNumero(String mensaje){
        System.out.println(mensaje + ": ");

        while (!SCANNER.hasNextInt()) {
            System.out.println("Por favor, ingresa un número válido.");
            SCANNER.next(); // Limpiar el valor no válido
        }

        int dato = SCANNER.nextInt();
        SCANNER.nextLine(); // Limpiar el buffer
        return dato;
    }

    public static double capturarDecimal(String mensaje){
        while (true){
            try {
                System.out.println(mensaje + ":");
                String input = SCANNER.nextLine();
                return Double.parseDouble(input);
            }catch (NumberFormatException e){
                System.out.println("❌ Error: Por favor, ingrese un número válido de 1.0 a 5.0");
            }
        }
    }
}
