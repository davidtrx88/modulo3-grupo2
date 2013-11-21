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
 */
public class JugadorReal implements JugadorUno{
    private String nombre;
    private int puntuacion;
    private ArrayList<Carta> mano;    
    
    /**
     *
     */
    public JugadorReal(){
        nombre ="";
        puntuacion =0;
        mano = new ArrayList<Carta>();        
    }
    
    /**
     *
     * @param n
     * @param p
     */
    public JugadorReal(String n, int p){
        this.nombre = n;
        this.puntuacion = p;
        mano = new ArrayList<Carta>();
    }

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

    @Override
    public void cogerCarta(Carta carta) {
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
    
    public boolean comprobarGanador(){
        return mano.isEmpty();
    }

    @Override
    public boolean tieneCartas() {        
        return mano.isEmpty();
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
