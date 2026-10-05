package controller;

import dao.impl.EntregaDAOImpl;
import model.Entrega;

import java.util.List;


public class ControladorEntregas {

    private final EntregaDAOImpl entregaDAO;

    public ControladorEntregas(){
        this.entregaDAO = new EntregaDAOImpl();
    }

    //CREATE
    public boolean agregarEntrega(Entrega entrega) {
        if((entrega.getId_pedido() == 0) || (entrega.getId_repartidor() == 0)){
            System.out.println("CE: No se permite ningun valor nulo al crear una entrega");
            return false;
        }
        boolean exito = entregaDAO.guardar(entrega);

        if (exito) {
            System.out.println("CE: Entrega actualizada con exito.");
        }else{
            System.out.println("CE: Error al asignar entrega al repartidor.");
        }
        return exito;
    }

    //READ
    public List<Entrega> listarEntregas() {
        return entregaDAO.listarTodos();
    }
    //UPDATE
    public boolean actualizarEntrega(Entrega entrega) {
        if ((entrega.getId() == 0 || entrega.getId_pedido() == 0 || entrega.getId_repartidor() == 0)) {
            System.out.println("No se permite ningun valor nulo en Entrega");
            return false;
        }
        boolean exito = entregaDAO.actualizar(entrega);
        if (exito) {
            System.out.println("Entrega actualizada con exito.");
        } else {
            System.out.println("Error al actualizar entrega al repartidor.");
        }
        return exito;
    }
    //DELETE
    public boolean eliminarEntrega(int id) {

        boolean exito = entregaDAO.eliminar(id);
        if (exito) {
            System.out.println("Entrega eliminada con exito.");
        }else{
            System.out.println("Error al eliminar entrega.");
        }
        return exito;

    }

}
