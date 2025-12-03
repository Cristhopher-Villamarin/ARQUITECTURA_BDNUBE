package ec.edu.monster.vista;

import ec.edu.monster.controlador.MainController;

import java.util.Scanner;

public class MainView {
    private static Scanner scanner = new Scanner(System.in);
    private static MainController mainController = new MainController();

    public static void main(String[] args) {
        while (true) {
            if (mainController.getUsuarioActual() == null) {
                menuLogin();
            } else {
                menuPrincipal();
            }
        }
    }

    private static void menuLogin() {
        StringBuilder contenido = new StringBuilder();
        contenido.append("1. Iniciar Sesión\n");
        contenido.append("2. Salir\n");
        System.out.println(encloseInBox("EUREKABANK - SOAP", contenido.toString()));
        System.out.print("Seleccione una opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        switch (opcion) {
            case 1 -> iniciarSesion();
            case 2 -> {
                System.out.println("Gracias por usar nuestros servicios. ¡Hasta luego!");
                System.exit(0);
            }
            default -> System.out.println("Opción inválida. Intente nuevamente.");
        }
    }

    private static void iniciarSesion() {
        System.out.print("Ingrese su nombre de usuario: ");
        String username = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String password = scanner.nextLine();

        if (mainController.iniciarSesion(username, password)) {
            System.out.println("Inicio de sesión exitoso.");
        } else {
            System.out.println("Credenciales incorrectas. Intente nuevamente.");
        }
    }

    private static void menuPrincipal() {
        StringBuilder contenido = new StringBuilder();
        contenido.append("1. Realizar Depósito\n");
        contenido.append("2. Realizar Retiro\n");
        contenido.append("3. Realizar Transferencia\n");
        contenido.append("4. Ver Movimientos\n");
        contenido.append("5. Ver Datos Cuenta\n");
        contenido.append("6. Cerrar Sesión\n");
        System.out.println(encloseInBox("MENÚ PRINCIPAL", contenido.toString()));
        System.out.print("Seleccione una opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        switch (opcion) {
            case 1 -> realizarDeposito();
            case 2 -> realizarRetiro();
            case 3 -> realizarTransferencia();
            case 4 -> verMovimientos();
            case 5 -> verDatosCuenta();
            case 6 -> cerrarSesion();
            default -> System.out.println("Opción inválida. Intente nuevamente.");
        }
    }

private static void realizarDeposito() {
    System.out.print("Ingrese el número de cuenta: ");
    String cuenta = scanner.nextLine();
    System.out.print("Ingrese el monto a depositar: ");
    String monto = scanner.nextLine();

    if (mainController.realizarDeposito(cuenta, monto)) {
        System.out.println("Depósito realizado con éxito.");
    } else {
        System.out.println("Error al realizar el depósito.");
    }
}



    private static void realizarRetiro() {
        System.out.print("Ingrese el número de cuenta: ");
        String cuenta = scanner.nextLine();
        System.out.print("Ingrese el monto a retirar: ");
        String monto = scanner.nextLine();

        if (mainController.realizarRetiro(cuenta, monto)) {
            System.out.println("Retiro realizado con éxito.");
        } else {
            System.out.println("Error al realizar el retiro.");
        }
    }


private static void realizarTransferencia() {
    System.out.print("Ingrese el número de cuenta origen: ");
    String cuentaOrigen = scanner.nextLine();
    System.out.print("Ingrese el número de cuenta destino: ");
    String cuentaDestino = scanner.nextLine();
    System.out.print("Ingrese el monto a transferir: ");
    String monto = scanner.nextLine();

    if (mainController.realizarTransferencia(cuentaOrigen, cuentaDestino, monto)) {
        System.out.println("Transferencia realizada con éxito.");
    } else {
        System.out.println("Error al realizar la transferencia.");
    }
}

    private static void verMovimientos() {
        System.out.print("Ingrese el número de cuenta: ");
        String cuenta = scanner.nextLine();

        String resultado = mainController.verMovimientos(cuenta);
        System.out.println(resultado);
    }

    private static void verDatosCuenta() {
        System.out.print("Ingrese el número de cuenta: ");
        String cuenta = scanner.nextLine();

        String resultado = mainController.verDatosCuenta(cuenta);
        System.out.println(resultado);
    }

    private static void cerrarSesion() {
        mainController.cerrarSesion();
        System.out.println("Sesión cerrada exitosamente.");
    }

    // Utilidades de recuadro ASCII (coherentes con el estilo del controlador)
    private static String encloseInBox(String title, String multilineContent) {
        String[] lines = multilineContent.split("\\r?\\n");
        int maxLineLength = title.length();
        for (String line : lines) {
            if (line.length() > maxLineLength) {
                maxLineLength = line.length();
            }
        }
        int innerWidth = Math.max(maxLineLength, title.length());
        String horizontal = repeat('-', innerWidth + 2);

        StringBuilder box = new StringBuilder();
        box.append('+').append(horizontal).append("+\n");
        box.append('|').append(padCenter(" " + title + " ", innerWidth + 2)).append('|').append("\n");
        box.append('+').append(horizontal).append("+\n");
        for (String line : lines) {
            box.append('|').append(padRight(" " + line, innerWidth + 2)).append('|').append("\n");
        }
        box.append('+').append(horizontal).append('+');
        return box.toString();
    }

    private static String repeat(char ch, int count) {
        StringBuilder sb = new StringBuilder(count);
        for (int i = 0; i < count; i++) {
            sb.append(ch);
        }
        return sb.toString();
    }

    private static String padRight(String text, int width) {
        if (text.length() >= width) {
            return text;
        }
        StringBuilder sb = new StringBuilder(width);
        sb.append(text);
        while (sb.length() < width) {
            sb.append(' ');
        }
        return sb.toString();
    }

    private static String padCenter(String text, int width) {
        if (text.length() >= width) {
            return text;
        }
        int totalPadding = width - text.length();
        int left = totalPadding / 2;
        int right = totalPadding - left;
        StringBuilder sb = new StringBuilder(width);
        for (int i = 0; i < left; i++) sb.append(' ');
        sb.append(text);
        for (int i = 0; i < right; i++) sb.append(' ');
        return sb.toString();
    }
}