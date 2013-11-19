package fringe;



import java.applet.*;
import java.awt.*;
import java.awt.image.*;
import java.net.*;
import java.util.*;
import java.io.*;
import java.lang.Math.*;

/**
 ImageLabel es un algoritmo que aplica el Etiquetado de Componentes Conectados
 *@author:Neil Brown, DAI
 *@author:Judy Robertson, SELLIC OnLine
 *@see code.iface.imagelabel
 */

public class ImageLabel {
  
  //ancho de la imagen de entrada en pixeles
  private int i1_w;
  
  //la ancho y alto de la imagen de salida
  
  private int d_w;
  private int d_h;
  private int[] dest_1d;
  private int labels[];
  private int numberOfLabels;
  private boolean labelsValid = false;
  
  /**
   *Constructs a new Image Operator
   *@parametro firstwidth la ancho de la imagen de entrada
   */

  public ImageLabel( int firstwidth ){
    i1_w = firstwidth;
  }
  
  /**
   *getNeighbours conseguir� el valor de pixel del vecino del i esto est� ox y oy 
   * si el punto es fuera de la imagen, entonces devuelve un 0
   * Esta versi�n se pone de la imagen de la fuente.
   */
  
  private int getNeighbours( int [] src1d, int i, int ox, int oy ){
    int x, y, result;
    
    x = ( i % d_w ) + ox; // d_w and d_h are assumed to be set to the
    y = ( i / d_w ) + oy; // width and height of scr1d
    
    if( ( x < 0 ) || ( x >= d_w ) || ( y < 0 ) || ( y >= d_h ) ) {
      result = 0;
    } else {
      result = src1d[ y * d_w + x ] & 0x000000ff;
    }
    return result;
  }
  
 
  
  /**
   * *getNeighbours conseguir� el valor de pixel del vecino del i esto est� ox y oy 
   * si el punto es fuera de la imagen, entonces devuelve un 0
   * Esta versi�n se pone de la imagen destino.
   */
  
  private int getNeighbourd( int [] src1d, int i, int ox, int oy ){
    int x, y, result;
    
    x = ( i % d_w ) + ox; // d_w and d_h are assumed to be set to the
    y = ( i / d_w ) + oy; // width and height of scr1d
    
    if( ( x < 0 ) || ( x >= d_w ) || ( y < 0 ) || ( y >= d_h ) ) {
      result = 0;
    } else {
      result = src1d[ y * d_w + x ];
    }
    return result;
  }
  
  /**
   * Associa(equivalencia) con b un deber�a ser menos que la b para dar alg�n ordenamiento (la clasificaci�n) 
   * si la b ya es asociada con alg�n otro valor, entonces propagar * la lista hacia abajo
    */
  
  private void associate( int a, int b ) {
    
    if( a > b ) {
      associate( b, a );
      return;
    }
    if( ( a == b ) || ( labels[ b ] == a ) ) return;
    if( labels[ b ] == b ) {
      labels[ b ] = a;
    } else {
      associate( labels[ b ], a );
      if (labels[ b ] > a) {             //***rbf new
        labels[ b ] = a;
      }
    }
  }
  /**
   * Reduce el numero de Etiquetas
   */
  private int reduce( int a ){
    
    if( labels[ a ] == a ){
      return a;
    } else {
      return reduce( labels[ a ] );
    }
  }
  
  /**
   *
   * doLabel aplica el Algortimo de Etiquetaje m�s la compensaci�n y esperan el escalamiento 
   * La imagen de entrada para ser monocromatica de 8 bits 0=black caso_contrario=white 
   * @parametro src1_1d la serie de pixel de entrada 
   * @parametro with es el ancho de la imagen de destino en pixeles 
   * @parametro height es la altura de la imagen de destino en pixeles 
   * @retorna una serie de pixel que contiene la imagen etiquetada
   *
   */
  
  //NB For images  0,0 is the top left corner.
  
  public int [][] doLabel(int [][] imagen_or, int width, int height){
    
            
            
    int nextlabel = 1;
    int nbs[] = new int[ 4 ];
    int nbls[] = new int[ 4 ];
    int[] src1_1d= Arreglo1D(imagen_or,width, height);

    //Get size of image and make 1d_arrays
    d_w = width;
    d_h = height;
    
    dest_1d = new int[d_w*d_h];
    labels  = new int[d_w*d_h / 2]; // the most labels there can be is 1/2 of the points in checkerboard
    
    int src1rgb;
    int result = 0;
    int px, py, count, found;
    
    labelsValid = false; // only set to true once we've complete the task
    //initialise labels
    for (int i=0; i<labels.length; i++) labels[ i ] = i;
    
    //now Label the image
    for (int i=0; i< src1_1d. length; i++){

      src1rgb = src1_1d[ i ] & 0x000000ff;
      
      if( src1rgb == 0 ){
	result = 0;  //nothing here
      } else {

        //The 4 visited neighbours
        nbs[ 0 ] = getNeighbours( src1_1d, i, -1, 0 );
        nbs[ 1 ] = getNeighbours( src1_1d, i, 0, -1 );
        nbs[ 2 ] = getNeighbours( src1_1d, i, -1, -1 );
        nbs[ 3 ] = getNeighbours( src1_1d, i, 1, -1 );
      
        //Their corresponding labels
        nbls[ 0 ] = getNeighbourd( dest_1d, i, -1, 0 );
        nbls[ 1 ] = getNeighbourd( dest_1d, i, 0, -1 );
        nbls[ 2 ] = getNeighbourd( dest_1d, i, -1, -1 );
        nbls[ 3 ] = getNeighbourd( dest_1d, i, 1, -1 );
      
	//label the point
	if( (nbs[0] == nbs[1]) && (nbs[1] == nbs[2]) && (nbs[2] == nbs[3])
	    && (nbs[0] == 0 )) { 
	  // all neighbours are 0 so gives this point a new label
	  result = nextlabel;
	  nextlabel++;
	} else { //one or more neighbours have already got labels
	  count = 0;
	  found = -1;
	  for( int j=0; j<4; j++){
	    if( nbs[ j ] != 0 ){
	      count +=1;
	      found = j;
	    }
	  }
	  if( count == 1 ) {
	    // only one neighbour has a label, so assign the same label to this.
	    result = nbls[ found ];
	  } else {
	    // more than 1 neighbour has a label
	    result = nbls[ found ];
	    // Equivalence the connected points
	    for( int j=0; j<4; j++){
	      if( ( nbls[ j ] != 0 ) && (nbls[ j ] != result ) ){
		associate( nbls[ j ], result );
	      }
	    }
	  }
	}
      }
      dest_1d[i] = result;
    }
    
    //reduce labels ie 76=23=22=3 -> 76=3
    //done in reverse order to preserve sorting
    for( int i= labels.length -1; i > 0; i-- ){
      labels[ i ] = reduce( i );
    }
    
    /*now labels will look something like 1=1 2=2 3=2 4=2 5=5.. 76=5 77=5
      this needs to be condensed down again, so that there is no wasted
      space eg in the above, the labels 3 and 4 are not used instead it jumps
      to 5.
      */
    int condensed[] = new int[ nextlabel ]; // cant be more than nextlabel labels
    
    count = 0;
    for (int i=0; i< nextlabel; i++){
      if( i == labels[ i ] ) condensed[ i ] = count++;
    }
    // Record the number of labels
    numberOfLabels = count - 1;

    // now run back through our preliminary results, replacing the raw label
    // with the reduced and condensed one, and do the scaling and offsets too
    
    //Now generate an array of colours which will be used to label the image
    int [] labelColors = new int[numberOfLabels+1];
    
    //Variable used to check if the color generated is acceptable
    boolean acceptColor = false;

    for(int i = 0; i < labelColors.length; i++){
      acceptColor = false;
      while(!acceptColor){
	double tmp = Math. random();
	labelColors[i] = (int)(tmp * 16777215);
	if(((labelColors[i] & 0x000000ff) < 200) &&
	   (((labelColors[i] & 0x0000ff00) >> 8) < 64) &&
	   (((labelColors[i] & 0x00ff0000) >> 16) < 64)){
	  //Color to be rejected so don't set acceptColor
	}
	else{
	  acceptColor = true;
	}
      }
      if (i == 0) labelColors[i] = 0;
    }

    for (int i=0; i< src1_1d. length; i++){
      result = condensed[ labels[ dest_1d[ i ] ] ];
      //result = (int) ( scale * (float) result + oset );
      //truncate if necessary
      //if( result > 255 ) result = 255;
      //if( result <  0  ) result = 0;
      //produce grayscale
      //dest_1d[i] =  0xff000000 | (result + (result << 16) + (result << 8));
      //dest_1d[i] = labelColors[result] + 0xff000000;
      dest_1d[i] = result;
    }

    //paso la imagen vector binario a matiz etiketada
    int etiketas[][]=Arrego2D(dest_1d,width,height);
    labelsValid = true; // only set to true now we've complete the task
    return etiketas;
  }
  
  /**  
   *getColours 
   *@return the number of unique, non zero colours. -1 if not valid
   */
  
  public int getColours() {
    
    if( labelsValid ) {
     
      return numberOfLabels;
    } else {
      return -1;
    }
  }
 
  /**
   * Returns the number of labels.
   */
  public int getNumberOfLabels() {
    return numberOfLabels;
  }
  
  
  private int [] Arreglo1D(int arreglo2D[][],int ancho, int alto){
     int a1d []= new int [ancho*alto];
     int a2d [][] = new int [alto][ancho];
     int temp;
     
     a2d = arreglo2D;
     for (int i=0;i<alto;i++){
          for (int j=0;j<ancho;j++){
              temp = a2d [i][j];
              a1d[i*ancho+j] = temp;
          }
      }
      return a1d;
  }
  
  
      private int[][] Arrego2D(int arreglo[],int ancho,int alto){
        int a1d [] = new int[ancho*alto];
        int a2d [][] = new int [alto][ancho];
        int temp;
        
        a1d = arreglo;
        for (int i=0;i<alto;i++){
            for (int j=0;j<ancho;j++){
                temp = a1d[i*ancho+j];
                a2d[i][j] = temp;
            }
        }
        return a2d;
    }
 
 
  
}






