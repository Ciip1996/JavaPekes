/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ciaccg.ce.model;

/**
 *
 * @author dell1
 */
public class Galeria {
    private int idGaleria;
    private String codigo;
    private String foto;
    private String descripcion;
    private int status;

    public int getIdGaleria() {
        return idGaleria;
    }

    public void setIdGaleria(int idGaleria) {
        this.idGaleria = idGaleria;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
