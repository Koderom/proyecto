/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Erick
 */
public class Cueva {
    
    private int data; //indice
    private char tipo;
    public boolean ocultar;
    
    //pintar
    public int x,y;
    public static final int d = 80;
    
    public Cueva(){
        
        this.data = Integer.MIN_VALUE;
        this.tipo = 'v'; // v = vacio , r = raton, s = salida
        ocultar = false;
        
    }
    public Cueva(int data){
        this.data = data;
        
        this.tipo = 'v';
    }
    public Cueva(int data, int x, int y){
        this.data = data;
        this.tipo = 'v';
        this.x = x;
        this.y = y;
        System.out.println(this);
    }
    public void setPosicion(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void setTipo(char tipo){
        this.tipo = tipo;
    }
    public char getTipo(){
        return this.tipo;
    }
    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
    @Override
    public String toString(){
        return "["+data+" / "+tipo+"]";
    }
    //graficar
    private Image imagen;
   public void pintaNodo(Graphics g, JPanel a){
       g.setColor(Color.white);
       if(this.tipo == 'r' ){
           imagen = new ImageIcon(getClass().getResource("/img/raton1.png")).getImage();
            g.drawImage(imagen, x - d/2-5 , y-d/2-5, 100, 100, a);
            g.drawString(String.valueOf(this.data), x , y+20 );
            //g.drawOval(this.x - d/2,this.y - d/2, d, d);
       }else{
        if(this.tipo == 's' && !ocultar){
            imagen = new ImageIcon(getClass().getResource("/img/salida.png")).getImage();
            g.drawImage(imagen, x - d/2 -10, y-d/2-10, 80, 80, a);
            g.drawString(String.valueOf(this.data), x , y );
            //g.drawOval(this.x - d/2,this.y - d/2, d, d);
        }else{
            if(this.tipo == 'g' ){
                imagen = new ImageIcon(getClass().getResource("/img/raton2.png")).getImage();
                g.drawImage(imagen, x - d/2-5 , y-d/2-5, 90, 100, a);
                g.drawString(String.valueOf(this.data), x , y );
            }else{
                imagen = new ImageIcon(getClass().getResource("/img/Imagen2.png")).getImage();
                g.drawImage(imagen, x - d/2, y-d/2, 80, 80, a);
                g.drawString(String.valueOf(this.data), x-10 , y );
                //g.drawOval(this.x - d/2,this.y - d/2, d, d);
            }
            
        }
       }
   }
   
}
