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
public class JugadorReal implements Jugador{
    private String nombre;
    private int puntuacion;
    private ArrayList<Carta> mano;
    
    /**
     *
     */
    public JugadorReal(){
        nombre ="";
        puntuacion =0;
        mano = new ArrayList<Carta>();
    }
    
    /**
     *
     * @param n
     * @param p
     */
    public JugadorReal(String n, int p){
        this.nombre = n;
        this.puntuacion = p;
        mano = new ArrayList<Carta>();
    }

    @Override
    public Carta realizarJugada(Carta ultimaCarta) {
     
        return null;
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

    public void mostrarCartasMano(){
        System.out.println("Las cartas del jugador "+nombre+" son:");
        for(int i=0;i<mano.size();i++){             
            Carta c = mano.get(i);
            if(c instanceof Normal){
                Normal normal = (Normal) c;
                System.out.println("    "+normal.getNumero()+" "+normal.getColor());
            }
            else if(c instanceof Especial){
                Especial especial = (Especial) c;
                System.out.println("    "+especial.getTipo()+" "+especial.getColor());
            }
        }
    }
    
    
}
