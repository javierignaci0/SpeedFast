package controller;

import dao.EntregaDAO;
import model.Entrega;

import java.util.List;


public class ControladorEntregas {

    private EntregaDAO entregaDAO;

    public ControladorEntregas(){
        this.entregaDAO = new EntregaDAO();
    }

    public boolean agregarEntrega(Entrega entrega) {
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

}
