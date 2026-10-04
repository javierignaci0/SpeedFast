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

    public boolean actualizarPedido(Pedido pedido) {
        if (pedido == null || pedido.getDireccion() == null || pedido.getTipo() == null || pedido.getEstado() == null) {
            System.out.println("No se permite ningun valor nulo en Pedido");
            return false;
        }
        boolean exito = pedidoDAO.editar(pedido);
        if (exito) {
            System.out.println("Pedido actualizado con exito.");
        }else{
            System.out.println("Error al actualizar pedido.");
        }
        return exito;
    }

    public boolean eliminarPedido(Pedido pedido) {
        if (pedido == null || pedido.getDireccion() == null || pedido.getTipo() == null || pedido.getEstado() == null) {
            System.out.println("No se permite ningun valor nulo en Pedido");
            return false;
        }
        boolean exito = pedidoDAO.eliminar(pedido);
        if (exito) {
            System.out.println("Pedido eliminado con exito.");
        }else {
            System.out.println("Error al eliminar pedido.");
        }
        return exito;
    }
}
