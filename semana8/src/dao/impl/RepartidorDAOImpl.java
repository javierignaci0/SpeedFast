package dao.impl;

import dao.RepartidorDAO;
import model.Repartidor;
import util.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepartidorDAOImpl implements RepartidorDAO {

    public boolean guardar(Repartidor repartidor) {

        String sql = "INSERT INTO repartidores (nombre) VALUES (?)";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, repartidor.getNombre());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al guardar repartidor: " + e.getMessage());
            return false;
        }
    }



    // metodo que RECUPERA
    public List<Repartidor> listarTodos() {

        List<Repartidor> repartidores = new ArrayList<>();

        String sql = "SELECT id, nombre FROM repartidores ORDER BY id";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Repartidor repartidor = new Repartidor(
                        rs.getInt("id"),
                        rs.getString("nombre")
                );

                repartidores.add(repartidor);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar repartidores: " + e.getMessage());
        }

        return repartidores;
    }

    public boolean editar(Repartidor repartidor) {
        return false;
    }


    public boolean eliminar(Repartidor repartidor) {
        return false;
    }

} // listo
