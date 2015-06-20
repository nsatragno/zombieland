package com.rzg.zombieland.comunes.misc;

/**
 * Representa los movimientos que puede realizar un personaje. 
 * @author nicolas
 *
 */
public class Movimiento implements Comparable<Movimiento> {
    /**
     * El tipo de movimiento.
     * @author nicolas
     *
     */
	public enum Tipo {
		/**
		 * Movimiento hacia «arriba». 
		 */
		NORTE(0, 1),
		/**
		 * Movimiento hacia la derecha.
		 */
		ESTE(1, 0),
		/**
		 * Movimiento hacia «abajo».
		 */
		SUR(0, -1),
		/**
		 * Movimiento hacia la izquierda.
		 */
		OESTE(-1, 0),
        /**
         * No se mueve.
         */
		NINGUNO(0, 0);
		
		private int dX;
		
        private int dY;

        private Tipo(int dX, int dY) {
		    this.dX = dX;
		    this.dY = dY;
		}
        
        public int getDX() {
            return dX;
        }
        
        public int getDY() {
            return dY;
        }
	}

    public static final Movimiento NINGUNO = new Movimiento(0, Tipo.NINGUNO);

	// Tiempo en el que se recibe la orden de movimiento, para poder determinar el orden de estos.
	private long tiempoDeMovimientoMillis;
	
	// El tipo de movimiento.
	private Tipo tipo;
	
	public Movimiento(Tipo tipo) {
	    this(System.currentTimeMillis(), tipo);
	}
	
	private Movimiento(long tiempo, Tipo tipo) {
	    if (tipo == null)
	        throw new NullPointerException("El tipo de movimiento no puede ser null");
	    this.tiempoDeMovimientoMillis = tiempo;
	    this.tipo = tipo;
    }
	
	@Override
	public int compareTo(Movimiento o) {
	    return Long.compare(tiempoDeMovimientoMillis, o.tiempoDeMovimientoMillis);
	}

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + (int) (tiempoDeMovimientoMillis ^ (tiempoDeMovimientoMillis >>> 32));
        result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Movimiento other = (Movimiento) obj;
        if (tiempoDeMovimientoMillis != other.tiempoDeMovimientoMillis)
            return false;
        if (tipo != other.tipo)
            return false;
        return true;
    }

    /**
     * @param coordenada - la coordenada que se va a mover.
     * @return una nueva coordenada, desplazada según el movimiento.
     */
    public Coordenada mover(Coordenada coordenada) {
        return new Coordenada(coordenada.getX() + tipo.getDX(), coordenada.getY() + tipo.getDY());
    }
}
