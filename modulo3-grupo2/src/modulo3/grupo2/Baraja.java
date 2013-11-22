/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo3.grupo2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Carmen M. Morillo Arias, David Cruz Toral, Mustafa Abdoun Bouali
 * 
 * Clase que implementa una baraja de los tipos Uno o Blackjack
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
        
        List<Carta> cartassb = new ArrayList<Carta>();
        
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
                    cartassb.add(carta1);
                    cartassb.add(carta2);                
                }
                Especial cartaroba1 = new Especial(20,color,"Roba 2");
                Especial cartaroba2 = new Especial(20,color,"Roba 2");
                Especial cartacambio1 = new Especial(20,color,"Cambio de sentido");
                Especial cartacambio2 = new Especial(20,color,"Cambio de sentido");
                Especial cartasalta1 = new Especial(20,color,"Salta turno");
                Especial cartasalta2 = new Especial(20,color,"Salta turno");
                
                cartassb.add(cartaroba1);
                cartassb.add(cartaroba2);
                cartassb.add(cartacambio1);
                cartassb.add(cartacambio2);
                cartassb.add(cartasalta1);
                cartassb.add(cartasalta2);
            } 
            
            Especial cartacomodin1 = new Especial(50,"Comodin de color");
            Especial cartacomodin2 = new Especial(50,"Comodin de color");
            Especial cartacomodin3 = new Especial(50,"Comodin de color");
            Especial cartacomodin4 = new Especial(50,"Comodin de color");
            cartassb.add(cartacomodin1);
            cartassb.add(cartacomodin2);
            cartassb.add(cartacomodin3);
            cartassb.add(cartacomodin4);
            
            Especial cartaroba1 = new Especial(50,"Comodin roba 4");
            Especial cartaroba2 = new Especial(50,"Comodin roba 4");
            Especial cartaroba3 = new Especial(50,"Comodin roba 4");
            Especial cartaroba4 = new Especial(50,"Comodin roba 4");
            cartassb.add(cartaroba1);
            cartassb.add(cartaroba2);
            cartassb.add(cartaroba3);
            cartassb.add(cartaroba4);
            
        }
        else if(tipo.equalsIgnoreCase("blackjack")){
            numeroCartas = 52;
            this.tipo = tipo;
            
            List<String> palos = new ArrayList<String>();
            palos.add("Picas");
            palos.add("Corazones");
            palos.add("Treboles");
            palos.add("Rombos");

            for(String palo : palos){
                Inglesa carta = new Inglesa(1,11,palo); 
                for(int i=2;i<11;i++){
                    Inglesa carta2 = new Inglesa(i,i,palo); 
                    cartassb.add(carta2);
                }
                Inglesa cartaj = new Inglesa(11,10,palo);  
                Inglesa cartaq = new Inglesa(12,10,palo);
                Inglesa cartak = new Inglesa(13,10,palo);  
                
                cartassb.add(cartaj);
                cartassb.add(cartaq);
                cartassb.add(cartak);
            }        
        }
     
        //Barajamos las cartas
        long semilla = System.nanoTime();
        Collections.shuffle(cartassb, new Random(semilla));
        cartas = new ArrayDeque<Carta> (cartassb);
        
    }
    
    /**
     * Devuelve en número de cartas de la baraja
     * @return numero de cartas de la baraja
     */    
    public int getNumeroCartas() {
        return numeroCartas;
    }

    /**
     * Devuelve el tipo de la baraja
     * @return tipo de baraja
     */    
    public String getTipo() {
        return tipo;
    }

    
    public void setNumeroCartas(int numeroCartas) {
        this.numeroCartas = numeroCartas;
    }

    /**
     * Asigna el numero de cartas
     * @param tipo tipo de baraja
     */    
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
