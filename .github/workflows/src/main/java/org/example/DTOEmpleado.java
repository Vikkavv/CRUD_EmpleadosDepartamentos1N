package org.example;

public class DTOEmpleado {
    private int id;
    private String nombre;
    private int edad;
    private int dptoId;

    public DTOEmpleado(Integer id, String nombre, int edad, int dptoId) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.dptoId = dptoId;
    }

    public DTOEmpleado(String nombre, int edad, int dptoId) {
        this(0, nombre, edad, dptoId);
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getDptoId() {
        return dptoId;
    }

    public void setDptoId(int dptoId) {
        this.dptoId = dptoId;
    }

    @Override
    public String toString() {
        return "DTOEmpleado{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", dptoId=" + dptoId +
                "}\n";
    }
}
