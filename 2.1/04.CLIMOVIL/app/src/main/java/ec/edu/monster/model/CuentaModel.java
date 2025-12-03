package ec.edu.monster.model;

public class CuentaModel {
    private double decCuenSaldo;
    private int intCuenContMov;
    private String strCuenNumero;

    public CuentaModel() {
    }

    public CuentaModel(double decCuenSaldo, int intCuenContMov, String strCuenNumero) {
        this.decCuenSaldo = decCuenSaldo;
        this.intCuenContMov = intCuenContMov;
        this.strCuenNumero = strCuenNumero;
    }

    public double getDecCuenSaldo() {
        return decCuenSaldo;
    }

    public void setDecCuenSaldo(double decCuenSaldo) {
        this.decCuenSaldo = decCuenSaldo;
    }

    public int getIntCuenContMov() {
        return intCuenContMov;
    }

    public void setIntCuenContMov(int intCuenContMov) {
        this.intCuenContMov = intCuenContMov;
    }

    public String getStrCuenNumero() {
        return strCuenNumero;
    }

    public void setStrCuenNumero(String strCuenNumero) {
        this.strCuenNumero = strCuenNumero;
    }
}
