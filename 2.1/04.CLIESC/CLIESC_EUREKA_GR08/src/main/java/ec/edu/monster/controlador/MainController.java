package ec.edu.monster.controlador;

import ec.edu.monster.service.CuentaService;
import ec.edu.monster.service.LoginService;
import ec.edu.monster.ws.MovimientoModel;
import ec.edu.monster.ws.CuentaModel;
import ec.edu.monster.service.MovimientoService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class MainController {
    private LoginService loginService = new LoginService();
    private CuentaService cuentaService = new CuentaService();
    private MovimientoService movimientoService = new MovimientoService();
    private String usuarioActual = null;

    public String getUsuarioActual() {
        return usuarioActual;
    }

    public boolean iniciarSesion(String username, String password) {
        try {
            String hashedPassword = hashPassword(password);
            if (loginService.auth(username, password)) {
                usuarioActual = username;
                return true;
            }
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error en la autenticación: " + e.getMessage());
        }
        return false;
    }

    public void cerrarSesion() {
        usuarioActual = null;
    }

    public boolean realizarDeposito(String cuenta, String monto, String tipo, String cd) {
        return cuentaService.realizarDeposito(cuenta, monto, tipo, cd);
    }

    public boolean realizarRetiro(String cuenta, String monto) {
        return cuentaService.realizarDeposito(cuenta, monto, "RET", null);
    }

    public boolean realizarTransferencia(String cuentaOrigen, String cuentaDestino, String monto) {
        boolean retiroExitoso = realizarRetiro(cuentaOrigen, monto);
        if (retiroExitoso) {
            return realizarDeposito(cuentaDestino, monto, "TRA", cuentaOrigen);
        }
        return false;
    }

    public String verMovimientos(String cuenta) {
        try {
            List<MovimientoModel> movimientos = movimientoService.obtenerMovimientos(cuenta);
            CuentaModel cuentaModel = cuentaService.obtenerCuentaPorNumero(cuenta);

            StringBuilder resultado = new StringBuilder();
            if (movimientos == null || movimientos.isEmpty()) {
                resultado.append("No se encontraron movimientos para la cuenta: ").append(cuenta).append("\n");
                
                return encloseInBox("MOVIMIENTOS DE LA CUENTA " + cuenta, resultado.toString());
            }

            // Mostrar saldo al inicio para que no se pierda en listados largos
            if (cuentaModel != null) {
                resultado.append(String.format("Saldo Actual de la Cuenta: %.2f%n", cuentaModel.getDecCuenSaldo()));
                resultado.append("\n");
            }

            resultado.append(String.format("%-10s %-20s %-12s %-20s %-15s %-15s %-15s%n",
                    "Nro", "Fecha", "Tipo Mov.", "Descripción", "Importe", "Cta Ref.", "Saldo"));
            resultado.append("----------------------------------------------------------------------------------------------------\n");

            for (MovimientoModel mov : movimientos) {
                resultado.append(String.format("%-10d %-20s %-12s %-20s %-15.2f %-15s %-15.2f%n",
                        mov.getNumeroMovimiento(),
                        mov.getFechaMovimiento(),
                        mov.getCodigoTipoMovimiento(),
                        mov.getTipoDescripcion(),
                        mov.getImporteMovimiento(),
                        mov.getCuentaReferencia() != null ? mov.getCuentaReferencia() : "N/A",
                        mov.getSaldo()));
            }

            // Calcular totales
            double totalIngresos = movimientos.stream()
                    .filter(m -> !m.getTipoDescripcion().equals("Retiro"))
                    .mapToDouble(MovimientoModel::getImporteMovimiento)
                    .sum();

            double totalEgresos = movimientos.stream()
                    .filter(m -> m.getTipoDescripcion().equals("Retiro"))
                    .mapToDouble(MovimientoModel::getImporteMovimiento)
                    .sum();

            resultado.append("\nRESUMEN:\n");
            resultado.append(String.format("Total Ingresos: %.2f%n", totalIngresos));
            resultado.append(String.format("Total Egresos (Retiros): %.2f%n", Math.abs(totalEgresos)));
            resultado.append(String.format("Saldo Neto: %.2f%n", totalIngresos + totalEgresos));
            
            // Mostrar saldo actual de la cuenta
            if (cuentaModel != null) {
                resultado.append(String.format("Saldo Actual de la Cuenta: %.2f%n", cuentaModel.getDecCuenSaldo()));
            }

            return encloseInBox("MOVIMIENTOS DE LA CUENTA " + cuenta, resultado.toString());
        } catch (Exception e) {
            return "Error al obtener los movimientos: " + e.getMessage();
        }
    }

    public String verDatosCuenta(String cuenta) {
        try {
            CuentaModel cuentaModel = cuentaService.obtenerCuentaPorNumero(cuenta);

            if (cuentaModel == null) {
                return "No se encontraron datos para la cuenta: " + cuenta;
            }

            StringBuilder resultado = new StringBuilder();
            resultado.append(String.format("%-20s %-30s%n", "Campo", "Valor"));
            resultado.append("--------------------------------------------------\n");
            resultado.append(String.format("%-20s %-30.2f%n", "Saldo", cuentaModel.getDecCuenSaldo()));
            resultado.append(String.format("%-20s %-30d%n", "Contador Movimientos", cuentaModel.getIntCuenContMov()));
            resultado.append("--------------------------------------------------\n");

            return encloseInBox("DATOS DE LA CUENTA " + cuenta, resultado.toString());
        } catch (Exception e) {
            return "Error al obtener los datos de la cuenta: " + e.getMessage();
        }
    }

    private String hashPassword(String input) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(input.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    // Envoltura en recuadro ASCII para mejorar la visualización en consola
    private String encloseInBox(String title, String multilineContent) {
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

    private String repeat(char ch, int count) {
        StringBuilder sb = new StringBuilder(count);
        for (int i = 0; i < count; i++) {
            sb.append(ch);
        }
        return sb.toString();
    }

    private String padRight(String text, int width) {
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

    private String padCenter(String text, int width) {
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