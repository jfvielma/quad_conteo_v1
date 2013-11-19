/*
 * MiCanvas.java
 *
 * Created on 25 de octubre de 2007, 12:52 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fringe;


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
    public boolean dibujaQuad;
    private int conta=0;

    //matriz que contiene todos los puntos (x1,y1,x2,y2) de las particiones
    private int divisiones[][]=new int[100][4];
    //Variable que contiene el conteo de las particiones que se han hecho
    private int cont=0;
    private boolean bandera_guarda=true;
    private boolean bandera_ciclo=true;

    
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
//        System.out.println(tam);
        

        for (int yi=0;yi<alto;yi++)
             for (int xi=0;xi<ancho;xi++){
                //inter2[index]=(255<<24)|(255<<16)|255;
                g.setColor(new java.awt.Color(matrix[xi][yi],matrix[xi][yi],matrix[xi][yi]));
                g.drawOval(xi,yi,r,r);
             }
        if (dibujaQuad==true) 
            PRQuadtree(0,0,ancho,alto);
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
      
      Dy=Dx;
      
      Graphics g = getGraphics();   
      
//      double desviacion=Complex.varianza(matrix,x1,y1,x2,y2);

//      if (desviacion>80) {  
      
      int aux_matrix[][]=new int [Dx][Dy];
      
      
      for (int i=0;i<Dy;i++)
          for (int j=0;j<Dx;j++)
              aux_matrix[i][j]=matrix[i+y1][j+x1];
              
      
      //obtiene el interferograma en binario
        Imagen no_se=new Imagen(aux_matrix,Dx,Dy);
        int interbin[][]=no_se.Binariza();

        //genera la imagen etiketada
        ImageLabel other =new ImageLabel(Dx);
        int inter_etik[][]=other.doLabel(interbin,Dx, Dy);
        int nums=other.getNumberOfLabels();
      
      if (nums >= 4) {  
//      if (nums < 3) {      
          
//      if (((x2-x1)/2 > (Math.random()*20)) && ((y2-y1)/2 > (Math.random()*20))) {      
//      if (((x2-x1)/2 > 20-1) && ((y2-y1)/2 > 20-1)) {   
        
        g.setColor(Color.GREEN);  
        g.drawLine(x1+Dx/2,y1,x1+Dx/2,y2);
        g.drawLine(x1,y1+Dy/2,x2,y1+Dy/2);
        PRQuadtree(x1,y1,x1+Dx/2,y1+Dy/2);
        PRQuadtree(x1,y1+Dy/2,x1+Dx/2,y2);
        PRQuadtree(x1+Dx/2,y1,x2,y1+Dy/2);
        PRQuadtree(x1+Dx/2,y1+Dy/2,x2,y2);
      }// si no se particiona se guardan las dimensiones de la ventana en la matriz
      else {
//            int xx1=x1;
//            int yy1=y1;
//            int xx2=0;
//            int yy2=0;
//            
//            if (x2==0)
//                xx2=x2;
//            else xx2=x2-1;
//            if (y2==0)
//                yy2=y2;
//            else yy2=y2-1;
//            
//            System.out.println("Ventana :" +"( x: "+ xx1+" , y: " +yy1+" )"+" ( x: "+xx2+" , y: "+yy2+" )"+ "Franjas: " +nums);
//            conta++;
//            
//            if (y2==this.alto && x2==this.ancho)
//                System.out.println("Finalizado");
                
            //verifica si termino si no guarda las posiciones de las particiones
            if (y2==this.alto && x2==this.ancho){
                System.out.println("Finalizado");
                bandera_ciclo=false;
            }
            if (bandera_guarda){ //guarda las divisiones
                divisiones[cont][0]=x1;
                divisiones[cont][1]=y1;
                if (x2==0)
                    divisiones[cont][2]=x2;
                else divisiones[cont][2]=x2-1;
                if (y2==0)
                    divisiones[cont][3]=y2;
                else divisiones[cont][3]=y2-1;
                    System.out.println("holaaaa:" +"( "+ x1+" , " +y1+" )"+" ( "+x2+" , "+y2+" )");
            
                cont+=1;
                if(bandera_ciclo==false)
                    bandera_guarda=false;
            }
            
      }
      
       System.out.println("Ventanas: "+cont);
    }
    
    public void paint( Graphics g ) {
//        System.out.println("ya entro");
       if (dibujaFranjas==true)
            dibuja_interferograma(matrix,ancho,alto);
    }
    
    public void setDibujaFranjas(boolean _bandera) {
        dibujaFranjas=_bandera;
    }
    
    public void setDibujaQuadree(boolean _bandera) {
        dibujaQuad=_bandera;
    }

}
