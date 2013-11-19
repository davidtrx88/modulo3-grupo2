/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo3.grupo2;

/**
 *
 * @author Carmen M. Morillo Arias, David Cruz Toral, Mustafa Abdoun Bouali
 */
public class Normal extends Carta{
    
    
    private String tipo;
    private String color;
    
    public Normal(){}
    public Normal(int numero, int valor, String color, String tipo){
               
                super(numero, valor);
                this.color = color;
                this.tipo="NULL";
     }
    public Normal (int numero, int valor, String color){
               
                super(numero, valor);
                this.color = color;
     }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
     

   
     
     
    
    
}
