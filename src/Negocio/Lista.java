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
public class Lista {
    Nodo Raiz;
    int n;
    
    public Lista(){
        Raiz = null;
        n = 0;
    }
    public void add(int data, int peso){
        Nodo aux = Raiz;
        Nodo ant = null;
        while(aux != null && aux.getData() <= data){
            if(aux.getData()==data){
                aux.setPeso(peso);
                return;
            }
            ant = aux;
            aux = aux.getEnlace();   
        }  
        Nodo nv = new Nodo(data, peso);
        if(ant == null){
            nv.setEnlace(aux);
            Raiz = nv;
        }else{
            ant.setEnlace(nv);
            nv.setEnlace(aux);
        }
        n++;
    }
    public void del(int data){
        Nodo aux = Raiz;
        Nodo ant = null;
        while(aux != null && aux.getData() <= data){
            if(aux.getData() == data){
                if(ant == null){
                    aux = aux.getEnlace();
                    Raiz = aux;
                }else
                    ant.setEnlace(aux.getEnlace());
                n--;
                return;
            }
            ant = aux;
            aux = aux.getEnlace();
        }
        return ;//no existe
    }
    public int get(int i){ // i empieza en 0 
        Nodo aux = Raiz;
        for (int j = 0; j < i; j++) {
           
            aux = aux.getEnlace();
            if(aux == null) return -1;
        }
        return aux.getData();
    }
    public boolean existe(int data){
        Nodo aux = Raiz;
        for (int j = 0; j < n; j++) {
            if(aux.getData() == data) return true;
            aux = aux.getEnlace();
        }
        return false;
    }
    
    public int getPeso(int data){
        Nodo aux = Raiz;
        while(aux != null && aux.getData() <= data){
            if(aux.getData() == data) return aux.getPeso();
            aux = aux.getEnlace();
        }
        return 0;//no existe
    }
    public void setPeso(int data, int peso){
        Nodo aux = Raiz;
        while(aux != null && aux.getData() <= data){
            if(aux.getData() == data) aux.setPeso(peso);
            aux = aux.getEnlace();
        }
    }
    public boolean isVacia(){
        return n == 0;
    }
    @Override
    public String toString(){
        String s = "[";
        Nodo aux = Raiz;
        while(aux != null){
            s = s + aux + " , ";
            aux = aux.getEnlace();
        }
        if(s.length()>1) s = s.substring(0,s.length()-3);
        s = s + "]";
        return s;
    }
}
