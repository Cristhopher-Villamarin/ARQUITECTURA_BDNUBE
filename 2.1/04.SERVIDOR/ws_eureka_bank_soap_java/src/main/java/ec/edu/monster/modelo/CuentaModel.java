package ec.edu.monster.modelo;

import java.sql.Date;

public class CuentaModel {
    
    // Tabla Cuenta DB
    private String chrCuenCodigo;       // Código de la cuenta
    private String chrMoneCodigo;       // Código de moneda
    private String chrSucucodigo;       // Código de sucursal
    private String chrEmplCreaCuenta;   // Código de empleado que crea la cuenta
    private String chrClieCodigo;       // Código de cliente
    private double decCuenSaldo;        // Saldo de la cuenta
    private Date dttCuenFechaCreacion;  // Fecha de creación de la cuenta
    private String vchCuenEstado;       // Estado de la cuenta (ACTIVO, ANULADO, CANCELADO)
    private int intCuenContMov;         // Contador de movimientos de la cuenta
    private String chrCuenClave;        // Clave de la cuenta

  
    
    public String getChrCuenCodigo() {
        return chrCuenCodigo;
    }

    public void setChrCuenCodigo(String chrCuenCodigo) {
        this.chrCuenCodigo = chrCuenCodigo;
    }

    public String getChrMoneCodigo() {
        return chrMoneCodigo;
    }

    public void setChrMoneCodigo(String chrMoneCodigo) {
        this.chrMoneCodigo = chrMoneCodigo;
    }

    public String getChrSucucodigo() {
        return chrSucucodigo;
    }

    public void setChrSucucodigo(String chrSucucodigo) {
        this.chrSucucodigo = chrSucucodigo;
    }

    public String getChrEmplCreaCuenta() {
        return chrEmplCreaCuenta;
    }

    public void setChrEmplCreaCuenta(String chrEmplCreaCuenta) {
        this.chrEmplCreaCuenta = chrEmplCreaCuenta;
    }

    public String getChrClieCodigo() {
        return chrClieCodigo;
    }

    public void setChrClieCodigo(String chrClieCodigo) {
        this.chrClieCodigo = chrClieCodigo;
    }

    public double getDecCuenSaldo() {
        return decCuenSaldo;
    }

    public void setDecCuenSaldo(double decCuenSaldo) {
        this.decCuenSaldo = decCuenSaldo;
    }

    public Date getDttCuenFechaCreacion() { 
        return dttCuenFechaCreacion;
    }

    public void setDttCuenFechaCreacion(Date dttCuenFechaCreacion) {
        this.dttCuenFechaCreacion = dttCuenFechaCreacion;
    }

    public String getVchCuenEstado() {
        return vchCuenEstado;
    }

    public void setVchCuenEstado(String vchCuenEstado) {
        this.vchCuenEstado = vchCuenEstado;
    }

    public int getIntCuenContMov() {
        return intCuenContMov;
    }

    public void setIntCuenContMov(int intCuenContMov) {
        this.intCuenContMov = intCuenContMov;
    }

    public String getChrCuenClave() {
        return chrCuenClave;
    }

    public void setChrCuenClave(String chrCuenClave) {
        this.chrCuenClave = chrCuenClave;
    }
}
