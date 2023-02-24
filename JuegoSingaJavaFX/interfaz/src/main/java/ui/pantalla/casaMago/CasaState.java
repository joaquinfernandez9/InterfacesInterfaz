package ui.pantalla.casaMago;

import lombok.Data;

@Data
public class CasaState {
    private String mensaje;
    private String casa;
    private boolean isCambio;


    public CasaState(String mensaje, String casa, boolean isCambio) {
        this.mensaje = mensaje;
        this.casa = casa;
        this.isCambio = isCambio;
    }
}
