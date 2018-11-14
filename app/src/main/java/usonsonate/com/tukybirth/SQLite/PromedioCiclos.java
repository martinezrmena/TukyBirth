package usonsonate.com.tukybirth.SQLite;

public class PromedioCiclos {

    private String DURACION_PERIODO = "-1";
    private String DURACION_CICLO = "-1";
    private String COUNT = "-1";

    public PromedioCiclos(String DURACION_PERIODO, String DURACION_CICLO, String COUNT) {
        this.DURACION_PERIODO = DURACION_PERIODO;
        this.DURACION_CICLO = DURACION_CICLO;
        this.COUNT = COUNT;
    }

    public PromedioCiclos() {
    }

    public String getDURACION_PERIODO() {
        return DURACION_PERIODO;
    }

    public void setDURACION_PERIODO(String DURACION_PERIODO) {
        this.DURACION_PERIODO = DURACION_PERIODO;
    }

    public String getDURACION_CICLO() {
        return DURACION_CICLO;
    }

    public void setDURACION_CICLO(String DURACION_CICLO) {
        this.DURACION_CICLO = DURACION_CICLO;
    }

    public String getCOUNT() {
        return COUNT;
    }

    public void setCOUNT(String COUNT) {
        this.COUNT = COUNT;
    }
}
