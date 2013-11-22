/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo3.grupo2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import modulo3.grupo2.interfaces.Juego;
import java.util.List;
import modulo3.grupo2.interfaces.JugadorBlackjack;
import modulo3.grupo2.interfaces.JugadorUno;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;


/**
 *
 * @author Carmen M. Morillo Arias, David Cruz Toral, Mustafa Abdoun Bouali
 * 
 * Formalizacion de la clase Blackjack
 */
public class Blackjack implements Juego{
    
    private Baraja baraja;
    private List<JugadorBlackjack> jugadores;
    
    /**
     * Constructor de la clase Blackjack
     */
    public Blackjack() {
        jugadores = new LinkedList<JugadorBlackjack>();
        crearBaraja();        
    }
          
    /**
     * Crea la baraja con las cartas necesarias para el juego
     */
    @Override
    public void crearBaraja() {
        baraja = new Baraja("blackjack");
    }

    /**
     * Añade un jugador a la partida
     * @param jugador jugador a añadir
     */    
    @Override
    public void anadirJugador(JugadorBlackjack jugador) {
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

    /**
     * Reparte las cartas para iniciar el juego
     */    
    @Override
    public void repartir() {
        for(int j=0;j<2;j++){
            for(int i=0;i<jugadores.size();i++){
                JugadorBlackjack jugador = jugadores.get(i);
                jugador.cogerCarta(baraja.getCarta());
            }        
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

    /**
     * guarda la partida

     */
    @Override
    public void guardarJuego() {
         
        try {
 
        Element juego = new Element("Juego");
        Document doc = new Document(juego);
//        doc.setRootElement(juego);
        
        for(int i =0; i<jugadores.size();i++){
            Element jugador = new Element("Jugador");
          
            jugador.setAttribute(new Attribute("id", "0"+i));
            jugador.addContent(new Element("Nombre").setText(jugadores.get(i).getNombre()));
            jugador.addContent(new Element("Puntuacion").setText(""+ jugadores.get(i).getPuntuacion()));


            doc.getRootElement().addContent(jugador);


            // new XMLOutputter().output(doc, System.out);
            XMLOutputter xmlOutput = new XMLOutputter();

            // display nice nice
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(doc, new FileWriter("file.xml"));
        }
        System.out.println("File Saved!");
      } catch (IOException io) {
        System.out.println(io.getMessage());
      }
    }

    /**
     * Método sin implementación para esta clase
     * @param jugador JugadorUno
     */
    @Override
    public void anadirJugador(JugadorUno jugador) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * Calcula quién fué el ganador del juego y lo muestra por pantalla.
     */
    public void calcularGanador(){
        int puntuacionjb = jugadores.get(0).getPuntuacion();
        int puntuacionjr = jugadores.get(1).getPuntuacion();   
        
        if(jugadores.get(1) instanceof Repartidor){
            Repartidor r = (Repartidor) jugadores.get(1);
            System.out.println("Las cartas del jugador "+r.getNombre()+" son:");
            r.mostrarCartas();            
        }
        else{
            RepartidorAgresivo r = (RepartidorAgresivo) jugadores.get(1);
            System.out.println("Las cartas del jugador "+r.getNombre()+" son:");
            r.mostrarCartas();                    
        }
        
        if(puntuacionjb != puntuacionjr){
            if(puntuacionjb == 21){
                System.out.println("Enhorabuena! "+jugadores.get(0).getNombre()+" has ganado!");
            }
            else if(puntuacionjr == 21){
                System.out.println("Lo siento, "+jugadores.get(1).getNombre()+" ha ganado :(");
            }
            else if(puntuacionjr < 21 && puntuacionjb < 21){ //Si la puntuación de ambos <21 gana el que más se acerque
                if(puntuacionjr > puntuacionjb){
                    System.out.println("Lo siento, "+jugadores.get(1).getNombre()+" ha ganado :(");
                }
                else{
                    System.out.println("Enhorabuena! "+jugadores.get(0).getNombre()+" has ganado!");
                }
            }else if(puntuacionjr > 21 && puntuacionjb > 21){ //Si la puntuacion de ambos >21 gana el que más se acerque
                if(puntuacionjr < puntuacionjb){
                    System.out.println("Lo siento, "+jugadores.get(1).getNombre()+" ha ganado :(");
                }
                else{
                    System.out.println("Enhorabuena! "+jugadores.get(0).getNombre()+" has ganado!");
                }                
            }
            else if(puntuacionjr > 21 && puntuacionjb < 21){
                System.out.println("Enhorabuena! "+jugadores.get(0).getNombre()+" has ganado!");
            }
            else if(puntuacionjr < 21 && puntuacionjb > 21){
                System.out.println("Lo siento, "+jugadores.get(1).getNombre()+" ha ganado :(");
            }
        }
        else{ //El repartidor y el jugador tienen la misma puntuación
            System.out.println("Lo siento, "+jugadores.get(1).getNombre()+" ha ganado :(");
        }
    }
    
    /**
     * Método aleatorio que escoge un jugador de uno de los dos tipos de repartidor
     */
    public void escogerRepartidor(){
        int numjugador = (int) (Math.random()*2+1);
        if(numjugador == 1){
            Repartidor jr = new Repartidor();        
            anadirJugador(jr);              
        }
        else{
            RepartidorAgresivo jr = new RepartidorAgresivo();        
            anadirJugador(jr);                    
        }    
    }
    
    /**
     * Devuelve el jugador repartidor
     * @return jugador repartidor
     */
    public JugadorBlackjack obtenerJugadorRepartidor(){
        return jugadores.get(1);
    }
}
