package model;

public class PedidoEncomienda extends Pedido{

    private String medidas;
    private int peso;


    public PedidoEncomienda(String idPedido, String direccionEntrega, int distancia, String medidas, int peso){
        super(idPedido, direccionEntrega, distancia);
        this.medidas = medidas;
        this.peso = peso;
    }

    public String getMedidas() {
        return medidas;
    }

    public void setMedidas(String medidas) {
        this.medidas = medidas;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    @Override
    public void calcularTiempoEntrega() {

        double tiempoEntrega = 20 + (1.5 * (getDistancia()));
        System.out.println("Tiempo de entrega aproximado: " + Math.round(tiempoEntrega)+ " min.");
    }

    @Override
    public String asignarRepartidor(Repartidor repartidor) {
        return "Tipo pedido: Encomienda" +
                ", Asignando repartidor..." +
                ", Verificando medidas..." + getMedidas() +
                ", Verificando peso..." + getPeso() +
                " -> Pedido asignado a " + repartidor.getNombreRepartidor();
    }
}
