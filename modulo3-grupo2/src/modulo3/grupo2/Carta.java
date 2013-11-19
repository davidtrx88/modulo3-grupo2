/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo3.grupo2;

/**
 *
 * @author Carmen M. Morillo Arias, David Cruz Toral, Mustafa Abdoun Bouali
 */
public class Carta {
           
    private int numero;
    private int valor;

    
    public Carta(){}
    
    /**constructor de la clase carta
     *
     * @param valor
     * @param numero
     */
    public Carta(int valor, int numero)
    {
        this.valor = valor;
        this.numero = numero;

    } 
    
    /**
     * @return the numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * @return the valor
     */
    public int getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(int valor) {
        this.valor = valor;
    }
    
       

    
    
}
