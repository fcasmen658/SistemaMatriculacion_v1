package org.iesalandalus.programacion.matriculacion.dominio;

public enum Grado {
    GDCFGB("Ciclo Formativo de Grado Básico"),
    GDCFGM("Ciclo Formativo de Grado Medio"),
    GDCFGS("Ciclo Formativo de Grado Superior"),
    ;

    private final String cadenaAMostrar;

    Grado(String cadenaAMostrar) {
        this.cadenaAMostrar = cadenaAMostrar;
    }

    public String imprimir() {
        return this.ordinal() + ".-" + cadenaAMostrar;
    }

    @Override
    public String toString() {
        return "Grado{" +
                "cadenaAMostrar='" + cadenaAMostrar + '\'' +
                '}';
    }
}