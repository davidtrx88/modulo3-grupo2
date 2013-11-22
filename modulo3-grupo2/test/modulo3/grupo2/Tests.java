/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo3.grupo2;

import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author carmen
 */
public class Tests {
    
    
    @Test
    public void testGetNumeroCartas() {
        System.out.println("getNumeroCartas");
        Baraja instance = new Baraja("uno");
        int expResult = 108;
        int result = instance.getNumeroCartas();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetNumeroCarta(){
     System.out.println("getNumeroNumeroCarta");
     Especial carta = new Especial(50,"comodin de color");
     int expResult = -1;
     int result = carta.getNumero();
     assertEquals(expResult,result);
    
    }
    
    @Test
    public void testGetPalo(){
    
     System.out.println("GetPalo");
     Inglesa carta = new Inglesa(3,3,"corazones");
     String expResult = "corazones";
     String result = carta.getPalo();
     assertEquals(expResult,result);
    
    }

    @Test
    public void testGetPuntuacion(){
        System.out.println("GetPuntuacion");
        Repartidor repartidor = new Repartidor(); 

        Inglesa carta1 = new Inglesa(3,3,"corazones");
        Inglesa carta2 = new Inglesa(4,4,"picas");
        Inglesa carta3 = new Inglesa(11,10,"rombos");

        repartidor.cogerCarta(carta1);
        repartidor.cogerCarta(carta2);
        repartidor.cogerCarta(carta3);

        int expResult = 18;
        int result = repartidor.getPuntuacion();
        assertEquals(expResult,result);
    }
}
