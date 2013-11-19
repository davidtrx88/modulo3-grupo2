/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo3.grupo2;

import java.util.LinkedList;
import modulo3.grupo2.interfaces.Jugador;
import modulo3.grupo2.interfaces.Juego;
import java.util.List;


/**
 *
 * @author Carmen M. Morillo Arias, David Cruz Toral, Mustafa Abdoun Bouali
 */
public class Blackjack implements Juego{
    
    private String turno;
    private Baraja baraja;
    private List<Jugador> jugadores;
    
    /**
     * Jugador
     */
    public Blackjack() {
        jugadores = new LinkedList<Jugador>();
        crearBaraja();        
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
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
    public void anadirJugador(Jugador jugador) {
        jugadores.add(jugador);
    }

    /**
     * Elimina un jugador el juego
     * @param jugador jugador a eliminar
     */    
    @Override
    public void eliminarJugador(Jugador jugador) {
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
                Jugador jugador = jugadores.get(i);
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
        
}
