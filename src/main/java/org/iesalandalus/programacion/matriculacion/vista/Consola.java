package org.iesalandalus.programacion.matriculacion.vista;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.Alumnos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.Asignaturas;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.CiclosFormativos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.Matriculas;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class Consola {
    public static final CiclosFormativos ciclosFormativos = new CiclosFormativos(5);
    public static final Asignaturas asignaturas = new Asignaturas(5);
    public static final Alumnos alumnos = new Alumnos(5);
    public static final Matriculas matriculas = new Matriculas(5);

    private Consola() {
    }

    public static void mostrarMenu() {
        System.out.print("\n");
        System.out.println("GESTOR DE MATRICULAS IES AL-ÁNDALUS");
        System.out.println("---     Menú de opciones      ---");
        System.out.print("\n");
        for (Opcion opcion : Opcion.values()) {
            System.out.println(opcion.ordinal() + ". " + opcion.getCadenaAMostrar());
        }
    }

    public static Opcion elegirOpcion() {
        System.out.println("Elige una opción: ");
        int opcionElegidaUsuario = Entrada.entero();
        while (opcionElegidaUsuario < 0 || opcionElegidaUsuario > Opcion.values().length) {
            System.out.println("ERROR: Opción no valida.");
        }
        return Opcion.values()[opcionElegidaUsuario];
    }

    public static Alumno leerAlumno() {
        System.out.println("Introduce los datos del alumno:");
        System.out.print("Nombre y apellidos: ");
        String nombre = Entrada.cadena();
        System.out.print("DNI: ");
        String dni = Entrada.cadena();
        System.out.print("Correo electrónico: ");
        String correo = Entrada.cadena();
        System.out.print("Teléfono: ");
        String telefono = Entrada.cadena();
        System.out.print("Introduce la fecha nacimiento (dd/mm/aaaa): ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaNacimientoCadena = Entrada.cadena();
        LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoCadena, formatter);

        return new Alumno(nombre, dni, correo, telefono, fechaNacimiento);
    }

    public static Alumno getAlumnoPorDni() {
        System.out.println("Introduce el DNI del alumno: ");
        String dni = Entrada.cadena();
        return new Alumno("Pepe Apellido1 Apellido2", dni, "pepe@wanadoo.com", "123456789", LocalDate.of(2000, 1, 1));
    }

    public static LocalDate leerFecha(String mensaje) {
        LocalDate fechaNacimiento = null;
        do {
            System.out.print(mensaje);
            try {
                fechaNacimiento = LocalDate.parse(Entrada.cadena(), DateTimeFormatter.ofPattern(Alumno.FORMATO_FECHA));
            } catch (DateTimeParseException e) {
                System.out.println("ERROR: Formato de fecha no válido.");
            }
        } while (fechaNacimiento == null);
        return fechaNacimiento;
    }

    public static Grado leerGrado() {
        System.out.println("Introduce el grado: ");
        for (Grado grado : Grado.values()) {
            System.out.println(grado.imprimir());
        }
        System.out.println("Selecciona un grado: ");
        int opcion = Entrada.entero();
        while (opcion < 0 || opcion > Grado.values().length) {
            System.out.println("ERROR: Opción no valida.");
        }
        return Grado.values()[Entrada.entero()];
    }

    public static CicloFormativo leerCicloFormativo() {
        System.out.println("Introduce los datos del ciclo formativo: ");
        System.out.println("Código: ");
        int codigo = Entrada.entero();
        System.out.println("Familia profesional: ");
        String familiaProfesional = Entrada.cadena();
        System.out.println("Grado: ");
        Grado grado = leerGrado();
        System.out.println("Nombre: ");
        String nombre = Entrada.cadena();
        System.out.print("Horas: ");
        int horas = Entrada.entero();

        return new CicloFormativo(codigo, familiaProfesional, grado, nombre, horas);
    }

    public static void mostrarCiclosFormativos(CiclosFormativos ciclosFormativos) {
        if (ciclosFormativos.get().length == 0) {
            System.out.println("No hay ciclos formativos registrados.");
        } else {
            for (CicloFormativo cicloFormativo : ciclosFormativos.get()) {
                System.out.println(cicloFormativo);
            }
        }
    }

    public static CicloFormativo getCicloFormativoPorCodigo() {
        System.out.print("Introduce el código del ciclo formativo: ");
        int codigo = Entrada.entero();
        return new CicloFormativo(codigo, "", null, "", 0);
    }

    public static Curso leerCurso() {
        System.out.println("Introduce el curso: ");
        for (Curso curso : Curso.values()) {
            System.out.println(curso.imprimir());
        }
        if (Curso.values()[Entrada.entero()] == null) {
            do {
                System.out.println("ERROR: Opción no valida.");
            } while (Curso.values()[Entrada.entero()] == null);
        }
        return Curso.values()[Entrada.entero()];
    }

    public static EspecialidadProfesorado leerEspecialidadProfesorado() {
        System.out.println("Introduce la especialidad: ");
        for (EspecialidadProfesorado especialidadProfesorado : EspecialidadProfesorado.values()) {
            System.out.println(especialidadProfesorado.imprimir());
        }
        if (EspecialidadProfesorado.values()[Entrada.entero()] == null) {
            do {
                System.out.println("ERROR: Opción no valida.");
            } while (EspecialidadProfesorado.values()[Entrada.entero()] == null);
        }
        return EspecialidadProfesorado.values()[Entrada.entero()];
    }

    public static Asignatura leerAsignatura(CiclosFormativos ciclosFormativos) {
        System.out.println("Introduce los datos de la asignatura: ");
        System.out.print("Código: ");
        String codigo = Entrada.cadena();
        System.out.print("Nombre: ");
        String nombre = Entrada.cadena();
        System.out.print("Horas anuales: ");
        int horasAnuales = Entrada.entero();
        System.out.print("Horas desdoble: ");
        int horasDesdoble = Entrada.entero();
        System.out.print("Curso: ");
        Curso curso = leerCurso();
        System.out.println("Especialidad: ");
        EspecialidadProfesorado especialidadProfesorado = leerEspecialidadProfesorado();
        return new Asignatura(codigo, nombre, horasAnuales, curso, horasDesdoble, especialidadProfesorado, getCicloFormativoPorCodigo());
    }

    public static Asignatura getAsignaturaPorCodigo() {
        System.out.println("Introduce el codigo de la asignatura: ");
        String codigo = Entrada.cadena();
        return new Asignatura(codigo, "", 0, Curso.values()[0], 0, EspecialidadProfesorado.values()[0], getCicloFormativoPorCodigo());
    }

    private static void mostrarAsignaturas(Asignaturas asignaturas) {
        int size = asignaturas.getTamano();
        for (int i = 0; i < size; i++) {
            Asignatura[] asignatura = asignaturas.get();
            if (asignatura == null) {
                System.out.println("No hay asignaturas registradas.");
            }
            System.out.println(Arrays.toString(asignatura)); //todo IDE
        }
    }

    private static boolean asignaturaYaMatriculada(Asignaturas asignaturas) {
        Asignatura asignatura = getAsignaturaPorCodigo();
        asignatura = asignaturas.buscar(asignatura);
        return asignatura != null;
    }

    public static Matricula leerMatricula(Alumnos alumnos, Asignaturas asignaturas) throws OperationNotSupportedException {
        int idMatricula;
        String cursoAcademico;
        LocalDate fechaMatriculacion;
        Alumno alumno;
        Asignatura[] coleccionAsignaturas;
        do {
            System.out.println("Introduce los datos de la matricula: ");
            System.out.print("ID: ");
            idMatricula = Entrada.entero();
            System.out.println("Curso académico: ");
            cursoAcademico = Entrada.cadena();
            System.out.println("Fecha matriculación: ");
            fechaMatriculacion = LocalDate.parse(Entrada.cadena(), DateTimeFormatter.ofPattern(Matricula.FORMATO_FECHA));
            alumno = getAlumnoPorDni();
            alumno = alumnos.buscar(alumno);
            coleccionAsignaturas = new Asignatura[5];
            for (int i = 0; i < coleccionAsignaturas.length; i++) {
                coleccionAsignaturas[i] = getAsignaturaPorCodigo();
                coleccionAsignaturas[i] = asignaturas.buscar(coleccionAsignaturas[i]);
            }
        } while (alumno == null || coleccionAsignaturas[0] == null);
        return new Matricula(idMatricula, cursoAcademico, fechaMatriculacion, alumno, coleccionAsignaturas);
    }

    public static Matricula getMatriculaPorIdentificador() throws OperationNotSupportedException {
        Matricula matricula;
        int idMatricula = 1234;
        String cursoAcademico = "21/22";
        LocalDate fechaMatriculacion = LocalDate.of(2022, 1, 1);
        Alumno alumno = getAlumnoPorDni();
        Asignatura[] coleccionAsignaturas = new Asignatura[5];
        for (int i = 0; i < coleccionAsignaturas.length; i++) {
            coleccionAsignaturas[i] = getAsignaturaPorCodigo();
        }
        matricula = new Matricula(idMatricula, cursoAcademico, fechaMatriculacion, alumno, coleccionAsignaturas);
        return matricula;
    }


}
