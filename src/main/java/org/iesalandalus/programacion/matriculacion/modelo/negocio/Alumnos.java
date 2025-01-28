package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;

import javax.naming.OperationNotSupportedException;

public class Alumnos {
    private int capacidad;
    private int tamano;
    private Alumno[] coleccionAlumnos;

    public Alumnos(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        coleccionAlumnos = new Alumno[capacidad];
    }

    public Alumno[] get() {
        return copiaProfundaAlumnos();
    }

    private Alumno[] copiaProfundaAlumnos() {
        Alumno[] copiaAlumnos = new Alumno[capacidad];
        for (int i = 0; !tamanoSuperado(i); i++) {
            copiaAlumnos[i] = new Alumno(coleccionAlumnos[i]);
        }
        return copiaAlumnos;
    }

    private int buscarIndice(Alumno alumno) {
        if (alumno == null) {
            throw new NullPointerException("ERROR: El alumno no puede ser nulo.");
        }
        int indice = 0;
        boolean alumnoEncontrado = false;
        while (!tamanoSuperado(indice) && !alumnoEncontrado) {
            if (get()[indice].equals(alumno)) {
                alumnoEncontrado = true;
            } else {
                indice++;
            }
        }
        return indice;
    }

    public void insertar(Alumno alumno) throws OperationNotSupportedException {
        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede insertar un alumno nulo.");
        }

        int indice = buscarIndice(alumno);
        if (capacidadSuperada(indice)) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más alumnos.");
        }

        if (tamanoSuperado(indice)) {
            coleccionAlumnos[indice] = new Alumno(alumno);
            tamano++;
        } else {
            throw new OperationNotSupportedException("ERROR: Ya existe un alumno con ese dni.");
        }
    }

    private boolean tamanoSuperado(int indice) throws IllegalArgumentException {
        return indice >= getTamano();
    }

    private boolean capacidadSuperada(int indice) throws IllegalArgumentException {
        return indice >= getCapacidad();
    }

    public Alumno buscar(Alumno alumno) {
        if (alumno == null) {
            throw new NullPointerException("ERROR: El alumno no puede ser nulo.");
        }
        int indice = buscarIndice(alumno);
        if (tamanoSuperado(indice)) {
            return null;
        }
        return new Alumno(get()[indice]);
    }

    /*public void borrar(Alumno alumno) throws OperationNotSupportedException {
        // Verifica que el alumno no sea nulo
        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede borrar un alumno nulo.");
        }

        // Busca y borra el alumno si existe
        int indice = buscarIndice(alumno);
        if (indice != -1) {
            // Desplaza los alumnos hacia la izquierda para rellenar el hueco
            for (int i = indice; !tamanoSuperado(i); i++) {
                coleccionAlumnos[i] = coleccionAlumnos[i + 1];
            }
            desplazarUnaPosicionHaciaIzquierda(indice);
            tamano--;
        } else {
            throw new OperationNotSupportedException("ERROR: El alumno no se encuentra en la colección de alumnos.");
        }
    }*/

    public void borrar(Alumno alumno) throws OperationNotSupportedException {
        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede borrar un alumno nulo.");
        }
        int indice = buscarIndice(alumno);
        if (tamanoSuperado(indice)) {
            throw new OperationNotSupportedException("ERROR: No existe ningún alumno como el indicado.");
        } else {
            desplazarUnaPosicionHaciaIzquierda(indice);
        }
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        coleccionAlumnos[indice] = null;
        for (int i = indice; !tamanoSuperado(i); i++) {
            if (i < getCapacidad() - 1) {
                coleccionAlumnos[i] = coleccionAlumnos[i + 1];
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
