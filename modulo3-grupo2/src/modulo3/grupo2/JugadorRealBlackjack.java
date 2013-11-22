/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo3.grupo2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import modulo3.grupo2.interfaces.JugadorBlackjack;

/**
 *
 * @author Carmen M. Morillo Arias, David Cruz Toral, Mustafa Abdoun Bouali
 * 
 * Implementación del jugador real para Blackjack
 */
public class JugadorRealBlackjack implements JugadorBlackjack{
    
    private String nombre;
    private int puntuacion;
    private List<Carta> mano;

    /**
     * Constructor
     * @param nombre nombre del jugador
     */
    public JugadorRealBlackjack(String nombre) {
        this.nombre = nombre;
        puntuacion = 0;
        mano = new ArrayList<Carta>();
    }

    /**
     * Realizar una jugada por un jugador real
     * @return true si quiere pedir carta, false si quiere parar de jugar
     */
    @Override
    public boolean realizarJugada() {
        mostrarCartas();
        System.out.println(nombre+" tienes "+getPuntuacion()+" puntos.");
        System.out.println("¿Que quieres hacer?");
        System.out.println("1. Pedir carta.");
        System.out.println("2. Parar de jugar.");
        Scanner sc = new Scanner(System.in);
        int opcion;
        do{
            opcion= sc.nextInt();
        }while(opcion>2 && opcion<1);
        
        
        if(opcion == 1){
            return true;
        }
        else{
            return false;
        }        
    }

    /**
     * Obtener la puntuacion del jugador
     * @return puntuacion
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
     * Muestra las cartas que tiene el jugador en la jugada actual
     */    
    public void mostrarCartas(){
        for(int i=0;i<mano.size();i++){
            Inglesa cinglesa = (Inglesa) mano.get(i);
            System.out.println(cinglesa.getNumero()+" "+cinglesa.getPalo());
        }
    }

    /**
     * Robar carta
     * @param c carta a añadir a la mano  
     */    
    @Override
    public void cogerCarta(Carta c) {
        mano.add(c);
    }

    /**
     * Obtener el nombre del jugador
     * @return nombre nombre del jugador
     */    
    @Override
    public String getNombre() {
        return nombre;
    }
}
