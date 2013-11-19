/*
 * MiCanvas.java
 *
 * Created on 25 de octubre de 2007, 12:52 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package quad1;


import java.awt.Graphics;
import java.awt.*;
/**
 *
 * @author Julio
 */
 class MiCanvas extends Canvas {
    private int ancho;
    private int alto;
    private int matrix[][];
    private int r=1;
    public boolean dibujaFranjas;


    
    public MiCanvas() {
         reshape( 15,15,160,160 );
        }

    public void dibuja_interferograma(int m[][],int x, int y){
        // se obtienen los valores del interferograma
        this.matrix=m;
        this.alto=y;
        this.ancho=x;
      
        Graphics g = getGraphics();   
        
        
        Dimension tam = getSize();
        System.out.println(tam);
        

        for (int yi=0;yi<alto;yi++)
             for (int xi=0;xi<ancho;xi++){
                //inter2[index]=(255<<24)|(255<<16)|255;
                g.setColor(new java.awt.Color(matrix[yi][xi],matrix[yi][xi],matrix[yi][xi]));
                g.drawOval(yi,xi,r,r);
             }
        //PRQuadtree(0,0,ancho,alto);
        // Se destruye el objeto
        g.dispose();
    }
    
   /***********************************************************************
   Function name: PRQuadtree
   Description:   a recursive function, that creates a PR Quadtree
                  on the area between (x1,y1) and (x2,y2).
***********************************************************************/
    private void PRQuadtree(int x1, int y1, int x2, int y2) {
      int Dx = x2-x1;
      int Dy = y2-y1;
      
      Graphics g = getGraphics();   
      
      double varianza=Complex.varianza(matrix,x1,y1,x2,y2);
      if (varianza<125) {     
//      if (((x2-x1)/2 > (Math.random()*20)) && ((y2-y1)/2 > (Math.random()*20))) {     
        g.setColor(Color.GREEN);
          
        g.drawLine(x1+Dx/2,y1,x1+Dx/2,y2);
        g.drawLine(x1,y1+Dy/2,x2,y1+Dy/2);
        PRQuadtree(x1,y1,x1+Dx/2,y1+Dy/2);
        PRQuadtree(x1,y1+Dy/2,x1+Dx/2,y2);
        PRQuadtree(x1+Dx/2,y1,x2,y1+Dy/2);
        PRQuadtree(x1+Dx/2,y1+Dy/2,x2,y2);
      }
      
    }
    
    public void paint( Graphics g ) {
        System.out.println("ya entro");
       if (dibujaFranjas==true)
            dibuja_interferograma(matrix,ancho,alto);
    }
    
    public void setDibujaFranjas(boolean _bandera) {
        dibujaFranjas=_bandera;
    }

}
