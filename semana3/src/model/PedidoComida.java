package model;

public class PedidoComida extends Pedido {
    private String restaurante;
    private int distanciaKm;

    public PedidoComida(int id, String cliente, String direccion, String restaurante, int distanciaKm) {
        super(id, cliente, direccion);
        this.restaurante = restaurante;
        this.distanciaKm = distanciaKm;
    }

    @Override
    public void asignarRepartidor() {

            System.out.println("Repartidor de comida asignado automatico a repartidor con mochila");
        }


    @Override
    public int calcularTiempoEntrega() {
        // Comida: 10 min base + 5 min por km
        return 10 + (distanciaKm * 5);
    }

    @Override
    protected String obtenerTipo() {
        return "Comida";
    }

    @Override
    public void mostrarResumen() {
        super.mostrarResumen();
        System.out.println("Restaurante: " + restaurante);
    }
}