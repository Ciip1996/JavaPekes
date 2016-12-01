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
import org.ciaccg.ce.model.Venta;

/**
 *
 * @author dell1
 */
public class TableAdapterVentas {
   
    TableView tabla;
    TableColumn columnaId;
    TableColumn columnaSubtotal;
    TableColumn columnaDescuento;
    TableColumn columnaImpuestos;
    TableColumn columnaTotal;
    TableColumn columnaLiquidado;
    TableColumn columnaNombre;
    TableColumn columnaFolio;
    

    ObservableList<Venta> ventas;
    
    public TableAdapterVentas(TableView tabla,List<Venta> ventas)
    {
        this.tabla = tabla;
        actualizar(ventas);
        crearColumnas();
        agregarColumnas();
    }
    
    private void crearColumnas()
    {
        columnaId = new TableColumn("ID");
        columnaSubtotal = new TableColumn("Subtotal");
        columnaDescuento = new TableColumn("Descuento");
        columnaImpuestos = new TableColumn("Impuestos");
        columnaTotal = new TableColumn("Total");
        columnaLiquidado = new TableColumn("Liquidado");
        columnaNombre = new TableColumn("Nombre de Contacto");
        columnaFolio = new TableColumn("Folio Cliente");
        
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
                        if (getIndex() >= 0 && getIndex() < ventas.size()) 
                        {
                            System.out.println("renglon:"+getIndex());
                            setAlignment(Pos.CENTER_RIGHT);
                            setText(""+ventas.get(getIndex()).getIdVenta());
                           
                        }
                    }
                };
            }
        });
        columnaSubtotal.setCellFactory(new Callback<TableColumn<Object,Double>,TableCell<Object,Double>>()
        {
            @Override
            public TableCell<Object,Double> call(TableColumn<Object,Double>param)
            {
           
                return new TableCell<Object,Double>()
                {
                    @Override
                    protected void updateItem(Double item,boolean empty)
                    {
                        super.updateItem(item,empty);
                        if (getIndex() >= 0 && getIndex() < ventas.size()) 
                        {
                            setAlignment(Pos.CENTER_LEFT);
                            setText(""+ventas.get(getIndex()).getSubtotal());
                        }
                    }
                };
            }
        });
       columnaDescuento.setCellFactory(new Callback<TableColumn<Object,Double>,TableCell<Object,Double>>()
        {
            @Override
            public TableCell<Object,Double> call(TableColumn<Object,Double>param)
            {
               
                return new TableCell<Object,Double>()
                {
                    @Override
                    protected void updateItem(Double item,boolean empty)
                    {
                        super.updateItem(item,empty);
                        if (getIndex() >= 0 && getIndex() < ventas.size()) 
                        {
                            setAlignment(Pos.CENTER_LEFT);
                            setText(""+ventas.get(getIndex()).getDescuento());
                        }
                    }
                };
            }
        });
       columnaImpuestos.setCellFactory(new Callback<TableColumn<Object,Double>,TableCell<Object,Double>>()
        {
            @Override
            public TableCell<Object,Double> call(TableColumn<Object,Double>param)
            {
               
                return new TableCell<Object,Double>()
                {
                    @Override
                    protected void updateItem(Double item,boolean empty)
                    {
                        super.updateItem(item,empty);
                        if (getIndex() >= 0 && getIndex() < ventas.size()) 
                        {
                            setAlignment(Pos.CENTER_LEFT);
                            setText(""+ventas.get(getIndex()).getImpuestos());
                        }
                    }
                };
            }
        });
       columnaTotal.setCellFactory(new Callback<TableColumn<Object,Double>,TableCell<Object,Double>>()
        {
            @Override
            public TableCell<Object,Double> call(TableColumn<Object,Double>param)
            {
                
                return new TableCell<Object,Double>()
                {
                    @Override
                    protected void updateItem(Double item,boolean empty)
                    {
                        super.updateItem(item,empty);
                        if (getIndex() >= 0 && getIndex() < ventas.size()) 
                        {
                            setAlignment(Pos.CENTER_LEFT);
                            setText(""+ventas.get(getIndex()).getTotal());
                        }
                    }
                };
            }
        });
        columnaLiquidado.setCellFactory(new Callback<TableColumn<Object,Integer>,TableCell<Object,Integer>>()
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
                        if (getIndex() >= 0 && getIndex() < ventas.size()) 
                        {
                            System.out.println("renglon:"+getIndex());
                            setAlignment(Pos.CENTER_RIGHT);
                            setText(""+ventas.get(getIndex()).getLiquidado());
                           
                        }
                    }
                };
            }
        });
        columnaNombre.setCellFactory(new Callback<TableColumn<Object,String>,TableCell<Object,String>>()
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
                        if (getIndex() >= 0 && getIndex() < ventas.size()) 
                        {
                            setAlignment(Pos.CENTER_LEFT);
                            setText(""+ventas.get(getIndex()).getCliente().getNombreContacto());
                        }
                    }
                };
            }
        });
        columnaFolio.setCellFactory(new Callback<TableColumn<Object,String>,TableCell<Object,String>>()
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
                        if (getIndex() >= 0 && getIndex() < ventas.size()) 
                        {
                            setAlignment(Pos.CENTER_LEFT);
                            setText(""+ventas.get(getIndex()).getCliente().getFolioCliente());
                        }
                    }
                };
            }
        });
    }
    
    public void actualizar(List<Venta> ventas)
    {
        this.ventas = FXCollections.observableArrayList(ventas);
        tabla.setItems(this.ventas);
    }
    
    public void agregarColumnas()
    {
        tabla.getColumns().clear();
        tabla.getColumns().addAll(columnaId,columnaSubtotal,columnaDescuento,columnaImpuestos,columnaTotal,columnaLiquidado,columnaNombre, columnaFolio);
    }
    


}
