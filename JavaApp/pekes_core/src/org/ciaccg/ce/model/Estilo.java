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
public class Estilo {
    private int Identificador;
    private String Material;
    private String Color;
    private boolean Estatus;

    public int getIdentificador() {
        return Identificador;
    }

    public void setIdentificador(int Identificador) {
        this.Identificador = Identificador;
    }

    public String getMaterial() {
        return Material;
    }

    public void setMaterial(String Material) {
        this.Material = Material;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String Color) {
        this.Color = Color;
    }

    public boolean isEstatus() {
        return Estatus;
    }

    public void setEstatus(boolean Estatus) {
        this.Estatus = Estatus;
    }
}
