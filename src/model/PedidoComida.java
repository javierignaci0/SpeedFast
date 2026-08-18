package model;


public class PedidoComida extends Pedido{

    private String restaurante;
    protected Repartidor repartidor;


    public PedidoComida(String idPedido, String direccionEntrega, String tipoPedido, String restaurante){
        super(idPedido, direccionEntrega, tipoPedido);
        this.restaurante = restaurante;
    }

    public String getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(String restaurante) {
        this.restaurante = restaurante;
    }


    @Override
    public String asignarRepartidor(Repartidor repartidor){
        String resultado = "";

        resultado += "Tipo pedido: Comida, "
                + "Asignando repartidor..."
                + ", Verificando mochila termica...";

        if (repartidor.isMochilaTermica()){
            resultado += "OK, Pedido asignado a " +  repartidor.getNombreRepartidor();

        }else {
            resultado += "No tiene mochila, Pedido no asignado";

        }
        return resultado;


    }
}
