/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ciaccg.ce.core;

import flexjson.JSONDeserializer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONObject;
import org.ciaccg.ce.model.Parametro;
import org.ciaccg.ce.model.estadoRespuesta;
import org.ciaccg.ce.utils.ConvertidorJSON;
import org.ciaccg.ce.utils.PreparadorHttp;

/**
 *
 * @author Iván
 */
public class ControladorLogin {
    public estadoRespuesta Acceder(String usuario, String contraseña) throws Exception {
        String RUTA_WS = "http://localhost:95/WebSitePekes/WebService.asmx/Acceso";
        String rutaServicio = RUTA_WS;
        URL url = new URL(rutaServicio); //Paquete import java.net.URI; recive la ruta del webservice.asmx
        HttpURLConnection connHttp = (HttpURLConnection) url.openConnection(); // sirve para ver si el servidor nos puede atender.        
        InputStreamReader isr;//es como la tuberia 
        BufferedReader br;//es como la llave
        String lineaActual;
        String strXML;
        estadoRespuesta er;
        PreparadorHttp prp = new PreparadorHttp();
        List<Parametro> listaParametros = new ArrayList<>();
        Parametro nick = new Parametro("Nick", usuario);
        Parametro psw = new Parametro("Password", contraseña);
        listaParametros.add(nick);
        listaParametros.add(psw);
        connHttp = prp.Post(connHttp, listaParametros);
        int respuestaServidor = connHttp.getResponseCode();//decirle al servidor que procese la peticion ya con la configuracion y dame una respuesta
        if (respuestaServidor == 200) {
            isr = new InputStreamReader(connHttp.getInputStream());
            br = new BufferedReader(isr);
            strXML = "";
            while ((lineaActual = br.readLine()) != null) {
                strXML += lineaActual;
            }
            br.close();
            isr.close();
            connHttp.disconnect();
            ConvertidorJSON cjson = new ConvertidorJSON();
            String json = cjson.XMLtoJSONString(strXML);
            er = this.convertirJSON(json);
            return er;
        } else {
            er = new estadoRespuesta();
            er.setEstado("Error numero" + respuestaServidor + " al intentar conecta con el servidor.");
            er.setEstado("NoAceptado");
            return er;
        }
    }
     private estadoRespuesta convertirJSON(String strJSON)throws Exception{
        estadoRespuesta er = new estadoRespuesta();
        JSONDeserializer<List<estadoRespuesta>> jdss = new JSONDeserializer<List<estadoRespuesta>>();
        JSONObject jso = (JSONObject) net.sf.json.JSONSerializer.toJSON(strJSON);//net.sf.json
        /**
         * Le indicamos al deserializador que,
         * cad elemento de la propiedad JSON "Producto",
         * se debera convertir a un objeto 
         * de tipo producto:
         */
        jdss.use(null,estadoRespuesta.class).use("values",estadoRespuesta.class);
        /**
         * Convertimos la cadena JSON en una lit que contiene objetos de tipo producto:
         */
        er = (estadoRespuesta) jdss.deserialize(jso.getString("estadoRespuesta"));
        return er;
    }
}
