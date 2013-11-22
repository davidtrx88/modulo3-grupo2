/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo3.grupo2;

/**
 *
 * @author Carmen M. Morillo Arias, David Cruz Toral, Mustafa Abdoun Bouali
 * 
 * Implementacion de la clase Especial que hereda Carta
 */
public class Especial extends Carta {
    
    private String tipo;
    private String color;
    
    /**
     * Constructor por defecto de la subclase Especial
     */
    public Especial(){}
    
    /**  
     * Constructor de la clase Especial. 
     * El número que hereda de Carta será igual a -1 por ser especial.
     * @param valor valor de la carta en el juego
     * @param color color de la carta en el juego
     * @param tipo tipo de carta especial: Roba 2, Cambio de sentido, Salta turno, Comodín de color, Comodín roba 4     
     */    
    public Especial (int valor, String color, String tipo){
        
        super(valor, -1);
        this.color = color;
        this.tipo = tipo;
        
    }

    /**  
     * Constructor de la clase Especial. 
     * El número que hereda de Carta será igual a -1 por ser especial.
     * @param valor valor de la carta en el juego
     * @param tipo tipo de carta especial: Roba 2, Cambio de sentido, Salta turno, Comodín de color, Comodín roba 4
     */
    public Especial (int valor, String tipo){
        
        super(valor, -1);
        this.tipo = tipo;
    
    }

    /**
     * Obtiene el tipo de carta
     * @return tipo de carta
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Obtiene el color de la carta
     * @return color de la carta
     */
    public String getColor() {
        return color;
    }

    /**
     * Establece el tipo de carta especial: Roba 2, Cambio de sentido, Salta turno, Comodín de color, Comodín roba 4     
     * @param tipo tipo de carta especial: Roba 2, Cambio de sentido, Salta turno, Comodín de color, Comodín roba 4     
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Establece el color de la carta
     * @param color color de la carta
     */
    public void setColor(String color) {
        this.color = color;
    }
    
}
