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
import org.ciaccg.ce.model.Cliente;
/**
 *
 * @author Iván
 */
public class TableAdapterClientes {
    TableView tabla;
    TableColumn colIdCliente; //IdPersona
    TableColumn colNombre;
    TableColumn colPaterno;
    TableColumn colMaterno;
    TableColumn colRfc;
    TableColumn colCurp;
    TableColumn colDomicilio;
    TableColumn colCiudad;
    TableColumn colCodigoPostal;
    TableColumn colEmail;
    TableColumn colTelMovil;
    TableColumn colFechaNacimiento;
    TableColumn colFolioCliente;
    TableColumn colNombreContacto;
    TableColumn colCategoria;
    //TableColumn colEstatus;

    ObservableList<Cliente> clientes;
    public TableAdapterClientes(TableView tabla, List<Cliente> clientes){
        this.tabla = tabla;/*Recive lista no observable porque el servicio REST tiene problemas con 
        las listas observables por eso solo la vista usa listas observables */
        actualizar(clientes);
        crearColumnas();
        agregarColumnas();
    }
    private void crearColumnas(){
        colIdCliente = new TableColumn("ID");
        colFolioCliente = new TableColumn("No. Cliente");
        colNombre = new TableColumn("Nombre");
        colPaterno = new TableColumn("Paterno");
        colMaterno = new TableColumn("Materno");
        colCiudad = new TableColumn("Ciudad");
        colEmail = new TableColumn("Email");
        colCategoria = new TableColumn("Categoria");

        colTelMovil = new TableColumn("Telefono Movil");
        colRfc = new TableColumn("RFC");
        colNombreContacto = new TableColumn("Nomb. Contacto");
        colDomicilio = new TableColumn("Domicilio");//
        colCurp = new TableColumn("Curp");//
        colCodigoPostal = new TableColumn("C.P.");//
        colFechaNacimiento = new TableColumn("Fecha Nac.");//
        //colEstatus = new TableColumn("Estatus");
        
        colIdCliente.setCellFactory(new Callback<TableColumn<Object, Integer>, TableCell<Object,Integer>>()
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
                        if (getIndex() >= 0 && getIndex() < clientes.size())
                        {
                            setAlignment(Pos.CENTER_RIGHT);
                            setText("" + clientes.get(getIndex()).getIdentificador());
                        }
                    }
                };
                return tc;
            }                
        });
        colNombre.setCellFactory(new Callback<TableColumn<Object, String>, TableCell<Object,String>>()
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
                        if (getIndex() >= 0 && getIndex() < clientes.size())
                        {
                            setAlignment(Pos.CENTER_RIGHT);
                            setText(clientes.get(getIndex()).getPersona().getNombre());
                        }
                    }
                };
                return tc;
            }                
        });
        colMaterno.setCellFactory(new Callback<TableColumn<Object, Integer>, TableCell<Object,Integer>>()
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
                        if (getIndex() >= 0 && getIndex() < clientes.size())
                        {
                            setAlignment(Pos.CENTER_RIGHT);
                            setText("" + clientes.get(getIndex()).getPersona().getApellidoMaterno());
                        }
                    }
                };
                return tc;
            }                
        });
        colPaterno.setCellFactory(new Callback<TableColumn<Object, Integer>, TableCell<Object,Integer>>()
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
                        if (getIndex() >= 0 && getIndex() < clientes.size())
                        {
                            setAlignment(Pos.CENTER_RIGHT);
                            setText("" + clientes.get(getIndex()).getPersona().getApellidoPaterno());
                        }
                    }
                };
                return tc;
            }                
        });
        colFolioCliente.setCellFactory(new Callback<TableColumn<Object, Integer>, TableCell<Object,Integer>>()
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
                        if (getIndex() >= 0 && getIndex() < clientes.size())
                        {
                            setAlignment(Pos.CENTER_RIGHT);
                            setText("" + clientes.get(getIndex()).getFolioCliente());
                        }
                    }
                };
                return tc;
            }                
        });
        colCiudad.setCellFactory(new Callback<TableColumn<Object, Integer>, TableCell<Object,Integer>>()
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
                        if (getIndex() >= 0 && getIndex() < clientes.size())
                        {
                            setAlignment(Pos.CENTER_RIGHT);
                            setText("" + clientes.get(getIndex()).getPersona().getCiudad());
                        }
                    }
                };
                return tc;
            }                
        });
        colEmail.setCellFactory(new Callback<TableColumn<Object, Integer>, TableCell<Object,Integer>>()
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
                        if (getIndex() >= 0 && getIndex() < clientes.size())
                        {
                            setAlignment(Pos.CENTER_RIGHT);
                            setText("" + clientes.get(getIndex()).getPersona().getEmail());
                        }
                    }
                };
                return tc;
            }                
        });
        
        colCategoria.setCellFactory(new Callback<TableColumn<Object, String>, TableCell<Object,String>>()//
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
                        if (getIndex() >= 0 && getIndex() < clientes.size())
                        {
                            setAlignment(Pos.CENTER_RIGHT);
                            setText("" + clientes.get(getIndex()).getCategoria());
                        }
                    }
                };
                return tc;
            }                
        });
        
        colTelMovil.setCellFactory(new Callback<TableColumn<Object, String>, TableCell<Object,String>>()//
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
                        if (getIndex() >= 0 && getIndex() < clientes.size())
                        {
                            setAlignment(Pos.CENTER_RIGHT);
                            setText("" + clientes.get(getIndex()).getPersona().getTelMovil());
                        }
                    }
                };
                return tc;
            }                
        });
        colRfc.setCellFactory(new Callback<TableColumn<Object, String>, TableCell<Object,String>>()//
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
                        if (getIndex() >= 0 && getIndex() < clientes.size())
                        {
                            setAlignment(Pos.CENTER_RIGHT);
                            setText("" + clientes.get(getIndex()).getPersona().getRfc());
                        }
                    }
                };
                return tc;
            }                
        });
        colNombreContacto.setCellFactory(new Callback<TableColumn<Object, String>, TableCell<Object,String>>()
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
                        if (getIndex() >= 0 && getIndex() < clientes.size())
                        {
                            setAlignment(Pos.CENTER_RIGHT);
                            setText("" + clientes.get(getIndex()).getNombreContacto());
                        }
                    }
                };
                return tc;
            }                
        });        
        colDomicilio.setCellFactory(new Callback<TableColumn<Object, String>, TableCell<Object,String>>()
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
                        if (getIndex() >= 0 && getIndex() < clientes.size())
                        {
                            setAlignment(Pos.CENTER_RIGHT);
                            setText("" + clientes.get(getIndex()).getPersona().getDomicilio());
                        }
                    }
                };
                return tc;
            }                
        });
        
        colCurp.setCellFactory(new Callback<TableColumn<Object, String>, TableCell<Object,String>>()
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
                        if (getIndex() >= 0 && getIndex() < clientes.size())
                        {
                            setAlignment(Pos.CENTER_RIGHT);
                            setText("" + clientes.get(getIndex()).getPersona().getCurp() + "   ");
                        }
                    }
                };
                return tc;
            }                
        });
        colCodigoPostal.setCellFactory(new Callback<TableColumn<Object, String>, TableCell<Object,String>>()//
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
                        if (getIndex() >= 0 && getIndex() < clientes.size())
                        {
                            setAlignment(Pos.CENTER_RIGHT);
                            setText("" + clientes.get(getIndex()).getPersona().getCodigoPostal());
                        }
                    }
                };
                return tc;
            }                
        });
        colFechaNacimiento.setCellFactory(new Callback<TableColumn<Object, String>, TableCell<Object,String>>()//
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
                        if (getIndex() >= 0 && getIndex() < clientes.size())
                        {
                            setAlignment(Pos.CENTER);
                            setText("" + clientes.get(getIndex()).getPersona().getFechaNacimiento());
                        }
                    }
                };
                return tc;
            }                
        });
        
        /*colEstatus.setCellFactory(new Callback<TableColumn<Object, Integer>, TableCell<Object,Integer>>()
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
                        if (getIndex() >= 0 && getIndex() < clientes.size())
                        {
                            setAlignment(Pos.CENTER_RIGHT);
                            setText("" + clientes.get(getIndex()).getEstatus());
                        }
                    }
                };
                return tc;
            }                
        });*/
    }
    public void actualizar(List<Cliente> clientes){
        this.clientes = null; 
        this.clientes = FXCollections.observableArrayList(clientes);
        tabla.setItems(this.clientes);
    }
    public void limpiar(){
        tabla = new TableView();
        agregarColumnas();
    }
    private void agregarColumnas(){
        tabla.getColumns().clear();
        tabla.getColumns().addAll(
                colIdCliente,colFolioCliente,
                colNombre,colPaterno,colMaterno,colFechaNacimiento,
                colCiudad,colDomicilio,colCodigoPostal,
                colNombreContacto,colEmail,colTelMovil,colCategoria,
                colRfc,colCurp
        );
    }
}
