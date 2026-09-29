package controller;

import dao.RepartidorDAO;
import model.Repartidor;

import java.util.List;

public class ControladorRepartidores {

    private RepartidorDAO repartidorDAO;

    public ControladorRepartidores() {
        this.repartidorDAO = new RepartidorDAO();
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
