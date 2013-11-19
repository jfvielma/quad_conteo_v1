/*
 * Complex.java
 *
 * Created on 5 de marzo de 2008, 07:35 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package quad1;

/**
 *
 * @author Julio
 */
public class Complex {
    
    /** Creates a new instance of Complex */
//    public static double Complex(int[][] I, int x1, int y1, int x2, int y2) {
//        double prome;
//        double varia;
//        
//        prome=promedio(I,x1,y1,x2,y2);
//        varia=varianza(prome, I,x1,y1,x2,y2);
//        
//        return varia;
//    }
    
    
//    private double promedio(int[][] I, int x1, int y1, int x2, int y2){
//        double prom=0;
//        double suma=0;
//        int cont=0;
//        
//        for (int yi=y1;yi<=y2;yi++)
//            for (int xi=x1;xi<=x2;xi++){
//                suma+=I[yi][xi];
//                cont+=cont;
//            }
//        prom=suma/cont;
//        
//        return prom;
//    }
    
    public static double varianza(int[][] I, int x1, int y1, int x2, int y2){
        double varian=0;
        double prom=0;
        double suma=0;
        double desvia=0;
        int num_pix=(x2-x1)*(y2-y1);
        
 
        for (int yi=y1;yi<y2;yi++)
            for (int xi=x1;xi<x2;xi++){
                suma+=I[yi][xi];
            }
        
        prom=suma/num_pix;
        suma=0;
        
        System.out.println("promedio:"+prom);
        for (int yi=y1;yi<y2;yi++)
            for (int xi=x1;xi<x2;xi++){
                suma+=Math.pow(I[yi][xi]-prom,2);
            }
        varian=suma/num_pix-1;
        desvia=Math.sqrt(varian);
        System.out.println("varianza:"+varian);
        System.out.println("desviacion:"+Math.sqrt(varian));
        return desvia;
    }
    
}
