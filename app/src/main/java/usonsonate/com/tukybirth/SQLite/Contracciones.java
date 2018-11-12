package usonsonate.com.tukybirth.SQLite;

import java.io.Serializable;

public class Contracciones implements Serializable {

    private String id;
    private String duracion;
    private String intervalo;
    private String iniciofin;

    public Contracciones(String id, String duracion, String intervalo, String iniciofin) {
        this.id = id;
        this.duracion = duracion;
        this.intervalo = intervalo;
        this.iniciofin = iniciofin;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(String intervalo) {
        this.intervalo = intervalo;
    }

    public String getIniciofin() {
        return iniciofin;
    }

    public void setIniciofin(String iniciofin) {
        this.iniciofin = iniciofin;
    }



}
