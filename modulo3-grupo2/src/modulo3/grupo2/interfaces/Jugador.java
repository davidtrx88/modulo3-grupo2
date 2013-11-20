/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modulo3.grupo2.interfaces;

import modulo3.grupo2.Carta;

/**
 *
 * @author Carmen M. Morillo Arias, David Cruz Toral, Mustafa Abdoun Bouali
 */
public interface Jugador {
    
    public Carta realizarJugada(Carta ultimaCarta);
    public void cogerCarta(Carta carta);
    public String getNombre();
    public boolean tieneCartas();
    
}
