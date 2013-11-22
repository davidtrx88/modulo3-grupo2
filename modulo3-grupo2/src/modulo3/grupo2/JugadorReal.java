/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modulo3.grupo2;

import java.util.ArrayList;
import java.util.Scanner;
import modulo3.grupo2.interfaces.JugadorUno;

/**
 *
 * @author Carmen M. Morillo Arias, David Cruz Toral, Mustafa Abdoun Bouali
 * 
 * Implementación del jugador real para el juego UNO
 */
public class JugadorReal implements JugadorUno{
    private String nombre;
    private int puntuacion;
    private ArrayList<Carta> mano;    
    
    /**
     * Constructor por defecto
     */
    public JugadorReal(){
        nombre ="";
        puntuacion =0;
        mano = new ArrayList<Carta>();        
    }
    
    /**
     * Constructor para JugadorCartaEspecial
     * @param nombre nombre del jugador
     * @param puntuacion puntuacion del jugador
     */
    public JugadorReal(String nombre, int puntuacion){
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
    public Carta realizarJugada(Carta ultimaCarta) {             
        Scanner sc = new Scanner(System.in);
        System.out.println("¿Qué carta quieres jugar? (Elige un número, 0 para robar)");
        mostrarCartasMano();
        int poscarta; 
        boolean cartaValida = false;       
        int max = 0;
        
        do{
            poscarta = sc.nextInt();            
            if(poscarta == 0){
                return null;
            }            
            else if(poscarta <= mano.size()){                
                cartaValida = true;
                Carta c = mano.get(poscarta-1);
                mano.remove(poscarta-1);
                return c;
            } 
            else{
                System.out.println("No tienes tantas cartas! Escoge un número válido...");
            }
        }while(!cartaValida); 
        
        return null;
    }

    /**
     * Robar carta
     * @param carta a añadir a la mano  
     */    
    @Override
    public void cogerCarta(Carta carta) {
         mano.add(carta);
    }

    /**
     * Obtener el nombre del jugador
     * @return nombre nombre del jugador
     */
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
     * Muestra las cartas que tiene el jugador en la jugada actual
     */    
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
                System.out.println("   ("+j+"). "+especial.getTipo()+" "+especial.getColor());
            }
        }
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
