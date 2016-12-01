/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ciaccg.ce.core;

import flexjson.JSONDeserializer;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;
import org.ciaccg.ce.db.ConexionSQLServer;
import org.ciaccg.ce.model.Cliente;
import org.ciaccg.ce.model.Galeria;
import org.ciaccg.ce.model.Venta;

/**
 *
 * @author dell1
 */
public class ControladorVenta {
    private static final String RUTA_WS = "http://127.0.0.1:94/WebPekes";
    
   /* public void getbyNameRest(Galeria galeria)throws Exception 
    {
       
        String rutaServicio = RUTA_WS + "/WebService.asmx/consultarGaleriaPorNombre";
        URL url = new URL(rutaServicio);
        HttpURLConnection connHttp = (HttpURLConnection) url.openConnection();
        int respuestaServidor = 0;
        BufferedWriter bwriter = null;
        OutputStream out = null;
        BufferedReader br = null;
        String lineaActual = null;
        String contenidoRespuesta = "";
        String strJSON = null;
        
        flexjson.JSONSerializer jss = new flexjson.JSONSerializer();
        
        //INDICAMOS A FLEXJSON QUE IGNORE EL ATRIBUTO CLASS
        //YA QUE NO LO NECESITA EL REST HECHO EN .NET
        jss.exclude("class");
        strJSON = jss.serialize(galeria);
        System.out.println(strJSON);
        strJSON = "{'galeria':"+strJSON + "}";
        System.out.println(strJSON);
      
        //METODO DE CONEXION
        connHttp.setRequestMethod("GET");
        //ENVIAREMOS LOS PARAMATROS CODIFICADOS A TRAVES DE LA URL
        connHttp.setRequestProperty("Content-Type","application/json; utf-8");
        //ENVIAREMOS LOS VALORES DEL METODO POST
        connHttp.setDoOutput(true);
        //RECIBIREMOS RESPUESTA DEL SERVIDOR
        connHttp.setDoInput(true);
        
        out = new BufferedOutputStream(connHttp.getOutputStream());
        bwriter = new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
        bwriter.write(strJSON);
        bwriter.flush();
        bwriter.close();
        out.close();
        
        connHttp.connect();
        respuestaServidor = connHttp.getResponseCode();
        
        if (respuestaServidor == HttpURLConnection.HTTP_OK) 
        {
            br = new BufferedReader(new InputStreamReader(connHttp.getInputStream()));
            while((lineaActual=br.readLine())!=null)
                contenidoRespuesta +=lineaActual;
        }
        else
        {
            throw new Exception("Error en el servidor: "+ respuestaServidor);
        }
        
        
    }
   */ 
    public List<Venta> getAllRest()throws Exception 
    {
        List<Venta> ventas = new ArrayList<>();
        String rutaServicio = RUTA_WS + "/WebService.asmx/consultarVentas";
        URL url = new URL(rutaServicio);
        HttpURLConnection connHttp = (HttpURLConnection) url.openConnection();
        int respuestaServidor = 0;
        InputStreamReader isr = null;
        BufferedReader br = null;
        String lineaActual = null;
        String strContent = null;
        String strJSON = null;
        //Indicamos que tipo de objeto regresara nuestro deserializador, en este caso una lista ModeloProducto
      
        connHttp.setRequestMethod("GET");
        connHttp.setRequestProperty("datatype","json");
        respuestaServidor = connHttp.getResponseCode();
        
        if (respuestaServidor == HttpURLConnection.HTTP_OK) 
        {
            isr = new InputStreamReader(connHttp.getInputStream());
            br = new BufferedReader(isr);
            strContent = "";
            while((lineaActual = br.readLine()) != null)
                strContent += lineaActual;
            
            br.close();
            connHttp.disconnect();
            
            strJSON = convertirXMLToJSON(strContent);
            System.out.println(strJSON);
            ventas = convertirJSON(strJSON);
            //indicamos al deserializer que cada elemento de propiedad ModeloProducto se convertira en un objeto
            //de clase producto
        
            //Convertir la cadena JSON en nuna lista que contiene objetos de tipo ModeloProducto
          
        }
        else
        {
            throw new Exception("Error en el servidor: "+ respuestaServidor);
        }
        
        return ventas;
    }
    
    private String convertirXMLToJSON(String strXML)
    {
        XMLSerializer xmls = new XMLSerializer();
        String strJSON = xmls.read(strXML).toString();
        return strJSON;
    }
    private List<Venta> convertirJSON(String strJSON) throws Exception
    {
        List<Venta> lista = new ArrayList<>();
        JSONDeserializer<List<Venta>> jdss = new JSONDeserializer<List<Venta>>();
   
        JSONObject jso = (JSONObject) net.sf.json.JSONSerializer.toJSON(strJSON);
        
        jdss.use(null,ArrayList.class).use("values", Venta.class);
        lista = (List<Venta>) jdss.deserialize(jso.getString("Venta"));
        return lista;
     }
    public List<Venta> getAll() throws Exception
    {
        
        Venta venta = new Venta();
        List<Venta> ventas  = new ArrayList<>();
        //definimos consulta a ejecutar
        String query = "EXEC dbo.pa_java_ConsultarVentas";        
        //Generamos un objeto de conexion y nos conectamos con la BD en SQL Server
        ConexionSQLServer connSQLServer = new ConexionSQLServer();
        
        Connection conn = connSQLServer.abrir();
        
        //Generamos un objeto PreparedStatement para ejecutar la consulta
        PreparedStatement pstmt = conn.prepareStatement(query);
        
        //Ejecutamos la consulta y guardamos el resultado
        ResultSet rs = pstmt.executeQuery();
        
        //Nos movemos al primer registro devuelto
        while(rs.next())
        {
            Cliente cl = new Cliente();
            venta = new Venta();
            
            venta.setIdVenta(rs.getInt("pk_IdVenta"));
            venta.setSubtotal(rs.getDouble("Subtotal"));
            venta.setDescuento(rs.getDouble("Descuento"));
            venta.setImpuestos(rs.getDouble("Impuestos"));
            venta.setTotal(rs.getDouble("Total"));
            venta.setLiquidado(rs.getInt("Liquidado"));
            cl.setNombreContacto(rs.getString("NombreContacto"));
            cl.setFolioCliente(rs.getString("FolioCliente"));
            venta.setCliente(cl);
            
            ventas.add(venta);
        }
        //Cerramos los objetos de conexion con la BD
        rs.close();
        pstmt.close();
        connSQLServer.cerrar();
        
        return ventas;
    }
     public List<Venta> findByClientName(Cliente clienteName) throws Exception
    {
        
        Venta venta = new Venta();
        List<Venta> ventas  = new ArrayList<>();
        //definimos consulta a ejecutar
        String query = "EXEC dbo.pa_java_ConsultarVentas_PorNombreCliente ?";        
        //Generamos un objeto de conexion y nos conectamos con la BD en SQL Server
        ConexionSQLServer connSQLServer = new ConexionSQLServer();
        
        Connection conn = connSQLServer.abrir();
        
        //Generamos un objeto PreparedStatement para ejecutar la consulta
        PreparedStatement pstmt = conn.prepareStatement(query);
        
        pstmt.setString(1, clienteName.getNombreContacto());
        //Ejecutamos la consulta y guardamos el resultado
        ResultSet rs = pstmt.executeQuery();
        
        //Nos movemos al primer registro devuelto
        while(rs.next())
        {
            Cliente cl = new Cliente();
            venta = new Venta();
            
            venta.setIdVenta(rs.getInt("pk_IdVenta"));
            venta.setSubtotal(rs.getDouble("Subtotal"));
            venta.setDescuento(rs.getDouble("Descuento"));
            venta.setImpuestos(rs.getDouble("Impuestos"));
            venta.setTotal(rs.getDouble("Total"));
            venta.setLiquidado(rs.getInt("Liquidado"));
            cl.setNombreContacto(rs.getString("NombreContacto"));
            cl.setFolioCliente(rs.getString("FolioCliente"));
            venta.setCliente(cl);
            
            ventas.add(venta);
        }
        //Cerramos los objetos de conexion con la BD
        rs.close();
        pstmt.close();
        connSQLServer.cerrar();
        
        return ventas;
    }
     
      public List<Venta> findByClientNameRest(Venta venta) throws Exception
      {
        List<Venta> ventas = new ArrayList<>();
        String rutaServicio = RUTA_WS + "/WebService.asmx/buscarVentasPorCliente";
        URL url = new URL(rutaServicio);
        HttpURLConnection connHttp = (HttpURLConnection) url.openConnection();
        int respuestaServidor = 0;
        BufferedWriter bwriter = null;
        OutputStream out = null;
        BufferedReader br = null;
        String lineaActual = null;
        String contenidoRespuesta = "";
        String strJSON = null;
        
        flexjson.JSONSerializer jss = new flexjson.JSONSerializer();
        
        //INDICAMOS A FLEXJSON QUE IGNORE EL ATRIBUTO CLASS
        //YA QUE NO LO NECESITA EL REST HECHO EN .NET
        jss.exclude("class");
        strJSON = jss.serialize(venta);
        System.out.println(strJSON);
        strJSON = "{'venta':"+strJSON + "}";
        System.out.println(strJSON);
      
        //METODO DE CONEXION
        connHttp.setRequestMethod("GET");
        //ENVIAREMOS LOS PARAMATROS CODIFICADOS A TRAVES DE LA URL
        connHttp.setRequestProperty("Content-Type","application/json; utf-8");
        //ENVIAREMOS LOS VALORES DEL METODO POST
        connHttp.setDoOutput(true);
        //RECIBIREMOS RESPUESTA DEL SERVIDOR
        connHttp.setDoInput(true);
        
        out = new BufferedOutputStream(connHttp.getOutputStream());
        bwriter = new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
        bwriter.write(strJSON);
        bwriter.flush();
        bwriter.close();
        out.close();
        
        connHttp.connect();
        respuestaServidor = connHttp.getResponseCode();
  
        if (respuestaServidor == HttpURLConnection.HTTP_OK) 
        {
            br = new BufferedReader(new InputStreamReader(connHttp.getInputStream()));
            while((lineaActual=br.readLine())!=null)
                contenidoRespuesta +=lineaActual;
            
            br.close();
            connHttp.disconnect();
            
            strJSON = convertirXMLToJSON(contenidoRespuesta);
            System.out.println(strJSON);
            ventas = convertirJSON(strJSON);
        }
        else
        {
            throw new Exception("Error en el servidor: "+ respuestaServidor);
        }
        return ventas;
      } 
}
