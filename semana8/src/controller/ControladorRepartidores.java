package controller;

import dao.impl.RepartidorDAOImpl;
import model.Repartidor;

import java.util.List;

public class ControladorRepartidores {

    private final RepartidorDAOImpl repartidorDAO;

    public ControladorRepartidores() {
        this.repartidorDAO = new RepartidorDAOImpl();
    }

    /**
     *
     * @param repartidor recibe la instancia de Repartidor desde el VentanaAlternativaForm que sera sometido a validacion
     * @return un boolean de exito si atraviesa la validacion, para ser enviado RepartidorDAOImpl que lo ingresara a la
     * base de datos.
     */
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

    /**
     *
     * @return una lista de todos los repartidores obtenidos desde la base de datos
     */
    public List<Repartidor> listarRepartidores(){
        return repartidorDAO.listarTodos();
    }

    /**
     *
     * @param repartidor enviado desde VentanaAlternativaForm para ser validado
     * @return booleano de exito si atraviesa la validacion de parametros, siendo enviada a RepartidorDAOImpl para ser
     * actualizado en la base de datos.
     */
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

    /**
     *
     * @param id de objeto Repartidor enviado desde VentanaAlternativaForm para ser eliminado
     * @return booleano de exito, que lo envia a RepartidorDAOImpl para ser eliminado de la base de datos.
     */
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
