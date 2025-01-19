package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.Alumno;

import javax.naming.OperationNotSupportedException;

public class Alumnos {
    private int capacidad;
    private int tamano;
    private Alumno[] coleccionAlumnos;

    public Alumnos(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.coleccionAlumnos = new Alumno[getCapacidad()];
        this.capacidad = capacidad;
        this.tamano = 0;
    }

    public Alumno[] get() {
        return copiaProfundaAlumnos();
    }

    private Alumno[] copiaProfundaAlumnos() {
        Alumno[] copiaAlumnos = new Alumno[getCapacidad()];

        for (int i = 0; !tamanoSuperado(i); i++) {
            copiaAlumnos[i] = new Alumno(coleccionAlumnos[i]);
        }
        return copiaAlumnos;
    }

    public void insertar(Alumno alumno) throws OperationNotSupportedException {
        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede insertar un alumno nulo.");
        }
        if (tamanoSuperado(getTamano())) {
            throw new OperationNotSupportedException("ERROR: No existe ningún alumno como el indicado.");
        }

        int indice = buscarIndice(alumno);
        if (capacidadSuperada(indice)) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más alumnos.");
        }

        for (int i = 0; i < tamano; i++) {
            if (coleccionAlumnos[i].equals(alumno)) {
                throw new OperationNotSupportedException("ERROR: El alumno ya existe en la colección.");
            }
        }

        coleccionAlumnos[tamano] = new Alumno(alumno);
        tamano++;
    }

    private int buscarIndice(Alumno alumno) {
        if (alumno == null) {
            throw new NullPointerException("ERROR: El alumno no puede ser nulo.");
        }
        for (int i = 0; !tamanoSuperado(i); i++) {
            if (alumno.equals(coleccionAlumnos[i])) {
                return i;
            }
        }
        return -1;
    }

    private boolean tamanoSuperado(int i) throws IllegalArgumentException {
        if (i < 0) {
            throw new IllegalArgumentException("ERROR: El índice debe ser mayor o igual que cero.");
        }
        if (i >= getCapacidad()) {
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

    public Alumno buscar(Alumno alumno) {
        if (alumno == null) {
            throw new NullPointerException("ERROR: El alumno no puede ser nulo.");
        }
        int indice = buscarIndice(alumno);
        if (indice == -1) {
            throw new IllegalArgumentException("ERROR: El alumno no existe.");
        }
        for (int i = 0; !tamanoSuperado(i); i++) {
            if (alumno.equals(coleccionAlumnos[i])) {
                return coleccionAlumnos[i];
            }
        }
        return coleccionAlumnos[buscarIndice(alumno)];
    }

    public void borrar(Alumno alumno) throws OperationNotSupportedException {
        if (alumno == null) {
            throw new NullPointerException("ERROR: El alumno no puede ser nulo.");
        }
        if (tamanoSuperado(getTamano())) {
            throw new OperationNotSupportedException("ERROR: No existe ningún alumno como el indicado.");
        }
        if (buscarIndice(alumno) == -1) {
            throw new OperationNotSupportedException("ERROR: El alumno no se encuentra en la colección de alumnos.");
        }
        int indice = buscarIndice(alumno);
        for (int i = indice; !tamanoSuperado(i); i++) {
            coleccionAlumnos[i] = coleccionAlumnos[i + 1];
        }
        desplazarUnaPosicionHaciaIzquierda(indice);
        tamano--;
    }

    private void desplazarUnaPosicionHaciaIzquierda(int i) throws OperationNotSupportedException {
        if (!tamanoSuperado(i))
            throw new OperationNotSupportedException("ERROR: El alumno no se encuentra en la colección de alumnos.(96)");
        for (int j = i; !tamanoSuperado(j); j++) {
            coleccionAlumnos[j] = coleccionAlumnos[j + 1];
        }
    }

    public int getTamano() {
        return tamano;
    }

    public int getCapacidad() {
        return capacidad;
    }
}
