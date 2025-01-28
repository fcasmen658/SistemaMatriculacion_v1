package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;

import javax.naming.OperationNotSupportedException;

public class Matriculas {
    private int capacidad;
    private int tamano;
    private Matricula[] coleccionMatriculas;

    public Matriculas(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        coleccionMatriculas = new Matricula[getCapacidad()];
    }

    public Matricula[] get() throws OperationNotSupportedException {
        return copiaProfundaMatriculas();
    }

    private Matricula[] copiaProfundaMatriculas() throws OperationNotSupportedException {
        Matricula[] copia = new Matricula[tamano];
        for (int i = 0; !tamanoSuperado(i); i++) {
            copia[i] = new Matricula(coleccionMatriculas[i]);
        }
        return copia;
    }

    public int getTamano () {
        return tamano;
    }

    public int getCapacidad () {
        return capacidad;
    }

    private int buscarIndice(Matricula matricula) {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede buscar una matrícula nula.");
        }
        for (int i = 0; i < tamano; i++) {
            if (matricula.equals(coleccionMatriculas[i])) {
                return i;
            }
        }
        return -1;
    }

    public void insertar(Matricula matricula) throws OperationNotSupportedException {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede insertar una matrícula nula.");
        }
        int indice = buscarIndice(matricula);
        if (indice != -1) {
            throw new OperationNotSupportedException("ERROR: Ya existe una matrícula con ese identificador.");
        }
        if (capacidadSuperada(tamano)) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más matrículas.");
        }
        coleccionMatriculas[tamano] = new Matricula(matricula);
        tamano++;
    }

    private boolean tamanoSuperado(int indice) {
        return indice >= tamano;
    }

    private boolean capacidadSuperada(int indice) {
        return indice >= tamano;
    }

    public Matricula buscar(Matricula matricula) throws OperationNotSupportedException {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede buscar una matrícula nula.");
        }
        int indice = buscarIndice(matricula);
        if (indice == -1) {
            return null;
        } else {
            return new Matricula(coleccionMatriculas[indice]);
        }
    }

    public void borrar(Matricula matricula) throws OperationNotSupportedException {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede borrar una matrícula nula.");
        }
        int indice = buscarIndice(matricula);
        if (tamanoSuperado(indice)) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna matrícula como la indicada.");
        } else {
            desplazarUnaPosicionHaciaIzquierda(indice);
        }
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        for (int i = indice; i < tamano - 1; i++) {
            coleccionMatriculas[i] = coleccionMatriculas[i + 1];
        }
        coleccionMatriculas[tamano - 1] = null;
        tamano--;
    }

    public Matricula[] get(Alumno alumno) throws OperationNotSupportedException {
        int contador = 0;
        for (int i = 0; i < tamano; i++) {
            if (alumno.equals(coleccionMatriculas[i].getAlumno())) {
                contador++;
            }
        }
        Matricula[] copiaMatriculas = new Matricula[contador];
        int j = 0;
        for (int i = 0; i < tamano; i++) {
            if (alumno.equals(coleccionMatriculas[i].getAlumno())) {
                copiaMatriculas[j++] = new Matricula(coleccionMatriculas[i]);
            }
        }
        return copiaMatriculas;
    }

    public Matricula[] get(String cursoAcademico) throws OperationNotSupportedException {
        int contador = 0;
        for (int i = 0; i < tamano; i++) {
            if (cursoAcademico.equals(coleccionMatriculas[i].getCursoAcademico())) {
                contador++;
            }
        }
        Matricula[] copiaMatriculas = new Matricula[contador];
        int j = 0;
        for (int i = 0; i < tamano; i++) {
            if (cursoAcademico.equals(coleccionMatriculas[i].getCursoAcademico())) {
                copiaMatriculas[j++] = new Matricula(coleccionMatriculas[i]);
            }
        }
        return copiaMatriculas;
    }

    public Matricula[] get(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        int contador = 0;
        for (int i = 0; i < tamano; i++) {
            if (cicloFormativo.equals(coleccionMatriculas[i].getCicloFormativo())) {
                contador++;
            }
        }
        Matricula[] copiaMatriculas = new Matricula[contador];
        int j = 0;
        for (int i = 0; i < tamano; i++) {
            if (cicloFormativo.equals(coleccionMatriculas[i].getCicloFormativo())) {
                copiaMatriculas[j++] = new Matricula(coleccionMatriculas[i]);
            }
        }
        return copiaMatriculas;
    }

}
