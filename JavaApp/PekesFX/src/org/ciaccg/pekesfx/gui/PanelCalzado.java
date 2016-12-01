/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ciaccg.pekesfx.gui;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.ciaccg.ce.core.ControladorCalzadoREST;
import org.ciaccg.ce.model.Calzado;
import org.ciaccg.ce.model.Prueba;
/**
 *
 * @author ISSC311.PekesTeam
 */
public class PanelCalzado
{
    @FXML AnchorPane rootPane;
    @FXML TextField txtCodigoProducto;
    @FXML ComboBox cboMarca;
    @FXML Button btnSeleccionarFoto;
    @FXML TextArea txtDescripcion;
    @FXML TableView tblCalzado;
    WindowMain app;
    FXMLLoader fxmll;

    public PanelCalzado(WindowMain app) {
        this.app = app;
        fxmll = new FXMLLoader(System.class.getResource("/org/ciaccg/pekesfx/gui/fxml/Panel_Calzado.fxml"));
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
        btnSeleccionarFoto.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent evt) {
                try {
                    ControladorCalzadoREST c = new ControladorCalzadoREST();
                    Calzado calzado = new Calzado();                                        
                    calzado.setIdentificador(10);
                    calzado.setCodigo("MPK1000");
                    calzado.setMarca("Pekes");
                    calzado.setEspecificaciones("chido");
                    calzado.setFoto("");
                    calzado.setPreciosXml("<xml></xml>");
                    calzado.setEstatus(1);
                    //List<Calzado> listaCalzado = c.getAll();
                    //TableAdapterCalzado tblAC = new TableAdapterCalzado(tblCalzado, listaCalzado);                   
                    Prueba test = new Prueba();
                    test.setA("CACA");
                    c.insert(test);
                    
                } catch (Exception e) {
                    String mensaje = e.getMessage();
                    e.printStackTrace();
                }
            }
        });
    }
  
}
