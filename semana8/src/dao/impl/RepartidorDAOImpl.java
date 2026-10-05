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

    /**
     *
     * @param repartidor recibe la instancia de Repartidor desde el ControladorRepartidor
     * @return booleano de filas afectadas > 0, que confirman o no que el Repartidor fue creado exitosamente en la base de
     * datos con sentencia SQL tipo INSERT.
     */
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

    /**
     *
     * @return una lista de objetos Repartidor, a traves de una sentencia SQL tipo SELECT de lectura de filas construye el objeto
     * con cada atributo recuperado.
     */
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

    /**
     *
     * @param repartidor enviado desde ControladorRepartidor para ser modificado por una sentencia SQL tipo UPDATE
     * @return boolean de filas afectadas > 0, confirmando o no que los parametros fueron reemplazados.
     */
    public boolean editar(Repartidor repartidor) {
        String sql = "UPDATE repartidores SET nombre=? WHERE id=?";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)){

            ps.setString(1, repartidor.getNombre());
            ps.setInt(2, repartidor.getId());

            return ps.executeUpdate() > 0;

        }catch (SQLException e){
            System.out.println("Error al actualizar repartidor: " + e.getMessage());
            return false;
        }
    }

    /**
     *
     * @param id de Repartidor enviado desde ControladorRepartidor para ser eliminado con sentencia SQL tipo DELETE
     * @return booleano de filas afectadas > 0, confirmando o no que la operacion se realizo.
     */
    public boolean eliminar(int id) {
        String sql = "DELETE FROM repartidores WHERE id=?";

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)){

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        }catch (SQLException e) {
            System.out.println("Error al eliminar repartidor: " + e.getMessage());
            return false;
        }
    }

}
