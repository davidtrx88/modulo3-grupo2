/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modulo3.grupo2;

import java.util.ArrayList;
import modulo3.grupo2.interfaces.JugadorBlackjack;

/**
 *
 * @author Carmen M. Morillo Arias, David Cruz Toral, Mustafa Abdoun Bouali
 * 
 * Clase Repartidor que implementa un repartidor con estrategia hasta 18 puntos.
 */
public class RepartidorAgresivo implements JugadorBlackjack{
    private String nombre;
    private int puntuacion;    
    private ArrayList<Carta> mano;
    
    /**
     * Constructor
     */
    public RepartidorAgresivo(){
        nombre ="Repartidor agresivo";
        puntuacion = 0;
        mano = new ArrayList<Carta>();
    }
    
    /**
     * Robar carta
     * @param carta a añadir a la mano  
     */
    @Override
    public void cogerCarta(Carta carta) {
        mano.add(carta);
    }

    /**
     * Obtener el nombre del jugador
     * @return nombre nombre del jugador
     */
    @Override
    public String getNombre() {
        return nombre;
    }

    /**
     * Calcula la puntuacion del jugador
     * @return puntuaciond el jugador
     */
    @Override
    public int getPuntuacion() {
        puntuacion = 0;
        for(int i=0;i<mano.size();i++){
            puntuacion = puntuacion+mano.get(i).getValor();
        }        
        return puntuacion;
    }

    /**
     * Jugada realizada por el jugador repartidor
     * @return true si quiere pedir carta, false si quiere parar de jugar
     */
    @Override
    public boolean realizarJugada() {                
        if(puntuacion<=18) return true;
        else return false;
    }
    
    /**
     * Muestra la primera carta del repartidor
     */    
    public void mostrarPrimeraCarta(){
        Inglesa cinglesa = (Inglesa) mano.get(0);
        System.out.println("Carta del repartidor: "+cinglesa.getNumero()+" "+cinglesa.getPalo());
    }
    
    /**
     * Muestra las cartas que tiene el jugador en la jugada actual
     */    
    public void mostrarCartas(){
        for(int i=0;i<mano.size();i++){
            Inglesa cinglesa = (Inglesa) mano.get(i);
            System.out.println(cinglesa.getNumero()+" "+cinglesa.getPalo());
        }
    }    
}
