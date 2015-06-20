package com.rzg.zombieland.comunes.misc;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

import org.junit.Test;

import com.rzg.zombieland.comunes.misc.Movimiento.Direccion;

public class MovimientoTest {

    @Test(expected = NullPointerException.class)
    public void testSinTipo() {
        new Movimiento(null);
    }

    @Test
    public void testOrdenamiento() {
        Movimiento m1 = new Movimiento(Direccion.ESTE);
        Movimiento m2 = new Movimiento(Direccion.ESTE);
        Movimiento m3 = new Movimiento(Direccion.ESTE);
        
        Movimiento [] listado = { m3, m2, m1 };
        Movimiento [] listadoOrdenado = { m1, m2, m3 };
        
        Arrays.sort(listado);
        assertArrayEquals(listadoOrdenado, listado);
    }
}
