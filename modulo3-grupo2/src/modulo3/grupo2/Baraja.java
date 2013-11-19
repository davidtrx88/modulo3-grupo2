/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo3.grupo2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 *
 * @author Carmen M. Morillo Arias, David Cruz Toral, Mustafa Abdoun Bouali
 */
public class Baraja {
    
    private int numeroCartas;
    private String tipo;
    private Deque<Carta> cartas;
    
    /**
     * Constructor
     * @param tipo tipo de baraja necesario en función del juego (uno o blackjack) 
     */
    public Baraja(String tipo){
        
        cartas = new ArrayDeque<Carta>();
        
        if(tipo.equalsIgnoreCase("uno")){
            numeroCartas = 108;
            this.tipo = tipo;
            
            List<String> colores = new ArrayList<String>();
            colores.add("rojo");
            colores.add("amarillo");
            colores.add("azul");
            colores.add("verde");
            
            for(String color : colores){
                for(int i=0;i<10;i++){
                    Normal carta1 = new Normal(i,i,color);
                    Normal carta2 = new Normal(i,i,color);
                    cartas.add(carta1);
                    cartas.add(carta2);                
                }
                Especial cartaroba1 = new Especial(20,color,"Roba 2");
                Especial cartaroba2 = new Especial(20,color,"Roba 2");
                Especial cartacambio1 = new Especial(20,color,"Cambio de sentido");
                Especial cartacambio2 = new Especial(20,color,"Cambio de sentido");
                Especial cartasalta1 = new Especial(20,color,"Salta turno");
                Especial cartasalta2 = new Especial(20,color,"Salta turno");
                
                cartas.add(cartaroba1);
                cartas.add(cartaroba2);
                cartas.add(cartacambio1);
                cartas.add(cartacambio2);
                cartas.add(cartasalta1);
                cartas.add(cartasalta2);
            } 
            
            Especial cartacomodin1 = new Especial(50,"Comodin de color");
            Especial cartacomodin2 = new Especial(50,"Comodin de color");
            Especial cartacomodin3 = new Especial(50,"Comodin de color");
            Especial cartacomodin4 = new Especial(50,"Comodin de color");
            cartas.add(cartacomodin1);
            cartas.add(cartacomodin2);
            cartas.add(cartacomodin3);
            cartas.add(cartacomodin4);
            
            Especial cartaroba1 = new Especial(50,"Comodin roba 4");
            Especial cartaroba2 = new Especial(50,"Comodin roba 4");
            Especial cartaroba3 = new Especial(50,"Comodin roba 4");
            Especial cartaroba4 = new Especial(50,"Comodin roba 4");
            cartas.add(cartaroba1);
            cartas.add(cartaroba2);
            cartas.add(cartaroba3);
            cartas.add(cartaroba4);
            
        }
        else if(tipo.equalsIgnoreCase("blackjack")){
            numeroCartas = 52;
            this.tipo = tipo;
            
            List<String> palos = new ArrayList<String>();
            palos.add("Picas");
            palos.add("Corazones");
            palos.add("Treboles");
            palos.add("Rombo");

            for(String palo : palos){
//                Inglesa carta = new Inglesa("A",11,palo); 
                Inglesa carta = new Inglesa(1,11,palo); 
                for(int i=2;i<11;i++){
                    Inglesa carta2 = new Inglesa(i,i,palo); 
                    cartas.add(carta2);
                }
//                Inglesa cartaj = new Inglesa("J",10,palo);  
//                Inglesa cartaq = new Inglesa("Q",10,palo);
//                Inglesa cartak = new Inglesa("K",10,palo);  
                Inglesa cartaj = new Inglesa(11,10,palo);  
                Inglesa cartaq = new Inglesa(12,10,palo);
                Inglesa cartak = new Inglesa(13,10,palo);  
                
                cartas.add(cartaj);
                cartas.add(cartaq);
                cartas.add(cartak);
            }        
        }
    }

    public int getNumeroCartas() {
        return numeroCartas;
    }

    public String getTipo() {
        return tipo;
    }

    public void setNumeroCartas(int numeroCartas) {
        this.numeroCartas = numeroCartas;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    /**
     * Saca una carta de la baraja
     * @return la carta sacada
     */
    public Carta getCarta(){
        return cartas.removeFirst();
    }
    
    /**
     * Devuelve una carta a la baraja.
     * @param carta carta a añadir a la baraja.
     */
    public void anadirCarta(Carta carta){
        cartas.addLast(carta);
    }
}
