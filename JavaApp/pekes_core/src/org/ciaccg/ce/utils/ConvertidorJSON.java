/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ciaccg.ce.utils;

import flexjson.JSONDeserializer;
import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;
import org.ciaccg.ce.model.Calzado;

/**
 *
 * @author Iván Ibarra Pacheco
 */
public class ConvertidorJSON {

    public String XMLtoJSONString(String strXML) {
        XMLSerializer xmls = new XMLSerializer(); //de la libreria Json Lib net.sf.json.xml nos ayuda a agarrar el xml y devuelve el JSON
        String strJSON = xmls.read(strXML).toString();//esta funcion me regresa un JSON object pero queremos un string.
        return strJSON;
    }
    public List<Calzado> convertirJSONCalzado(String strJSON) throws Exception {
        List<Calzado> lista = new ArrayList<>();
        JSONDeserializer<List<Calzado>> jdss = new JSONDeserializer<>();
        JSONObject jso = (JSONObject) net.sf.json.JSONSerializer.toJSON(strJSON);//net.sf.json
        /**
         * Le indicamos al deserializador que, cad elemento de la propiedad JSON
         * "Producto", se debera convertir a un objeto de tipo producto:
         */
        jdss.use(null, ArrayList.class).use("values", Calzado.class);
        /**
         * Convertimos la cadena JSON en una lit que contiene objetos de tipo
         * producto:
         */
        lista = (List<Calzado>) jdss.deserialize(jso.getString("Calzado"));
        return lista;
    }

    public List<Object> convertirJSON(String strJSON, Class<?> tipo, String query) throws Exception {
        List<Object> lista = new ArrayList<>();
        JSONDeserializer<List<Object>> jdss = new JSONDeserializer<>();
        JSONObject jso = (JSONObject) net.sf.json.JSONSerializer.toJSON(strJSON);//net.sf.json
        /**
         * Le indicamos al deserializador que, cad elemento de la propiedad JSON
         * "Producto", se debera convertir a un objeto de tipo producto:
         */
        jdss.use(null, ArrayList.class).use("values", Object.class);
        /**
         * Convertimos la cadena JSON en una lit que contiene objetos de tipo
         * producto:
         */
        lista = (List<Object>) jdss.deserialize(jso.getString(query));
        return lista;
    }
}
