/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ciaccg.ce.model;

/**
 *
 * @author Iván
 */
public class estadoRespuesta {
    private String estado;
    private String mensaje;
    /*private Sesion sesionInformacion;*/

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

   /* public Sesion getSesionInformacion() {
        return sesionInformacion;
    }

    public void setSesionInformacion(Sesion sesionInformacion) {
        this.sesionInformacion = sesionInformacion;
    }*/

}
