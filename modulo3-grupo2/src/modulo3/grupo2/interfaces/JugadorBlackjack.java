/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo3.grupo2.interfaces;

import modulo3.grupo2.Carta;

/**
 *
 * @author carmen
 */
public interface JugadorBlackjack {
    
    public boolean realizarJugada();
    public int getPuntuacion();
    public void cogerCarta(Carta c);
    public String getNombre();
    
}
