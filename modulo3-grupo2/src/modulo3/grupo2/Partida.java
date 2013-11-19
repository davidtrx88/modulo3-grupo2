/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo3.grupo2;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import modulo3.grupo2.excepciones.ExcepcionJugadaNoValida;
import modulo3.grupo2.interfaces.Juego;
import modulo3.grupo2.interfaces.Jugador;

/**
 *
 * @author Carmen M. Morillo Arias, David Cruz Toral, Mustafa Abdoun Bouali
 */
public class Partida {
        
    
    public void menuPrincipal(){
        System.out.println("Elige una opción...");
        System.out.println("1. Jugar al UNO.");
        System.out.println("2. Jugar al BlackJack.");
        System.out.println("3. Salir.");
    }
    
    
    public void juegaUno(){
        
        Uno juego = new Uno();        
        
        //Añadimos los jugadores
        System.out.println("¿Cuántos jugadores?");
        Scanner sc = new Scanner(System.in);
        int njugadores = sc.nextInt();
        
        Scanner sc2 = new Scanner(System.in);
        for(int i=0;i<njugadores;i++){
            System.out.println("Introduce el nombre del jugador "+i);
            String nombre = sc2.toString();
            
            JugadorReal jr = new JugadorReal(nombre,0);            
            juego.anadirJugador(jr);
        }
        
        //Si sólo hay un jugador, añadimos otros con distintas estrategias
        if(njugadores == 1){
            JugadorNumero jugador1 = new JugadorNumero("Jugador1",0);
            juego.anadirJugador(jugador1);
            JugadorColor jugador2 = new JugadorColor("Jugador2",0);
            juego.anadirJugador(jugador2);
            JugadorCartaEspecial jugador3 = new JugadorCartaEspecial("Jugador3",0);
            juego.anadirJugador(jugador3);
        }
        
        //Creamos la baraja, repartimos, mostramos la primera carta bocarriba, mostramos a cada
        //jugador real sus cartas.
        juego.crearBaraja();
        juego.repartir();
        juego.mostrarCartasJugador();
        juego.mostrarUltimaCarta();
        
        
        //Comienza el juego        
        boolean terminado = false;
        boolean ultimaCartaValida = true;
        
        while(!terminado){
            System.out.println("El turno es del jugador: "+juego.getTurno());
            Jugador jugadorActual = juego.obtenerJugador(juego.getTurno());
                       
            if(jugadorActual instanceof JugadorReal){
                Carta c = jugadorActual.realizarJugada(juego.getUltimaCarta());
                
                if(c == null){ //Quiere robar
                    jugadorActual.cogerCarta(juego.darCarta());
                    ultimaCartaValida = false;                    
                }
                else{
                    try {
                        juego.validarJugada(c);
                    } catch (ExcepcionJugadaNoValida ex) {
                        System.out.println("La carta jugada no es válida!!");
                        jugadorActual.cogerCarta(c);
                        ultimaCartaValida = false;
                    }
                }
                
                if(ultimaCartaValida){ //La carta que lanzó es jugador es válida
                   juego.anadirCartaBaraja(juego.getUltimaCarta());
                   juego.setUltimaCarta(c);                   
                }
                
                
            }
        }

        
        
        
        
    }
    
    
    
}
