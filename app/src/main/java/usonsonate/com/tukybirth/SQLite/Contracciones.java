package usonsonate.com.tukybirth.SQLite;

import android.support.annotation.NonNull;

import java.io.Serializable;

public class Contracciones implements Serializable {

    public String id;
    public String duracion;
    public String intervalo;
    public String iniciofin;

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
