package com.rzg.zombieland.cliente.interfaz;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * Listener para el método shown de un component.
 * @author nicolas
 *
 */
public abstract class ComponentShownListener implements ComponentListener {

    @Override
    public void componentResized(ComponentEvent e) { }

    @Override
    public void componentMoved(ComponentEvent e) { }

    @Override
    public void componentHidden(ComponentEvent e) { }

}
