package ui.common;

public enum Screens {
    LOGIN("/fxml/login.fxml"),
    MENU("/fxml/hello-view.fxml"),
    CASA_MAGO("/fxml/casa_mago.fxml"),
    MAZMORRA("/fxml/mazmorra.fxml"),;

    private String ruta;
    Screens(String ruta) {
        this.ruta=ruta;
    }
    public String getRuta(){return ruta;}
}