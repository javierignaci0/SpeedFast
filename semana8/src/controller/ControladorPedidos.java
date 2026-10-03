package controller;

import dao.impl.PedidoDAOImpl;
import model.Pedido;


import java.util.List;

public class ControladorPedidos {

    private PedidoDAOImpl pedidoDAO;

    public ControladorPedidos() {
        this.pedidoDAO = new PedidoDAOImpl();
    }

    public boolean agregarPedido(Pedido pedido) {
        boolean exito = pedidoDAO.guardar(pedido);

        if (exito) {
            System.out.println("Pedido agregado con exito.");
        }else{
            System.out.println("Error al agregar pedido.");
        }
        return exito;
    }

    public List<Pedido> listarPedidos() {
        return pedidoDAO.listarTodos();
    }
}
