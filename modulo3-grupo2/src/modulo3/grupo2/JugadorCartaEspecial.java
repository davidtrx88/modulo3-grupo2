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
public class JugadorCartaEspecial implements Jugador{
    private String nombre;
    private int puntuacion;
    private ArrayList<Carta> mano;
    
    /**
     *
     */
    public JugadorCartaEspecial(){
        nombre ="";
        puntuacion =0;
        mano = new ArrayList<Carta>();
    }
    
    /**
     *
     * @param n
     * @param p
     */
    public JugadorCartaEspecial(String n, int p){
        this.nombre = n;
        this.puntuacion = p;
        mano = new ArrayList<Carta>();
    }

    @Override
    public Carta realizarJugada(Carta ultimaCarta){
        Carta ca = new Carta(); //esta variable la pongo para poder asignasele una de la pila boca abajo
        Normal ultNormal = new Normal();
        Especial ultEspecial = new Especial();
        if(ultimaCarta instanceof Normal){
            ultNormal = (Normal)ultimaCarta;
        }else{
            ultEspecial = (Especial)ultimaCarta;
        }
        
        boolean encontrado=false;
        if(!encontrado){
            for(int i=0; i<mano.size() && !encontrado; i++){
                if(mano.get(i) instanceof Especial){ // Si es carta es de tipo Normal
                    Especial especial = (Especial) mano.get(i);
                    Especial c = new Especial();
                    if(especial.getTipo().equalsIgnoreCase("Comodin roba 4") || especial.getTipo().equalsIgnoreCase("Comodin de color")){ // R4 es comodin roba cuatro y CC es comodin color
                        c.setNumero(especial.getNumero()); 
                        c.setValor(especial.getValor());
                        c.setColor(especial.getColor());
                        c.setTipo(especial.getTipo());
                        
                        
                        return c;
                    }
                    encontrado = true;
                    mano.remove(i);
                }

            }
        }else if(!encontrado){
            for(int i=0; i<mano.size() && !encontrado; i++){//mano es el arraylist donde estan las cartas de un jugador
                if(mano.get(i) instanceof Normal){ // Si es carta es de tipo Normal
                    Normal normal = (Normal)mano.get(i);
                    Normal c = new Normal();
                    
                    if(normal.getNumero()== ultimaCarta.getNumero() && ultimaCarta.getNumero()>=0 ){
                        c.setNumero(normal.getNumero()); 
                        c.setValor(normal.getValor());
                        c.setColor(normal.getColor());

                        
                        return c;
                    }
                    encontrado = true;
                    mano.remove(i);
                }

            }
        }else if(!encontrado){
            for(int i=0; i<mano.size() && !encontrado; i++){
                if(mano.get(i) instanceof Normal){ // Si es carta es de tipo Normal
                    Normal normal = (Normal) mano.get(i);
                    Normal c = new Normal();
                
                    if(normal.getColor().equalsIgnoreCase(ultNormal.getColor()) && normal.getTipo().equalsIgnoreCase("NULL")){
                        c.setNumero(normal.getNumero()); 
                        c.setValor(normal.getValor());
                        c.setColor(normal.getColor());

                        
                         return c;
                    }
                    encontrado = true;
                    mano.remove(i);
                }
            }
            
        }else if(!encontrado){
            for(int i=0; i<mano.size() && !encontrado; i++){
                if(mano.get(i) instanceof Especial){ // Si es carta es de tipo Normal
                    Especial especial = (Especial) mano.get(i);
                    Especial c = new Especial();
                    if(especial.getColor().equalsIgnoreCase(ultEspecial.getColor())&& especial.getTipo().equalsIgnoreCase("NULL")){
                        c.setNumero(especial.getNumero()); 
                        c.setValor(especial.getValor());
                        c.setColor(especial.getColor());
                        c.setTipo(especial.getTipo());
                        
                        
                        return c;
                    }
                    encontrado = true;
                    mano.remove(i);
                }

            }
        
        }else{// no se si iria aqui o como una condicion al llamar a la funcion realizarJugada
            cogerCarta(ca);//la carta que debe de coger es de la pila de robar
            //pasarTurno();
            return null;
        }
        return null;
    }

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
