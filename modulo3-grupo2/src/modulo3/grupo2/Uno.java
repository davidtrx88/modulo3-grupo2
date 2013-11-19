/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo3.grupo2;

import java.util.LinkedList;
import modulo3.grupo2.interfaces.Jugador;
import modulo3.grupo2.interfaces.Juego;
import modulo3.grupo2.excepciones.ExcepcionJugadaNoValida;
import java.util.List;

/**
 *
 * @author Carmen M. Morillo Arias, David Cruz Toral, Mustafa Abdoun Bouali
 */
public class Uno implements Juego {
    
    private String turno;
    private Baraja baraja;
    private List<Jugador> jugadores;
    private Carta ultimaCarta;
    
    /**
     * Constructor
     */
    public Uno(){
        turno = "";        
        jugadores = new LinkedList<Jugador>();
        ultimaCarta = null;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }       

    /**
     * Crea la baraja con las cartas necesarias para el juego
     */
    @Override
    public void crearBaraja() {
        baraja = new Baraja("uno");
    }

    /**
     * Añade un jugador a la partida
     * @param jugador jugador a añadir
     */
    @Override
    public void anadirJugador(Jugador jugador) {
        jugadores.add(jugador);
    }

    /**
     * Elimina un jugador el juego
     * @param jugador jugador a eliminar
     */
    @Override
    public void eliminarJugador(Jugador jugador) {
        for(int i=0;i<jugadores.size();i++){
            if(jugadores.get(i).getNombre().equalsIgnoreCase(jugador.getNombre())){
                jugadores.remove(i);
                break;
            }
        }
    }
    
    public Jugador obtenerJugador(String nombre){
        for(int i=0;i<jugadores.size();i++){
            if(jugadores.get(i).getNombre().equalsIgnoreCase(nombre)){
                return jugadores.get(i);                
            }
        } 
        return null;
    }
    
    public int obtenerPosicionJugador(String nombre){
        for(int i=0;i<jugadores.size();i++){
            if(jugadores.get(i).getNombre().equalsIgnoreCase(nombre)){
                return i;
            }
        } 
        return -1;
    }    

    /**
     * Reparte las cartas para iniciar el juego
     */
    @Override
    public void repartir() {
        for(int j=0;j<8;j++){
            for(int i=0;i<jugadores.size();i++){
                Jugador jugador = jugadores.get(i);
                jugador.cogerCarta(baraja.getCarta());
            }        
        }
        ultimaCarta = baraja.getCarta();
    }
    
    public void mostrarUltimaCarta(){
        System.out.println("La úlima carta es:");              
        
        if(ultimaCarta instanceof Normal){
            Normal normal = (Normal) ultimaCarta;
            System.out.println("    "+normal.getNumero()+" "+normal.getColor());
        }
        else if(ultimaCarta instanceof Especial){
            Especial especial = (Especial) ultimaCarta;
            System.out.println("    "+especial.getTipo()+" "+especial.getColor());
        }                
    }

    /**
     * El jugador roba una carta de la baraja
     * @return carta que ha robado
     */
    @Override
    public Carta darCarta() {
        return baraja.getCarta();
    }

    @Override
    public void terminarJuego() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void guardarJuego() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void mostrarCartasJugador(){
        for(int i=0;i<jugadores.size();i++){
            Jugador jugador = jugadores.get(i);
            
            if(jugador instanceof JugadorReal){
                JugadorReal jr = (JugadorReal) jugador;
                jr.mostrarCartasMano();
            }
        }
    }
    
    /**
     * Comprueba que la carta jugada por un jugador es válida o no en función de la 
     * última carta que se jugó.
     * @param cartaJugador carta que lanza el jugador
     * @throws ExcepcionJugadaNoValida 
     */
    public void validarJugada(Carta cartaJugador) throws ExcepcionJugadaNoValida{
        if(ultimaCarta instanceof Especial){           
            if(cartaJugador instanceof Especial){
                Especial ultimaEspecial = (Especial) ultimaCarta;
                Especial jugadorEspecial = (Especial) cartaJugador;
                
                if(!ultimaEspecial.getTipo().equalsIgnoreCase(jugadorEspecial.getTipo())){
                    throw new ExcepcionJugadaNoValida();
                }
            }            
        }
        else {
            Normal ultimaNormal = (Normal) ultimaCarta;
            Normal jugadorNormal = (Normal) cartaJugador;
            
            if(ultimaNormal.getNumero() != jugadorNormal.getNumero() && !ultimaNormal.getColor().equalsIgnoreCase(jugadorNormal.getColor())){
                throw new ExcepcionJugadaNoValida();
            }
        }
    }
    
    public Carta getUltimaCarta(){
        return ultimaCarta;
    }
    
    public void setUltimaCarta(Carta c){
        ultimaCarta = c;
    }
        
    public void anadirCartaBaraja(Carta c){
        baraja.anadirCarta(c);
    }
    
    public void modificarTurno(Carta c, String nombreJugador){
        int posicionUltimoJugador = this.obtenerPosicionJugador(nombreJugador);
        
        if(posicionUltimoJugador != -1){
            if(c instanceof Especial){
                //Ver aquí que tipo de carta es y establecer el turno del siguiente
            }
        }
    }
    
}
