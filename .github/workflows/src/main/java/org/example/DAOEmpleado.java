package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOEmpleado {

    private static Connection conn;

    public DAOEmpleado() throws SQLException {
        conn = DB.getConnection();
    }

    //SELECTS

    public ArrayList<DTOEmpleado> selectAll() throws SQLException {
        ArrayList<DTOEmpleado> empleados = new ArrayList<>();
        PreparedStatement select = conn.prepareStatement("SELECT * FROM empleados");
        ResultSet rs = select.executeQuery();
        while (rs.next()) {
            empleados.add(new DTOEmpleado(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4)));
        }
        return empleados;
    }

    public DTOEmpleado selectById(int id) throws SQLException {
        PreparedStatement select = conn.prepareStatement("SELECT * FROM empleados where id = ?");
        select.setInt(1, id);
        ResultSet rs = select.executeQuery();
        if (rs.next()) {
            return new DTOEmpleado(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
        }
        throw new RuntimeException("No se ha encontrado el id del empleado");
    }

    public DTOEmpleado selectByName(String nombre) throws SQLException {
        PreparedStatement select = conn.prepareStatement("SELECT * FROM empleados where nombre = ?");
        select.setString(1, nombre);
        ResultSet rs = select.executeQuery();
        if (rs.next()) {
            return new DTOEmpleado(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
        }
        throw new RuntimeException("No se ha encontrado el nombre");
    }

    public ArrayList<DTOEmpleado> selectByDeptId(int deptId) throws SQLException {
        ArrayList<DTOEmpleado> empleados = new ArrayList<>();
        PreparedStatement select = conn.prepareStatement("SELECT * FROM empleados where dpto_id = ?");
        select.setInt(1, deptId);
        ResultSet rs = select.executeQuery();
        while (rs.next()) {
            empleados.add(new DTOEmpleado(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4)));
        }
        return empleados;
    }

    //INSERT

    public int insert(DTOEmpleado empleado) throws SQLException {
        PreparedStatement insert = conn.prepareStatement("INSERT INTO empleados (nombre, edad, dpto_id) VALUES (?, ?, ?)");
        insert.setString(1, empleado.getNombre());
        insert.setInt(2, empleado.getEdad());
        insert.setInt(3, empleado.getDptoId());
        return insert.executeUpdate();
    }

    //UPDATE

    public int update(DTOEmpleado empleado) throws SQLException {
        if(empleado.getId() == null){
            throw new RuntimeException("El id del empleado no puede ser nulo");
        }
        PreparedStatement update = conn.prepareStatement("UPDATE empleados SET nombre = ?, edad = ?, dpto_id = ? WHERE id = ?");
        update.setString(1, empleado.getNombre());
        update.setInt(2, empleado.getEdad());
        update.setInt(3, empleado.getDptoId());
        update.setInt(4, empleado.getId());
        return update.executeUpdate();
    }

    //DELETE

    public int delete(int id) throws SQLException {
        PreparedStatement delete = conn.prepareStatement("DELETE FROM empleados WHERE id = ?");
        delete.setInt(1, id);
        return delete.executeUpdate();
    }

    public int deleteByDeptId(int deptId) throws SQLException {
        PreparedStatement delete = conn.prepareStatement("DELETE FROM empleados WHERE dpto_id = ?");
        delete.setInt(1, deptId);
        return delete.executeUpdate();
    }

}
