/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ciaccg.pekesfx.gui;

import java.sql.Date;
import static java.sql.Date.valueOf;
import java.time.LocalDate;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.ciaccg.ce.core.ControladorClienteBDD;
import org.ciaccg.ce.model.Cliente;
import org.ciaccg.ce.model.Persona;
import org.ciaccg.pekesfx.gui.components.TableAdapterClientes;

/**
 *
 * @author ISSC311.PekesTeam
 */
public class PanelClientes {

    @FXML AnchorPane rootPane;
    @FXML TextField txtNombre;
    @FXML TextField txtNombreContacto;
    @FXML TextField txtFolio;
    @FXML TextField txtApellidoPaterno;
    @FXML TextField txtApellidoMaterno;
    @FXML TextField txtFechaNacimiento;
    @FXML TextField txtRfc;
    @FXML TextField txtCurp;
    @FXML TextField txtDomicilio;
    @FXML TextField txtCiudad;
    @FXML TextField txtCodigoPostal;
    @FXML TextField txtEmail;
    @FXML TextField txtTelefonoMovil;
    @FXML TextField txtIdAlumno;
    @FXML TextField txtMatricula;
    @FXML TextField txtFechaIngreso;
    @FXML ComboBox cboCategoria;
    @FXML Button btnRefrescarTabla;
    @FXML Button btnCrearCliente;
    @FXML Button btnActualizarCliente;
    @FXML Button btnBajaLogica;
    @FXML Button btnFiltrar;
    //@FXML ToggleButton btnEstatus;
    @FXML TableView tblClientes;
    @FXML DatePicker dtFechaNacimiento;
    @FXML ListView lstClientes;
    
    WindowMain app;
    FXMLLoader fxmll;
    ObservableList<String> listaString;
    ControladorClienteBDD controladorCliente;
    TableAdapterClientes tableAdapterClientes;

    int IdActual;

    public PanelClientes(WindowMain app) {

        this.app = app;
        fxmll = new FXMLLoader(System.class.getResource("/org/ciaccg/pekesfx/gui/fxml/Panel_Clientes.fxml"));
        fxmll.setController(this);

    }

    public AnchorPane getRootPane() {

        return rootPane;
    }

    public void inicializar() {
        try{
            fxmll.load();
            controladorCliente = new ControladorClienteBDD();
            tableAdapterClientes = new TableAdapterClientes(tblClientes, controladorCliente.getAll("SELECT * FROM V_Cliente"));
            this.agregarListeners();
        }catch(Exception e){
            
        }
    }

    private void agregarListeners() {
        btnCrearCliente.setOnAction(evt -> {
            try {
                cargarCliente();
                actualizarDatos();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        btnRefrescarTabla.setOnAction(evt -> {
            try {
                actualizarDatos();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        btnActualizarCliente.setOnAction(evt -> {
            try {           
                modificarCliente();
                actualizarDatos();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        btnBajaLogica.setOnAction(evt -> {
            eliminarCliente();
        });
        btnFiltrar.setOnAction(evt -> {
            String query = "SELECT * FROM V_Cliente WHERE Estatus = 0;";
            try {
                tableAdapterClientes.limpiar();
                tableAdapterClientes.actualizar(controladorCliente.getAll(query));
            } catch (Exception ex) {                
                ex.printStackTrace();

            }
        });
        tblClientes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                mostrarDetallesClientes((Cliente) tblClientes.getSelectionModel().getSelectedItem());
            }
        });
    }
    private void limpiarCampos() {
        /**
         * Limpiar todos los campos
         */
        Platform.runLater(() -> { 
        txtNombreContacto.setText("");
        txtFolio.setText("");
        txtNombre.setText("");
        txtApellidoPaterno.setText("");
        txtApellidoMaterno.setText("");
        txtCiudad.setText("");
        txtCodigoPostal.setText("");
        txtCurp.setText("");
        txtDomicilio.setText("");
        txtEmail.setText("");
        });
    }

    private void mostrarDetallesClientes(Cliente cli) {
        Platform.runLater(() -> { 
        if (cli != null) {
            /*AQUI poner todoos los textbox y controles y setText con las propiedades del objeto*/
            Persona persona = new Persona();
            IdActual = cli.getIdentificador();
            txtNombreContacto.setText(cli.getNombreContacto());
            txtFolio.setText(cli.getFolioCliente());
            txtNombre.setText(cli.getPersona().getNombre());
            txtApellidoPaterno.setText(cli.getPersona().getApellidoPaterno());
            txtApellidoMaterno.setText(cli.getPersona().getApellidoMaterno());
            txtCiudad.setText(cli.getPersona().getCiudad());
            txtCodigoPostal.setText(cli.getPersona().getCodigoPostal());
            txtCurp.setText(cli.getPersona().getCurp());
            txtDomicilio.setText(cli.getPersona().getDomicilio());
            txtEmail.setText(cli.getPersona().getEmail());
            Date dt = cli.getPersona().getFechaNacimiento();
            LocalDate ld = dt.toLocalDate();
            dtFechaNacimiento.setValue(ld);
            txtRfc.setText(cli.getPersona().getRfc());
            txtTelefonoMovil.setText(cli.getPersona().getTelMovil());
            Object x = cli.getCategoria();
            cboCategoria.getSelectionModel().select(x);
            cli.setPersona(persona);
        } 
        });
        
    }

    private void eliminarCliente() {
        try {
            Cliente cl = new Cliente();
            cl.setIdentificador(IdActual);
            cl.setPersona(null);
            controladorCliente.deleteLogical(cl);
            //Limpiar campos visuales
            txtNombreContacto.setText("");
            txtFolio.setText("");
            txtNombre.setText("");
            txtApellidoPaterno.setText("");
            txtApellidoMaterno.setText("");
            txtCiudad.setText("");
            txtCodigoPostal.setText("");
            txtCurp.setText("");
            txtDomicilio.setText("");
            txtEmail.setText("");
            dtFechaNacimiento.setValue(null);
            txtRfc.setText("");
            txtTelefonoMovil.setText("");

        } catch (Exception ex) {
            String mensaje = ex.getMessage();
        }
    }

    private void actualizarDatos() throws Exception {    
        tableAdapterClientes.actualizar(controladorCliente.getAll("SELECT * FROM V_Cliente"));
    }

    private void cargarCliente() {
        try {
            Cliente cliente = new Cliente();
            Persona persona = new Persona();
            cliente.setEstatus(1);
            cliente.setCategoria(cboCategoria.getSelectionModel().getSelectedItem().toString());
            cliente.setNombreContacto(txtNombreContacto.getText());
            cliente.setFolioCliente(txtFolio.getText());
            persona.setApellidoMaterno(txtApellidoMaterno.getText());
            persona.setNombre(txtNombre.getText());
            persona.setApellidoPaterno(txtApellidoPaterno.getText());
            persona.setCiudad(txtCiudad.getText());
            persona.setCodigoPostal(txtCodigoPostal.getText());
            persona.setCurp(txtCurp.getText());
            persona.setDomicilio(txtDomicilio.getText());
            persona.setEmail(txtEmail.getText());
            persona.setFechaNacimiento(valueOf(dtFechaNacimiento.getValue()));
            persona.setRfc(txtRfc.getText());
            persona.setTelMovil(txtTelefonoMovil.getText());
            cliente.setPersona(persona);
            controladorCliente.insert(cliente);
        } catch (Exception e) {
            String mensaje = e.getMessage();
            e.printStackTrace();
        }
    }

    private void modificarCliente() {
        try {
            Cliente cliente = new Cliente();
            Persona persona = new Persona();
            //cliente.setEstatus(1);
            cliente.setNombreContacto(txtNombreContacto.getText());
            cliente.setFolioCliente(txtFolio.getText());
            persona.setApellidoMaterno(txtApellidoMaterno.getText());
            persona.setNombre(txtNombre.getText());
            persona.setApellidoPaterno(txtApellidoPaterno.getText());
            persona.setCiudad(txtCiudad.getText());
            persona.setCodigoPostal(txtCodigoPostal.getText());
            persona.setCurp(txtCurp.getText());
            persona.setDomicilio(txtDomicilio.getText());
            persona.setEmail(txtEmail.getText());
            persona.setFechaNacimiento(valueOf(dtFechaNacimiento.getValue()));
            persona.setRfc(txtRfc.getText());
            persona.setTelMovil(txtTelefonoMovil.getText());
            persona.setIdentificador(IdActual);
            cliente.setCategoria(cboCategoria.getSelectionModel().getSelectedItem().toString());
            cliente.setPersona(persona);
            controladorCliente.update(cliente);
        } catch (Exception e) {
            String mensaje = e.getMessage();
            e.printStackTrace();
        }
    }
}
