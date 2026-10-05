package dao;

import model.Pedido;

import java.util.List;

public interface PedidoDAO {

    boolean guardar(Pedido pedido);
    List<Pedido> listarTodos();
    boolean actualizar(Pedido pedido);
    boolean eliminar(int id);
}
