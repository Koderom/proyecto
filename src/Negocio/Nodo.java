/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

/**
 *
 * @author Erick
 */
public class Nodo {
    int Data;
    int Peso;
    Nodo Enlace;
    
    public Nodo(){
        Enlace = null;
        Peso = Integer.MIN_VALUE;
        Data = Integer.MIN_VALUE;
    }
    public Nodo(int Data){
        Enlace = null;
        Peso = Integer.MIN_VALUE;
        this.Data = Data;
    }
    public Nodo(int Data, int Peso){
        Enlace = null;
        this.Peso = Peso;
        this.Data = Data;
    }

    public int getData() {
        return Data;
    }

    public void setData(int Data) {
        this.Data = Data;
    }

    public int getPeso() {
        return Peso;
    }

    public void setPeso(int Peso) {
        this.Peso = Peso;
    }

    public Nodo getEnlace() {
        return Enlace;
    }

    public void setEnlace(Nodo Enlace) {
        this.Enlace = Enlace;
    }
    public String toString(){
        String s= "";
        if(this.Data != Integer.MIN_VALUE) s = s + "("+this.Data+"/";
        else s = s + "("+"_/";
        if(this.Peso != Integer.MIN_VALUE) s = s + this.Peso+")";
        else s = s +"_)";
        return s;
    }
}
