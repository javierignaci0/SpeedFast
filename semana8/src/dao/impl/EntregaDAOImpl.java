package dao.impl;


import dao.EntregaDAO;
import model.Entrega;
import util.ConexionDB;
import java.sql.Connection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class EntregaDAOImpl implements EntregaDAO {

    /**
     *
     * @param entrega recibe la instancia de Entrega desde el ControladorEntregas
     * @return un boolean de filas afectas si son mayores a 0, que insertan una sentencia
     * SQL tipo INSERT en la base de datos, creando un objeto en la tabla 'entregas'.
     */
    @Override
    public boolean guardar(Entrega entrega){
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

    /**
     *
     * @return una lista de objetos Entrega, a traves de una sentencia SQL tipo SELECT de lectura de filas construye el objeto
     * con cada atributo recuperado.
     */
    @Override
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

    /**
     *
     * @param entrega enviado desde ControladorEntregas para ser modificado por una sentencia SQL tipo UPDATE
     * @return boolean de filas afectadas > 0, confirmando o no que los parametros fueron reemplazados.
     */
    @Override
    public boolean actualizar(Entrega entrega) {

        String sql = "UPDATE entregas SET id_pedido=?, id_repartidor=? where id=?";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)){

            ps.setInt(1, entrega.getId_pedido());
            ps.setInt(2, entrega.getId_repartidor());
            ps.setInt(3, entrega.getId());

            return ps.executeUpdate() > 0;

        }catch(SQLException e){
            System.out.println("Error al actualizar en entrega: " + e.getMessage());
            return false;
            }
    }

    /**
     *
     * @param id de Entrega enviado desde ControladorEntregas para ser eliminado con sentencia SQL tipo DELETE
     * @return booleano de filas afectadas > 0, confirmando o no que la operacion se realizo.
     */
    @Override
    public boolean eliminar(int id) {

        String sql = "DELETE FROM entregas where id=?";

        try (Connection conexion = ConexionDB.conectar();
            PreparedStatement ps = conexion.prepareStatement(sql)){

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        }
        catch (SQLException e){
            System.out.println("Error al eliminar en entrega: " + e.getMessage());
            return false;
        }

    }
}
