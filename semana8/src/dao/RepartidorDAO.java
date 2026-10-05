package dao;

import model.Repartidor;

import java.util.List;

public interface RepartidorDAO {

    boolean guardar(Repartidor repartidor);
    List<Repartidor> listarTodos();
    boolean editar(Repartidor repartidor);
    boolean eliminar(int id);

}
