package com.rzg.zombieland.comunes.comunicacion;

import com.rzg.zombieland.comunes.misc.Movimiento;


/**
 * Orden de movimiento enviada por un cliente al servidor.
 * @author nicolas
 *
 */
public class Comando extends PeticionCliente {
    public Comando(String bytes) {
        super(bytes);
        // TODO deserealizar.
    }

    // Indica el movimiento que el jugador realizó.
    private Movimiento movimiento;
    
    @Override
    public String serializar() {
        // TODO implementar.
        return null;
    }
}
