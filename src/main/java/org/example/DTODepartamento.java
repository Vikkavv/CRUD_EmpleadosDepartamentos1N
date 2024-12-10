package org.example;

import java.util.ArrayList;

public class DTODepartamento {
    private Integer id;
    private String nombre;
    private ArrayList<DTOEmpleado> empleados;

    public DTODepartamento(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.empleados= new ArrayList<>();
    }

    public DTODepartamento(String nombre) {
        this(null, nombre);
    }

    public DTODepartamento(String nombre, ArrayList<DTOEmpleado> empleados) {
        this(null, nombre);
        this.empleados = empleados;
    }

    public DTODepartamento(Integer id, String nombre, ArrayList<DTOEmpleado> empleados) {
        this(id, nombre);
        this.empleados = empleados;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<DTOEmpleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(ArrayList<DTOEmpleado> empleados) {
        this.empleados = empleados;
    }

    @Override
    public String toString() {
        return "DTODepartamento{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                "}\n";
    }
}
