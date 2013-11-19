/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo3.grupo2.interfaces;

import modulo3.grupo2.Carta;

/**
 *
 * @author Carmen M. Morillo Arias, David Cruz Toral, Mustafa Abdoun Bouali
 */
public interface Juego {
    
    public void crearBaraja();
    
    public void anadirJugador(Jugador jugador);
    
    public void eliminarJugador(Jugador jugador);
    
    public void repartir();
    
    public Carta darCarta();
    
    public void terminarJuego();
    
    public void guardarJuego();
              
}
