/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo3.grupo2;

import java.util.Scanner;


/**
 *
 * @author Carmen M. Morillo Arias, David Cruz Toral, Mustafa Abdoun Bouali
 * 
 * Programa principal que lanza la ejecución de los juegos 
 */
public class Modulo3Grupo2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        
        Partida p = new Partida();
                
        int opcion = -1;
        Scanner sc = new Scanner(System.in);
        do{
            p.menuPrincipal();          
            do{
                opcion = sc.nextInt();
            }while(opcion<0 && opcion>3);
            
            System.out.println();
            
            if(opcion == 1){
                p.juegaUno();
            }
            else if(opcion == 2){
                p.juegaBlackJack();
            
            }else{
                System.out.println("Hasta luego!");
            }        
            
           
        }while(opcion != 3);
    }
}
