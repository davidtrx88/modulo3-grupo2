/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo3.grupo2;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import modulo3.grupo2.excepciones.ExcepcionJugadaNoValida;
import modulo3.grupo2.interfaces.JugadorUno;

/**
 *
 * @author Carmen M. Morillo Arias, David Cruz Toral, Mustafa Abdoun Bouali
 */
public class Partida {

    private static final String Bundle_Name = Partida.class.getPackage().getName()+".MessagesBundle";
    private static ResourceBundle message = ResourceBundle.getBundle(Bundle_Name);

    public void menuPrincipal(){
        System.out.println();
        System.out.println("--------------------");
        System.out.println(message.getString("bv"));
        System.out.println("--------------------");
        System.out.println();
        System.out.println(message.getString("opc"));
        System.out.println(message.getString("juno"));
        System.out.println(message.getString("jbj"));
        System.out.println(message.getString("salir"));       
    }
    
    
    public void juegaUno(){
        
        Uno juego = new Uno();        

        System.out.println();
        System.out.println("--------------------");
        System.out.println("|       UNO        |");
        System.out.println("--------------------");
        System.out.println();
        
        
        //Añadimos los jugadores
        System.out.println(message.getString("nj"));
        Scanner sc = new Scanner(System.in);
        int njugadores = sc.nextInt();
        
        Scanner sc2 = new Scanner(System.in);
        for(int i=0;i<njugadores;i++){
            System.out.println(message.getString("nomj")+i);
            String nombre = sc2.nextLine();
            
            JugadorReal jr = new JugadorReal(nombre,0);            
            juego.anadirJugador(jr);
        }
        
        //Si sólo hay un jugador, añadimos otros con distintas estrategias        
        if(njugadores == 1){            
            JugadorNumero jugador1 = new JugadorNumero("Jugador1",0);
            juego.anadirJugador(jugador1);
            JugadorColor jugador2 = new JugadorColor("Jugador2",0);
            juego.anadirJugador(jugador2);
            JugadorCartaEspecial jugador3 = new JugadorCartaEspecial("Jugador3",0);
            juego.anadirJugador(jugador3);
        }
        
        //Creamos la baraja, repartimos, mostramos la primera carta
        juego.crearBaraja();
        juego.repartir();        
        juego.setPrimerTurno();
        
        //Comienza el juego        
        boolean terminado = false;
        boolean ultimaCartaValida = true;
        int robasiguiente = -1;
        boolean jugadorAnteriorRobo = false;
        
        //Si la primera carta es especial y el primer jugador tiene que robar, entonces modificamos
        //robasiguiente para que robe las cartas necesarias, y dejamos como turno el del primer jugador.
        if(juego.getUltimaCarta() instanceof Especial){            
            robasiguiente = juego.modificarTurno(juego.getUltimaCarta(), juego.getTurno());
            Especial primeraCarta = (Especial) juego.getUltimaCarta();
            if(!primeraCarta.getTipo().equalsIgnoreCase("Cambio de sentido")){
                juego.setPrimerTurno();
            }            
        }        
        while(!terminado){
            juego.mostrarUltimaCarta();            
            
            if(robasiguiente != -1){ //Tiene que robar y pasar turno
                System.out.println(message.getString("tj")+juego.getTurno()+message.getString("rb"));
                JugadorUno jugadorActual = juego.obtenerJugador(juego.getTurno());
                
                if(robasiguiente == 2){
                    jugadorActual.cogerCarta(juego.darCarta());
                    jugadorActual.cogerCarta(juego.darCarta());
                    
                    if(juego.getDireccion() == 1){
                        juego.setTurno(juego.jugadorDerecha(jugadorActual.getNombre()));
                    }
                    else{
                        juego.setTurno(juego.jugadorIzquierda(jugadorActual.getNombre()));
                    }
                    System.out.println(message.getString("cr2"));
                    robasiguiente = -1;
                    jugadorAnteriorRobo = true;
                }
                else if(robasiguiente == 4){
                    jugadorActual.cogerCarta(juego.darCarta());
                    jugadorActual.cogerCarta(juego.darCarta());
                    jugadorActual.cogerCarta(juego.darCarta());
                    jugadorActual.cogerCarta(juego.darCarta());
                    if(juego.getDireccion() == 1){
                        juego.setTurno(juego.jugadorDerecha(jugadorActual.getNombre()));
                    }
                    else{
                        juego.setTurno(juego.jugadorIzquierda(jugadorActual.getNombre()));
                    }
                    System.out.println(message.getString("cr4"));
                    robasiguiente = -1;
                    jugadorAnteriorRobo = true;
                }
            }
            else {
                System.out.println(message.getString("tj")+juego.getTurno());
                JugadorUno jugadorActual = juego.obtenerJugador(juego.getTurno());
                
                

                if(jugadorActual instanceof JugadorReal){
                    Carta c = jugadorActual.realizarJugada(juego.getUltimaCarta());

                    if(c == null){ //Quiere robar
                        jugadorActual.cogerCarta(juego.darCarta());
                        ultimaCartaValida = false;                    
                    }
                    else{
                        try {
                            juego.validarJugada(c);
                            ultimaCartaValida = true;
                        } catch (ExcepcionJugadaNoValida ex) {
                            System.out.println(message.getString("nv"));
                            jugadorActual.cogerCarta(c);
                            ultimaCartaValida = false;
                        }
                    }                                        

                    if(ultimaCartaValida){ //La carta que lanzó es jugador es válida
                       if(c instanceof Especial){
                           Especial cespecial = (Especial) c;
                           if(cespecial.getTipo().equalsIgnoreCase("Comodin de color") ||cespecial.getTipo().equalsIgnoreCase("Comodin roba 4")){
                               System.out.println(message.getString("ec"));
                               String color = sc2.nextLine();
                               cespecial.setColor(color);                          
                           }
                       }
                       juego.anadirCartaBaraja(juego.getUltimaCarta());
                       juego.setUltimaCarta(c);   
                       robasiguiente = juego.modificarTurno(c, jugadorActual.getNombre());
                       if(jugadorActual.tieneCartas() == true){                           
                           terminado = true;
                       }
                    }                                
                } 
                else{                    
                    Carta c = jugadorActual.realizarJugada(juego.getUltimaCarta());
                    if(c == null){ //Quiere robar
                        jugadorActual.cogerCarta(juego.darCarta());
                        ultimaCartaValida = false;                    
                    }
                    else{
                        try {
                            juego.validarJugada(c);
                            ultimaCartaValida = true;
                        } catch (ExcepcionJugadaNoValida ex) {
                            System.out.println(message.getString("nv"));
                            jugadorActual.cogerCarta(c);
                            ultimaCartaValida = false;
                        }
                    }                                        

                    if(ultimaCartaValida){ //La carta que lanzó es jugador es válida
                       juego.anadirCartaBaraja(juego.getUltimaCarta());
                       juego.setUltimaCarta(c);   
                       robasiguiente = juego.modificarTurno(c, jugadorActual.getNombre());
                       if(jugadorActual.tieneCartas() == true){                           
                           terminado = true;
                       }
                    }                                                                       
                }
            }
        }        
        juego.mostrarRankingPuntuaciones();

        System.out.println("¿Quiere guardar el resultado?");
        
        Scanner sg = new Scanner(System.in);
        String respuesta = sg.nextLine();
        if (respuesta.equalsIgnoreCase("si")||respuesta.equalsIgnoreCase("yes")){
            juego.guardarJuego();
        }
    }
    
    public void juegaBlackJack(){
        
        Blackjack juego = new Blackjack();

        System.out.println();
        System.out.println("--------------------");
        System.out.println("|      BLACKJACK    |");
        System.out.println("--------------------");
        System.out.println();
        
        
        Scanner sc = new Scanner(System.in);
        System.out.println(message.getString("nomj"));
        String nombre = sc.nextLine();

        JugadorRealBlackjack jb = new JugadorRealBlackjack(nombre);            
        juego.anadirJugador(jb);                        
        
        juego.escogerRepartidor();
        
        juego.repartir();
        
        if(juego.obtenerJugadorRepartidor() instanceof Repartidor){
            Repartidor jr = (Repartidor) juego.obtenerJugadorRepartidor();
            jr.mostrarPrimeraCarta();

            boolean jbtermina = false;

            while(!jbtermina){

                if(jb.realizarJugada()){
                    jb.cogerCarta(juego.darCarta());                
                }
                else{                
                    jbtermina = true;
                }
            }

            boolean jrtermina = false;

            while(!jrtermina){

                if(jr.realizarJugada()){
                    jr.cogerCarta(juego.darCarta());
                    jr.getPuntuacion();
                }
                else{
                    jrtermina = true;
                }
            }
            
        }
        else{
            RepartidorAgresivo jr = (RepartidorAgresivo) juego.obtenerJugadorRepartidor();            
            jr.mostrarPrimeraCarta();

            boolean jbtermina = false;

            while(!jbtermina){

                if(jb.realizarJugada()){
                    jb.cogerCarta(juego.darCarta());                
                }
                else{                
                    jbtermina = true;
                }
            }

            boolean jrtermina = false;

            while(!jrtermina){

                if(jr.realizarJugada()){
                    jr.cogerCarta(juego.darCarta());
                    jr.getPuntuacion();
                }
                else{
                    jrtermina = true;
                }
            }            
        }        
        
        juego.calcularGanador();
        
        System.out.println("¿Quiere guardar el resultado?");
        Scanner sg = new Scanner(System.in);
        String respuesta = sg.nextLine();
        if (respuesta.equalsIgnoreCase("si")||respuesta.equalsIgnoreCase("yes")){
            juego.guardarJuego();
        }
    }
    
    /**
     * Método para cambiar la localización
     * @param locale localización
     */
    public static void setLocale(Locale locale){
        message = ResourceBundle.getBundle(Bundle_Name,locale);
    }
    
    /**
     * Método para consultar un término
     * @param key término
     * @return término en otro idioma
     */
    public static String getString(String key){
        return message.getString(key);
    }
    
}
