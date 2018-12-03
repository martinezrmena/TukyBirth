package usonsonate.com.tukybirth.Referencias;

public class References {

    private String Tipo;
    private String Autor;
    private String Referencia;
    private int color;

    public References(String tipo, String autor, String referencia, int color) {
        Tipo = tipo;
        Autor = autor;
        Referencia = referencia;
        this.color = color;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public String getAutor() {
        return Autor;
    }

    public void setAutor(String autor) {
        Autor = autor;
    }

    public String getReferencia() {
        return Referencia;
    }

    public void setReferencia(String referencia) {
        Referencia = referencia;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
