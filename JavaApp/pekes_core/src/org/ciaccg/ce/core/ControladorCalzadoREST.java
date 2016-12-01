/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ciaccg.ce.core;
//strJSON = "{'idProducto':'1','descripcion':'DESCRIPCION','fotografia':'','precio':'123.45','estado':'1'}";

import flexjson.JSONDeserializer;//
import java.io.BufferedOutputStream;//
import java.io.BufferedReader;//
import java.io.BufferedWriter;//
import java.io.InputStreamReader;//
import java.io.OutputStream;//
import java.io.OutputStreamWriter;//
import java.net.HttpURLConnection;//
import java.net.URL;//
import java.util.ArrayList;//
import java.util.List;//
import net.sf.json.JSONObject;//
import org.ciaccg.ce.model.Calzado;//
import org.ciaccg.ce.model.Prueba;
import org.ciaccg.ce.utils.ConvertidorJSON;//

/**
 *import net.sf.json.JSON
 * @author Iván
 */
public class ControladorCalzadoREST {
    private static final String RUTA_WS = "http://localhost:95/WebSitePekes/WebService.asmx/";//es lo mismo que localhost/SitioJava

    /** 
     * Consulta los registros de modelos de productos a traves de un servicio REST.
     * @return Una lista con objetos de tipo Producto.
     * @throws Exception 
     */
    public List<Calzado> getAll() throws Exception {
        List<Calzado> modelosProducto = new ArrayList<>();
        String rutaServicio = RUTA_WS + "GetAllCalzado";
        URL url = new URL(rutaServicio); //Paquete import java.net.URI; recive la ruta del webservice.asmx
        HttpURLConnection connHttp = (HttpURLConnection)url.openConnection(); // sirve para ver si el servidor nos puede atender.        
        connHttp.setRequestMethod("GET"); //el tipo de metodo es get.
        connHttp.setRequestProperty("dataType", "json");// le decimos que el tipo de dato es json.
        InputStreamReader isr = null;//es como la tuberia 
        BufferedReader br = null;//es como la llave
        String lineaActual =  null;
        String strXML = null;
        String strJSON = null;
        List<Calzado> productos;
        //JSONDeserializer<List<Producto>> jdss =  new JSONDeserializer<>();
        
        int respuestaServidor = connHttp.getResponseCode();//decirle al servidor que procese la peticion ya con la configuracion y dame una respuesta
        if (respuestaServidor == 200){ //HttpURLConnection.HTTP_OK) {//Si nos puede atender, hay conexion
            //HTTP_OK es 200 osea que ocurrio exitosamente.
            isr = new InputStreamReader(connHttp.getInputStream());//obtiene el contenido devuelto por el servidor
            br = new BufferedReader(isr);//conecta el isr con el br.
            strXML = "";
            while((lineaActual = br.readLine()) != null)
                strXML += lineaActual;
            br.close();//lo cerramos para no gastar recursos.
            isr.close();
            connHttp.disconnect();
            ConvertidorJSON convertidorjson = new ConvertidorJSON();
            String json = convertidorjson.XMLtoJSONString(strXML);//obtenemos la cadena de texto del json     
            productos = convertirJSON(json);
        }
        else{//surgio algun error en el servidor
            throw new Exception("Error en el servidor: " + respuestaServidor);
        }
        return productos;
    }
    private List<Calzado> convertirJSON(String strJSON)throws Exception{
        List<Calzado> lista = new ArrayList<>();
        JSONDeserializer<List<Calzado>> jdss = new JSONDeserializer<List<Calzado>>();
        JSONObject jso = (JSONObject) net.sf.json.JSONSerializer.toJSON(strJSON);//net.sf.json
        /**
         * Le indicamos al deserializador que,
         * cad elemento de la propiedad JSON "Producto",
         * se debera convertir a un objeto 
         * de tipo producto:
         */
        jdss.use(null,ArrayList.class).use("values",Calzado.class);
        /**
         * Convertimos la cadena JSON en una lit que contiene objetos de tipo producto:
         */
        lista = (List<Calzado>) jdss.deserialize(jso.getString("Calzado"));
        return lista;
    }
    
    public void insert(Prueba test) throws Exception 
    {
        String rutaServicio = RUTA_WS + "InsertarCalzado";
        URL url = new URL(rutaServicio); //Paquete import java.net.URI; recive la ruta del webservice.asmx
        HttpURLConnection connHttp = (HttpURLConnection)url.openConnection(); // sirve para ver si el servidor nos puede atender.                
        /**
         * Declaración de variables
         */
        int respuestaServidor = 0;
        BufferedWriter bwriter = null; //Este objeto nos ayudara a enviarle datos al servidor.
        OutputStream out = null;//Este objeto nos ayudará a mandarel los datos como parametrso al servidor.
        BufferedReader br = null;//Este objeto nos ayudara a capturar la respuesta que el servidor nos envíe.
        String lineaActual;//Esta es una varaible auxiliar que nos ayudara a ir leyendo la respuesta del servidor.
        String contenidoRespuesta = null;//En esta variable se ira guardando la respuesta del servidor hasta que finalice.
        String strJSON = "";
        /**
         * se serializa el json:
         */
        flexjson.JSONSerializer jss = new flexjson.JSONSerializer();
        jss.exclude("class");//quitar los atributos que yo le diga en este caso el class (no lo necesita REST en .NET)
        strJSON = jss.serialize(test);//Serializamos el objeto calzado para convertirlo en un string JSON.
        strJSON = "{\"test\":"+strJSON+"}";
        /**
         * Enviamos los parametros al servidor:
         */
        connHttp.setRequestMethod("POST");//Establecemos el método de conexión.
        connHttp.setRequestProperty("Content-Type", "application/json; utf-8");
       
        connHttp.setDoOutput(true);//Establecemos que enviaremos los valores por un método POST.
        connHttp.setDoInput(true);//Establecemos que recibiremos respueta del servidor.
        /**
         * Enviamos los parametros al servidor: 
         */
        out = new BufferedOutputStream(connHttp.getOutputStream());
        bwriter = new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
        bwriter.write(strJSON);
        bwriter.flush();
        bwriter.close();
        
        connHttp.connect();//Mantenemos la conexion con el servidor:
        respuestaServidor = connHttp.getResponseCode();//decirle al servidor que procese la peticion ya con la configuracion y dame una respuesta
        
        if (respuestaServidor == 200){ //HttpURLConnection.HTTP_OK) {//Si nos puede atender, hay conexion
            br = new BufferedReader(new InputStreamReader(connHttp.getInputStream()));
            while((lineaActual = br.readLine()) != null)
                contenidoRespuesta += lineaActual;            
        }
        else{//surgio algun error en el servidor
            throw new Exception("Error en el servidor: " + respuestaServidor);
        }
        br.close();//lo cerramos para no gastar recursos.            
        connHttp.disconnect();
    }
}
