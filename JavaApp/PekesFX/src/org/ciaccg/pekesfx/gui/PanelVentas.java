/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ciaccg.pekesfx.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.ciaccg.ce.core.ControladorVenta;
import org.ciaccg.ce.model.Cliente;
import org.ciaccg.ce.model.Venta;
import org.ciaccg.pekesfx.gui.components.TableAdapterVentas;
/**
 *
 * @author dell1
 */
public class PanelVentas {
    @FXML AnchorPane rootPane;
    @FXML TableView tblVentas;
    @FXML Button btnBuscarVenta;
    @FXML TextField txtNombreContacto;
    
    WindowMain app;
    FXMLLoader fxmll;

    ControladorVenta cv;
    TableAdapterVentas tav;
    
     public PanelVentas(WindowMain app) {

        this.app = app;
        fxmll = new FXMLLoader(System.class.getResource("/org/ciaccg/pekesfx/gui/fxml/Panel_Ventas.fxml"));
        fxmll.setController(this);

    }
    public AnchorPane getRootPane() {

        return rootPane;
    }
    public void inicializar() throws Exception {
        fxmll.load();
        try
        {
            cv = new ControladorVenta();
            tav = new TableAdapterVentas(tblVentas,cv.getAllRest());
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        this.agregarListeners();
    }
    private void agregarListeners() {
         btnBuscarVenta.setOnAction(evt -> {
            buscarVentasPorCliente();      
        });
    }
   
    private void buscarVentasPorCliente()
    {
        try
        {
            cv = new ControladorVenta();
            Cliente cl = new Cliente();
            Venta v = new Venta();
            cl.setNombreContacto(txtNombreContacto.getText());
            v.setCliente(cl);
            tav = new TableAdapterVentas(tblVentas,cv.findByClientNameRest(v));
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
