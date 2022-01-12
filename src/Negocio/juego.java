/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import static Negocio.Cueva.d;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Erick
 */
public class juego {
    public int PA; // posicion actual del raton
    
    public ArrayList<Lista>tuneles;
    public ArrayList<Cueva> cuevas;
    private Image imagen;
    
    public juego(){
        tuneles = new ArrayList();
        cuevas = new ArrayList();
        
        int PA = -1;
    }
    //caso 1 - definir el laberinto
    public void adicionarCueva(int i, int x, int y){ // o vertice
        Lista nuevo = new Lista();
        tuneles.add(nuevo);
        Cueva indice = new Cueva(i,x,y);
        this.cuevas.add(indice);
    }
    public void adicionarCueva(int i){ // pruebas en consola
        Lista nuevo = new Lista();
        tuneles.add(nuevo);
        Cueva indice = new Cueva(i);
        this.cuevas.add(indice);      
        verConsola();
    }
    public void eliminarCueva(int indice){
        int i = buscarCueva(indice);
        if(i != -1){
            this.cuevas.remove(i);
            this.tuneles.remove(i);
            for(Lista a: this.tuneles){
                a.del(indice);
            }
            verConsola();
        }
        
    }
    public void adicionarTunel(int cueva1, int cueva2){
        int a = buscarCueva(cueva1);
        int b = buscarCueva(cueva2);
        if(a != -1 && b != -1){
            this.tuneles.get(a).add(cueva2,0);
            this.tuneles.get(b).add(cueva1,0);
        }
        verConsola();
        
    }
    public void eliminarTunel(int cueva1, int cueva2){
        int a = buscarCueva(cueva1);
        int b = buscarCueva(cueva2);
        if(a != -1 && b != -1){
            this.tuneles.get(a).del(cueva2);
            this.tuneles.get(b).del(cueva1);
        }
    }
    
    public void adicionarTrampa(int desde, int hasta){
        int i = buscarCueva(desde);
        int j = buscarCueva(hasta);
        if(i != -1 && j != -1 &&this.tuneles.get(i).existe(hasta)){
            this.tuneles.get(i).setPeso(hasta, 1);
            this.tuneles.get(j).setPeso(desde, 1);
        }else{
            JOptionPane.showMessageDialog(null, "error al encontrar las cuevas");
            verConsola();
        }
    }
    public void eliminarTrampa(int desde, int hasta){
        int i = buscarCueva(desde);
        int j = buscarCueva(hasta);
        if(i != -1 && j != -1){
            this.tuneles.get(i).setPeso(hasta, 0);
            this.tuneles.get(j).setPeso(desde, 0);
        }
    }
    
    // caso 2 definir el inicio, final y movimientos
    public void adicionarSalida(int cueva){
        int i = buscarCueva(cueva);
        if(i != -1){
            this.cuevas.get(i).setTipo('s');
        }
        verConsola();
    }
    public void eliminarSalida(int cueva){
        int i = buscarCueva(cueva);
        if(i != -1){
            this.cuevas.get(i).setTipo('v');
        }
    }
    public void definirInicio(int cueva){
        int i = buscarCueva(cueva);
        if(i != -1){
            for(Cueva c: this.cuevas){
                if(c.getTipo()=='r') c.setTipo('v');
            }
            this.cuevas.get(i).setTipo('r');
            this.PA = cueva;
        }
        verConsola();
    }
    public int moverRaton(int destino){
        int d = buscarCueva(destino);
        int act = buscarCueva(PA);
        boolean aux = this.tuneles.get(act).existe(destino);
        if(!aux) return -1; // el destino no es adyacente
        if(this.tuneles.get(act).getPeso(destino)==1){
            this.cuevas.get(act).setTipo('v');
            this.cuevas.get(d).setTipo('m');
            return 0; //se murio
        }else{
            if( d != -1){
                if(this.cuevas.get(d).getTipo()=='s'){
                    this.cuevas.get(act).setTipo('v');
                    this.cuevas.get(d).setTipo('g');
                    return 2;//ya gano
                }else{
                    this.cuevas.get(act).setTipo('v');
                    this.cuevas.get(d).setTipo('r');                
                    this.PA = destino;
                    return 1;//todo normas
                }
            }else{
                return -1; //error de destino
            }
        }
        
    }
    //caso 3 - dar ruta solucion
    public String rutaSolucion(){
        return buscarSalida();
    }
    
    
    
    
    
    // funciones auxiliares
    public void verConsola(){
        int i = 0;
        for(Cueva a: this.cuevas){
            System.out.println("cueva : " +a+" -> cuevas adyacentes : "+ tuneles.get(i).toString());
            i++;
        }
    }
    public int buscarCueva(int x){
        int a = -1;
        for(Cueva n : this.cuevas){
            if(n.getData()==x) a = this.cuevas.indexOf(n);
        }
        return a;
    }
    boolean[] marca;
    public void desmarcarTodo(){
        marca = new boolean[this.cuevas.size()];
        for (int i = 0; i < marca.length; i++) {
            marca[i]=false;
        }
    }
    public String buscarSalida(){
        desmarcarTodo();
        String s = buscarSalidaR(PA);
        if(s.equals("")) return "no existe salida segura ";
        return s;

    }
    private String buscarSalidaR(int a){
        int x = buscarCueva(a);
        if(marca[x]) return "";
        marca[x]=true;
        Lista Lady = this.tuneles.get(x);
        Cueva c = this.cuevas.get(x);
        if(c.getTipo() == 's') {
            
            return String.valueOf(a);
        }
        String s = "", aux ="";
        for (int i = 0; i < Lady.n; i++) {
            int ady = Lady.get(i);
            if(Lady.getPeso(ady)!= 1){
                aux =  buscarSalidaR(ady);
                if(!aux.equals("")){
                    s = String.valueOf(a) +"->" +aux;
                   
                }
            }
        }
        
        return s;
    }
    public void pintar(Graphics g,int a,JPanel b){
        int x1 = -1;
        int y1 = -1;
        int x2 = -1;
        int y2 = -1;
        
        g.setColor(Color.white);
        int i = buscarCueva(a);
        
        if(i != -1){
            Lista l = this.tuneles.get(i);
            for (int j = 0; j < l.n; j++) {
                
            int ady = l.get(j);
                
            if(ady != -1)
                x1 = this.cuevas.get(i).x;
                y1 = this.cuevas.get(i).y;   
                int k = buscarCueva(ady);
                x2 =  this.cuevas.get(k).x;
                y2 = this.cuevas.get(k).y;
                if(x1 != x2 && y1 != y2)
                    g.drawLine(x1, y1,x2 ,y2 );
                if(l.getPeso(ady)==1 && !this.cuevas.get(i).ocultar){
                    if(this.cuevas.get(i).getTipo()=='m'){
                        imagen = new ImageIcon(getClass().getResource("/img/raton3.png")).getImage();
                        g.drawImage(imagen, (x1+x2)/2-40, (y1+y2)/2-40, 110, 80, b);
                    }else{
                        imagen = new ImageIcon(getClass().getResource("/img/Imagen3.png")).getImage();
                        g.drawImage(imagen, (x1+x2)/2-40, (y1+y2)/2-40, 80, 80, b);
                    }
                    
                    
                }
            }
        }
    }
    public void ocultar(){
        for(Cueva c:this.cuevas){
            c.ocultar = true;
        }
    }
    public void Noocultar(){
        for(Cueva c:this.cuevas){
            c.ocultar = false;
        }
    }
    public void restar(){
        for(Cueva c: this.cuevas){
            if(c.getTipo()=='g'){
                c.setTipo('s');
            }else{
                if(c.getTipo() == 'm'){
                    c.setTipo('v');
                    
                }else{
                    if(c.getTipo() == 'r'){
                    c.setTipo('v');
                }
                }
            }
        }
    }
}
