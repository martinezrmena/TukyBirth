package usonsonate.com.tukybirth.Calendar;

public class DetalleDia {

    private int BackGroundImagen;
    private int IconImage;
    private String Title;
    private String Description;

    public DetalleDia() {
    }

    public DetalleDia(int backGroundImagen, int iconImage, String title, String description) {
        BackGroundImagen = backGroundImagen;
        IconImage = iconImage;
        Title = title;
        Description = description;
    }

    public int getBackGroundImagen() {
        return BackGroundImagen;
    }

    public void setBackGroundImagen(int backGroundImagen) {
        BackGroundImagen = backGroundImagen;
    }

    public int getIconImage() {
        return IconImage;
    }

    public void setIconImage(int iconImage) {
        IconImage = iconImage;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
