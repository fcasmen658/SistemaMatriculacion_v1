package org.iesalandalus.programacion.matriculacion.controlador;

import org.iesalandalus.programacion.matriculacion.modelo.Modelo;
import org.iesalandalus.programacion.matriculacion.vista.Vista;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;

import javax.naming.OperationNotSupportedException;

public class Controlador {
    private Modelo modelo;
    private Vista vista;

    public Controlador(Modelo modelo, Vista vista) {
        if (modelo == null) {
            throw new NullPointerException("ERROR: El modelo no puede ser nulo.");
        }
        if (vista == null) {
            throw new NullPointerException("ERROR: La vista no puede ser nula.");
        }
        this.modelo = modelo;
        this.vista = vista;
        this.vista.setControlador(this);
    }

    public void comenzar() throws OperationNotSupportedException {
        modelo.comenzar();
        vista.comenzar();

    }

    public void terminar() {
        modelo.terminar();
        vista.terminar();

    }

    public void insertar(Alumno alumno) throws OperationNotSupportedException {
        this.modelo.insertar(alumno);
    }

    public Alumno buscar(Alumno alumno) {
        return this.modelo.buscar(alumno);
    }

    public void borrar(Alumno alumno) throws OperationNotSupportedException {
        this.modelo.borrar(alumno);
    }

    public Alumno[] getAlumnos() {
        return this.modelo.getAlumnos();
    }

    public void insertar(Asignatura asignatura) throws OperationNotSupportedException {
        this.modelo.insertar(asignatura);
    }

    public Asignatura buscar(Asignatura asignatura) {
        return this.modelo.buscar(asignatura);
    }

    public void borrar(Asignatura asignatura) throws OperationNotSupportedException {
        this.modelo.borrar(asignatura);
    }

    public Asignatura[] getAsignaturas() {
        return this.modelo.getAsignaturas();
    }

    public void insertar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        this.modelo.insertar(cicloFormativo);
    }

    public CicloFormativo buscar(CicloFormativo cicloFormativo) {
        return this.modelo.buscar(cicloFormativo);
    }

    public void borrar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        this.modelo.borrar(cicloFormativo);
    }

    public CicloFormativo[] getCiclosFormativos() throws OperationNotSupportedException {
        return this.modelo.getCiclosFormativos();
    }

    public void insertar(Matricula matricula) throws OperationNotSupportedException {
        this.modelo.insertar(matricula);
    }

    public Matricula buscar(Matricula matricula) throws OperationNotSupportedException {
        return this.modelo.buscar(matricula);
    }

    public void borrar(Matricula matricula) throws OperationNotSupportedException {
        this.modelo.borrar(matricula);
    }

    public Matricula[] getMatriculas() throws OperationNotSupportedException {
        return this.modelo.getMatriculas();
    }

    public Matricula[] getMatriculas(Alumno alumno) throws OperationNotSupportedException {
        return this.modelo.getMatriculas(alumno);
    }

    public Matricula[] getMatriculas(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        return this.modelo.getMatriculas(cicloFormativo);
    }

    public Matricula[] getMatriculas(String cursoAcademico) throws OperationNotSupportedException {
        return this.modelo.getMatriculas(cursoAcademico);
    }

}
