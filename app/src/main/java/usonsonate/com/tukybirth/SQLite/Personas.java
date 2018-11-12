package usonsonate.com.tukybirth.SQLite;

import java.io.Serializable;
import java.util.Date;

public class Personas implements Serializable {

    private String id;
    private String Nombre;
    private String Password;
    private String Periodo;
    private String PMS;
    private String Ciclo;
    private String Ultimo_periodo;
    private String Cumpleaños;
    private String FechaIngreso;

    public Personas(String id, String nombre, String password, String periodo, String PMS, String ciclo, String ultimo_periodo, String cumpleaños, String fechaIngreso) {
        this.id = id;
        Nombre = nombre;
        Password = password;
        Periodo = periodo;
        this.PMS = PMS;
        Ciclo = ciclo;
        Ultimo_periodo = ultimo_periodo;
        Cumpleaños = cumpleaños;
        FechaIngreso = fechaIngreso;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPeriodo() {
        return Periodo;
    }

    public void setPeriodo(String periodo) {
        Periodo = periodo;
    }

    public String getPMS() {
        return PMS;
    }

    public void setPMS(String PMS) {
        this.PMS = PMS;
    }

    public String getCiclo() {
        return Ciclo;
    }

    public void setCiclo(String ciclo) {
        Ciclo = ciclo;
    }

    public String getUltimo_periodo() {
        return Ultimo_periodo;
    }

    public void setUltimo_periodo(String ultimo_periodo) {
        Ultimo_periodo = ultimo_periodo;
    }

    public String getCumpleaños() {
        return Cumpleaños;
    }

    public void setCumpleaños(String cumpleaños) {
        Cumpleaños = cumpleaños;
    }

    public String getFechaIngreso() {
        return FechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        FechaIngreso = fechaIngreso;
    }
}
