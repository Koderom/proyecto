/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Erick
 */
public class Lienzo extends JPanel implements MouseListener{
    public juego J;
    public Point p1,p2;
    public int a,b;
    public int i;
    public Lienzo(){
        J = new juego();
        this.addMouseListener(this);
        i = 0;
        int a = -1;
        int b = -1;
        
        
    }
    private Image imagen;
    @Override
    public void paint(Graphics g){
        //cargar fondo
        imagen = new ImageIcon(getClass().getResource("/img/fondo.jpg")).getImage();
        g.drawImage(imagen, 0, 0, this.getWidth(), getHeight(), this);
        setOpaque(false);
        super.paint(g);
        //cargar tuneles
        for (Cueva l: J.cuevas) {
            J.pintar(g, l.getData(),this);
        }
        //cargar cuevas
        for (Cueva l: J.cuevas) {
            l.pintaNodo(g,this);
        }
        
        
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        //adicionar cuevas
        if(e.getButton() == MouseEvent.BUTTON1){
            this.J.adicionarCueva(i, e.getX(), e.getY());
            //this.NodosAdy.add(new Cueva(i,e.getX(),e.getY()));
            repaint();
            i++;
        }
        //adicionar tuneles
        if(e.getButton() == MouseEvent.BUTTON3){
            
            for(Cueva c:J.cuevas){
                if(new Rectangle(c.x - c.d/2, c.y - c.d/2, c.d,c.d).contains(e.getPoint())){
                    if(p1 == null) {
                        p1 = new Point(c.x, c.y);
                        this.a = c.getData();
                    }else{
                        if(p1 != p2){
                            p2 = new Point(c.x, c.y);
                            this.b = c.getData();
                            J.adicionarTunel(a, b);
                            repaint();
                            p1 = null;
                            p2 = null;
                        }
                        
                    }
                    
                }
            }
        }
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
            
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
   
}
