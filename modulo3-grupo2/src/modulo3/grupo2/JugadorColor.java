/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modulo3.grupo2;

import java.util.ArrayList;
import modulo3.grupo2.interfaces.JugadorUno;

/**
 *
 * @author Carmen M. Morillo Arias, David Cruz Toral, Mustafa Abdoun Bouali
 * 
 * Implementación del jugador según la estrategia "carta color"
 */
public class JugadorColor implements JugadorUno{
    private String nombre;
    private int puntuacion;
    private ArrayList<Carta> mano;

    /**
     * Constructor por defecto
     */    
    public JugadorColor(){
        nombre ="";
        puntuacion =0;
        mano = new ArrayList<Carta>();
    }
    
    /**
     * Constructor para JugadorColor
     * @param nombre nombre del jugador
     * @param puntuacion puntuacion del jugador
     */
    public JugadorColor(String nombre, int puntuacion){
        this.nombre = nombre;
        this.puntuacion = puntuacion;
        mano = new ArrayList<Carta>();
    }
    
    /**
     * Método para realizar una jugada
     * @param ultimaCarta ultima carta que se jugó en la partida
     * @return carta que juega el jugador, null si quiere robar
     */    
    @Override
    public Carta realizarJugada(Carta ultimaCarta){        
        Normal ultNormal = null;
        Especial ultEspecial = null;
        boolean encontrado=false;
        
        if(ultimaCarta instanceof Normal){
            ultNormal = (Normal)ultimaCarta;
             if(!encontrado){
                for(int i=0; i<mano.size() && !encontrado; i++){
                    if(mano.get(i) instanceof Normal){ // Si es carta es de tipo Normal
                        Normal normal = (Normal) mano.get(i);

                        if(normal.getColor().equalsIgnoreCase(ultNormal.getColor()) ){
                            mano.remove(i);
                            return normal;   
                        }
                    }
                }         
            }
            if(!encontrado){
                for(int i=0; i<mano.size() && !encontrado; i++){
                    if(mano.get(i) instanceof Especial){ // Si es carta es de tipo Especial
                        Especial especial = (Especial) mano.get(i);
                        if(!especial.getTipo().equalsIgnoreCase("Comodin roba 4") && !especial.getTipo().equalsIgnoreCase("Comodin de color")){
                            if(especial.getColor().equalsIgnoreCase(ultNormal.getColor())/*|| especial.getColor().equalsIgnoreCase(ultNormal.getColor())*/){
                                mano.remove(i);
                                return especial;                     
                            }                    
                        }
                    }
                }            
            }
            if(!encontrado){
                for(int i=0; i<mano.size() && !encontrado; i++){ //mano es el arraylist donde estan las cartas de un jugador                
                    if(mano.get(i) instanceof Normal){ // Si es carta es de tipo Normal
                        Normal normal = (Normal) mano.get(i);

                        if(normal.getNumero() == ultimaCarta.getNumero()){                       
                            mano.remove(i);
                            return normal;                                                
                        }
                    }
                }         
            }
            if(!encontrado){
                for(int i=0; i<mano.size() && !encontrado; i++){
                    if(mano.get(i) instanceof Especial){ // Si es carta es de tipo Normal
                        Especial especial = (Especial) mano.get(i);
                        if(especial.getTipo().equalsIgnoreCase("Comodin roba 4")){
                            mano.remove(i);
                            return especial;                        
                        }else if( especial.getTipo().equalsIgnoreCase("Comodin de color")){
                            especial.setColor("azul");
                            mano.remove(i);
                            return especial;
                        }
                    }
                }
            }
            
        }else{
            
            ultEspecial = (Especial) ultimaCarta;
            if(ultEspecial.getTipo().equalsIgnoreCase("Comodin roba 4") || ultEspecial.getTipo().equalsIgnoreCase("Comodin de color")){
                if(!encontrado){
                    for(int i=0; i<mano.size() && !encontrado; i++){
                        if(mano.get(i) instanceof Normal){ // Si es carta es de tipo Normal
                            Normal normal = (Normal) mano.get(i);

                            if(normal.getColor().equalsIgnoreCase(ultEspecial.getColor()) ){
                                mano.remove(i);
                                return normal;   
                            }


                        }
                    }
                }
            }
            if(!encontrado){
                for(int i=0; i<mano.size() && !encontrado; i++){
                    if(mano.get(i) instanceof Especial){ // Si es carta es de tipo Especial
                        Especial especial = (Especial) mano.get(i);
                        if(!especial.getTipo().equalsIgnoreCase("Comodin roba 4") && !especial.getTipo().equalsIgnoreCase("Comodin de color")){
                            if(especial.getColor().equalsIgnoreCase(ultEspecial.getColor())/*|| especial.getColor().equalsIgnoreCase(ultNormal.getColor())*/){
                                mano.remove(i);
                                return especial;                     
                            }                    
                        }
                    }
                }            
            }
            if(!encontrado){
                for(int i=0; i<mano.size() && !encontrado; i++){
                    if(mano.get(i) instanceof Especial){ // Si es carta es de tipo Normal
                        Especial especial = (Especial) mano.get(i);
                        if(especial.getTipo().equalsIgnoreCase("Comodin roba 4")){
                            mano.remove(i);
                            return especial;                        
                        }else if( especial.getTipo().equalsIgnoreCase("Comodin de color")){
                            especial.setColor("azul");
                            mano.remove(i);
                            return especial;
                        }
                    }
                }
            }
        }       
        return null;
    }

    /**
     * Robar carta
     * @param carta a añadir a la mano  
     */    
    @Override
    public void cogerCarta(Carta carta) {//la carta que debe de coger es de la pila de robar
        
        mano.add(carta);
        
    }

    /**
     * Obtener el nombre del jugador
     * @return nombre nombre del jugador
     */
    @Override
    public String getNombre() {
        return nombre;
    }

    /**
     * Establecer el nombre del jugador
     * @param nombre nombre del jugador
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtener la puntuación del jugador
     * @return puntuacion 
     */
    public int getPuntuacion() {
        return puntuacion;
    }

    /**
     * Establecer la puntuacion del jugador
     * @param puntuacion puntuacion del jugador
     */
    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    /**
     * Comprobar si el jugador tiene cartas
     * @return true si no tiene, false si tiene
     */    
    @Override
    public boolean tieneCartas() {
        return mano.isEmpty();
    }   
        
    /**
     * Calcula la puntuación total del jugador
     * @return puntuación total del jugador
     */    
    @Override
    public int calcularPuntuacion() {
        int puntos = 0;
        for(int i=0;i<mano.size();i++){            
            puntos = puntos+mano.get(i).getValor();
        }
        return puntos;
    }    
}
