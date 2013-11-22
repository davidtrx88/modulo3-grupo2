/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo3.grupo2;

/**
 *
 * @author Carmen M. Morillo Arias, David Cruz Toral, Mustafa Abdoun Bouali
 * 
 * Implementacion de la clase carta
 */
public class Carta {
           
    private int numero;
    private int valor;

    /**
     * Constructor por defecto
     */
    public Carta(){}
    
    /**
     * Constructor de la clase carta
     * @param valor valor de la carta en el juego
     * @param numero numero de la carta
     */
    public Carta(int valor, int numero)
    {
        this.valor = valor;
        this.numero = numero;

    } 
    
    /**
     * Devuelve el número de la carta
     * @return numero de la carta
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Establecer el valor del número de la carta
     * @param numero numero de la carta
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * Obtiene el valor de la carta
     * @return valor valor de la carta en el juego
     */
    public int getValor() {
        return valor;
    }

    /**
     * Establece el valor de la carta en el juego
     * @param valor de la carta en el juego
     */
    public void setValor(int valor) {
        this.valor = valor;
    }
    
       

    
    
}
