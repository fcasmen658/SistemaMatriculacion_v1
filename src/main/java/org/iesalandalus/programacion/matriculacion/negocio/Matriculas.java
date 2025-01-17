package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.dominio.Matricula;

import javax.naming.OperationNotSupportedException;
import java.util.Objects;

public class Matriculas {
    private int capacidad;
    private int tamano;
    private Matricula[] coleccionMatriculas;
    private Asignatura[] coleccionAsignaturas;

    public Matriculas(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.coleccionMatriculas = new Matricula[getCapacidad()];
        this.capacidad = capacidad;
        this.tamano = 0;
    }

    //todo preguntar
    public Matricula[] get() {
        if (tamano == 0) {
            return new Matricula[0];
        }
        Matricula[] copiaMatriculas = new Matricula[tamano];
        for (int i = 0; i < tamano; i++) {
            copiaMatriculas[i] = new Matricula(coleccionMatriculas[i]);
        }
        return copiaProfundaMatriculas();
    }

    private Matricula[] copiaProfundaMatriculas() {
        Matricula[] copiaMatriculas = new Matricula[capacidad];
        for (int i = 0; !tamanoSuperado(i); i++) {
            copiaMatriculas[i] = new Matricula(coleccionMatriculas[i]);
        }
        return copiaMatriculas;
    }

    private boolean tamanoSuperado(int i) {
        return i >= capacidad;
    }

    private boolean capacidadSuperada(int i) {
        return i >= capacidad;
    }

    public void insertar(Matricula matricula) throws OperationNotSupportedException {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede insertar una matrícula nula.");
        }
        int indice = buscarIndice(matricula);
        if (capacidadSuperada(indice)) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más matrículas.");
        }
        if (tamanoSuperado(indice)) {
            coleccionMatriculas[indice] = new Matricula(matricula);
            tamano++;
        } else {
            throw new OperationNotSupportedException("ERROR: Ya existe una matrícula con ese identificador.");
        }
    }

    private int buscarIndice(Matricula matricula) {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede buscar una matrícula nula.");
        }
        for (int i = 0; !tamanoSuperado(i); i++) {
            if (matricula.equals(coleccionMatriculas[i])) {
                return i;
            }
        }
        return -1;
    }

    public void borrar(Matricula matricula) throws OperationNotSupportedException {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede borrar una matrícula nula.");
        }
        int indice = buscarIndice(matricula);
        if (indice == -1) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna matrícula con ese identificador.");
        }
        for (int i = indice; !tamanoSuperado(i); i++) {
            coleccionMatriculas[i] = coleccionMatriculas[i + 1];
            desplazarUnaPosicionHaciaIzquierda(i);
        }
        tamano--;
    }

    public Matricula buscar(Matricula matricula) {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede buscar una matrícula nula.");
        }
        int indice = buscarIndice(matricula);
        if (indice == -1) {
            throw new IllegalArgumentException("ERROR: No existe ninguna matrícula con ese identificador.");
        }
        for (int i = 0; !tamanoSuperado(i); i++) {
            if (Objects.equals(coleccionMatriculas[i].getIdMatricula(), matricula.getIdMatricula())) {
                return coleccionMatriculas[i];
            }
        }
        return coleccionMatriculas[buscarIndice(matricula)];
    }

    public int getTamano() {
        return tamano;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void desplazarUnaPosicionHaciaIzquierda(int i) throws OperationNotSupportedException {
        if (tamanoSuperado(i)) {
            throw new OperationNotSupportedException("ERROR: No se puede desplazar una posición hacia la izquierda.");

        } else {
            for (int j = i; !tamanoSuperado(j); j++) {
                coleccionMatriculas[j] = coleccionMatriculas[j + 1];
            }
            tamano--;

        }
    }

    public Matricula[] get(Alumno alumno) {
        Matricula[] copiaMatriculas = new Matricula[capacidad];
        for (int i = 0; !tamanoSuperado(i); i++) {
            if (alumno.equals(coleccionMatriculas[i].getAlumno())) {
                copiaMatriculas[i] = new Matricula(coleccionMatriculas[i]);
            }
        }
        return copiaMatriculas;
    }

    public Matricula[] get(String cursoAcademico) {
        Matricula[] copiaMatriculas = new Matricula[capacidad];
        for (int i = 0; !tamanoSuperado(i); i++) {
            if (cursoAcademico.equals(coleccionMatriculas[i].getCursoAcademico())) {
                copiaMatriculas[i] = new Matricula(coleccionMatriculas[i]);
            }
        }
        return copiaMatriculas;
    }
    /* //todo
    public Matricula[] get (CicloFormativo cicloFormativo) {
        Matricula[] copiaMatriculas = new Matricula[capacidad];
        for (int i = 0; !tamanoSuperado(i); i++) {
            if (cicloFormativo.equals(coleccionMatriculas[i].get())) {
                copiaMatriculas[i] = new Matricula(coleccionMatriculas[i]);
            }
        }
        return copiaMatriculas;
    }*/

}

