package org.ciaccg.pekesfx.gui;

import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


/**
 *
 * @author Grios
 */
public class WindowMain extends Application
{
    @FXML BorderPane pnlContenedorPrincipal;
    @FXML JFXButton btnCargarModuloClientes;
    @FXML JFXButton btnCargarModuloCalzado;
    @FXML JFXButton btnCargarModuloEstilos;
    @FXML JFXButton btnCargarModuloGalería;
    @FXML JFXButton btnCargarModuloVentas;
    
    Stage stage;
    Scene scene;
    
    PanelClientes pnlClientes;
    PanelCalzado pnlCalzado;
    PanelEstilos pnlEstilos;
    PanelGaleria pnlGaleria;
    PanelVentas pnlVentas;
    LoginWindow pnlLogin;
    
    FXMLLoader fxmll;
    
    public WindowMain()
    {
        super();
        fxmll = new FXMLLoader(System.class.getResource("/org/ciaccg/pekesfx/gui/fxml/Window_Main.fxml"));
        fxmll.setController(this);
    }        
    
    @Override
    public void start(Stage primaryStage)
    {
        stage = primaryStage;                        
        try
        {
            fxmll.load();
            scene = new Scene(fxmll.getRoot());
            inicializar();
            agregarListeners();
            stage.setMinWidth(800);        
            stage.setMinHeight(500);            
            stage.setScene(scene);
            stage.show();
        } 
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }
        
        
    }
    
    private void inicializar() 
    {
        try{
        pnlClientes = new PanelClientes(this);
        pnlClientes.inicializar();
        pnlCalzado = new PanelCalzado(this);
        pnlCalzado.inicializar();
        pnlLogin = new LoginWindow(this);
        pnlLogin.inicializar();
        pnlGaleria = new PanelGaleria(this);
        pnlGaleria.inicializar();
        pnlVentas = new PanelVentas(this);
        pnlVentas.inicializar();
        
        //cargarModuloLogin();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void agregarListeners()
    {
        btnCargarModuloClientes.setOnAction(evt -> {
            pnlContenedorPrincipal.setCenter(pnlClientes.getRootPane());
        });
        btnCargarModuloCalzado.setOnAction(evt -> {
            pnlContenedorPrincipal.setCenter(pnlCalzado.getRootPane());
        });
        btnCargarModuloEstilos.setOnAction(evt -> {
            pnlContenedorPrincipal.setCenter(pnlEstilos.getRootPane());
        });
        btnCargarModuloGalería.setOnAction(evt -> {
            pnlContenedorPrincipal.setCenter(pnlGaleria.getRootPane());
        });
         btnCargarModuloVentas.setOnAction(evt -> {
            pnlContenedorPrincipal.setCenter(pnlVentas.getRootPane());
        });
    }
    private void cargarModuloLogin(){
       // pnlContenedorPrincipal.setCenter(pnlLogin.getRootPane());
    }
}
