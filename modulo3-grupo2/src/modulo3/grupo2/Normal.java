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
    
    

    private String color;
    
    public Normal(){}
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
    
    
     

   
     
     
    
    
}
