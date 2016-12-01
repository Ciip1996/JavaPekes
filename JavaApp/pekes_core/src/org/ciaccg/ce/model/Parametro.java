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
public class Parametro {
    private String Nombre;
    private String Valor;
    
    public Parametro(String nombre, String valor ){
        this.Nombre =  nombre;
        this.Valor = valor;
    }

    public Parametro() {

    }
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getValor() {
        return Valor;
    }

    public void setValor(String Valor) {
        this.Valor = Valor;
    }
}
