/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo3.grupo2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import modulo3.grupo2.interfaces.JugadorBlackjack;
import modulo3.grupo2.interfaces.JugadorUno;
import modulo3.grupo2.interfaces.Juego;
import modulo3.grupo2.excepciones.ExcepcionJugadaNoValida;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author Carmen M. Morillo Arias, David Cruz Toral, Mustafa Abdoun Bouali
 */
public class Uno implements Juego {
    
    private String turno;
    private Baraja baraja;
    private List<JugadorUno> jugadores;
    private Carta ultimaCarta;
    private int direccion; // 1=derecha, 2=izquierda
    
    /**
     * Constructor
     */
    public Uno(){
        turno = "";        
        jugadores = new LinkedList<JugadorUno>();
        ultimaCarta = null;  
        direccion = 1; 
    }

    public int getDireccion() {
        return direccion;
    }

    public void setDireccion(int direccion) {
        this.direccion = direccion;
    }
        

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    } 
    
    public void setPrimerTurno(){
        turno = jugadores.get(0).getNombre();
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
    public void anadirJugador(JugadorUno jugador) {
        jugadores.add(jugador);
    }

    /**
     * Elimina un jugador el juego
     * @param jugador jugador a eliminar
     */
    @Override
    public void eliminarJugador(JugadorUno jugador) {
        for(int i=0;i<jugadores.size();i++){
            if(jugadores.get(i).getNombre().equalsIgnoreCase(jugador.getNombre())){
                jugadores.remove(i);
                break;
            }
        }
    }
    
    public JugadorUno obtenerJugador(String nombre){
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
        System.out.println("Numero de jugadores: "+jugadores.size());
        for(int j=0;j<8;j++){
            for(int i=0;i<jugadores.size();i++){
                JugadorUno jugador = jugadores.get(i);
                jugador.cogerCarta(baraja.getCarta());
            }        
        }        
        ultimaCarta = baraja.getCarta();
        if(ultimaCarta instanceof Especial){
            Especial ce = (Especial) ultimaCarta;
            if(ce.getTipo().equalsIgnoreCase("Comodin roba 4")||ce.getTipo().equalsIgnoreCase("Comodin de color")){
                ce.setColor("rojo");
            }
            
        }
    }
    
    public void mostrarUltimaCarta(){
        System.out.println("La última carta es:");              
        
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
         
        try {
 
        Element juego = new Element("Juego");
        Document doc = new Document(juego);
        doc.setRootElement(juego);
        
        for(int i =0; i<jugadores.size();i++){
            Element jugador = new Element("Jugador");
          
            jugador.setAttribute(new Attribute("id", "0"+i));
            jugador.addContent(new Element("Nombre").setText(jugadores.get(i).getNombre()));
            jugador.addContent(new Element("Puntuacion").setText(""+ jugadores.get(i).calcularPuntuacion()));


            doc.getRootElement().addContent(jugador);


            // new XMLOutputter().output(doc, System.out);
            XMLOutputter xmlOutput = new XMLOutputter();

            // display nice nice
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(doc, new FileWriter("c:\\Users\\David\\Documents\\NetBeansProjects\\modulo3-grupo2\\file.xml"));
        }
        System.out.println("File Saved!");
      } catch (IOException io) {
        System.out.println(io.getMessage());
      }
    }
    
    public void mostrarCartasJugador(){
        for(int i=0;i<jugadores.size();i++){
            JugadorUno jugador = jugadores.get(i);
            
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
            if(cartaJugador instanceof Especial){ //Caso 1: especial - especial
                Especial ultimaEspecial = (Especial) ultimaCarta;
                Especial jugadorEspecial = (Especial) cartaJugador;
                
                //Si ultimaCarta es comodin, entonces el color ha de coincidir.
                if(ultimaEspecial.getTipo().equalsIgnoreCase("Comodin roba 4")||ultimaEspecial.getTipo().equalsIgnoreCase("Comodin de color")){
                    if(!ultimaEspecial.getColor().equalsIgnoreCase(jugadorEspecial.getColor())){
                        throw new ExcepcionJugadaNoValida();
                    }                
                }
                else{ //Si la carta no es comodín, entonces el tipo ha de coincidir o el color
                    if(!jugadorEspecial.getTipo().equalsIgnoreCase("Comodin roba 4") && !jugadorEspecial.getTipo().equalsIgnoreCase("Comodin de color")){
                        if(!ultimaEspecial.getTipo().equalsIgnoreCase(jugadorEspecial.getTipo())){
                            if(!ultimaEspecial.getColor().equalsIgnoreCase(jugadorEspecial.getColor())){
                                throw new ExcepcionJugadaNoValida();
                            }                            
                        }                    
                    }                    
                }
            }
            else{ //Caso 2: especial - normal
                Especial ultimaEspecial = (Especial) ultimaCarta;
                Normal jugadorNormal = (Normal) cartaJugador;
                
                if(!ultimaEspecial.getColor().equalsIgnoreCase(jugadorNormal.getColor())){
                    throw new ExcepcionJugadaNoValida();
                }                            
            }
        }
        else if(cartaJugador instanceof Especial){ //Caso 3: normal - especial
            Normal ultimaNormal = (Normal) ultimaCarta;
            Especial jugadorEspecial = (Especial) cartaJugador;
            //Si ultimaCarta es normal y la lanzada no es comodín, entonces han de coincidir los colores
            if(!jugadorEspecial.getTipo().equalsIgnoreCase("Comodin roba 4")&&!jugadorEspecial.getTipo().equalsIgnoreCase("Comodin de color")){
                if(!jugadorEspecial.getColor().equalsIgnoreCase(ultimaNormal.getColor())){                    
                    throw new ExcepcionJugadaNoValida();
                }
            }
        }                        
        else { //Caso 4: normal - normal
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
    
    public String jugadorDerecha(String nombreJugador){
        int posicionJugadorActual = this.obtenerPosicionJugador(nombreJugador);
        
        if(posicionJugadorActual == (jugadores.size()-1)){ //Si es el último, quien está a su derecha es el primero     
            return jugadores.get(0).getNombre();            
        }
        else{
            return jugadores.get(posicionJugadorActual+1).getNombre();
        }
    }
    
    public String jugadorIzquierda(String nombreJugador){
        int posicionJugadorActual = this.obtenerPosicionJugador(nombreJugador);
                
        if(posicionJugadorActual == 0){ //Si es el primero, quien está a su izquierda es el último
            return jugadores.get(jugadores.size()-1).getNombre();
        }
        else{
            return jugadores.get(posicionJugadorActual-1).getNombre();     
        }            
        
    }
    
    //Devuelve -1 si no tiene que robar, o el numero de cartas que tiene que robar
    public int modificarTurno(Carta c, String nombreJugador){         
        if(c instanceof Especial){
            //Ver aquí que tipo de carta es y establecer el turno del siguiente
            Especial cespecial = (Especial) c;
            if(cespecial.getTipo().equalsIgnoreCase("Salta turno")){
                if(direccion == 1){                    
                    turno = jugadorDerecha(jugadorDerecha(nombreJugador));
                    return -1;
                }
                else{
                    turno = jugadorIzquierda(jugadorIzquierda(nombreJugador));
                    return -1;
                }
            }
            else if(cespecial.getTipo().equalsIgnoreCase("Cambio de sentido")){
                if(direccion == 1){                      
                    if(jugadores.size()>2){
                       turno = jugadorIzquierda(nombreJugador);                    
                    }                    
                    direccion = 2;
                    return -1;
                    
                }
                else{   
                    if(jugadores.size()>2){
                        turno = jugadorDerecha(nombreJugador);                    
                    }                   
                    direccion = 1;
                    return -1;
                }                
            }
            else if(cespecial.getTipo().equalsIgnoreCase("Roba 2")){
                if(direccion == 1){                    
                    turno = jugadorDerecha(nombreJugador);                    
                    return 2;
                }
                else{                    
                    turno = jugadorIzquierda(nombreJugador);
                    return 2;
                }                
            }
            else if(cespecial.getTipo().equalsIgnoreCase("Comodin de color")){
                if(direccion == 1){                    
                    turno = jugadorDerecha(nombreJugador);                    
                    return -1;
                }
                else{                    
                    turno = jugadorIzquierda(nombreJugador);
                    return -1;
                }                
            }
            else if(cespecial.getTipo().equalsIgnoreCase("Comodin roba 4")){
                if(direccion == 1){                    
                    turno = jugadorDerecha(nombreJugador);                    
                    return 4;
                }
                else{                    
                    turno = jugadorIzquierda(nombreJugador);
                    return 4;
                }                
            }            
        }
        else{ //Si la carta es normal
            if(direccion == 1){                    
                turno = jugadorDerecha(nombreJugador);                    
                return -1;
            }
            else{                    
                turno = jugadorIzquierda(nombreJugador);
                return -1;
            }            
        }
        return -1;        
    }
    
    public void mostrarRankingPuntuaciones(){
//        Map<String, Integer> ranking = new HashMap();
        for(int i=0;i<jugadores.size();i++){
//            ranking.put(jugadores.get(i).getNombre(),jugadores.get(i).calcularPuntuacion());
            System.out.println("Jugador: "+jugadores.get(i).getNombre()+" "+jugadores.get(i).calcularPuntuacion()+" puntos.");
            
        }
    }

    @Override
    public void anadirJugador(JugadorBlackjack jugador) {}
}
