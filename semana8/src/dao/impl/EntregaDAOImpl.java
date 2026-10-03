package dao.impl;


import dao.EntregaDAO;
import model.Entrega;
import util.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class EntregaDAOImpl implements EntregaDAO {

    // metodo que INSERTA
    public boolean guardar(Entrega entrega){

        if(entrega == null || entrega.getFecha() == null || entrega.getHora() == null){
            System.out.println("No se permite ningun valor nulo en Entrega");
            return false;
        }

        String sql =
                "INSERT INTO entregas (id_pedido, id_repartidor, fecha, hora) VALUES (?,?,?,?)";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql))
        {
                ps.setInt(1, entrega.getId_pedido());
                ps.setInt(2, entrega.getId_repartidor());
                ps.setDate(3, Date.valueOf(entrega.getFecha()));
                ps.setTime(4, Time.valueOf(entrega.getHora()));

                return ps.executeUpdate() > 0;

        }catch(SQLException e){
            System.out.println("Error al insertar en entrega: " + e.getMessage());
            return false;

        }
    }

    // metodo que RECUPERA
    public List<Entrega> listarTodos() {

        List<Entrega> entregas = new ArrayList<>();

        String sql = "SELECT id, id_pedido, id_repartidor, fecha, hora FROM entregas ORDER BY id";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Entrega entrega = new Entrega(
                        rs.getInt("id"),
                        rs.getInt("id_pedido"),
                        rs.getInt("id_repartidor"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getTime("hora").toLocalTime()
                );

                entregas.add(entrega);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar entregas: " + e.getMessage());
        }

        return entregas;
    }

    @Override
    public boolean editar(Entrega entrega) {
        return false;
    }

    @Override
    public boolean eliminar(Entrega entrega) {
        return false;
    }
}
