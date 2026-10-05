package controller;

import dao.impl.EntregaDAOImpl;
import model.Entrega;

import java.util.List;


public class ControladorEntregas {

    private final EntregaDAOImpl entregaDAO;

    public ControladorEntregas(){
        this.entregaDAO = new EntregaDAOImpl();
    }

    /**
     *
     * @param entrega recibe la instancia de Entrega desde el VentanaAlternativaForm que sera sometido a validacion
     * @return un boolean de exito si atraviesa la validacion, para ser enviado EntregaDAOImpl que lo ingresara a la
     * base de datos.
     */
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

    /**
     *
     * @return una lista de todas las entregas obtenidas desde la base de datos
     */
    public List<Entrega> listarEntregas() {
        return entregaDAO.listarTodos();
    }

    /**
     *
     * @param entrega enviado desde VentanaAlternativaForm para ser validado
     * @return booleano de exito si atraviesa la validacion de parametros, siendo enviada a EntregaDAOImpl para actualizarse
     * en la base de datos
     */
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

    /**
     *
     * @param id de objeto Entrega enviado desde VentanaAlternativaForm para ser eliminado
     * @return booleano de exito, que lo envia a EntregaDAOImpl para ser eliminado de la base de datos.
     */
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
