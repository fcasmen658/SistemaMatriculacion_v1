package org.iesalandalus.programacion.matriculacion.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Alumno {

    private static final String ER_TELEFONO = "\\d{9}";
    private static String ER_CORREO = "[0-9a-zA-Z-._+&]+@+[0-9a-zA-Z-._+&]+[.]+[a-zA-Z]{2,6}$";
    private static final String ER_DNI = "[0-9]{8}[A-Za-z]";
    public static final String FORMATO_FECHA = "dd/MM/yyyy";
    private static final String ER_NIA = "^[a-z]{4}\\d{3}$";
    private static final int MIN_EDAD_ALUMNADO = 16;

    private String nombre;
    private String telefono;
    private String correo;
    private String dni;
    private LocalDate fechaNacimiento;
    private String nia;

    //Constructor con parámetros
    public Alumno(String nombre, String dni, String correo, String telefono, LocalDate fechaNacimiento) {
        setNombre(nombre);
        setDni(dni);
        setCorreo(correo);
        setTelefono(telefono);
        setFechaNacimiento(fechaNacimiento);
        setNia();
    }

    //Constructor copia
    public Alumno(Alumno alumno) {
        if (alumno == null) {
            throw new NullPointerException("ERROR: No es posible copiar un alumno nulo.");
        }
        setNombre(alumno.getNombre());
        setDni(alumno.getDni());
        setDni(alumno.getDni());
        setCorreo(alumno.getCorreo());
        setTelefono(alumno.getTelefono());
        setFechaNacimiento(alumno.getFechaNacimiento());
        setNia();
    }

    //Getters y Setters

    private String getIniciales() {
        StringBuilder iniciales = new StringBuilder();
        for (String palabra : nombre.split("\\s+")) {
            iniciales.append(palabra.charAt(0));
        }
        return iniciales.toString().toUpperCase();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null) {
            throw new NullPointerException("ERROR: El nombre de un alumno no puede ser nulo.");
        }
        if (nombre.isBlank()) {
            throw new IllegalArgumentException("ERROR: El nombre de un alumno no puede estar vacío.");
        }
        this.nombre = formateaNombre(nombre);
    }

    public String getDni() {
        return dni;
    }

    private void setDni(String dni) {
        if (dni == null) {
            throw new NullPointerException("ERROR: El dni de un alumno no puede ser nulo.");
        }
        if (dni.isBlank() || dni.length() != 9 || !dni.matches(ER_DNI)) {
            throw new IllegalArgumentException("ERROR: El dni del alumno no tiene un formato válido.");
        }
        if (!comprobarLetraDni(dni)) {
            throw new IllegalArgumentException("ERROR: La letra del dni del alumno no es correcta.");
        }
        this.dni = dni.substring(0, 8) + Character.toUpperCase(dni.charAt(8));
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        if (correo == null) {
            throw new NullPointerException("ERROR: El correo de un alumno no puede ser nulo.");
        }
        if (!correo.matches(ER_CORREO)) {
            throw new IllegalArgumentException("ERROR: El correo del alumno no tiene un formato válido.");
        }
        if (correo.isBlank()) {
            throw new IllegalArgumentException("ERROR: El correo del alumno no puede estar vacío.");
        }
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (telefono == null) {
            throw new NullPointerException("ERROR: El teléfono de un alumno no puede ser nulo.");
        }
        if (!telefono.matches(ER_TELEFONO) || telefono.isBlank()) {
            throw new IllegalArgumentException("ERROR: El teléfono del alumno no tiene un formato válido.");
        }
        this.telefono = telefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    private void setFechaNacimiento(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) {
            throw new NullPointerException("ERROR: La fecha de nacimiento de un alumno no puede ser nula.");
        }
        if (fechaNacimiento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("ERROR: La fecha de nacimiento de un alumno no puede ser posterior a la fecha actual.");
        }
        if (fechaNacimiento.isAfter(LocalDate.now().minusYears(MIN_EDAD_ALUMNADO))) {
            throw new IllegalArgumentException("ERROR: La edad del alumno debe ser mayor o igual a " + MIN_EDAD_ALUMNADO + " años.");
        }
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNia() {
        return nia;
    }

    private void setNia() {
        if (nombre == null) {
            throw new NullPointerException("ERROR: No puede establecerse el NIA del alumnado ya que el nombre es nulo.");
        }
        if (nombre.isBlank()) {
            throw new IllegalArgumentException("ERROR: No puede establecerse el NIA del alumnado ya que el nombre es vacío.");
        }
        int numeroDni = dni.length();
        this.nia = nombre.toLowerCase().substring(0, 4) + dni.substring(5, 8);
    }

    //Métodos
    private String formateaNombre(String nombre) {
        if (nombre == null) {
            throw new NullPointerException("ERROR: No puede formatearse un nombre nulo.");
        }
        if (nombre.isBlank()) {
            throw new IllegalArgumentException("ERROR: No puede formatearse un nombre en blanco.");
        }

        // Eliminar espacios en blanco al principio y al final
        nombre = nombre.trim();

        // Dividir el nombre en palabras
        String[] palabras = nombre.split("\\s+"); // Utiliza una expresión regular para dividir por uno o más espacios

        StringBuilder nombreFormateado = new StringBuilder();

        for (String palabra : palabras) {
            if (!palabra.isEmpty()) { // Asegurarse de que la palabra no esté vacía
                // Capitalizar la primera letra y poner el resto en minúsculas
                String palabraFormateada = palabra.substring(0, 1).toUpperCase() + palabra.substring(1).toLowerCase();
                nombreFormateado.append(palabraFormateada).append(" "); // Agregar la palabra formateada y un espacio
            }
        }

        // Convertir a cadena y eliminar el último espacio
        return nombreFormateado.toString().trim();
    }

    private static boolean comprobarLetraDni(String dni) {

        Pattern pDNI = Pattern.compile(ER_DNI);
        Matcher mDNI = pDNI.matcher(dni);

        int numeroDni = Integer.parseInt(dni.substring(0, 8));
        String letraDni = dni.substring(8);
        String letraDniMayuscula = letraDni.toUpperCase();
        String[] letrasTabla = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};

        int resultadodivision = numeroDni % 23;
        String dniValido = letrasTabla[resultadodivision];

        return dniValido.equals(letraDniMayuscula);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alumno alumno)) return false;
        return Objects.equals(getDni(), alumno.getDni());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDni());
    }

    public String imprimir() {
        return "Alumno{" +
                "ER_TELEFONO='" + ER_TELEFONO + '\'' +
                ", ER_CORREO='" + ER_CORREO + '\'' +
                ", ER_DNI='" + ER_DNI + '\'' +
                ", FORMATO_FECHA='" + FORMATO_FECHA + '\'' +
                ", ER_NIA='" + ER_NIA + '\'' +
                ", MIN_EDAD_ALUMNADO=" + MIN_EDAD_ALUMNADO +
                ", nombre='" + nombre + '\'' +
                ", iniciales='" + getIniciales() + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                ", dni='" + dni + '\'' +
                ", fechaNacimiento=" + fechaNacimiento.format(DateTimeFormatter.ofPattern(FORMATO_FECHA)) +
                ", nia='" + getNia() + '\'' +
                '}';
    }

    @Override
    public String toString() {

        return "Número de Identificación del Alumnado (NIA)=" + nia +
                " nombre=" + nombre + " (" + getIniciales() + ")" +
                ", DNI=" + dni +
                ", correo=" + correo +
                ", teléfono=" + telefono +
                ", fecha nacimiento=" + fechaNacimiento.format(DateTimeFormatter.ofPattern(FORMATO_FECHA));
    }


}
