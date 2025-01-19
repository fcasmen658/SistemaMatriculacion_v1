package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.Asignatura;

import javax.naming.OperationNotSupportedException;

public class Asignaturas {

    private int capacidad;
    private int tamano;
    private Asignatura[] coleccionAsignaturas;

    public Asignaturas(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.coleccionAsignaturas = new Asignatura[getCapacidad()];
        this.capacidad = capacidad;
        this.tamano = 0;
    }

    public Asignatura[] get() {
        return copiaProfundaAsignaturas();
    }

    private Asignatura[] copiaProfundaAsignaturas() {
        Asignatura[] copia = new Asignatura[getTamano()];
        for (int i = 0; i < getTamano(); i++) {
            copia[i] = coleccionAsignaturas[i];
        }
        return copia;
    }

    public int getTamano() {
        return tamano;
    }

    public int getCapacidad() {
        return capacidad;
    }


    public void insertar(Asignatura asignatura) throws OperationNotSupportedException {
        if (asignatura == null) {
            throw new NullPointerException("ERROR: No se puede insertar una asignatura nula.");
        }
        if (tamanoSuperado(getTamano())) {
            throw new OperationNotSupportedException("ERROR: La colección de asignaturas ha alcanzado su capacidad.");
        }
        int indice = buscarIndice(asignatura);
        if (tamanoSuperado(indice)) {
            coleccionAsignaturas[tamano] = new Asignatura(asignatura);
            tamano++;
        } else {
            throw new OperationNotSupportedException("ERROR: Ya existe una asignatura con ese código.");
        }
    }

    private boolean tamanoSuperado(int tamano) {
        return tamano >= getTamano();
    }

    private int buscarIndice(Asignatura asignatura) {
        if (asignatura == null) {
            throw new NullPointerException("ERROR: La asignatura no puede ser nula.");
        }
        int indice = -1;
        for (int i = 0; !tamanoSuperado(i) && indice == -1; i++) {
            if (coleccionAsignaturas[i].equals(asignatura)) {
                indice = i;
            }
        }
        return indice;
    }

    private boolean capacidadSuperada(int indice) {
        if (indice < 0) {
            throw new IllegalArgumentException("ERROR: El índice no puede ser negativo.");
        }
        return indice >= getCapacidad();
    }


    public Asignatura buscar(Asignatura asignatura) {
        if (asignatura == null) {
            throw new NullPointerException("ERROR: La asignatura no puede ser nula.");
        }
        int indice = buscarIndice(asignatura);
        if (tamanoSuperado(indice)) {
            return null;
        } else {
            return new Asignatura(coleccionAsignaturas[indice]);
        }
    }

    public void borrar(Asignatura asignatura) throws OperationNotSupportedException {
        if (asignatura == null) {
            throw new NullPointerException("ERROR: La asignatura no puede ser nula.");
        }
        int indice = buscarIndice(asignatura);
        if (indice == -1) {
            throw new OperationNotSupportedException("ERROR: La asignatura no se encuentra en la colección de asignaturas.");
        }
        for (int i = indice; !tamanoSuperado(i); i++) {
            coleccionAsignaturas[i] = coleccionAsignaturas[i + 1];
        }
        tamano--;
        desplazarUnaPosicionHaciaIzquierda(indice);
    }

    private void desplazarUnaPosicionHaciaIzquierda(int i) throws OperationNotSupportedException {
        if (tamanoSuperado(i)) {
            throw new OperationNotSupportedException("ERROR: No se puede desplazar una posición hacia la izquierda.");
        } else {
            for (int j = i; !tamanoSuperado(j); j++) {
                coleccionAsignaturas[j] = coleccionAsignaturas[j + 1];
            }
            tamano--;
        }
    }

}
