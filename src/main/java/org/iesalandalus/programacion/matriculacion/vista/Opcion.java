package org.iesalandalus.programacion.matriculacion.vista;

public enum Opcion {

    SALIR("Salir"),
    INSERTAR_ALUMNO("Insertar alumno"),
    BUSCAR_ALUMNO("Buscar alumno"),
    BORRAR_ALUMNO("Borrar alumno"),
    MOSTRAR_ALUMNOS("Mostrar alumnos"),
    INSERTAR_CICLO_FORMATIVO("Registrar ciclo formativo"),
    BUSCAR_CICLO_FORMATIVO("Buscar ciclo formativo"),
    BORRAR_CICLO_FORMATIVO("Borrar ciclo formativo"),
    MOSTRAR_CICLOS_FORMATIVOS("Mostrar ciclos formativos"),
    INSERTAR_ASIGNATURA("Registrar asignatura"),
    BUSCAR_ASIGNATURA("Buscar asignatura"),
    BORRAR_ASIGNATURA("Borrar asignatura"),
    MOSTRAR_ASIGNATURAS("Mostrar asignaturas"),
    INSERTAR_MATRICULA("Matricular alumno"),
    BUSCAR_MATRICULA("Buscar matricula"),
    ANULAR_MATRICULA("Anular matricula"),
    MOSTRAR_MATRICULAS("Mostrar matriculas"),
    MOSTRAR_MATRICULAS_ALUMNO("Mostrar matriculas por alumno"),
    MOSTRAR_MATRICULAS_CICLO_FORMATIVO("Mostrar matriculas por ciclo formativo"),
    MOSTRAR_MATRICULAS_CURSO_ACADEMICO("Mostrar matriculas por curso académico");

    private String cadenaAMostrar;

    private Opcion(String cadenaAMostrar) {
        this.cadenaAMostrar = cadenaAMostrar;
    }

    public String getCadenaAMostrar() {
        return cadenaAMostrar;
    }

    @Override
    public String toString() {
        return ordinal() + ".-" + cadenaAMostrar;
    }
}
