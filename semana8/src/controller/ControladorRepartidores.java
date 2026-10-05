package controller;

import dao.impl.RepartidorDAOImpl;
import model.Repartidor;

import java.util.List;

public class ControladorRepartidores {

    private final RepartidorDAOImpl repartidorDAO;

    public ControladorRepartidores() {
        this.repartidorDAO = new RepartidorDAOImpl();
    }

    public boolean agregarRepartidor(Repartidor repartidor){
        //validacion controlador
        if (repartidor.getNombre() == null || repartidor.getNombre().isBlank()){
            System.out.println("El nombre del repartidor es obligatorio.");
            return false;
        }
        // C -> M
        boolean exito = repartidorDAO.guardar(repartidor);
        if (exito){
            System.out.println("Repartidor guardado correctamente.");
        }else{
            System.out.println("No se pudo crear el Repartidor.");
        }
        return exito;
    }

    public List<Repartidor> listarRepartidores(){
        return repartidorDAO.listarTodos();
    }

    public boolean actualizarRepartidor(Repartidor repartidor){
        if(repartidor == null || repartidor.getNombre() == null){
            System.out.println("No se permite ningun valor nulo en Repartidor");
            return false;
        }

        boolean exito = repartidorDAO.editar(repartidor);

        if(exito){
            System.out.println("Repartidor se actualizo correctamente.");
        }else {
            System.out.println("Error al actualizar repartidor.");
        }
        return exito;
    }

    public boolean eliminarRepartidor(int id){

        boolean exito = repartidorDAO.eliminar(id);

        if(exito){
            System.out.println("Repartidor se eliminado correctamente.");
        }else{
            System.out.println("Error al eliminar repartidor.");
        }
        return exito;

    }
}
