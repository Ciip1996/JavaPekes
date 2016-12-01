package org.ciaccg.pekesfx.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class PanelEstilos {
   
    @FXML AnchorPane rootPane;
    WindowMain app;
    FXMLLoader fxmll;

    public PanelEstilos(WindowMain app) {
        this.app = app;
        fxmll = new FXMLLoader(System.class.getResource("/org/ciaccg/pekesfx/gui/fxml/Panel_Estilos.fxml"));
        fxmll.setController(this);
    }
    public AnchorPane getRootPane() {
        return rootPane;
    }
    public void inicializar() throws Exception {
        fxmll.load();
        this.agregarListeners();
    }
    private void agregarListeners() throws Exception {
      
    }
  
}
