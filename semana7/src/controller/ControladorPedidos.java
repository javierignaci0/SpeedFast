package controller;

import dao.PedidoDAO;
import model.Pedido;


import java.util.List;

public class ControladorPedidos {

    private PedidoDAO pedidoDAO;

    public ControladorPedidos() {
        this.pedidoDAO = new PedidoDAO();
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
