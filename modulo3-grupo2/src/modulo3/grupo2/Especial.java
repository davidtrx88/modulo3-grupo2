/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo3.grupo2;

/**
 *
 * @author Carmen M. Morillo Arias, David Cruz Toral, Mustafa Abdoun Bouali
 */
public class Especial extends Carta {
    
    private String tipo;
    private String color;
    
    public Especial(){}
    public Especial (int valor, String color, String tipo){
        
        super(valor, -1);
        this.color = color;
        this.tipo = tipo;
        
    }

    /**  constructor de la clase Especial
     *
     * @param valor
     * @param tipo
     */
    public Especial (int valor, String tipo){
        
        super(valor, -1);
        this.tipo = tipo;
    
    }

    public String getTipo() {
        return tipo;
    }

    public String getColor() {
        return color;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setColor(String color) {
        this.color = color;
    }

    
    
    
}
