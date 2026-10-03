package dao;

import model.Entrega;

import java.util.List;

public interface EntregaDAO {

    boolean guardar(Entrega entrega);
    List<Entrega> listarTodos();
    boolean editar(Entrega entrega);
    boolean eliminar(Entrega entrega);



}
