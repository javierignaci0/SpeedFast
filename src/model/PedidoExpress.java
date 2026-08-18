package model;

public class PedidoExpress extends Pedido{

    private int distrito;

    public PedidoExpress(String idPedido, String direccionEntrega, String tipoPedido, int distrito) {
        super(idPedido, direccionEntrega, tipoPedido);
        this.distrito = distrito;
    }

    public int getDistrito() {
        return distrito;
    }

    public void setDistrito(int distrito) {
        this.distrito = distrito;
    }

//    public calcularDistancia(){
//        int distancia = Repartidor.getDistancia();
//        for Repartidor.getDistancia()
//        if (distrito == 0){}
//    }

    @Override
    public String asignarRepartidor(Repartidor repartidor){
        return "Tipo pedido: Express" +
                ", Asignando repartidor..." +
                ", Verificando distancia..." + repartidor.getDistancia() + "km" +
                " -> Pedido asignado a..." + repartidor.getNombreRepartidor();
    }
}
