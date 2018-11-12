package usonsonate.com.tukybirth.SQLite;

import java.util.Date;

public class Personas {private String Nombre;

    private String Password;
    private int Periodo;
    private int PMS;
    private int Ciclo;
    private Date Ultimo_periodo;
    private Date Cumpleaños;
    private Date FechaIngreso;

    public Personas(String nombre, String password, int periodo, int PMS, int ciclo, Date ultimo_periodo, Date cumpleaños, Date fechaIngreso) {
        Nombre = nombre;
        Password = password;
        Periodo = periodo;
        this.PMS = PMS;
        Ciclo = ciclo;
        Ultimo_periodo = ultimo_periodo;
        Cumpleaños = cumpleaños;
        FechaIngreso = fechaIngreso;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getPeriodo() {
        return Periodo;
    }

    public void setPeriodo(int periodo) {
        Periodo = periodo;
    }

    public int getPMS() {
        return PMS;
    }

    public void setPMS(int PMS) {
        this.PMS = PMS;
    }

    public int getCiclo() {
        return Ciclo;
    }

    public void setCiclo(int ciclo) {
        Ciclo = ciclo;
    }

    public Date getUltimo_periodo() {
        return Ultimo_periodo;
    }

    public void setUltimo_periodo(Date ultimo_periodo) {
        Ultimo_periodo = ultimo_periodo;
    }

    public Date getCumpleaños() {
        return Cumpleaños;
    }

    public void setCumpleaños(Date cumpleaños) {
        Cumpleaños = cumpleaños;
    }

    public Date getFechaIngreso() {
        return FechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        FechaIngreso = fechaIngreso;
    }
}
