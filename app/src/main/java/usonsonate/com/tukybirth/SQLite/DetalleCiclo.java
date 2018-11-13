package usonsonate.com.tukybirth.SQLite;

public class DetalleCiclo {

    private String id_detalle;
    private String id_ciclo;
    private String fecha_introduccion;
    private String severidad;
    private String detalle;

    public DetalleCiclo() {
    }

    public DetalleCiclo(String id_detalle, String id_ciclo, String fecha_introduccion, String severidad, String detalle) {
        this.id_detalle = id_detalle;
        this.id_ciclo = id_ciclo;
        this.fecha_introduccion = fecha_introduccion;
        this.severidad = severidad;
        this.detalle = detalle;
    }

    public String getId_detalle() {
        return id_detalle;
    }

    public void setId_detalle(String id_detalle) {
        this.id_detalle = id_detalle;
    }

    public String getId_ciclo() {
        return id_ciclo;
    }

    public void setId_ciclo(String id_ciclo) {
        this.id_ciclo = id_ciclo;
    }

    public String getFecha_introduccion() {
        return fecha_introduccion;
    }

    public void setFecha_introduccion(String fecha_introduccion) {
        this.fecha_introduccion = fecha_introduccion;
    }

    public String getSeveridad() {
        return severidad;
    }

    public void setSeveridad(String severidad) {
        this.severidad = severidad;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
}
