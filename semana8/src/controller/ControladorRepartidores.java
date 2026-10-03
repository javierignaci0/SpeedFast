package controller;

import dao.impl.RepartidorDAOImpl;
import model.Repartidor;

import java.util.List;

public class ControladorRepartidores {

    private RepartidorDAOImpl repartidorDAO;

    public ControladorRepartidores() {
        this.repartidorDAO = new RepartidorDAOImpl();
    }

    public boolean agregarRepartidor(String nombre){
        if (nombre == null || nombre.isBlank()){
            return false;
        }

        Repartidor repartidor = new Repartidor(nombre);
        return repartidorDAO.guardar(repartidor);
    }

    public List<Repartidor> listarRepartidores(){
        return repartidorDAO.listarTodos();
    }
}
