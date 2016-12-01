/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ciaccg.pekesfx.gui;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.ciaccg.ce.core.ControladorLogin;
import org.ciaccg.ce.model.estadoRespuesta;
/**
 *
 * @author ISSC311.PekesTeam
 */
public class LoginWindow
{
    @FXML AnchorPane rootPane;
    @FXML TextField txtUsuario;
    @FXML PasswordField txtContraseña;
    @FXML Button btnEntrar;
    
    Stage stage;
    Scene scene;
    
    WindowMain app;  
    FXMLLoader fxmll;
    ControladorLogin login = new ControladorLogin();
    public LoginWindow(WindowMain app)
    {
        this.app = app;    
        fxmll = new FXMLLoader(System.class.getResource("/org/ciaccg/pekesfx/gui/fxml/InicioSesion.fxml"));
        fxmll.setController(this);
    }
     public AnchorPane getRootPane() {

        return rootPane;
    }
   
      public void inicializar() throws Exception {
        fxmll.load();
        login = new ControladorLogin();
        this.agregarListeners();
    }
    private void agregarListeners()
    {
       btnEntrar.setOnAction(evt -> {
           try {
               IniciarSesion();
           }
           catch (Exception ex) {
              
           }
        });
    }
    private void IniciarSesion() throws Exception
    {
        try
        {
            //aqui se manda llamar al controlador que ejecuta la logica de base de datos
            String usuario = txtUsuario.getText();
            String contraseña = txtContraseña.getText();
            estadoRespuesta logeo = login.Acceder(usuario, contraseña);
            if (logeo.getEstado().equals("Aceptado")) {
                Stage stage = new Stage();
                BorderPane pane = new BorderPane();
                Scene scene = new Scene(pane);
                stage.setScene(scene);
                stage.setTitle("Inicio de Sesion Correcto " + logeo.getMensaje());
                stage.show();
            }else {
                Stage stage = new Stage();
                BorderPane pane = new BorderPane();
                Scene scene = new Scene(pane);
                stage.setScene(scene);
                stage.setTitle("Inicio de Sesion Incorrecto" + logeo.getMensaje());
                stage.show();
            }
        } catch (Exception e) {
            String mensaje = e.getMessage();
            e.printStackTrace();
        }

    }
}
