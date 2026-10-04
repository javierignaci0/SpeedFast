package controller;

import dao.impl.EntregaDAOImpl;
import model.Entrega;

import java.util.List;


public class ControladorEntregas {

    private EntregaDAOImpl entregaDAO;

    public ControladorEntregas(){
        this.entregaDAO = new EntregaDAOImpl();
    }

    public boolean agregarEntrega(Entrega entrega) {

        if(entrega == null || entrega.getFecha() == null || entrega.getHora() == null){
            System.out.println("No se permite ningun valor nulo en Entrega");
            return false;
        }
        boolean exito = entregaDAO.guardar(entrega);

        if (exito) {
            System.out.println("Entrega asignado con exito.");
        }else{
            System.out.println("Error al asignar entrega al repartidor.");
        }
        return exito;
    }

    public List<Entrega> listarEntregas() {
        return entregaDAO.listarTodos();
    }

    public boolean actualizarEntrega(Entrega entrega) {
        if (entrega == null || entrega.getFecha() == null || entrega.getHora() == null) {
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

    public boolean eliminarEntrega(Entrega entrega) {
        if (entrega == null || entrega.getFecha() == null || entrega.getHora() == null) {
            System.out.println("No se permite ningun valor nulo en Entrega");
        }
        boolean exito = entregaDAO.eliminar(entrega.getId());
        if (exito) {
            System.out.println("Entrega eliminada con exito.");
        }else{
            System.out.println("Error al eliminar entrega.");
        }
        return exito;

    }

}
