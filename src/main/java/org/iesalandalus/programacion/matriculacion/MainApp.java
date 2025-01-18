package org.iesalandalus.programacion.matriculacion;


import org.iesalandalus.programacion.matriculacion.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.dominio.Matricula;
import org.iesalandalus.programacion.matriculacion.negocio.Alumnos;
import org.iesalandalus.programacion.matriculacion.negocio.Asignaturas;
import org.iesalandalus.programacion.matriculacion.negocio.CiclosFormativos;
import org.iesalandalus.programacion.matriculacion.negocio.Matriculas;
import org.iesalandalus.programacion.matriculacion.vista.Consola;
import org.iesalandalus.programacion.matriculacion.vista.Opcion;

import javax.naming.OperationNotSupportedException;
import java.util.Arrays;

public class MainApp {

    public static final int CAPACIDAD = 3;
    private static Matriculas matriculas = new Matriculas(CAPACIDAD);
    private static Asignaturas asignaturas = new Asignaturas(CAPACIDAD);
    private static Alumnos alumnos = new Alumnos(CAPACIDAD);
    private static CiclosFormativos ciclosFormativos = new CiclosFormativos(CAPACIDAD);

    public static void main(String[] args) {

        MainApp app = new MainApp();
        Opcion opcion;
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            ejecutarOpcion(opcion);
        } while (opcion != Opcion.SALIR);
    }

    private static void ejecutarOpcion(Opcion opcion) {
        switch (opcion) {
            case SALIR:
                System.out.println("Gracias por utilizar el gestor de matrículas.");
                System.exit(0);
            case INSERTAR_ALUMNO:
                insertarAlumno();
                break;
            case BUSCAR_ALUMNO:
                buscarAlumno();
                break;
            case BORRAR_ALUMNO:
                borrarAlumno();
                break;
            case MOSTRAR_ALUMNOS:
                mostrarAlumnos();
                break;
            case INSERTAR_CICLO_FORMATIVO:
                insertarCicloFormativo();
                break;
            case BUSCAR_CICLO_FORMATIVO:
                buscarCicloFormativo();
                break;
            case BORRAR_CICLO_FORMATIVO:
                borrarCicloFormativo();
                break;
            case MOSTRAR_CICLOS_FORMATIVOS:
                mostrarCiclosFormativos();
                break;
            case INSERTAR_ASIGNATURA:
                insertarAsignatura();
                break;
            case BUSCAR_ASIGNATURA:
                buscarAsignatura();
                break;
            case BORRAR_ASIGNATURA:
                borrarAsignatura();
                break;
            case MOSTRAR_ASIGNATURAS:
                mostrarAsignaturas();
                break;
            case INSERTAR_MATRICULA:
                insertarMatricula();
                break;
            case BUSCAR_MATRICULA:
                buscarMatricula();
                break;
            case ANULAR_MATRICULA:
                anularMatricula();
                break;
            case MOSTRAR_MATRICULAS:
                mostrarMatriculas();
                break;
            case MOSTRAR_MATRICULAS_ALUMNO:
                mostrarMatriculasPorAlumno();
                break;
            case MOSTRAR_MATRICULAS_CICLO_FORMATIVO:
                mostrarMatriculasPorCicloFormativo();
                break;
            case MOSTRAR_MATRICULAS_CURSO_ACADEMICO:
                mostrarMatriculasPorCursoAcademico();
                break;
        }
    }

    private static void insertarAlumno() {
        try {
            Alumno nuevoAlumno = Consola.leerAlumno();
            if (alumnos.buscar(nuevoAlumno) != null) {
                try {
                    alumnos.insertar(nuevoAlumno);
                    System.out.println("Alumno insertado.");
                    throw new OperationNotSupportedException("ERROR: Ya existe un alumno con ese DNI.");
                } catch (OperationNotSupportedException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("Ya existe un alumno con ese DNI.");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void buscarAlumno() {
        try {
            Alumno alumno = Consola.getAlumnoPorDni();
            Alumno encontrado = alumnos.buscar(alumno);
            System.out.println((encontrado != null) ? encontrado : "No se encontró el alumno");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void borrarAlumno() {
        try {
            Alumno alumno = Consola.getAlumnoPorDni();
            alumnos.borrar(alumno);
            System.out.println("Alumno eliminado correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void mostrarAlumnos() {
        Alumno[] coleccion = alumnos.get();
        if (coleccion.length == 0) {
            System.out.println("No hay alumnos registrados.");
        } else {
            for (Alumno alumno : coleccion) {
                if (alumno != null) System.out.println(alumno);
            }
        }
    }

    private static void insertarCicloFormativo() {
        try {
            CicloFormativo nuevoCicloFormativo = Consola.leerCicloFormativo();
            ciclosFormativos.insertar(nuevoCicloFormativo);
            System.out.println("Ciclo formativo insertado.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void buscarCicloFormativo() {
        try {
            CicloFormativo ciclo = Consola.leerCicloFormativo();
            CicloFormativo encontrado = ciclosFormativos.buscar(ciclo);
            System.out.println((encontrado != null) ? encontrado : "No se encontró el ciclo formativo");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void borrarCicloFormativo() {
        try {
            CicloFormativo cicloAEliminar = Consola.leerCicloFormativo();
            ciclosFormativos.borrar(cicloAEliminar);
            System.out.println("Ciclo formativo eliminado correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void mostrarCiclosFormativos() {
        CicloFormativo[] coleccion = ciclosFormativos.get();
        if (coleccion.length == 0) {
            System.out.println("No hay ciclos formativos registrados.");
        } else {
            for (CicloFormativo ciclo : coleccion) {
                if (ciclo != null) System.out.println(ciclo);
            }
        }
    }

    private static void borrarAsignatura() {
        try {
            Asignatura asignatura = Consola.getAsignaturaPorCodigo();
            asignaturas.borrar(asignatura);
            System.out.println("Asignatura eliminada correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void buscarAsignatura() {
        try {
            Asignatura asignatura = Consola.getAsignaturaPorCodigo();
            Asignatura encontrada = asignaturas.buscar(asignatura);
            System.out.println((encontrada != null) ? encontrada : "No se encontró la asignatura");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void insertarAsignatura() {
        try {
            Asignatura nuevaAsignatura = Consola.leerAsignatura(ciclosFormativos);
            asignaturas.insertar(nuevaAsignatura);
            System.out.println("Asignatura insertada.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void mostrarAsignaturas() {
        if (asignaturas.getTamano() < 0) {
            System.out.println("No hay asignaturas registradas.");
        }
        if (asignaturas.getTamano() == asignaturas.getCapacidad())
            System.out.println("No esta esa asignatura.");
        else {
            System.out.println(Arrays.toString(asignaturas.get()));
        }
    }

    private static void insertarMatricula() {
        try {
            Matricula nuevaMatricula = Consola.leerMatricula(alumnos, asignaturas);
            matriculas.insertar(nuevaMatricula);
            System.out.println("Matricula insertada.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void buscarMatricula() {
        try {
            Matricula matricula = Consola.getMatriculaPorIdentificador();
            Matricula encontrada = matriculas.buscar(matricula);
            System.out.println((encontrada != null) ? encontrada : "No se encontró la matricula");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void anularMatricula() {
        try {
            Matricula matriculaAEliminar = Consola.getMatriculaPorIdentificador();
            matriculas.borrar(matriculaAEliminar);
            System.out.println("Matricula eliminada correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void mostrarMatriculas() {
        Alumno alumno = Consola.leerAlumno();
        Matricula[] coleccion = matriculas.get();
        if (coleccion.length == 0) {
            System.out.println("No hay matriculas registradas.");
        } else {
            for (Matricula matricula : coleccion) {
                if (matricula != null) System.out.println(matricula);
            }
        }
    }

    private static void mostrarMatriculasPorAlumno() {
        try {
            Alumno alumno = Consola.leerAlumno();
            Matricula[] matriculasAlumno = matriculas.get();
            if (matriculasAlumno.length == 0) {
                System.out.println("El alumno no tiene matriculas registradas.");
            } else {
                for (Matricula matricula : matriculasAlumno) {
                    if (matricula != null) System.out.println(matricula);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void mostrarMatriculasPorCicloFormativo() {
        try {
            CicloFormativo ciclo = Consola.leerCicloFormativo();
            Matricula[] matriculasCiclo = matriculas.get();
            if (matriculasCiclo.length == 0) {
                System.out.println("El ciclo formativo no tiene matriculas registradas.");
            } else {
                for (Matricula matricula : matriculasCiclo) {
                    if (matricula != null) System.out.println(matricula);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void mostrarMatriculasPorCursoAcademico() {
        try {
            System.out.println("Introduce el curso académico (ejemplo: 24/25): ");
            Matricula[] matriculasCurso = matriculas.get();
            if (matriculasCurso.length == 0) {
                System.out.println("El curso academico no tiene matriculas registradas.");
            } else {
                for (Matricula matricula : matriculasCurso) {
                    if (matricula != null) System.out.println(matricula);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
