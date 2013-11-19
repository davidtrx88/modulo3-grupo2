/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo3.grupo2;

/**
 *
 * @author Carmen M. Morillo Arias, David Cruz Toral, Mustafa Abdoun Bouali
 */
public class Inglesa extends Carta {
    
    
 
    private String palo;
    
    public Inglesa(){} 
    public Inglesa (int numero, int valor, String palo){
           
                super(numero, valor);
                
                this.palo = palo;
    } 

    /**
     * @return the palo
     */
    public String getPalo() {
        return palo;
    }

    /**
     * @param palo the palo to set
     */
    public void setPalo(String palo) {
        this.palo = palo;
    }
    
      

    
 
     
    
}
