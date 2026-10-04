package dao.impl;

import dao.PedidoDAO;
import model.EstadoPedido;
import model.Pedido;
import util.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAOImpl implements PedidoDAO {

    // metodo que INSERTA
    public boolean guardar(Pedido pedido) {
        String sql =
                "INSERT INTO pedidos (direccion, tipo, estado) VALUES (?, ?, ?)";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, pedido.getDireccion());
            ps.setString(2, pedido.getTipo());
            ps.setString(3, pedido.getEstado().name());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        pedido.setId(rs.getInt(1));
                    }
                }
                return true;
            }
        return false;

    } catch (SQLException e) {
            System.out.println("Error al guardar pedido: " + e.getMessage());
            return false;
        }
    }

    // metodo que RECUPERA
    public List<Pedido> listarTodos() {

        List<Pedido> pedidos = new ArrayList<>();

        String sql = "SELECT id, direccion, tipo, estado FROM pedidos ORDER BY id";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                String estadoTexto = rs.getString("estado");


                Pedido pedido = new Pedido(
                        rs.getInt("id"),
                        rs.getString("direccion"),
                        rs.getString("tipo"),
                        EstadoPedido.valueOf(estadoTexto)
                );

                pedidos.add(pedido);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar pedidos: " + e.getMessage());
        }

        return pedidos;
    }

    public boolean editar(Pedido pedido){
        return false;
    }

    public boolean eliminar(Pedido pedido){
        return false;
    }

}// listo
