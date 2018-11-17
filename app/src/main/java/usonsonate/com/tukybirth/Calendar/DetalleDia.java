package usonsonate.com.tukybirth.Calendar;

public class DetalleDia {

    private int Icon;
    private String Title;
    private String Description;

    public DetalleDia() {
    }

    public DetalleDia(int icon, String title, String description) {
        Icon = icon;
        Title = title;
        Description = description;
    }

    public int getIcon() {
        return Icon;
    }

    public void setIcon(int icon) {
        Icon = icon;
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
