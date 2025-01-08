package com;

import java.util.Scanner;
import com.converter.CurrencyConverter;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("¡Bienvenidos al Conversor de Divisas de Alura!");

        // Menú de Opciones de entrada para el Usuario
        String menu = """
                A continuación, por favor selecciona las siguientes opciones:
                1) Pesos Colombianos (COP) ---> Dólares Estadounidenses (USD)
                2) Pesos Argentinos (ARS) ---> Pesos Colombianos (COP)
                3) Lira Libanesa (LBP) ---> Dólares Canadienses (CAD)
                4) Bolívares Venezolanos (VES) ---> Dólares Estadounidenses (USD)
                5) Salir
                """;

        int option = 0;

        while (option != 5) {
            System.out.println(menu);

            try {
                option = scanner.nextInt();

                // Si nuestro usuario elige una opción válida.
                if (option == 5) {
                    System.out.println("¡Gracias por utilizar este programa!");
                    break;
                }

                //Verificando que la opción se encuentre dentro de las opciones válidas.
                if (option < 1 || option > 5) {
                    System.out.println("Error: Opción no válida. Por favor, elije una opción del 1 al 5.");
                    continue;
                }

                System.out.print("Por favor, Ingresa la cantidad o monto que quieres convertir: ");
                double amount = scanner.nextDouble();

                String baseCurrency;
                String targetCurrency;

                switch (option) {
                    case 1:
                        baseCurrency = "COP";
                        targetCurrency = "USD";
                        break;
                    case 2:
                        baseCurrency = "ARS";
                        targetCurrency = "COP";
                        break;
                    case 3:
                        baseCurrency = "LBP";
                        targetCurrency = "CAD";
                        break;
                    case 4:
                        baseCurrency = "VES";
                        targetCurrency = "USD";
                        break;
                    default:
                        continue;
                }

                // Realizar la conversión
                double result = CurrencyConverter.convert(amount, baseCurrency, targetCurrency);

                if (result != -1) {
                    System.out.println(amount + " " + baseCurrency + " = " + result + " " + targetCurrency);
                }
// Si el usuario ingresa por error un carácter no numérico.
            } catch (Exception e) {
                System.out.println("Error: Entrada no válida. Por favor, ingresa solo números.");
                scanner.nextLine(); // Limpiar el buffer para evitar un bucle infinito
            }
        }

        // Muestra mensajes de Advertencia
        System.out.println("\n*** Advertencia ***");
        System.out.println("1. Las tasas de conversión mostradas están sujetas a cambios y pueden no ser exactas.");
        System.out.println("2. Por favor, consulta con fuentes oficiales para obtener tasas de cambio actualizadas.");
        System.out.println("3. Este programa es solo para fines educativos y no debe utilizarse para transacciones financieras reales.");

        scanner.close();

        scanner.close();
    }
}








