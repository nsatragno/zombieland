package com.rzg.zombieland.comunes.comunicacion;



/**
 * Contiene los datos que se envían para identificar a un jugador a través de la red.
 * @author nicolas
 *
 */
public class POJORegistro extends Enviable {
    // El nombre del jugador (el_groso_de_vl_99).
    private String nombre;
    
    // La clave del jugador.
    private String clave;
    
    // Pregunta y respuesta secretas de seguridad.
    private String preguntaSecreta;
    private String respuestaSecreta;
    
    public POJORegistro(String nombre,
                       String clave,
                       String preguntaSecreta,
                       String respuestaSecreta) {
        this.nombre = nombre;
        this.clave = clave;
        this.preguntaSecreta = preguntaSecreta;
        this.respuestaSecreta = respuestaSecreta;
    }
    
    public String getNombre() {
        return nombre;
    }

    public String getClave() {
        return clave;
    }

    public String getPreguntaSecreta() {
        return preguntaSecreta;
    }

    public String getRespuestaSecreta() {
        return respuestaSecreta;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof POJORegistro)) return false;
        POJORegistro otro = (POJORegistro)obj;
        return nombre.equals(otro.nombre) &&
               clave.equals(otro.clave) &&
               preguntaSecreta.equals(otro.preguntaSecreta) &&
               respuestaSecreta.equals(otro.respuestaSecreta);
    }
}
