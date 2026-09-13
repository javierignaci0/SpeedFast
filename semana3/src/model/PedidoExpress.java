package model;

public class PedidoExpress extends Pedido {
    private String tienda;
    private boolean prioritario;

    public PedidoExpress(int id, String cliente, String direccion, String tienda, boolean prioritario) {
        super(id, cliente, direccion);
        this.tienda = tienda;
        this.prioritario = prioritario;
    }

    @Override
    public void asignarRepartidor() {
        if (!cancelado || !despachado) {
            if (prioritario) {
                this.repartidor = "Repartidor Express Prioritario";
            } else {
                this.repartidor = "Repartidor Express Estandar";
            }
            System.out.println("Repartidor express asignado automáticamente.");
        }
    }

    @Override
    public int calcularTiempoEntrega() {
        // Express: 15 min base, -5 si es prioritario
        int tiempo = 15;
        if (prioritario){
            tiempo -= 5;
        }
        return tiempo;
    }

    @Override
    protected String obtenerTipo() {
        return "Express";
    }

    @Override
    public void mostrarResumen() {
        super.mostrarResumen();
        System.out.println("Tienda: " + tienda);

    }
}