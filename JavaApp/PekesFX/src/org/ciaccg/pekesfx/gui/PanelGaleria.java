/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ciaccg.pekesfx.gui;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.ciaccg.ce.core.ControladorGaleria;
import org.ciaccg.ce.model.Galeria;
import org.ciaccg.pekesfx.gui.components.TableAdapterGaleria;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javax.imageio.ImageIO;

/**
 *
 * @author pekesTeam
 */
public class PanelGaleria {

    @FXML
    AnchorPane rootPane;
    WindowMain app;
    FXMLLoader fxmll;
    @FXML
    TextField txtIdGaleria;
    @FXML
    TextField txtCodigo;
    @FXML
    TextField txtDescripcion;
    @FXML
    ComboBox cmbEstatus;
    @FXML
    Button btnRefrescarTabla;
    @FXML
    Button btnInsertarGaleria;
    @FXML
    Button btnModificarGaleria;
    @FXML
    Button btnSeleccionarFoto;
    @FXML
    ImageView imgvFotografia;

    
    @FXML
    TableView tblGaleria;
    @FXML
    ListView lstGaleria;
    ObservableList<String> listaString;

    TableAdapterGaleria tag;
    ControladorGaleria cg;

    public PanelGaleria(WindowMain app) {

        this.app = app;
        fxmll = new FXMLLoader(System.class.getResource("/org/ciaccg/pekesfx/gui/fxml/Panel_Galeria.fxml"));
        fxmll.setController(this);

    }

    public AnchorPane getRootPane() {
        return rootPane;
    }

    public void inicializar() throws Exception {
        fxmll.load();
        this.agregarListeners();

        cg = new ControladorGaleria();
        consultarGaleria();
    }

    private void agregarListeners() {
        btnInsertarGaleria.setOnAction(evt -> {
            insertarFoto();
            consultarGaleria();
        });
        btnModificarGaleria.setOnAction(evt -> {
            modificarFoto();
            consultarGaleria();
        });
        btnSeleccionarFoto.setOnAction(evt -> {
            seleccionarFoto();
        });
        tblGaleria.getSelectionModel().selectedItemProperty().addListener((ObservableValue obs, Object oldSelection, Object selected) -> {
            try {
                selectionChanged(selected);
            } catch (Exception ex) {
                String mensaje = ex.getMessage();
            }
        });

    }

    private void insertarFoto() {
        try {
            if (txtDescripcion.getText().isEmpty()  || txtCodigo.getText().isEmpty()) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Advertencia");
                alert.setHeaderText("Faltan campos por llenar");
                alert.setContentText("Favor de llenar los campos de texto para continuar.");
                alert.showAndWait();
            } else {
                cg = new ControladorGaleria();
                Galeria galeria = new Galeria();
                galeria.setDescripcion(txtDescripcion.getText());
                galeria.setCodigo(txtCodigo.getText());
                Image img = imgvFotografia.getImage();
                if (img == null) {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Advertencia");
                    alert.setHeaderText("Faltan campos por llenar");
                    alert.setContentText("Favor de seleccionar una foto.");

                    alert.showAndWait();
                } else {
                    BufferedImage buf_image = null;
                    byte[] imageByteArray = bufferedImageToByteArray(SwingFXUtils.fromFXImage(img, buf_image), "jpg");
                    String strImg = encodeImage(imageByteArray);
                    galeria.setFoto(strImg);
                    galeria.setStatus(Integer.valueOf(cmbEstatus.getSelectionModel().getSelectedItem().toString()));
                    cg.insertRest(galeria);
                }
            }
        } catch (Exception ex) {
            String mensaje = ex.getMessage();
            ex.printStackTrace();
        }
    }

    private void consultarGaleria() {
        try {
            cg = new ControladorGaleria();
            tag = new TableAdapterGaleria(tblGaleria, cg.getAllRest());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void modificarFoto() {
        try {
            if (txtDescripcion.getText().isEmpty() || txtIdGaleria.getText().isEmpty() || txtCodigo.getText().isEmpty()) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Advertencia");
                alert.setHeaderText("Faltan campos por llenar");
                alert.setContentText("Favor de llenar los campos de texto para continuar.");

                alert.showAndWait();
            } else {
                Galeria galeria = new Galeria();
                galeria.setIdGaleria(Integer.valueOf(txtIdGaleria.getText()));
                galeria.setDescripcion(txtDescripcion.getText());
                galeria.setCodigo(txtCodigo.getText());
                Image img = imgvFotografia.getImage();
                if (img == null) {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Advertencia");
                    alert.setHeaderText("Faltan campos por llenar");
                    alert.setContentText("Favor de seleccionar una foto.");

                    alert.showAndWait();
                } else {
                    BufferedImage buf_image = null;
                    byte[] imageByteArray = bufferedImageToByteArray(SwingFXUtils.fromFXImage(img, buf_image), "jpg");
                    String strImg = encodeImage(imageByteArray);
                    galeria.setFoto(strImg);
                    galeria.setStatus(Integer.valueOf(cmbEstatus.getSelectionModel().getSelectedItem().toString()));
                    cg = new ControladorGaleria();
                    cg.updateRest(galeria);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void seleccionarFoto() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Seleccionar Fotografia");

        File file = new File("" + chooser.showOpenDialog(rootPane.getScene().getWindow()));
        Image image = new Image(file.toURI().toString());

        imgvFotografia.setImage(image);

    }

    public void selectionChanged(Object obj) throws Exception {
        Galeria gal = (Galeria) obj;

        txtIdGaleria.setText(gal.getIdGaleria() + "");
        txtCodigo.setText(gal.getCodigo());
        txtDescripcion.setText(gal.getDescripcion());
        Object x = gal.getStatus();
        cmbEstatus.getSelectionModel().select(x);
        String strImg = gal.getFoto();
        byte[] imageByteArray = decodeImage(strImg);
        InputStream in = new ByteArrayInputStream(imageByteArray);
        BufferedImage bImage = ImageIO.read(in);
        Image img = SwingFXUtils.toFXImage(bImage, null);;
        imgvFotografia.setImage(img);

    }

    public static byte[] decodeImage(String imageDataString) {
        return Base64.getDecoder().decode(imageDataString);
    }

    public static String encodeImage(byte[] imageByteArray) {
        return Base64.getEncoder().encodeToString(imageByteArray);
    }

    public static byte[] bufferedImageToByteArray(BufferedImage image, String format) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, format, baos);
        return baos.toByteArray();
    }
}
