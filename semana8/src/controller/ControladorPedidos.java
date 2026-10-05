package controller;

import dao.impl.PedidoDAOImpl;
import model.Pedido;


import java.util.List;

public class ControladorPedidos {

    private final PedidoDAOImpl pedidoDAO;

    public ControladorPedidos() {
        this.pedidoDAO = new PedidoDAOImpl();
    }

    /**
     *
     * @param pedido recibe la instancia de Pedido desde el VentanaAlternativaForm que sera sometido a validacion
     * @return un boolean de exito si atraviesa la validacion, para ser enviado a PedidoDAOImpl que lo ingresara a la
     * base de datos.
     */
    public boolean agregarPedido(Pedido pedido) {
        boolean exito = pedidoDAO.guardar(pedido);

        if (exito) {
            System.out.println("CP: Pedido creado con exito.");
        }else{
            System.out.println("Error al agregar pedido.");
        }
        return exito;
    }

    /**
     *
     * @return una lista de todos los pedidos obtenidos desde la base de datos
     */
    public List<Pedido> listarPedidos() {
        return pedidoDAO.listarTodos();
    }

    /**
     *
     * @param pedido enviado desde VentanaAlternativaForm para ser validado
     * @return booleano de exito si atraviesa la validacion de parametros, siendo enviada a PedidoDAOImpl para ser
     * actualizado en la base de datos.
     */
    public boolean actualizarPedido(Pedido pedido) {
        if (pedido == null || pedido.getDireccion() == null || pedido.getTipo() == null || pedido.getEstado() == null) {
            System.out.println("No se permite ningun valor nulo en Pedido");
            return false;
        }

        boolean exito = pedidoDAO.actualizar(pedido);
        if (exito) {
            System.out.println("Pedido actualizado con exito.");
        }else{
            System.out.println("Error al actualizar pedido.");
        }
        return exito;
    }

    /**
     *
     * @param id de objeto Pedido enviado desde VentanaAlternativaForm para ser eliminado
     * @return booleano de exito, que lo envia a PedidoDAOImpl para ser eliminado de la base de datos.
     */
    public boolean eliminarPedido(int id) {

        boolean exito = pedidoDAO.eliminar(id);
        if (exito) {
            System.out.println("Pedido eliminado con exito.");
        }else {
            System.out.println("Error al eliminar pedido.");
        }
        return exito;
    }

    public boolean cambiarEstadoPedido(int idPedido) {
        return pedidoDAO.cambiarEstado(idPedido, "ENTREGADO");
    }

    public boolean verificarPedidoEntregado(int idPedido) {
        return pedidoDAO.pedidoYaEntregado(idPedido);
    }
}
