package model;

public class PedidoExpress extends Pedido{

    private int distrito;

    public PedidoExpress(String idPedido, String direccionEntrega, int distancia, int distrito) {
        super(idPedido, direccionEntrega, distancia);
        this.distrito = distrito;
    }

    public int getDistrito() {
        return distrito;
    }

    public void setDistrito(int distrito) {
        this.distrito = distrito;
    }

    @Override
    public void calcularTiempoEntrega() {

        double tiempoEntrega = 10;
        if (getDistancia() <= 5) {
            tiempoEntrega += 5;
        }

        System.out.println("Tiempo de entrega aproximado: " + tiempoEntrega + " min.");
    }

    @Override
    public String asignarRepartidor(Repartidor repartidor){
        return "Tipo pedido: Express" +
                ", Asignando repartidor..." +
                ", Verificando distancia..." + repartidor.getProximidad() + "km" +
                " -> Pedido asignado a..." + repartidor.getNombreRepartidor();
    }
}
