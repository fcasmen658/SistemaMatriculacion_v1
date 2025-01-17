package org.iesalandalus.programacion.matriculacion.dominio;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

public class Matricula {

    public static int MAXIMO_MESES_ANTERIOR_ANULACION = 6;
    public static int MAXIMO_DIAS_ANTERIOR_MATRICULA = 15;
    public static int MAXIMO_NUMERO_HORAS_MATRICULA = 1000;
    public static int MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA = 10;
    private static String ER_CURSO_ACADEMICO = "[0-9]{2}" + "-" + "[0-9]{2}";
    public static String FORMATO_FECHA = "dd/MM/yyyy";

    private Alumno alumno;
    private Asignatura[] coleccionAsignaturas;
    private int idMatricula;
    private String cursoAcademico;
    private LocalDate fechaMatriculacion;
    private LocalDate fechaAnulacion;

    //Constructor con parámetros
    public Matricula(int idMatricula, String cursoAcademico, LocalDate fechaMatriculacion, Alumno alumno, Asignatura[] coleccionAsignaturas) {
        setIdMatricula(idMatricula);
        setCursoAcademico(cursoAcademico);
        setFechaMatriculacion(fechaMatriculacion);
        setAlumno(alumno);
        setColeccionAsignaturas(coleccionAsignaturas);
    }

    //Constructor copia
    public Matricula(Matricula matricula) {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No es posible copiar una matrícula nula.");
        }
        setIdMatricula(matricula.getIdMatricula());
        setCursoAcademico(matricula.getCursoAcademico());
        setFechaMatriculacion(matricula.getFechaMatriculacion());
        setAlumno(matricula.getAlumno());
        setColeccionAsignaturas(matricula.getColeccionAsignaturas());
    }

    public int getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(int idMatricula) {

        if (idMatricula <= 0) {
            throw new IllegalArgumentException("ERROR: El identificador de una matrícula no puede ser menor o igual a 0.");
        }
        this.idMatricula = idMatricula;
    }

    public String getCursoAcademico() {
        return cursoAcademico;
    }

    public void setCursoAcademico(String cursoAcademico) {
        if (cursoAcademico == null) {
            throw new NullPointerException("ERROR: El curso académico de una matrícula no puede ser nulo.");
        }
        if (cursoAcademico.isBlank()) {
            throw new IllegalArgumentException("ERROR: El curso académico de una matrícula no puede estar vacío.");
        }
        if (!cursoAcademico.matches(ER_CURSO_ACADEMICO)) {
            throw new IllegalArgumentException("ERROR: El formato del curso académico no es correcto.");
        }
        if (!cursoAcademico.matches("\\d{2}-\\d{2}")) {
            throw new IllegalArgumentException("ERROR: El curso académico debe tener el formato dd-dd, por ejemplo, 24-25.");
        }
        this.cursoAcademico = cursoAcademico;
    }

    public LocalDate getFechaMatriculacion() {
        return fechaMatriculacion;
    }

    public void setFechaMatriculacion(LocalDate fechaMatriculacion) {
        if (fechaMatriculacion == null) {
            throw new NullPointerException("ERROR: La fecha de matriculación de una matrícula no puede ser nula.");
        }
        if (fechaMatriculacion.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("ERROR: La fecha de matriculación no puede ser posterior a hoy.");
        }
        if (fechaMatriculacion.isBefore(LocalDate.now().minusDays(MAXIMO_DIAS_ANTERIOR_MATRICULA))) {
            throw new IllegalArgumentException("ERROR: La fecha de matriculación no puede ser anterior a " + MAXIMO_DIAS_ANTERIOR_MATRICULA + " días.");
        }
        this.fechaMatriculacion = fechaMatriculacion;
    }

    public LocalDate getFechaAnulacion() {
        return fechaAnulacion;
    }

    public void setFechaAnulacion(LocalDate fechaAnulacion) {
        if (fechaAnulacion == null) {
            throw new NullPointerException("ERROR: La fecha de anulación de una mátricula no puede ser nula.");
        }
        if (fechaAnulacion.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("ERROR: La fecha de anulación de una matrícula no puede ser posterior a hoy.");
        }
        if (fechaAnulacion.isBefore(LocalDate.now().minusMonths(MAXIMO_MESES_ANTERIOR_ANULACION))) {
            throw new IllegalArgumentException("ERROR: La fecha de anulación no puede ser anterior a " + MAXIMO_MESES_ANTERIOR_ANULACION + " meses.");
        }
        if (fechaAnulacion.isBefore(fechaMatriculacion)) {
            throw new IllegalArgumentException("ERROR: La fecha de anulación no puede ser anterior a la fecha de matriculación.");
        }
        /*if (fechaAnulacion.isAfter(LocalDate.now()) || fechaAnulacion.isAfter(LocalDate.now().plusDays(MAXIMO_DIAS_ANTERIOR_MATRICULA))) {
            throw new IllegalArgumentException("ERROR: La fecha de anulación de una matrícula no puede ser posterior a hoy.");
        }*/
        /*if (fechaAnulacion.isBefore(LocalDate.now().minusMonths(MAXIMO_MESES_ANTERIOR_ANULACION))) {
            throw new IllegalArgumentException("ERROR: La fecha de anulación no puede ser anterior a " + MAXIMO_MESES_ANTERIOR_ANULACION + " meses.");
        }*/
        /*if (fechaAnulacion.isBefore(fechaMatriculacion)) {
            throw new IllegalArgumentException("ERROR: La fecha de anulación no puede ser anterior a la fecha de matriculación.");
        }*/
        this.fechaAnulacion = fechaAnulacion;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        if (alumno == null) {
            throw new NullPointerException("ERROR: El alumno de una matrícula no puede ser nulo.");
        }
        this.alumno = alumno;
    }

    public Asignatura[] getColeccionAsignaturas() {
        return coleccionAsignaturas;
    }

    public void setColeccionAsignaturas(Asignatura[] coleccionAsignaturas) {
        if (coleccionAsignaturas == null) {
            throw new NullPointerException("ERROR: La lista de asignaturas de una matrícula no puede ser nula.");
        }
        if (coleccionAsignaturas.length == 0) {
            throw new IllegalArgumentException("ERROR: La lista de asignaturas de una matrícula no puede estar vacía.");
        }
        if ((MAXIMO_NUMERO_HORAS_MATRICULA < coleccionAsignaturas.length) ||(superaMaximoNumeroHorasMatricula(coleccionAsignaturas))){
            throw new IllegalArgumentException("ERROR: No se puede realizar la matrícula ya que supera el máximo de horas permitidas (" + Matricula.MAXIMO_NUMERO_HORAS_MATRICULA + " horas).");
        }
        /*if (superaMaximoNumeroHorasMatricula(coleccionAsignaturas)) {
            throw new IllegalArgumentException("ERROR: No se pueden matricular más de " + MAXIMO_NUMERO_HORAS_MATRICULA + " horas.");
        }*/

        this.coleccionAsignaturas = coleccionAsignaturas;
    }

    private boolean superaMaximoNumeroHorasMatricula(Asignatura[] coleccionAsignaturas) {
        /*if (coleccionAsignaturas == null) {
            throw new NullPointerException("ERROR: El alumno de una matrícula no puede ser nulo.");
        } else if (coleccionAsignaturas.length >= MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA) {
            throw new IllegalArgumentException("ERROR: No se pueden matricular más de " + MAXIMO_NUMERO_HORAS_MATRICULA + " asignaturas.");
        }
        if (coleccionAsignaturas.length == 0) {
            return false;
        }*/

        int totalHorasMatricula=0;
        for (Asignatura asignatura : coleccionAsignaturas) {
            if (asignatura != null) {
                totalHorasMatricula += asignatura.getHorasAnuales();
            }
        }
        return totalHorasMatricula > MAXIMO_NUMERO_HORAS_MATRICULA;
    }

    private String asignaturasMatricula() {
        if (coleccionAsignaturas == null) {
            throw new NullPointerException("ERROR: La colección de asignaturas de una matricula no puede ser nula.");
        }
        if (coleccionAsignaturas.length > MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA) {
            throw new IllegalArgumentException("ERROR: No se pueden matricular más de " + MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA + " asignaturas.");
        }
        String asignaturasMatricula = "";
        for (Asignatura asignatura : coleccionAsignaturas) {
            asignaturasMatricula += asignatura.getCodigo() + " ";
        }
        return asignaturasMatricula;
    }

    public String imprimir() {
        String matricula = "Matricula: " + idMatricula + "\n";
        matricula += "Curso academico: " + cursoAcademico + "\n";
        matricula += "Fecha matriculacion: " + fechaMatriculacion + "\n";
        matricula += "Alumno: " + alumno.getNombre() + "\n";
        matricula += "Asignaturas matriculadas: " + asignaturasMatricula() + "\n";
        return matricula;
    }

    @Override
    public String toString() {
        return "Matricula{" +
                "alumno=" + alumno +
                ", coleccionAsignaturas=" + Arrays.toString(coleccionAsignaturas) +
                ", idMatricula=" + idMatricula +
                ", cursoAcademico='" + cursoAcademico + '\'' +
                ", fechaMatriculacion=" + fechaMatriculacion +
                ", fechaAnulacion=" + fechaAnulacion +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Matricula matricula)) return false;
        return idMatricula == matricula.idMatricula && Objects.equals(alumno, matricula.alumno) && Objects.deepEquals(coleccionAsignaturas, matricula.coleccionAsignaturas) && Objects.equals(cursoAcademico, matricula.cursoAcademico) && Objects.equals(fechaMatriculacion, matricula.fechaMatriculacion) && Objects.equals(fechaAnulacion, matricula.fechaAnulacion);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idMatricula);
    }


}
