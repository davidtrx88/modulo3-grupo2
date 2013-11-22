/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo3.grupo2;

import java.util.LinkedList;
import modulo3.grupo2.interfaces.Juego;
import java.util.List;
import modulo3.grupo2.interfaces.JugadorBlackjack;
import modulo3.grupo2.interfaces.JugadorUno;


/**
 *
 * @author Carmen M. Morillo Arias, David Cruz Toral, Mustafa Abdoun Bouali
 */
public class Blackjack implements Juego{
    
    private Baraja baraja;
    private List<JugadorBlackjack> jugadores;
    
    /**
     * JugadorUno
     */
    public Blackjack() {
        jugadores = new LinkedList<JugadorBlackjack>();
        crearBaraja();        
    }
          
    /**
     * Crea la baraja con las cartas necesarias para el juego
     */
    @Override
    public void crearBaraja() {
        baraja = new Baraja("blackjack");
    }

    /**
     * Añade un jugador a la partida
     * @param jugador jugador a añadir
     */    
    @Override
    public void anadirJugador(JugadorBlackjack jugador) {
        jugadores.add(jugador);
    }

    /**
     * Elimina un jugador el juego
     * @param jugador jugador a eliminar
     */    
    @Override
    public void eliminarJugador(JugadorUno jugador) {
        for(int i=0;i<jugadores.size();i++){
            if(jugadores.get(i).getNombre().equalsIgnoreCase(jugador.getNombre())){
                jugadores.remove(i);
                break;
            }
        }
    }

    /**
     * Reparte las cartas para iniciar el juego
     */    
    @Override
    public void repartir() {
        for(int j=0;j<2;j++){
            for(int i=0;i<jugadores.size();i++){
                JugadorBlackjack jugador = jugadores.get(i);
                jugador.cogerCarta(baraja.getCarta());
            }        
        }
    }

    /**
     * El jugador roba una carta de la baraja
     * @return carta que ha robado
     */    
    @Override
    public Carta darCarta() {
        return baraja.getCarta();
    }

    @Override
    public void terminarJuego() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void guardarJuego() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void anadirJugador(JugadorUno jugador) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void calcularGanador(){
        int puntuacionjb = jugadores.get(0).getPuntuacion();
        int puntuacionjr = jugadores.get(1).getPuntuacion();   
        
        if(jugadores.get(1) instanceof Repartidor){
            Repartidor r = (Repartidor) jugadores.get(1);
            System.out.println("Las cartas del jugador "+r.getNombre()+" son:");
            r.mostrarCartas();            
        }
        else{
            RepartidorAgresivo r = (RepartidorAgresivo) jugadores.get(1);
            System.out.println("Las cartas del jugador "+r.getNombre()+" son:");
            r.mostrarCartas();                    
        }
        
        if(puntuacionjb != puntuacionjr){
            if(puntuacionjb == 21){
                System.out.println("Enhorabuena! "+jugadores.get(0).getNombre()+" has ganado!");
            }
            else if(puntuacionjr == 21){
                System.out.println("Lo siento, "+jugadores.get(1).getNombre()+" ha ganado :(");
            }
            else if(puntuacionjr < 21 && puntuacionjb < 21){ //Si la puntuación de ambos <21 gana el que más se acerque
                if(puntuacionjr > puntuacionjb){
                    System.out.println("Lo siento, "+jugadores.get(1).getNombre()+" ha ganado :(");
                }
                else{
                    System.out.println("Enhorabuena! "+jugadores.get(0).getNombre()+" has ganado!");
                }
            }else if(puntuacionjr > 21 && puntuacionjb > 21){ //Si la puntuacion de ambos >21 gana el que más se acerque
                if(puntuacionjr < puntuacionjb){
                    System.out.println("Lo siento, "+jugadores.get(1).getNombre()+" ha ganado :(");
                }
                else{
                    System.out.println("Enhorabuena! "+jugadores.get(0).getNombre()+" has ganado!");
                }                
            }
            else if(puntuacionjr > 21 && puntuacionjb < 21){
                System.out.println("Enhorabuena! "+jugadores.get(0).getNombre()+" has ganado!");
            }
            else if(puntuacionjr < 21 && puntuacionjb > 21){
                System.out.println("Lo siento, "+jugadores.get(1).getNombre()+" ha ganado :(");
            }
        }
        else{ //El repartidor y el jugador tienen la misma puntuación
            System.out.println("Lo siento, "+jugadores.get(1).getNombre()+" ha ganado :(");
        }
    }
    
    public void escogerRepartidor(){
        int numjugador = (int) (Math.random()*2+1);
        if(numjugador == 1){
            Repartidor jr = new Repartidor();        
            anadirJugador(jr);              
        }
        else{
            RepartidorAgresivo jr = new RepartidorAgresivo();        
            anadirJugador(jr);                    
        }    
    }
    
    public JugadorBlackjack obtenerJugadorRepartidor(){
        return jugadores.get(1);
    }
}
