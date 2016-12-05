package org.ciaccg.pekesfx.gui;
import java.util.List;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.ciaccg.ce.core.ControladorCalzadoBDD;
import org.ciaccg.ce.model.Calzado;
import org.ciaccg.pekesfx.gui.components.TableAdapterCalzado;
/**
 *
 * @author ISSC311.PekesTeam
 */
public class PanelCalzado
{
    @FXML AnchorPane rootPane;
    @FXML ComboBox cboMarca;
    @FXML ComboBox cboFiltroMarca;
    @FXML ComboBox cboFiltroCategoria;
    @FXML Button btnSeleccionarFoto;
    @FXML TableView tblCalzado;
    @FXML TextField txtRutaFoto;
    @FXML TextField txtCodigoProducto;
    @FXML TextArea txtEspecificaciones;
    @FXML ImageView imgFoto;

    WindowMain app;
    FXMLLoader fxmll;
    ControladorCalzadoBDD ccbdd;

    public PanelCalzado(WindowMain app) {
        this.app = app;
        fxmll = new FXMLLoader(System.class.getResource("/org/ciaccg/pekesfx/gui/fxml/Panel_Calzado.fxml"));
        fxmll.setController(this);
    }
    public AnchorPane getRootPane() {
        return rootPane;
    }
    public void inicializar() throws Exception {
        try 
        {
            fxmll.load();
            this.cargarTablaCalzado();
            this.agregarListeners();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    private void agregarListeners() throws Exception {        
//        btnSeleccionarFoto.setOnAction(evt -> {
//            try {           
//               
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        });
    }
    public void cargarTablaCalzado() throws Exception{
        ccbdd = new ControladorCalzadoBDD();
        List<Calzado> listCalzado = ccbdd.getAll();
        TableAdapterCalzado tACalzado = new TableAdapterCalzado(tblCalzado, listCalzado);
    }
}
