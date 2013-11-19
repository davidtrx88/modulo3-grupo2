/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modulo3.grupo2;

import java.util.ArrayList;
import modulo3.grupo2.interfaces.Jugador;
import modulo3.grupo2.Normal;

/**
 *
 * @author Carmen M. Morillo Arias, David Cruz Toral, Mustafa Abdoun Bouali
 */
public class JugadorNumero implements Jugador{
    private String nombre;
    private int puntuacion;
    
    private ArrayList<Carta> mano;

    /**
     *
     */
    public JugadorNumero(){
        nombre ="";
        puntuacion =0;
        mano = new ArrayList<Carta>();
    }
    
    /**
     *
     * @param n
     * @param p
     */
    public JugadorNumero(String n, int p){
        this.nombre = n;
        this.puntuacion = p;
        mano = new ArrayList<Carta>();
    }
    
    /**
     *
     * @param ultimaCarta
     * @param mano
     * @return
     */
    @Override
    public Carta realizarJugada(Carta ultimaCarta){
        Carta c = new Carta();
        boolean encontrado=false;
        if(!encontrado){
            for(int i=0; i<mano.size() && !encontrado; i++){//mano es el arraylist donde estan las cartas de un jugador
                if(mano.get(i).getNumero()== ultimaCarta.getNumero() && ultimaCarta.getNumero()>=0 ){
                    c.setNumero(mano.get(i).getNumero()); 
                    c.setValor(mano.get(i).getValor());
                    c.setColor(mano.get(i).get.getColor());
                    
                    encontrado = true;
                    mano.remove(i);
                }

            }
        }else if(!encontrado){
            for(int i=0; i<mano.size() && !encontrado; i++){
                if(mano.get(i).getColor()== ultimaCarta.getColor() && mano.get(i).getTipo()==NULL){
                    c.setNumero(mano.get(i).getNumero()); 
                    c.setValor(mano.get(i).getValor());
                    c.setColor(mano.get(i).getColor());
                      
                    encontrado = true;
                     mano.remove(i);
                }
            }
            
        }else if(!encontrado){
            for(int i=0; i<mano.size() && !encontrado; i++){
                if(mano.get(i).getColor()== ultimaCarta.getColor()&& mano.get(i).getTipo()!=NULL){
                    c.setNumero(mano.get(i).getNumero()); 
                    c.setValor(mano.get(i).getValor());
                    c.setColor(mano.get(i).getColor());
                    c.setTipo(mano.get(i).getTipo());
                    encontrado = true;
                     mano.remove(i);
                }

            }
        }else if(!encontrado){
            for(int i=0; i<mano.size() && !encontrado; i++){
                if(mano.get(i).getTipo().equalsIgnoreCase("Comodin roba 4") || mano.get(i).getTipo().equalsIgnoreCase("Comodin de color")){ // R4 es comodin roba cuatro y CC es comodin color
                    c.setNumero(mano.get(i).getNumero()); 
                    c.setValor(mano.get(i).getValor());
                    c.setColor(mano.get(i).getColor());
                    c.setTipo(mano.get(i).getTipo());
                    encontrado = true;
                    mano.remove(i);
                }

            }
        }else{// no se si iria aqui o como una condicion al llamar a la funcion realizarJugada
            cogerCarta(c);//la carta que debe de coger es de la pila de robar
            //pasarTurno();
            return null;
        }
        return c;
    }

    /**
     *
     * @param carta
     */
    @Override
    public void cogerCarta(Carta carta) {//la carta que debe de coger es de la pila de robar
        
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
    
   
}
