package Models;

public class HolidayField extends Field {
    private String appearance;

    // Constructor
    public HolidayField(String appearance) {
        super("Holiday");
        this.appearance = appearance;
    }

    // Getters and Setters
    public String getAppearance() {return appearance;}
    public void setAppearance(String appearance) {this.appearance = appearance;}
    
}
