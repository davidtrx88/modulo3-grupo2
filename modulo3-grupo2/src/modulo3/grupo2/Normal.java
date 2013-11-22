/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo3.grupo2;

/**
 *
 * @author Carmen M. Morillo Arias, David Cruz Toral, Mustafa Abdoun Bouali
 * 
 * Implementacion de la clase Normal que hereda de Carta.
 * Representa a una carta con número entre 0 y 9 y un color.
 */
public class Normal extends Carta{
    
    private String color;
    
    public Normal(){}
    
    public Normal (int numero, int valor, String color){
               
                super(numero, valor);
                this.color = color;
    }

    /**
     * Obtiene el color de la carta
     * @return color de la carta
     */
    public String getColor() {
        return color;
    }

    /**
     * Establece el color de la carta
     * @param color color de la carta
     */
    public void setColor(String color) {
        this.color = color;
    } 
}
