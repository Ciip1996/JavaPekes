/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ciaccg.ce.utils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.ciaccg.ce.model.Parametro;

/**
 *
 * @author Iván
 */
public class PreparadorHttp {
    public HttpURLConnection Post(HttpURLConnection connHttp, List<Parametro> parametros) throws IOException {
        String urlParameters = "";
        for (int i = 0; i < parametros.size(); i++) {
            if (i > 0) 
                urlParameters += "&";
            urlParameters += parametros.get(i).getNombre() + "=" + parametros.get(i).getValor();
        }
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
        int postDataLength = postData.length;
        connHttp.setDoOutput(true);
        connHttp.setRequestProperty("dataType", "json");// le decimos que el tipo de dato es json.
        connHttp.setInstanceFollowRedirects(false);
        connHttp.setRequestMethod("POST");
        connHttp.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connHttp.setRequestProperty("charset", "utf-8");
        connHttp.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        connHttp.setUseCaches(false);
        try (DataOutputStream wr = new DataOutputStream(connHttp.getOutputStream())) {
            wr.write(postData);
        }
        return connHttp;
    }

}
