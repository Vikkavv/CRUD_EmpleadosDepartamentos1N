package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAODepartamento {

    private static Connection con;
    private static DAOEmpleado daoEmpleado;

    public DAODepartamento() throws SQLException {
        con = DB.getConnection();
        daoEmpleado = new DAOEmpleado();
    }

    //SELECTS

    public ArrayList<DTODepartamento> selectAll() throws SQLException {
        ArrayList<DTODepartamento> departamentos = new ArrayList<>();
        PreparedStatement select = con.prepareStatement("select * from departamento");
        ResultSet rs = select.executeQuery();
        while (rs.next()) {
            departamentos.add(new DTODepartamento(rs.getInt(1), rs.getString(2), daoEmpleado.selectByDeptId(rs.getInt(1))));
        }
        return departamentos;
    }

    public DTODepartamento selectById(int id) throws SQLException {
        PreparedStatement select = con.prepareStatement("select * from departamento where id = ?");
        select.setInt(1, id);
        ResultSet rs = select.executeQuery();
        if (rs.next()) {
            return new DTODepartamento(rs.getInt(1), rs.getString(2), daoEmpleado.selectByDeptId(rs.getInt(1)));
        }
        throw new RuntimeException("No se ha encontrado el id del departamento");
    }

    public DTODepartamento selectByName(String name) throws SQLException {
        PreparedStatement select = con.prepareStatement("SELECT * FROM departamento WHERE nombre = ?");
        select.setString(1, name);
        ResultSet rs = select.executeQuery();
        if(rs.next()){
            return new DTODepartamento(rs.getInt(1), rs.getString(2), daoEmpleado.selectByDeptId(rs.getInt(1)));
        }
        throw new RuntimeException("El nombre del departamento no coincide con ninguno existente");
    }

    //INSERT

    public int insert(DTODepartamento departamento) throws SQLException {
        PreparedStatement insert = con.prepareStatement("INSERT INTO departamento (nombre) VALUES (?)");
        insert.setString(1, departamento.getNombre());
        return insert.executeUpdate();
    }

    //UPDATE

    public int update(DTODepartamento departamento) throws SQLException {
        PreparedStatement update = con.prepareStatement("UPDATE departamento SET nombre = ? WHERE id = ?");
        update.setString(1, departamento.getNombre());
        update.setInt(2, departamento.getId());
        return update.executeUpdate();
    }

    //DELETE

    public int delete(int id) throws SQLException {
        PreparedStatement delete = con.prepareStatement("DELETE FROM departamento WHERE id = ?");
        delete.setInt(1, id);
        return delete.executeUpdate();
    }

}
