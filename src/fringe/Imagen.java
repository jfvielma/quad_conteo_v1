package fringe;

/*
 * Imagen.java
 *
 * Created on 10 de marzo de 2008, 05:02 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */



/**
 *
 * @author Julio
 */
public class Imagen {
    private int ancho;
    private int alto;
    private int matrix[][];
    
    /** Creates a new instance of Imagen */
    public Imagen(int m[][],int x, int y) {
        this.matrix=m;
        this.alto=y;
        this.ancho=x;
    }
    
    public int[][] Binariza(){
        //matriz que contendra el interferograma Binarizado
        int inter[][]= new int[ancho][alto]; 

        for (int yi=0;yi<alto;yi++)
             for (int xi=0;xi<ancho;xi++){
                if (matrix[yi][xi]>=70)
                    inter[yi][xi]=0;
                else inter[yi][xi]=255;
             }
        return inter;
    }
    
}
