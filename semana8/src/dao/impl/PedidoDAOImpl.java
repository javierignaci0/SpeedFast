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
            System.out.println("DAO: Error al guardar pedido: " + e.getMessage());
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

    //metodo que actualiza
    @Override
    public boolean actualizar(Pedido pedido){
        String sql = "UPDATE pedidos SET direccion=?, tipo=?, estado=? WHERE id=?";

        try (Connection conexion = ConexionDB.conectar();
            PreparedStatement ps = conexion.prepareStatement(sql)){

            ps.setString(1, pedido.getDireccion());
            ps.setString(2, pedido.getTipo());
            ps.setString(3, pedido.getEstado().name());
            ps.setInt(4, pedido.getId());

            return ps.executeUpdate() > 0;

        }catch (SQLException e){
            System.out.println("Error al actualizar pedido: " + e.getMessage());
            return false;
        }
    }

    //metodo que elimina
    public boolean eliminar(int id){
        String sql = "DELETE FROM pedidos WHERE id=?";

        try (Connection con = ConexionDB.conectar();
            PreparedStatement ps = con.prepareStatement(sql)){

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        }catch (SQLException e) {
            System.out.println("Error al eliminar pedido: " + e.getMessage());
            return false;
        }
    }

    public boolean cambiarEstado(int id, String estado){
        String sql = "UPDATE pedidos SET estado=? WHERE id=?";

        try (Connection conexion = ConexionDB.conectar();
            PreparedStatement ps = conexion.prepareStatement(sql)){

            ps.setString(1, estado);
            ps.setInt(2, id);

            return ps.executeUpdate() > 0;

        }catch (SQLException e){
            System.out.println("Error al cambiar estado: " + e.getMessage());
            return false;
        }
    }

    public boolean pedidoYaEntregado(int idPedido) {
        String sql = "SELECT estado FROM pedidos WHERE id=?";

        try (Connection conexion = ConexionDB.conectar(); // Tu clase de conexión
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, idPedido);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String estado = rs.getString("estado");
                return "ENTREGADO".equalsIgnoreCase(estado);
            }

        } catch (SQLException e) {
            System.err.println("Error al consultar el estado: " + e.getMessage());
        }
        return false;
    }

}
