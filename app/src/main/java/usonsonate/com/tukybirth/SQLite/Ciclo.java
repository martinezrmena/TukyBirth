package usonsonate.com.tukybirth.SQLite;

import java.io.Serializable;

public class Ciclo implements Serializable {

    private String id_ciclo;
    private String duracion_ciclo;
    private String duracion_periodo;
    private String fecha_inicio;
    private String fecha_fin;
    private String estado;

    public Ciclo() {
    }

    public Ciclo(String id_ciclo, String duracion_ciclo, String duracion_periodo, String fecha_inicio, String fecha_fin, String estado) {
        this.id_ciclo = id_ciclo;
        this.duracion_ciclo = duracion_ciclo;
        this.duracion_periodo = duracion_periodo;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.estado = estado;
    }

    public Ciclo(String id_ciclo, String fecha_inicio) {
        this.id_ciclo = id_ciclo;
        this.fecha_inicio = fecha_inicio;
    }

    public String getId_ciclo() {
        return id_ciclo;
    }

    public void setId_ciclo(String id_ciclo) {
        this.id_ciclo = id_ciclo;
    }

    public String getDuracion_ciclo() {
        return duracion_ciclo;
    }

    public void setDuracion_ciclo(String duracion_ciclo) {
        this.duracion_ciclo = duracion_ciclo;
    }

    public String getDuracion_periodo() {
        return duracion_periodo;
    }

    public void setDuracion_periodo(String duracion_periodo) {
        this.duracion_periodo = duracion_periodo;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
