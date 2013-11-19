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
public class Repartidor2 implements Jugador{
    private String nombre;
    private int puntuacion;
    
    private ArrayList<Carta> mano;
    
    /**
     *
     */
    public Repartidor2(){
        nombre ="";
        puntuacion =0;
        mano = new ArrayList<Carta>();
    }
    
    /**
     *
     * @param n
     * @param p
     */
    public Repartidor2(String n, int p){
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
            if(punt<=18){
                cogerCarta(car);
                mano.add(car);
            }else if(punt<=21){
                //parar
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
        getMano().add(carta);
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

    /**
     * @return the mano
     */
    public ArrayList<Carta> getMano() {
        return mano;
    }

    /**
     * @param mano the mano to set
     */
    public void setMano(ArrayList<Carta> mano) {
        this.mano = mano;
    }

    
    
}
