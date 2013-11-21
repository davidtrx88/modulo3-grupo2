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
 */
public class JugadorCartaEspecial implements JugadorUno{
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
        mostrarCartasMano();
        Normal ultNormal = null;
        Especial ultEspecial = null;
        boolean encontrado=false;
        if(ultimaCarta instanceof Normal){
            ultNormal = (Normal)ultimaCarta;
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
                    if(mano.get(i) instanceof Normal){ // Si es carta es de tipo Normal
                        Normal normal = (Normal) mano.get(i);

                        if(normal.getColor().equalsIgnoreCase(ultNormal.getColor()) ){
                            mano.remove(i);
                            return normal;   
                        }
                    }
                }         
            }
            
        }else{
            ultEspecial = (Especial)ultimaCarta;
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

    @Override
    public void cogerCarta(Carta carta) {//la carta que debe de coger es de la pila de robar
        
        mano.add(carta);
        
    }

    /**
     * @return the nombre
     */
    @Override
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
    
    public void mostrarCartasMano(){
        System.out.println("Las cartas del jugador "+nombre+" son:");
        int j; //Mostramos las cartas desde el número 1 para utilizar el 0 como opción robar.
        for(int i=0;i<mano.size();i++){  
            j=i+1;
            Carta c = mano.get(i);
            if(c instanceof Normal){
                Normal normal = (Normal) c;
                System.out.println("   ("+j+"). "+normal.getNumero()+" "+normal.getColor());
            }
            else if(c instanceof Especial){
                Especial especial = (Especial) c;
                System.out.println("    ("+j+"). "+especial.getTipo()+" "+especial.getColor());
            }
        }
    }

    @Override
    public int calcularPuntuacion() {
        int puntos = 0;
        for(int i=0;i<mano.size();i++){            
            puntos = puntos+mano.get(i).getValor();
        }
        return puntos;
    }   
    
}
