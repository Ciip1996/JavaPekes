/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ciaccg.pekesfx.gui.components;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import org.ciaccg.ce.model.Calzado;
/**
 *
 * @author Iván
 */
public class TableAdapterCalzado {
    TableView tabla;
    TableColumn colIdCalzado; 
    TableColumn colCodigo;
    TableColumn colEspecificaciones;
    TableColumn colEstatus;
    
    ObservableList<Calzado> calzados;
    public TableAdapterCalzado(TableView tabla, List<Calzado> calzados){
        this.tabla = tabla;/*Recive lista no observable porque el servicio REST tiene problemas con 
        las listas observables por eso solo la vista usa listas observables */
        actualizar(calzados);
        crearColumnas();
        agregarColumnas();
    }
    private void crearColumnas(){
       
        colIdCalzado = new TableColumn("ID");
        colCodigo = new TableColumn("Calzado");
        colEspecificaciones = new TableColumn("Especificaciones");
        colEstatus = new TableColumn("Estatus");

        colIdCalzado.setCellFactory(new Callback<TableColumn<Object, Integer>, TableCell<Object,Integer>>()
        {
            @Override
            public TableCell<Object, Integer> call(TableColumn<Object, Integer> param)
            {
                TableCell tc = new TableCell<Object,Integer>()
                {
                    @Override 
                    protected void updateItem(Integer item, boolean empty)
                    {
                        super.updateItem(item, empty);//super es la clase padre del metodo a overradiar
                        if (getIndex() >= 0 && getIndex() < calzados.size())
                        {
                            setAlignment(Pos.CENTER_RIGHT);
                            setText("" + calzados.get(getIndex()).getIdentificador());
                        }
                    }
                };
                return tc;
            }                
        });
        colCodigo.setCellFactory(new Callback<TableColumn<Object, String>, TableCell<Object,String>>()
        {
            @Override
            public TableCell<Object, String> call(TableColumn<Object, String> param)
            {
                TableCell tc = new TableCell<Object,String>()
                {
                    @Override 
                    protected void updateItem(String item, boolean empty)
                    {
                        super.updateItem(item, empty);//super es la clase padre del metodo a overradiar
                        if (getIndex() >= 0 && getIndex() < calzados.size())
                        {
                            setAlignment(Pos.CENTER_RIGHT);
                            setText(calzados.get(getIndex()).getCodigo());
                        }
                    }
                };
                return tc;
            }                
        });
        colEspecificaciones.setCellFactory(new Callback<TableColumn<Object, Integer>, TableCell<Object,Integer>>()
        {
            @Override
            public TableCell<Object, Integer> call(TableColumn<Object, Integer> param)
            {
                TableCell tc = new TableCell<Object,Integer>()
                {
                    @Override 
                    protected void updateItem(Integer item, boolean empty)
                    {
                        super.updateItem(item, empty);//super es la clase padre del metodo a overradiar
                        if (getIndex() >= 0 && getIndex() < calzados.size())
                        {
                            setAlignment(Pos.CENTER_RIGHT);
                            setText("" + calzados.get(getIndex()).getEspecificaciones());
                        }
                    }
                };
                return tc;
            }                
        });
        colEstatus.setCellFactory(new Callback<TableColumn<Object, Integer>, TableCell<Object,Integer>>()
        {
            @Override
            public TableCell<Object, Integer> call(TableColumn<Object, Integer> param)
            {
                TableCell tc = new TableCell<Object,Integer>()
                {
                    @Override 
                    protected void updateItem(Integer item, boolean empty)
                    {
                        super.updateItem(item, empty);//super es la clase padre del metodo a overradiar
                        if (getIndex() >= 0 && getIndex() < calzados.size())
                        {
                            setAlignment(Pos.CENTER_RIGHT);
                            setText("" + calzados.get(getIndex()).getEstatus());
                        }
                    }
                };
                return tc;
            }                
        });
    }
    public void actualizar(List<Calzado> calzados){
        this.calzados = null; 
        this.calzados = FXCollections.observableArrayList(calzados);
        tabla.setItems(this.calzados);
    }
    public void limpiar(){
        tabla = new TableView();
        agregarColumnas();
    }
    private void agregarColumnas(){
        tabla.getColumns().clear();
        tabla.getColumns().addAll(
               colIdCalzado,
               colCodigo,
               colEspecificaciones,
               colEstatus
        );
    }
}
