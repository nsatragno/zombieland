package com.rzg.zombieland.cliente.misc;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

public class RutaImagen {
    
    public static Image get(final String pathAndFileName) {
        final URL url = Thread.currentThread().getContextClassLoader().getResource(pathAndFileName);
        if (url == null)
            System.out.println("asd");
        return Toolkit.getDefaultToolkit().getImage(url);
    }
}
