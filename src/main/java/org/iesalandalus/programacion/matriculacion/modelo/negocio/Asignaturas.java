package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;

import javax.naming.OperationNotSupportedException;

public class Asignaturas {

    private int capacidad;
    private int tamano;
    private Asignatura[] coleccionAsignaturas;

    public Asignaturas(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        coleccionAsignaturas = new Asignatura[getCapacidad()];
    }

    public Asignatura[] get() {
        return copiaProfundaAsignaturas();
    }

    private Asignatura[] copiaProfundaAsignaturas() {
        Asignatura[] copia = new Asignatura[getTamano()];
        for (int i = 0; !tamanoSuperado(i); i++) {
            copia[i] = new Asignatura(coleccionAsignaturas[i]);
        }
        return copia;
    }

    private int buscarIndice(Asignatura asignatura) {
        if (asignatura == null) {
            throw new NullPointerException("ERROR: La asignatura no puede ser nula.");
        }
        int indice = 0;
        boolean asignaturaEncontrada = false;
        while (!tamanoSuperado(indice) && !asignaturaEncontrada) {
            if (get()[indice].equals(asignatura)) {
                asignaturaEncontrada = true;
            } else {
                indice++;
            }
        }
        return indice;
    }

    public void insertar(Asignatura asignatura) throws OperationNotSupportedException {
        if (asignatura == null) {
            throw new NullPointerException("ERROR: No se puede insertar una asignatura nula.");
        }
        int indice = buscarIndice(asignatura);
        if (capacidadSuperada(indice)) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más asignaturas.");
        }
        if (tamanoSuperado(indice)) {
            coleccionAsignaturas[indice] = new Asignatura(asignatura);
            tamano++;
        } else {
            throw new OperationNotSupportedException("ERROR: Ya existe una asignatura con ese código.");
        }
    }

    private boolean tamanoSuperado(int indice) {
        return indice >= getTamano();
    }



    private boolean capacidadSuperada(int indice) {
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
            throw new NullPointerException("ERROR: No se puede borrar una asignatura nula.");
        }
        int indice = buscarIndice(asignatura);
        if (tamanoSuperado(indice)) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna asignatura como la indicada.");
        } else {
            desplazarUnaPosicionHaciaIzquierda(indice);
        }

    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) throws OperationNotSupportedException {

        coleccionAsignaturas[indice] = null;
        for (int i = indice; !tamanoSuperado(i); i++) {
            if (i < getCapacidad() - 1) {
                coleccionAsignaturas[i] = coleccionAsignaturas[i + 1];
            }
        }
        tamano--;
    }

    public int getTamano() {
        return tamano;
    }

    public int getCapacidad() {
        return capacidad;
    }

}
