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
import org.ciaccg.ce.model.Galeria;

/**
 *
 * @author dell1
 */
public class TableAdapterGaleria {
  
    TableView tabla;
    TableColumn columnaId;
    TableColumn columnaCodigo;

    TableColumn columnaDescripcion;
    TableColumn columnaEstatus;
    
    ObservableList<Galeria> galerias;
    
    public TableAdapterGaleria(TableView tabla,List<Galeria> galerias)
    {
        this.tabla = tabla;
        actualizar(galerias);
        crearColumnas();
        agregarColumnas();
    }
    
    private void crearColumnas()
    {
        columnaId = new TableColumn("ID");
        columnaCodigo = new TableColumn("Código");
       
        columnaDescripcion = new TableColumn("Descripcion");
        columnaEstatus = new TableColumn("Estatus");
      
        
        columnaId.setCellFactory(new Callback<TableColumn<Object,Integer>,TableCell<Object,Integer>>()
        {
            @Override
            public TableCell<Object,Integer> call(TableColumn<Object,Integer>param)
            {
               
                return new TableCell<Object,Integer>()
                {
                    @Override
                    protected void updateItem(Integer item,boolean empty)
                    {
                        super.updateItem(item,empty);
                        if (getIndex() >= 0 && getIndex() < galerias.size()) 
                        {
                           
                            setAlignment(Pos.CENTER_RIGHT);
                            setText(""+galerias.get(getIndex()).getIdGaleria());
                           
                        }
                    }
                };
            }
        });
        columnaCodigo.setCellFactory(new Callback<TableColumn<Object,String>,TableCell<Object,String>>()
        {
            @Override
            public TableCell<Object,String> call(TableColumn<Object,String>param)
            {
           
                return new TableCell<Object,String>()
                {
                    @Override
                    protected void updateItem(String item,boolean empty)
                    {
                        super.updateItem(item,empty);
                        if (getIndex() >= 0 && getIndex() < galerias.size()) 
                        {
                            setAlignment(Pos.CENTER_LEFT);
                            setText(""+galerias.get(getIndex()).getCodigo());
                        }
                    }
                };
            }
        });

        
        columnaDescripcion.setCellFactory(new Callback<TableColumn<Object,String>,TableCell<Object,String>>()
        {
            @Override
            public TableCell<Object,String> call(TableColumn<Object,String>param)
            {
               
                return new TableCell<Object,String>()
                {
                    @Override
                    protected void updateItem(String item,boolean empty)
                    {
                        super.updateItem(item,empty);
                        if (getIndex() >= 0 && getIndex() < galerias.size()) 
                        {
                            setAlignment(Pos.CENTER_LEFT);
                            setText(""+galerias.get(getIndex()).getDescripcion());
                        }
                    }
                };
            }
        });
        columnaEstatus.setCellFactory(new Callback<TableColumn<Object,Integer>,TableCell<Object,Integer>>()
        {
            @Override
            public TableCell<Object,Integer> call(TableColumn<Object,Integer>param)
            {
               
                return new TableCell<Object,Integer>()
                {
                    @Override
                    protected void updateItem(Integer item,boolean empty)
                    {
                        super.updateItem(item,empty);
                        if (getIndex() >= 0 && getIndex() < galerias.size()) 
                        {
                            
                            setAlignment(Pos.CENTER_RIGHT);
                            setText(""+galerias.get(getIndex()).getStatus());
                           
                        }
                    }
                };
            }
        });
    }
    
    public void actualizar(List<Galeria> galerias)
    {
        this.galerias = FXCollections.observableArrayList(galerias);
        tabla.setItems(this.galerias);
    }
    
    public void agregarColumnas()
    {
        tabla.getColumns().clear();
        tabla.getColumns().addAll(columnaId,columnaCodigo,columnaDescripcion,columnaEstatus);
    }
    

}
