/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo3.grupo2;

import java.util.Scanner;
import modulo3.grupo2.excepciones.ExcepcionJugadaNoValida;
import modulo3.grupo2.interfaces.JugadorUno;

/**
 *
 * @author Carmen M. Morillo Arias, David Cruz Toral, Mustafa Abdoun Bouali
 */
public class Partida {
        
    
    public void menuPrincipal(){
        System.out.println("Elige una opción...");
        System.out.println("1. Jugar al UNO.");
        System.out.println("2. Jugar al BlackJack.");
        System.out.println("3. Salir.");
    }
    
    
    public void juegaUno(){
        
        Uno juego = new Uno();        
        
        //Añadimos los jugadores
        System.out.println("¿Cuántos jugadores?");
        Scanner sc = new Scanner(System.in);
        int njugadores = sc.nextInt();
        
        Scanner sc2 = new Scanner(System.in);
        for(int i=0;i<njugadores;i++){
            System.out.println("Introduce el nombre del jugador "+i);
            String nombre = sc2.nextLine();
            
            JugadorReal jr = new JugadorReal(nombre,0);            
            juego.anadirJugador(jr);
        }
        
        //Si sólo hay un jugador, añadimos otros con distintas estrategias        
        if(njugadores == 1){            
            JugadorNumero jugador1 = new JugadorNumero("Jugador1",0);
            juego.anadirJugador(jugador1);
//            JugadorColor jugador2 = new JugadorColor("Jugador2",0);
//            juego.anadirJugador(jugador2);
//            JugadorCartaEspecial jugador3 = new JugadorCartaEspecial("Jugador3",0);
//            juego.anadirJugador(jugador3);
        }
        
        //Creamos la baraja, repartimos, mostramos la primera carta bocarriba, mostramos a cada
        //jugador real sus cartas.
        juego.crearBaraja();
        juego.repartir();
        juego.mostrarCartasJugador();        
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
                System.out.println("El turno es del jugador: "+juego.getTurno()+" roba carta!");
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
                    System.out.println("Has robado 2 cartas!");
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
                    System.out.println("Has robado 4 cartas!");
                    robasiguiente = -1;
                    jugadorAnteriorRobo = true;
                }
            }
            else {
                System.out.println("El turno es del jugador: "+juego.getTurno());
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
                            System.out.println("La carta jugada no es válida!!");
                            jugadorActual.cogerCarta(c);
                            ultimaCartaValida = false;
                        }
                    }                                        

                    if(ultimaCartaValida){ //La carta que lanzó es jugador es válida
                       if(c instanceof Especial){
                           Especial cespecial = (Especial) c;
                           if(cespecial.getTipo().equalsIgnoreCase("Comodin de color") ||cespecial.getTipo().equalsIgnoreCase("Comodin roba 4")){
                               System.out.println("Elige un color...");
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
                            System.out.println("La carta jugada no es válida!!");
                            jugadorActual.cogerCarta(c);
                            ultimaCartaValida = false;
                        }
                    }                                        

                    if(ultimaCartaValida){ //La carta que lanzó es jugador es válida
//                       if(c instanceof Especial){
//                           Especial cespecial = (Especial) c;
//                           if(cespecial.getTipo().equalsIgnoreCase("Comodin de color") ||cespecial.getTipo().equalsIgnoreCase("Comodin roba 4")){
//                               System.out.println("Elige un color...");
//                               String color = sc2.nextLine();
//                               cespecial.setColor(color);                          
//                           }
//                       }
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
    }
    
    public void juegaBlackJack(){
        
        Blackjack juego = new Blackjack();
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el nombre del jugador ");
        String nombre = sc.nextLine();

        JugadorRealBlackjack jb = new JugadorRealBlackjack(nombre);            
        juego.anadirJugador(jb);        
        
        //Poner aquí algo para que elija aleatoriamente al jugador repartidor
        
        Repartidor jr = new Repartidor();        
        juego.anadirJugador(jr);
        
        juego.repartir();
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
        
//        juego.calcularGanador();
    }
    
}
