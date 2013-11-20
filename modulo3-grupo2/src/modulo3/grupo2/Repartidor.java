/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modulo3.grupo2;

import java.util.ArrayList;
import modulo3.grupo2.interfaces.Jugador;

/**
 *
 * @author Carmen M. Morillo Arias, David Cruz Toral, Mustafa Abdoun Bouali
 */
public class Repartidor implements Jugador{
    private String nombre;
    private int puntuacion;
    
    private ArrayList<Carta> mano;
    
    /**
     *
     */
    public Repartidor(){
        nombre ="";
        puntuacion =0;
        mano = new ArrayList<Carta>();
    }
    
    /**
     *
     * @param n
     * @param p
     */
    public Repartidor(String n, int p){
        this.nombre = n;
        this.puntuacion = p;
        mano = new ArrayList<Carta>();
    }

    @Override
    public Carta realizarJugada(Carta carta) { //la carta que se pasa por parámetro tiene que ser la de la baraja
        Carta car = new Carta();
        int punt = 0;
        boolean excedido= false;
        while(!excedido){
            for(int i = 0 ; i < mano.size() ; i++){

                punt += mano.get(i).getValor();
            }
            if(punt<=16){
                cogerCarta(car);
                mano.add(car);
            }else if(punt<=21){
                //parar
                return null;
                //comparar
            }else
                excedido=true;
                mano.clear();
                //terminar
                
        }
        return null;//que devuelvo??
    }

    @Override
    public void cogerCarta(Carta carta) {
        mano.add(carta);
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the puntuacion
     */
    public int getPuntuacion() {
        return puntuacion;
    }

    /**
     * @param puntuacion the puntuacion to set
     */
    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    @Override
    public boolean tieneCartas() {
        return mano.isEmpty();
    }    
    
}
