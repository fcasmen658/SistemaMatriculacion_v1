package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;

import javax.naming.OperationNotSupportedException;

public class CiclosFormativos {
    private final CicloFormativo[] coleccionCiclosFormativos;
    private int tamano = 0;
    private int capacidad = 10;

    public CiclosFormativos(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        this.coleccionCiclosFormativos = new CicloFormativo[capacidad];
    }

    public CicloFormativo[] get() {
        return copiaProfundaCiclosFormativos();
    }

    private CicloFormativo[] copiaProfundaCiclosFormativos() {
        CicloFormativo[] copiaCiclosFormativos = new CicloFormativo[capacidad];
        for (int i = 0; !tamanoSuperado(i); i++) {
            copiaCiclosFormativos[i] = new CicloFormativo(coleccionCiclosFormativos[i]);
        }
        return copiaCiclosFormativos;
    }

    public int getTamano() {
        return tamano;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void insertar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: El ciclo formativo no puede ser nulo.");
        }
        if (tamanoSuperado(getTamano())) {
            throw new OperationNotSupportedException("ERROR: La colección de ciclos formativos ha alcanzado su capacidad.");
        }

        for (int i = 0; i < tamano; i++) {
            if (coleccionCiclosFormativos[i].equals(cicloFormativo)) {
                throw new OperationNotSupportedException("ERROR: El ciclo formativo ya existe en la colección.");
            }
        }
        coleccionCiclosFormativos[tamano] = new CicloFormativo(cicloFormativo);
        tamano++;
    }

    private int buscarIndice(CicloFormativo cicloFormativo) {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: El ciclo formativo no puede ser nulo.");
        }
        int indice = -1;
        for (int i = 0; !tamanoSuperado(i) && indice == -1; i++) {
            if (coleccionCiclosFormativos[i].equals(cicloFormativo)) {
                indice = i;
            }
        }
        return indice;
    }

    private boolean tamanoSuperado(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("ERROR: El índice debe ser mayor o igual que cero.");
        }
        if (i >= getTamano()) {
            throw new IllegalArgumentException("ERROR: El índice debe ser menor o igual que la capacidad.");
        }
        return i == getTamano();
    }

    private boolean capacidadSuperada(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("ERROR: El índice debe ser mayor o igual que cero.");
        }
        if (i >= getCapacidad()) {
            throw new IllegalArgumentException("ERROR: El índice debe ser menor o igual que la capacidad.");
        }
        return i == getCapacidad();
    }

    public CicloFormativo buscar(CicloFormativo cicloFormativo) {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: El ciclo formativo no puede ser nulo.");
        }
        int indice = buscarIndice(cicloFormativo);
        if (indice == -1) {
            throw new IllegalArgumentException("ERROR: El ciclo formativo no existe.");
        }
        return coleccionCiclosFormativos[indice];
    }

    public void borrar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: El ciclo formativo no puede ser nulo.");
        }
        int indice = buscarIndice(cicloFormativo);
        if (indice == -1) {
            throw new OperationNotSupportedException("ERROR: El ciclo formativo no existe.");
        }
        for (int i = indice; !tamanoSuperado(i); i++) {
            coleccionCiclosFormativos[i] = coleccionCiclosFormativos[i + 1];
        }
        desplazarUnaPosicionHaciaIzquierda(indice);
        tamano--;
    }

    private void desplazarUnaPosicionHaciaIzquierda(int i) throws OperationNotSupportedException {
        if (tamanoSuperado(i)) {
            throw new OperationNotSupportedException("ERROR: No se puede desplazar una posición hacia la izquierda.");
        } else {
            for (int j = i; !tamanoSuperado(j); j++) {
                coleccionCiclosFormativos[j] = coleccionCiclosFormativos[j + 1];
            }
            tamano--;
        }
    }

}
