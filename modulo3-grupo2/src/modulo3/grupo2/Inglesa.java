/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo3.grupo2;

/**
 *
 * @author Carmen M. Morillo Arias, David Cruz Toral, Mustafa Abdoun Bouali
 * 
 * Implementación del tipo de carta Inglesa que hereda de Carta.
 */
public class Inglesa extends Carta {
         
    private String palo;
    
    /**
     * Constructor por defecto
     */
    public Inglesa(){} 
    
    /**
     * Constructor de carta inglesa
     * @param numero número de la carta
     * @param valor valor de la carta en el juego
     * @param palo palo al que pertenece la carta
     */
    public Inglesa (int numero, int valor, String palo){
           
        super(numero, valor);                
        this.palo = palo;
    } 

    /**
     * Obtener el palo al que pertenece la carta
     * @return palo : picas, corazones, rombos, tréboles
     */
    public String getPalo() {
        return palo;
    }

    /**
     * Establece el palo
     * @param palo : picas, corazones, rombos, tréboles
     */
    public void setPalo(String palo) {
        this.palo = palo;
    }
    
      

    
 
     
    
}
