package org.temario.tema2;

// Ejemplo7
public class UsuarioDB extends Thread {
    private final PoolConexionesDB conexiones;

    public UsuarioDB(String nombre, PoolConexionesDB conexiones) {
        super(nombre);
        this.conexiones = conexiones;
    }

    @Override
    public void run() {
        conexiones.conectar();
    }
}